package com.kissr.jacob.Game;

import io.brace.lightsoutgaming.engine.graphics.Screen;

public abstract class Entity {
	
	int x, y;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}

	public abstract void update();
	public abstract void render(Screen screen);
	
}
