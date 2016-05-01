package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Plane {
	
	public static final int STAY = 0;
	public static final int UP = 1;
	public static final int DOWN = 2;
	
	private PApplet parent;
	private PImage[] images;
	private int x, y, w, h;
	private int frame, imageCount, movement;
	
	public Plane(PApplet parent, PImage[] images) {
		this.parent = parent;
		this.images = images;
		this.imageCount = images.length;
		this.x = 0;
		this.y = parent.height/2;
		this.w = images[0].width;
		this.h = images[0].height;
	}
	
	public void display() {
		
		switch(this.movement){
			case UP:
				this.y -= 5;
				break;
			case DOWN:
				this.y += 5;
				break;
			default:
				break;
		}
		this.parent.image(images[0], x, y, w, h);
		
	}
	
	public void setMovement(int m){
		this.movement = m;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getW(){
		return this.w;
	}
	
	public int getH(){
		return this.h;
	}
	
}
