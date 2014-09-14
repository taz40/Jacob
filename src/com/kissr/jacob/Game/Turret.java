package com.kissr.jacob.Game;

import io.brace.lightsoutgaming.engine.graphics.Screen;

public class Turret extends Entity {
	
	public Turret(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	double angle = 0;
	int range = 10;

	@Override
	public void update() {
		// TODO Auto-generated method stub
		angle += Math.toDegrees(0.1f);
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.renderSprite(x, y, Main.base, false);
		screen.renderSprite(x, y, Main.turet.rotate(angle), false);
	}

}
