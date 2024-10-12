package app;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SourceManager {
	public static SourceManager sharedInstance;
	protected Map<String, Image> sprites;
	public static Font appFont;

	public SourceManager(String sourceFolderPath) {
		if (sharedInstance == null) {
			sharedInstance = this;
		}

		sharedInstance.sprites = new HashMap<String, Image>();

		// load up all the game Sprites in the specified folder
		File sourceFolder = new File(sourceFolderPath);

		if (sourceFolder.exists() && sourceFolder.isDirectory()) {

			try {
				File[] assetsFilesArray = sourceFolder.listFiles();

				if (assetsFilesArray != null) {
					for (File assetFile : assetsFilesArray) {
						if (assetFile.getName().endsWith(".png")) {
							if (assetFile != null) {
								String spriteName = assetFile.getName().split("\\.")[0];
								Image spriteImage = ImageIO.read(assetFile);
								sharedInstance.sprites.put(spriteName, spriteImage);
							}
						} else if (assetFile.getName().endsWith(".ttf")) {
							if (sharedInstance.appFont == null) {
								sharedInstance.appFont = Font.createFont(Font.TRUETYPE_FONT, assetFile).deriveFont(24f);
							} else {
								System.err.println("Warn: Why 2 fonts???");
							}

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static ImageIcon getSpriteImageIcon(String spriteName) {
		Image img = sharedInstance.sprites.get(spriteName);
		if (img == null) {
			img = sharedInstance.sprites.get("NotFound");
		}
		return new ImageIcon(img);
	}
	
	public static Image getSpriteImage(String spriteName) {
		Image img = sharedInstance.sprites.get(spriteName);
		if (img == null) {
			img = sharedInstance.sprites.get("NotFound");
		}
		return img;
	}
	
}
