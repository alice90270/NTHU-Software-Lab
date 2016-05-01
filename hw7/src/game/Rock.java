package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Rock {
	public static final int DownRock = 0;
	public static final int NormalRock = 1;
	
	private PApplet parent;
	private PImage image;
	private int x, y, w, h;
	
	public Rock(PApplet parent, PImage image, int x, int type) {
		this.parent = parent;
		this.image = image;
		this.x = x;
		this.w = image.width;
		this.h = image.height;
		
		if(type==DownRock)
			this.y = 0;
		else
			this.y = parent.height-this.h;
	}
	
	public void display(){
		this.x -= 5;
		this.parent.image(this.image, this.x, this.y, this.w, this.h);
	}
	
	public int getColor(){
		return parent.get(this.x+this.w/2, this.y+this.h/2);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getW(){
		return this.w;
	}
}
