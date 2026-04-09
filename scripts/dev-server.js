const http = require("http");
const fs = require("fs/promises");
const path = require("path");
const { generateManifest, ROOT_DIR } = require("./assets-manifest");

const PORT = Number(process.env.PORT) || 5500;

const contentTypeByExt = {
  ".html": "text/html; charset=utf-8",
  ".css": "text/css; charset=utf-8",
  ".js": "application/javascript; charset=utf-8",
  ".json": "application/json; charset=utf-8",
  ".txt": "text/plain; charset=utf-8",
  ".xml": "application/xml; charset=utf-8",
  ".svg": "image/svg+xml",
  ".png": "image/png",
  ".jpg": "image/jpeg",
  ".jpeg": "image/jpeg",
  ".gif": "image/gif",
  ".webp": "image/webp",
  ".avif": "image/avif",
  ".ico": "image/x-icon",
  ".mp4": "video/mp4",
  ".webm": "video/webm",
  ".ogg": "application/ogg",
  ".mp3": "audio/mpeg",
  ".wav": "audio/wav",
  ".flac": "audio/flac",
  ".pdf": "application/pdf",
  ".zip": "application/zip",
};

function toSafePath(requestPath) {
  const clean = decodeURIComponent(requestPath.split("?")[0]);
  const withoutLeadingSlash = clean.replace(/^\/+/, "");
  const absolute = path.resolve(ROOT_DIR, withoutLeadingSlash || "index.html");
  if (!absolute.startsWith(ROOT_DIR)) return null;
  return absolute;
}

async function sendFile(response, absolutePath) {
  const ext = path.extname(absolutePath).toLowerCase();
  const type = contentTypeByExt[ext] || "application/octet-stream";
  const data = await fs.readFile(absolutePath);
  response.writeHead(200, {
    "Content-Type": type,
    "Cache-Control": "no-store",
  });
  response.end(data);
}

const server = http.createServer(async (request, response) => {
  try {
    const urlPath = request.url || "/";

    if (urlPath.startsWith("/assets-manifest.json")) {
      const manifest = await generateManifest({ writeToDisk: false });
      response.writeHead(200, {
        "Content-Type": "application/json; charset=utf-8",
        "Cache-Control": "no-store",
      });
      response.end(`${JSON.stringify(manifest, null, 2)}\n`);
      return;
    }

    const safePath = toSafePath(urlPath);
    if (!safePath) {
      response.writeHead(403, { "Content-Type": "text/plain; charset=utf-8" });
      response.end("Forbidden");
      return;
    }

    let stat;
    try {
      stat = await fs.stat(safePath);
    } catch {
      response.writeHead(404, { "Content-Type": "text/plain; charset=utf-8" });
      response.end("Not Found");
      return;
    }

    if (stat.isDirectory()) {
      const fallback = path.join(safePath, "index.html");
      await sendFile(response, fallback);
      return;
    }

    await sendFile(response, safePath);
  } catch (error) {
    response.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
    response.end(`Internal Server Error\n${error.message}`);
  }
});

server.listen(PORT, async () => {
  await generateManifest({ writeToDisk: true });
  console.log(`Assets Explorer running at http://localhost:${PORT}`);
});