const fs = require("fs/promises");
const path = require("path");

const ROOT_DIR = path.resolve(__dirname, "..");
const PUBLIC_DIR = path.join(ROOT_DIR, "public");
const ASSETS_DIR = path.join(PUBLIC_DIR, "assets");
const MANIFEST_FILE = path.join(PUBLIC_DIR, "assets-manifest.json");
const IGNORED_FOLDER_NAMES = new Set(["node_modules", ".git", ".svn", ".hg", "dist", "build"]);

function isIgnoredEntry(entryName) {
  return IGNORED_FOLDER_NAMES.has(entryName) || entryName.startsWith(".");
}

async function ensureAssetsFolder() {
  await fs.mkdir(ASSETS_DIR, { recursive: true });
}

async function walkAssets(currentDir, rootDir, out) {
  const entries = await fs.readdir(currentDir, { withFileTypes: true });
  const relativeDir = path.relative(rootDir, currentDir).split(path.sep).join("/");
  const currentName = path.basename(currentDir);

  if (currentDir !== rootDir && isIgnoredEntry(currentName)) {
    return;
  }

  out.folders.push({
    path: relativeDir,
    name: currentName,
    parent: relativeDir.includes("/") ? relativeDir.slice(0, relativeDir.lastIndexOf("/")) : "",
  });

  for (const entry of entries) {
    if (isIgnoredEntry(entry.name)) continue;

    const absolutePath = path.join(currentDir, entry.name);
    const relativePath = path.relative(rootDir, absolutePath).split(path.sep).join("/");

    if (entry.isDirectory()) {
      await walkAssets(absolutePath, rootDir, out);
      continue;
    }

    if (!entry.isFile()) continue;

    const stat = await fs.stat(absolutePath);
    const ext = path.extname(entry.name).slice(1).toLowerCase();
    const folder = path.dirname(relativePath) === "." ? "assets" : path.dirname(relativePath);

    out.push({
      path: relativePath,
      name: entry.name,
      ext,
      folder,
      size: stat.size,
      modifiedAt: stat.mtime.toISOString(),
    });
  }
}

async function generateManifest(options = {}) {
  const shouldWrite = options.writeToDisk !== false;

  await ensureAssetsFolder();

  const files = [];
  files.folders = [];
  await walkAssets(ASSETS_DIR, PUBLIC_DIR, files);

  files.sort((a, b) => a.path.localeCompare(b.path, undefined, { sensitivity: "base" }));
  files.folders.sort((a, b) => a.path.localeCompare(b.path, undefined, { sensitivity: "base" }));

  const manifest = {
    generatedAt: new Date().toISOString(),
    totalFiles: files.length,
    totalFolders: files.folders.length,
    folders: files.folders,
    files,
  };

  if (shouldWrite) {
    await fs.writeFile(MANIFEST_FILE, `${JSON.stringify(manifest, null, 2)}\n`, "utf8");
  }

  return manifest;
}

module.exports = {
  generateManifest,
  ROOT_DIR,
  PUBLIC_DIR,
};