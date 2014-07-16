package com.kissr.jacob.Game.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public int width, height;
	public String path;
	int[] pixels;
	
	public static SpriteSheet sheet = new SpriteSheet("/textures/sheet.png");
	
	public SpriteSheet(String path){
		load(path);
	}
	
	public void load(String path){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			pixels = new int[image.getWidth()*image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			this.width = image.getWidth();
			this.height = image.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
