const assetsGrid = document.getElementById("assetsGrid");
const foldersGrid = document.getElementById("foldersGrid");
const folderTree = document.getElementById("folderTree");
const breadcrumbs = document.getElementById("breadcrumbs");
const stats = document.getElementById("stats");
const statusMessage = document.getElementById("statusMessage");
const searchInput = document.getElementById("searchInput");
const reloadButton = document.getElementById("reloadButton");
const goUpButton = document.getElementById("goUpButton");
const cardTemplate = document.getElementById("assetCardTemplate");

const textLikeExtensions = new Set([
  "txt",
  "md",
  "json",
  "csv",
  "log",
  "xml",
  "yml",
  "yaml",
  "ini",
  "toml",
  "js",
  "ts",
  "jsx",
  "tsx",
  "css",
  "html",
]);

const imageExtensions = new Set(["png", "jpg", "jpeg", "gif", "webp", "svg", "bmp", "ico", "avif"]);
const videoExtensions = new Set(["mp4", "webm", "ogg", "mov", "m4v"]);
const audioExtensions = new Set(["mp3", "wav", "ogg", "m4a", "flac", "aac"]);
const MAX_VISIBLE_TEXT = 2400;
const MAX_HEX_BYTES = 512;

let currentFiles = [];
let currentFolders = [];
let folderByPath = new Map();
let folderChildrenMap = new Map();
let filesByFolderMap = new Map();
let activeFolderPath = "assets";

function normalizePathToUrl(relativePath) {
  return relativePath
    .split("/")
    .map((segment) => encodeURIComponent(segment))
    .join("/");
}

function normalizeFolderPath(path) {
  if (!path || path === ".") return "assets";
  return path.replace(/\\/g, "/");
}

function getFolderName(path) {
  const normalized = normalizeFolderPath(path);
  const parts = normalized.split("/");
  return parts[parts.length - 1] || "assets";
}

function humanFileSize(bytes) {
  if (!Number.isFinite(bytes) || bytes <= 0) return "0 B";
  const units = ["B", "KB", "MB", "GB", "TB"];
  const i = Math.min(Math.floor(Math.log(bytes) / Math.log(1024)), units.length - 1);
  const value = bytes / 1024 ** i;
  return `${value.toFixed(value >= 10 || i === 0 ? 0 : 1)} ${units[i]}`;
}

function setStatus(message, isError = false) {
  statusMessage.textContent = message;
  statusMessage.style.color = isError ? "var(--warn)" : "var(--text-soft)";
}

function decodeBytesToText(bytes) {
  const decoderLabels = ["utf-8", "windows-1252", "iso-8859-1"];

  for (let i = 0; i < decoderLabels.length; i += 1) {
    const label = decoderLabels[i];
    try {
      const decoder = new TextDecoder(label, { fatal: i === 0 });
      return {
        text: decoder.decode(bytes),
        encoding: label,
      };
    } catch {
      // Try next decoder.
    }
  }

  let fallback = "";
  for (const b of bytes) {
    fallback += String.fromCharCode(b);
  }

  return {
    text: fallback,
    encoding: "byte-map",
  };
}

function isBinaryLikeText(text) {
  if (!text) return false;

  const sample = text.slice(0, 3500);
  let controlCount = 0;

  for (const char of sample) {
    const code = char.charCodeAt(0);
    const isPrintable =
      code === 9 ||
      code === 10 ||
      code === 13 ||
      (code >= 32 && code <= 126) ||
      code >= 160;

    if (!isPrintable) controlCount += 1;
  }

  return controlCount / sample.length > 0.2;
}

function toHexDump(bytes, maxBytes = MAX_HEX_BYTES) {
  const slice = bytes.slice(0, maxBytes);
  const lines = [];

  for (let i = 0; i < slice.length; i += 16) {
    const chunk = slice.slice(i, i + 16);
    const offset = i.toString(16).padStart(6, "0");
    const hex = Array.from(chunk)
      .map((b) => b.toString(16).padStart(2, "0"))
      .join(" ")
      .padEnd(47, " ");

    const ascii = Array.from(chunk)
      .map((b) => (b >= 32 && b <= 126 ? String.fromCharCode(b) : "."))
      .join("");

    lines.push(`${offset}  ${hex}  ${ascii}`);
  }

  if (bytes.length > maxBytes) {
    lines.push(`... ${bytes.length - maxBytes} more bytes omitted`);
  }

  return lines.join("\n");
}

