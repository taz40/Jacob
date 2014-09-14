package com.kissr.jacob.Game;

import io.brace.lightsoutgaming.engine.LightsOut;
import io.brace.lightsoutgaming.engine.graphics.Sprite;
import io.brace.lightsoutgaming.engine.graphics.SpriteSheet;
import io.brace.lightsoutgaming.engine.input.Mouse;

import java.util.ArrayList;
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
	public static Level level = new Level("/levels/level.png");
	ArrayList<Entity> entities = new ArrayList<Entity>();
	public Entity preview = new Turret(0, 0);
	Random rand = new Random();
	int ex, ey;
	
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
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		preview.render(screen);
		show();
	}

	@Override
	protected void update() {
		preview.x = Mouse.mouseX - (base.width / 2);
		preview.y = Mouse.mouseY - (base.height / 2);
		if(Mouse.button == 1){
			entities.add(new Turret(Mouse.clickX - (base.width / 2), Mouse.clickY - (base.height / 2)));
		}
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
	}
	
}
