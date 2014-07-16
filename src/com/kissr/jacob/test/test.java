package com.kissr.jacob.test;

import java.util.Random;
import java.util.Scanner;


public class test {
	String[] confusedanswers ={"huh?","what?","wha?"};
	Random rand = new Random();
	public static void main (String[] args){
		new test();
	}
	
	public test(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("hi");
		delay("sup?",600);
		
		while(true){ //Game Loop
			if (scan.nextLine().equals("nothing")){
				delay("ok",800);
				
				delay("good bye",1500);
				break;
			}else{
				delay(confusedanswers[rand.nextInt(confusedanswers.length)],800);
			}
		}
	}
	
	public static void delay(String text, int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(text);
	}
	
}