function createCopyButton(textToCopy) {
  const button = document.createElement("button");
  button.type = "button";
  button.className = "copy-preview-btn";
  button.textContent = "Copy Text";

  button.addEventListener("click", async () => {
    try {
      await navigator.clipboard.writeText(textToCopy);
      const prev = button.textContent;
      button.textContent = "Copied";
      window.setTimeout(() => {
        button.textContent = prev;
      }, 900);
    } catch {
      button.textContent = "Copy failed";
    }
  });

  return button;
}

function appendTextPreview(previewContainer, displayText, copyText) {
  previewContainer.classList.add("has-text-preview");

  const wrap = document.createElement("div");
  wrap.className = "text-preview-wrap";

  const pre = document.createElement("pre");
  pre.textContent = displayText;

  wrap.appendChild(createCopyButton(copyText));
  wrap.appendChild(pre);
  previewContainer.appendChild(wrap);
}

function matchesFolderQuery(folder, query) {
  const haystack = `${folder.path} ${folder.name}`.toLowerCase();
  return haystack.includes(query);
}

function matchesFileQuery(file, query) {
  const haystack = `${file.path} ${file.ext} ${file.name} ${file.folder}`.toLowerCase();
  return haystack.includes(query);
}

function createFolderCard(folder, index) {
  const card = document.createElement("article");
  card.className = "folder-card";
  card.style.animationDelay = `${Math.min(index * 35, 350)}ms`;
  card.setAttribute("tabindex", "0");

  const pathLabel = document.createElement("p");
  pathLabel.className = "folder-card-path";
  pathLabel.textContent = folder.path;

  const title = document.createElement("h3");
  title.className = "folder-card-title";
  title.textContent = folder.name;

  const directSubfolders = (folderChildrenMap.get(folder.path) || []).length;
  const directFiles = (filesByFolderMap.get(folder.path) || []).length;

  const meta = document.createElement("p");
  meta.className = "folder-card-meta";
  meta.textContent = `${directSubfolders} folder${directSubfolders === 1 ? "" : "s"} • ${directFiles} file${directFiles === 1 ? "" : "s"}`;

  const openButton = document.createElement("button");
  openButton.type = "button";
  openButton.className = "folder-open-btn";
  openButton.textContent = "Open Folder";

  const openFolder = () => {
    setActiveFolder(folder.path);
  };

  openButton.addEventListener("click", (event) => {
    event.preventDefault();
    event.stopPropagation();
    openFolder();
  });

  card.addEventListener("click", () => {
    openFolder();
  });

  card.addEventListener("keydown", (event) => {
    if (event.key === "Enter" || event.key === " ") {
      event.preventDefault();
      openFolder();
    }
  });

  card.appendChild(pathLabel);
  card.appendChild(title);
  card.appendChild(meta);
  card.appendChild(openButton);
  return card;
}

