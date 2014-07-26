package com.kissr.jacob.Game;

import io.brace.lightsoutgaming.engine.graphics.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level extends Sprite{
	
	
	public Level(String path){
		loadLevel(path);
	}


	private void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width = image.getWidth()*16*3;
			height = image.getHeight()*16*3;
			int[] imagepixels = new int[image.getWidth()*image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), imagepixels, 0, image.getWidth());
			pixels = new int[(width*height)*3];
			for(int i = 0; i < pixels.length; i++){
				pixels[i] = 0xffff00ff;
			}
			for(int x = 0; x < image.getWidth(); x++){
				for(int y = 0; y < image.getHeight(); y++){
					try{
						if(imagepixels[x+y*image.getWidth()] == 0xffff0000){
							addSprite(x*16*3, y*16*3, Main.vertical);
						}
						if(imagepixels[x+y*image.getWidth()] == 0xffffff00){
							if(imagepixels[(x)+(y-1)*image.getWidth()] == 0xffffff00 || imagepixels[(x)+(y-1)*image.getWidth()] == 0xffff0000){
								if(imagepixels[(x)+(y+1)*image.getWidth()] == 0xffffff00){
									addSprite(x*16*3, y*16*3, Main.vertical);
								}
								if(imagepixels[(x+1)+(y)*image.getWidth()] == 0xffffff00){
									addSprite(x*16*3, y*16*3, Main.tr);
								}
								if(imagepixels[(x-1)+(y)*image.getWidth()] == 0xffffff00){
									addSprite(x*16*3, y*16*3, Main.tl);
								}
							}
							if(imagepixels[(x)+(y+1)*image.getWidth()] == 0xffffff00){
								if(imagepixels[(x+1)+(y)*image.getWidth()] == 0xffffff00){
									addSprite(x*16*3, y*16*3, Main.br);
								}
								if(imagepixels[(x-1)+(y)*image.getWidth()] == 0xffffff00){
									addSprite(x*16*3, y*16*3, Main.bl);
								}
							}
							if(imagepixels[(x-1)+(y)*image.getWidth()] == 0xffffff00){
								if(imagepixels[(x+1)+(y)*image.getWidth()] == 0xffffff00){
									addSprite(x*16*3, y*16*3, Main.horizontal);
								}
							}
						}
					}catch(IndexOutOfBoundsException e){
						
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addSprite(int xp, int yp, Sprite sprite){
		for(int x = 0; x < sprite.width; x++){
			for(int y = 0; y < sprite.height; y++){
				if(x+xp >= width || x+xp < 0 || y+yp >= height || y+yp < 0) continue;
				int col = sprite.pixels[x+y*sprite.width];
				pixels[(xp+x) + (yp+y) * width] = col;
			}
		}
	}
}
