package com.kissr.jacob.Game.Graphics;

public class Screen {

	int width, height;
	public int[] pixels;
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0xffffffff;
		}
	}
	
	public void DrawSpriteSheet(int xp, int yp, SpriteSheet sheet){
		for(int y = 0; y < sheet.height; y++){
			for(int x = 0; x < sheet.width; x++){
				int xa = x+xp;
				int ya = y+yp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col =  sheet.pixels[x+y*sheet.width];
				if(col != 0xffff00ff) pixels[xa+ya*width] = col;
			}
		}
	}
	
}