function createAssetCard(file, index) {
  const fragment = cardTemplate.content.cloneNode(true);
  const card = fragment.querySelector(".asset-card");
  card.style.animationDelay = `${Math.min(index * 35, 350)}ms`;

  fragment.querySelector(".asset-folder").textContent = file.folder || "assets";
  fragment.querySelector(".asset-ext").textContent = file.ext ? `.${file.ext}` : "(no ext)";
  const nameLink = fragment.querySelector(".asset-name-link");
  nameLink.href = normalizePathToUrl(file.path);
  nameLink.textContent = file.name;
  fragment.querySelector(".asset-size").textContent = humanFileSize(file.size);

  const link = fragment.querySelector(".asset-open");
  link.href = normalizePathToUrl(file.path);
  link.textContent = "Open";

  const downloadLink = fragment.querySelector(".asset-download");
  downloadLink.href = normalizePathToUrl(file.path);
  downloadLink.download = file.name;
  downloadLink.textContent = "Download";

  const previewContainer = fragment.querySelector(".asset-preview");
  const previewToggle = fragment.querySelector(".asset-preview-toggle");
  let previewLoaded = false;

  const showPreview = async () => {
    if (!previewLoaded) {
      previewContainer.innerHTML = "";
      await renderPreview(previewContainer, file);
      previewLoaded = true;
    }

    previewContainer.hidden = false;
    card.classList.add("expanded");
    previewToggle.textContent = "Hide Preview";
    previewToggle.setAttribute("aria-expanded", "true");
  };

  const hidePreview = () => {
    previewContainer.hidden = true;
    card.classList.remove("expanded");
    previewToggle.textContent = "Show Preview";
    previewToggle.setAttribute("aria-expanded", "false");
  };

  previewContainer.hidden = true;

  previewToggle.addEventListener("click", async (event) => {
    event.preventDefault();
    event.stopPropagation();

    if (previewContainer.hidden) {
      await showPreview();
    } else {
      hidePreview();
    }
  });

  card.addEventListener("click", async (event) => {
    if (event.target.closest("a, button")) return;

    if (previewContainer.hidden) {
      await showPreview();
    } else {
      hidePreview();
    }
  });

  return fragment;
}

function buildIndexes(files, folders) {
  folderByPath = new Map();
  folderChildrenMap = new Map();
  filesByFolderMap = new Map();

  const safeFolders = Array.isArray(folders) ? folders : [];
  for (const folder of safeFolders) {
    const path = normalizeFolderPath(folder.path);
    const fallbackParent = path.includes("/") ? path.slice(0, path.lastIndexOf("/")) : "";
    const parent = path === "assets" ? "" : normalizeFolderPath(folder.parent || fallbackParent);
    folderByPath.set(path, {
      path,
      parent,
      name: folder.name || getFolderName(path),
    });
  }

  if (!folderByPath.has("assets")) {
    folderByPath.set("assets", {
      path: "assets",
      parent: "",
      name: "assets",
    });
  }

  for (const file of files) {
    const fallbackFolder = file.path.includes("/") ? file.path.slice(0, file.path.lastIndexOf("/")) : "assets";
    const folderPath = normalizeFolderPath(file.folder || fallbackFolder);

    if (!folderByPath.has(folderPath)) {
      const fallbackParent = folderPath.includes("/") ? folderPath.slice(0, folderPath.lastIndexOf("/")) : "";
      folderByPath.set(folderPath, {
        path: folderPath,
        parent: folderPath === "assets" ? "" : normalizeFolderPath(fallbackParent),
        name: getFolderName(folderPath),
      });
    }

    if (!filesByFolderMap.has(folderPath)) {
      filesByFolderMap.set(folderPath, []);
    }

    filesByFolderMap.get(folderPath).push(file);
  }

  for (const folder of folderByPath.values()) {
    const parentPath = folder.path === "assets" ? "" : normalizeFolderPath(folder.parent || "assets");
    folder.parent = parentPath;

    if (!folderChildrenMap.has(parentPath)) {
      folderChildrenMap.set(parentPath, []);
    }

    folderChildrenMap.get(parentPath).push(folder);
  }

  for (const filesList of filesByFolderMap.values()) {
    filesList.sort((a, b) => a.name.localeCompare(b.name, undefined, { sensitivity: "base" }));
  }

  for (const children of folderChildrenMap.values()) {
    children.sort((a, b) => a.name.localeCompare(b.name, undefined, { sensitivity: "base" }));
  }
}

