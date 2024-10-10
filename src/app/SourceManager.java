package app;

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

	public SourceManager(String sourceFolderPath) {
		if (sharedInstance == null) {
			sharedInstance = this;
		}

		sharedInstance.sprites = new HashMap<String, Image>();
		
		// load up all the game Sprites in the specified folder
		File sourceFolder = new File(sourceFolderPath);
	
		if (sourceFolder.exists() && sourceFolder.isDirectory()) {
			
			try {
				File[] spriteFilesArray = sourceFolder.listFiles((dir, name) -> {
					return name.endsWith(".png");
				});
					
				if(spriteFilesArray != null) {
					for(File spriteFile : spriteFilesArray) {
						if(spriteFile != null) {
							String spriteName = spriteFile.getName().split("\\.")[0];
							Image spriteImage = ImageIO.read(spriteFile);
							sharedInstance.sprites.put(spriteName, spriteImage);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ImageIcon getSpriteImage(String spriteName){
		Image img = sharedInstance.sprites.get(spriteName);
		if(img == null) {
			img = sharedInstance.sprites.get("NotFound");
		}
		return new ImageIcon(img);
	}
}
