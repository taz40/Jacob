package com.kissr.jacob.Game;

import io.brace.lightsoutgaming.engine.LightsOut;
import io.brace.lightsoutgaming.engine.graphics.Sprite;
import io.brace.lightsoutgaming.engine.graphics.SpriteSheet;
import io.brace.lightsoutgaming.engine.input.Mouse;

import java.util.Random;

public class Main extends LightsOut {
	
	public static SpriteSheet sheet = new SpriteSheet("/textures/sheet.png");
	public static Sprite base = new Sprite(0,0,3,16,sheet);
	public static Sprite turet = new Sprite(1,0,3,16,sheet);
	public static Sprite enemy = new Sprite(0,1,3,16,sheet);
	public static Sprite horizontal = new Sprite(2, 0, 3, 16, sheet);
	public static Sprite vertical = new Sprite(2, 1, 3, 16, sheet);
	public static Sprite tr = new Sprite(4, 0, 3, 16, sheet);
	public static Sprite tl = new Sprite(3, 1, 3, 16, sheet);
	public static Sprite br = new Sprite(4, 1, 3, 16, sheet);
	public static Sprite bl = new Sprite(3, 0, 3, 16, sheet);
	public static Level level = new Level("/levels/1.png");
	double angle = 0;
	Random rand = new Random();
	int ex, ey;
	int tx, ty;
	
	public static void main(String[] args){
		Main main = new Main();
		main.init();
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		this.createDisplay("Tower Defence", 900, 900 / 16*9);
		this.start();
		ex = rand.nextInt(screen.getWidth()-48);
		ey = rand.nextInt(screen.getHeight()-48);
	}

	@Override
	protected void render(){
		screen.clear(0xffffffff);
		screen.renderSprite(0, 0, level, true);
		screen.renderSprite(ex, ey, enemy, false);
		screen.renderSprite(tx, tx, base, false);
		screen.renderSprite(tx, tx, turet.rotate(angle), false);
		show();
	}

	@Override
	protected void update() {
		angle = Math.toDegrees(Math.atan2(ey-tx, ex-tx));
		System.out.println(angle);
	}
	
}