function createTreeNode(folder) {
  const item = document.createElement("li");
  item.className = "tree-node";

  const link = document.createElement("button");
  link.type = "button";
  link.className = "tree-link";
  link.textContent = folder.name;
  link.title = folder.path;

  if (folder.path === activeFolderPath) {
    link.classList.add("active");
    link.setAttribute("aria-current", "true");
  } else if (activeFolderPath.startsWith(`${folder.path}/`)) {
    link.classList.add("ancestor");
  }

  link.addEventListener("click", () => {
    setActiveFolder(folder.path);
  });

  item.appendChild(link);

  const children = (folderChildrenMap.get(folder.path) || []).filter((child) => child.path !== folder.path);
  if (children.length) {
    const nested = document.createElement("ul");
    nested.className = "tree-list nested";
    for (const child of children) {
      nested.appendChild(createTreeNode(child));
    }
    item.appendChild(nested);
  }

  return item;
}

function renderFolderTree() {
  folderTree.innerHTML = "";

  const root = folderByPath.get("assets");
  if (!root) return;

  const treeRoot = document.createElement("ul");
  treeRoot.className = "tree-list";
  treeRoot.appendChild(createTreeNode(root));
  folderTree.appendChild(treeRoot);
}

function renderBreadcrumbs() {
  breadcrumbs.innerHTML = "";
  const segments = activeFolderPath.split("/");
  let current = "";

  segments.forEach((segment, index) => {
    current = index === 0 ? segment : `${current}/${segment}`;

    const isLast = index === segments.length - 1;
    const crumb = document.createElement("button");
    crumb.type = "button";
    crumb.className = "crumb";
    crumb.textContent = segment;

    if (isLast) {
      crumb.classList.add("current");
      crumb.disabled = true;
    } else {
      crumb.addEventListener("click", () => {
        setActiveFolder(current);
      });
    }

    breadcrumbs.appendChild(crumb);

    if (!isLast) {
      const separator = document.createElement("span");
      separator.className = "crumb-sep";
      separator.textContent = "/";
      breadcrumbs.appendChild(separator);
    }
  });
}

function renderFolderCards(folders) {
  foldersGrid.innerHTML = "";

  if (!folders.length) {
    const empty = document.createElement("p");
    empty.className = "folder-empty";
    empty.textContent = "No subfolders in this location.";
    foldersGrid.appendChild(empty);
    return;
  }

  folders.forEach((folder, index) => {
    foldersGrid.appendChild(createFolderCard(folder, index));
  });
}

function renderFileCards(files) {
  assetsGrid.innerHTML = "";

  if (!files.length) {
    const empty = document.createElement("p");
    empty.className = "folder-empty";
    empty.textContent = "No files in this folder.";
    assetsGrid.appendChild(empty);
    return;
  }

  files.forEach((file, index) => {
    assetsGrid.appendChild(createAssetCard(file, index));
  });
}

function renderCurrentFolder() {
  const query = searchInput.value.trim().toLowerCase();
  const rawFolders = (folderChildrenMap.get(activeFolderPath) || []).filter((folder) => folder.path !== activeFolderPath);
  const rawFiles = filesByFolderMap.get(activeFolderPath) || [];

  const visibleFolders = query ? rawFolders.filter((folder) => matchesFolderQuery(folder, query)) : rawFolders;
  const visibleFiles = query ? rawFiles.filter((file) => matchesFileQuery(file, query)) : rawFiles;

  renderFolderCards(visibleFolders);
  renderFileCards(visibleFiles);

  if (query) {
    stats.textContent = `${visibleFiles.length}/${rawFiles.length} files and ${visibleFolders.length}/${rawFolders.length} folders in ${activeFolderPath}`;
  } else {
    stats.textContent = `${rawFiles.length} file${rawFiles.length === 1 ? "" : "s"} and ${rawFolders.length} folder${rawFolders.length === 1 ? "" : "s"} in ${activeFolderPath}`;
  }

  if (query && !visibleFolders.length && !visibleFiles.length) {
    setStatus(`No matches found in ${activeFolderPath}.`);
  } else if (query) {
    setStatus(`Filtering in ${activeFolderPath}`);
  } else {
    setStatus("");
  }
}

function setActiveFolder(path) {
  const targetPath = normalizeFolderPath(path);
  activeFolderPath = folderByPath.has(targetPath) ? targetPath : "assets";

  renderBreadcrumbs();
  renderFolderTree();
  renderCurrentFolder();
}

