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
	private int count,frame, imageCount, movement;
	
	public Plane(PApplet parent, PImage[] images) {
		this.parent = parent;
		this.images = images;
		this.imageCount = images.length;
		this.x = 0;
		this.y = parent.height/2;
		this.w = images[0].width;
		this.h = images[0].height;
		
		this.count = 0;
		this.frame=0;
	}
	
	public void display() {
		switch(this.movement){
			case UP:
				this.y -= 5;
				this.frame=(this.frame+1)%2;	//frame永遠小於2
				this.count = 0;					//images[0]或[1]
				break;
			case DOWN:
				this.y += 5;
				this.count = 1;					//images[2]
				break;
			default:
				break;
		}
		/*count+frame構成0,1,2的變換*/
		this.parent.image(images[count+frame], x, y, w, h);
		
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
