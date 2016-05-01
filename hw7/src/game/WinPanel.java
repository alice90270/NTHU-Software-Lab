package game;

import processing.core.PApplet;

public class WinPanel extends PApplet{

	public void setup(){
		size(800, 530);
	}
	
	public void draw(){
		background(82,78,78);
		fill(255,205,55);
		textSize(60);
		textAlign(CENTER);
		text("You Win!", width/2, height/2+50);
	}
	
}