async function renderPreview(previewContainer, file) {
  const ext = (file.ext || "").toLowerCase();
  const encodedPath = normalizePathToUrl(file.path);

  if (imageExtensions.has(ext)) {
    const img = document.createElement("img");
    img.src = encodedPath;
    img.alt = file.name;
    img.loading = "lazy";
    previewContainer.appendChild(img);
    return;
  }

  if (videoExtensions.has(ext)) {
    const video = document.createElement("video");
    video.src = encodedPath;
    video.controls = true;
    video.preload = "metadata";
    previewContainer.appendChild(video);
    return;
  }

  if (audioExtensions.has(ext)) {
    const audio = document.createElement("audio");
    audio.src = encodedPath;
    audio.controls = true;
    audio.preload = "metadata";
    previewContainer.appendChild(audio);
    return;
  }

  if (ext === "pdf") {
    const frame = document.createElement("iframe");
    frame.src = encodedPath;
    frame.title = `${file.name} preview`;
    previewContainer.appendChild(frame);
    return;
  }

  try {
    const response = await fetch(encodedPath);
    if (!response.ok) throw new Error(`Could not load file: ${response.status}`);

    const bytes = new Uint8Array(await response.arrayBuffer());
    const { text, encoding } = decodeBytesToText(bytes);
    const normalizedText = text.replace(/\u0000/g, "");
    const isTextFriendly = textLikeExtensions.has(ext) || !isBinaryLikeText(normalizedText);

    if (isTextFriendly) {
      const output = normalizedText || "(empty file)";
      const visible = output.length > MAX_VISIBLE_TEXT ? `${output.slice(0, MAX_VISIBLE_TEXT)}\n\n... preview trimmed` : output;
      const finalVisible = `[${encoding}]\n${visible}`;
      appendTextPreview(previewContainer, finalVisible, output);
      return;
    }

    const hexText = toHexDump(bytes, MAX_HEX_BYTES);
    const hexVisible = `Binary-like file detected. Showing hex text preview for easy copy.\n\n${hexText}`;
    appendTextPreview(previewContainer, hexVisible, hexText);
  } catch {
    const badge = document.createElement("span");
    badge.className = "file-badge";
    badge.textContent = (ext || "file").toUpperCase();
    previewContainer.appendChild(badge);
  }
}

function applyFilter() {
  renderCurrentFolder();
}

async function loadManifest() {
  setStatus("Loading files...");
  try {
    const response = await fetch(`assets-manifest.json?t=${Date.now()}`);
    if (!response.ok) throw new Error(`Manifest request failed: ${response.status}`);

    const data = await response.json();
    currentFiles = Array.isArray(data.files) ? data.files : [];
    currentFolders = Array.isArray(data.folders) ? data.folders : [];

    buildIndexes(currentFiles, currentFolders);

    if (!folderByPath.has(activeFolderPath)) {
      activeFolderPath = "assets";
    }

    setActiveFolder(activeFolderPath);
  } catch (error) {
    currentFiles = [];
    currentFolders = [];
    folderByPath = new Map();
    folderChildrenMap = new Map();
    filesByFolderMap = new Map();
    folderTree.innerHTML = "";
    breadcrumbs.innerHTML = "";
    foldersGrid.innerHTML = "";
    assetsGrid.innerHTML = "";
    stats.textContent = "0 files and 0 folders";
    setStatus(
      "Could not read assets-manifest.json. Start the local server with: node scripts/dev-server.js",
      true,
    );
    console.error(error);
  }
}

searchInput.addEventListener("input", () => {
  applyFilter();
});

reloadButton.addEventListener("click", () => {
  loadManifest();
});

goUpButton.addEventListener("click", () => {
  if (activeFolderPath === "assets") {
    return;
  }

  const currentFolder = folderByPath.get(activeFolderPath);
  const parentPath = currentFolder && currentFolder.parent ? currentFolder.parent : "assets";
  setActiveFolder(parentPath);
});

loadManifest();