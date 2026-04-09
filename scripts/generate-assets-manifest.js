const { generateManifest } = require("./assets-manifest");

async function main() {
  try {
    const manifest = await generateManifest({ writeToDisk: true });
    console.log(`Manifest generated: ${manifest.totalFiles} file(s).`);
  } catch (error) {
    console.error("Failed to generate manifest:", error);
    process.exitCode = 1;
  }
}

main();