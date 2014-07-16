package com.kissr.jacob.Game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.kissr.jacob.Game.Graphics.Screen;
import com.kissr.jacob.Game.Graphics.SpriteSheet;

public class Game extends Canvas implements Runnable{
	
	boolean running = false;
	Thread loop;
	int width = 300;
	int height = width / 16*9;
	int scale = 3;
	JFrame frame;
	Screen screen;
	
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		screen = new Screen(width, height);
		frame = new JFrame("Tower Defence");
		frame.setSize(width*scale, height*scale);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
		start();
	}
	
	public synchronized void start(){
		loop = new Thread(this, "Game Loop");
		running = true;
		loop.start();
	}
	
	public synchronized void stop(){
		Thread stop = new Thread("Stop"){
			public void run(){
				running = false;
				try {
					loop.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}
		};
		stop.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long timer = System.currentTimeMillis();
		int frames = 0;
		final double ns = 1000000000.0 / 30.0;
		double delta = 0;
		frame.requestFocus();
		long lasttime = System.nanoTime();
		while(running){
			long now = System.nanoTime();
			delta += (now-lasttime) /ns;
			lasttime = now;
			while(delta >= 1){
				frames++;
				update();
				render();
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				System.out.println(frames);
				frames = 0;
			}
		}
	}
	
	public void update(){
		
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		screen.DrawSpriteSheet(10, 10, SpriteSheet.sheet);
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, width*scale, height*scale, null);
		g.dispose();
		bs.show();
	}
	
}
