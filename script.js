const assetsGrid = document.getElementById("assetsGrid");
const stats = document.getElementById("stats");
const statusMessage = document.getElementById("statusMessage");
const searchInput = document.getElementById("searchInput");
const reloadButton = document.getElementById("reloadButton");
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

function normalizePathToUrl(relativePath) {
  return relativePath
    .split("/")
    .map((segment) => encodeURIComponent(segment))
    .join("/");
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

function buildFolderGroups(files, folders) {
  const folderMap = new Map();

  for (const folder of folders) {
    folderMap.set(folder.path, {
      path: folder.path,
      files: [],
    });
  }

  for (const file of files) {
    const folderPath = file.folder || "assets";
    if (!folderMap.has(folderPath)) {
      folderMap.set(folderPath, {
        path: folderPath,
        files: [],
      });
    }

    folderMap.get(folderPath).files.push(file);
  }

  return Array.from(folderMap.values()).sort((a, b) => a.path.localeCompare(b.path, undefined, { sensitivity: "base" }));
}

function createFolderSection(folder, indexOffset) {
  const section = document.createElement("section");
  section.className = "folder-section";

  const header = document.createElement("header");
  header.className = "folder-section-header";

  const titleWrap = document.createElement("div");
  titleWrap.className = "folder-title-wrap";

  const title = document.createElement("h2");
  title.className = "folder-title";
  title.textContent = folder.path || "assets";

  const subtitle = document.createElement("p");
  subtitle.className = "folder-subtitle";
  subtitle.textContent = folder.path === "assets" ? "Root assets folder" : folder.path.split("/").slice(-1)[0];

  const count = document.createElement("span");
  count.className = "folder-count";
  count.textContent = `${folder.files.length} file${folder.files.length === 1 ? "" : "s"}`;

  titleWrap.appendChild(title);
  titleWrap.appendChild(subtitle);
  header.appendChild(titleWrap);
  header.appendChild(count);

  section.appendChild(header);

  if (!folder.files.length) {
    const empty = document.createElement("p");
    empty.className = "folder-empty";
    empty.textContent = "No files in this folder yet.";
    section.appendChild(empty);
    return section;
  }

  const grid = document.createElement("div");
  grid.className = "assets-grid folder-files-grid";

  Promise.all(
    folder.files.map((file, fileIndex) => createAssetCard(file, indexOffset + fileIndex)),
  ).then((cards) => {
    cards.forEach((card) => {
      grid.appendChild(card);
    });
  });

  section.appendChild(grid);
  return section;
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

async function renderFiles(files) {
  assetsGrid.innerHTML = "";

  if (!files.length) {
    setStatus("No files found. Add files to assets/ and click Refresh Files.");
    stats.textContent = `0 files across ${currentFolders.length} folder${currentFolders.length === 1 ? "" : "s"}`;
    return;
  }

  setStatus("");
  stats.textContent = `${files.length} file${files.length === 1 ? "" : "s"} in ${currentFolders.length} folder${currentFolders.length === 1 ? "" : "s"}`;

  const groups = buildFolderGroups(files, currentFolders);
  let indexOffset = 0;

  for (const folder of groups) {
    assetsGrid.appendChild(createFolderSection(folder, indexOffset));
    indexOffset += folder.files.length;
  }
}

function applyFilter() {
  const q = searchInput.value.trim().toLowerCase();
  if (!q) {
    renderFiles(currentFiles);
    return;
  }

  const filtered = currentFiles.filter((file) => {
    const haystack = `${file.path} ${file.ext} ${file.name} ${file.folder}`.toLowerCase();
    return haystack.includes(q);
  });

  renderFiles(filtered);
}

async function loadManifest() {
  setStatus("Loading files...");
  try {
    const response = await fetch(`assets-manifest.json?t=${Date.now()}`);
    if (!response.ok) throw new Error(`Manifest request failed: ${response.status}`);

    const data = await response.json();
    currentFiles = Array.isArray(data.files) ? data.files : [];
    currentFolders = Array.isArray(data.folders) ? data.folders : [];
    applyFilter();
  } catch (error) {
    currentFiles = [];
    currentFolders = [];
    assetsGrid.innerHTML = "";
    stats.textContent = "0 files";
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

loadManifest();