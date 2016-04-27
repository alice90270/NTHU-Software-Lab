import java.util.Random;

import processing.core.PImage;


public class SmallRabbit {

	private List parent;
	private int x = 25;
	private PImage[] smallRabbitToRight;
	private PImage[] smallRabbitToLeft;
	private boolean isRight = true;
	private int frame = 0;
	private int cnt = 0;
	private int frameRate = 10;
	private int frameRate_cnt = 0;
	private Random random;
	
	public SmallRabbit(List parent, PImage[] smallRabbitToRight, PImage[] smallRabbitToLeft) {
		this.parent = parent;
		this.smallRabbitToRight = smallRabbitToRight;
		this.smallRabbitToLeft = smallRabbitToLeft;
		this.random = new Random();
	}
	
	public void drawRabbit(int smallRabbitWay) {
		
		if(smallRabbitWay == 0) {
			if(x > 350 || x < 25) {
				if(isRight) {
					parent.image(smallRabbitToRight[frame], x, 530, 130, 150);
					if(cnt++ == 10) {
						frame++;
						frame %= 2;
						cnt -= 5;
						x += 5;
					}
					if(x > parent.width) 
						x = 0 - 130;
				}
				else {
					parent.image(smallRabbitToLeft[frame], x, 530, 130, 150);
					if(cnt++ == 10) {
						frame++;
						frame %= 2;
						cnt -= 5;
						x -= 5;
					}
					if(x < 0 - 130)
						x = parent.width;
					
				}
					
			}
			else {
				if(isRight) {
					parent.image(smallRabbitToRight[frame], x, 530, 130, 150);
					if(cnt++ == 10) {
						frame++;
						frame %= 2;
						cnt -= frameRate;
						if(frameRate_cnt++ == 10) {
							frameRate = random.nextInt(10) + 10;
							frameRate_cnt -= 10;
						}
						x += 5;
					}
					if(x == 350) {
						isRight = false;
					}
				}
				else {
					parent.image(smallRabbitToLeft[frame], x, 530, 130, 150);
					if(cnt++ == 10) {
						frame++;
						frame %= 2;
						cnt -= frameRate;
						if(frameRate_cnt++ == 10) {
							frameRate = random.nextInt(10) + 10;
							frameRate_cnt -= 10;
						}
						x -= 5;
					}
					
					if(x == 25) {
						isRight = true;
					}
				}
			}
		}
		else if(smallRabbitWay == 1) { //Left
			isRight = false;
			parent.image(smallRabbitToLeft[frame], x, 530, 130, 150);
			if(cnt++ == 10) {
				frame++;
				frame %= 2;
				cnt -= 3;
				x -= 5;
				if(x < 0 - 130)
					x = parent.width;
			}
			
		}
		else if(smallRabbitWay == 2) { //Right
			isRight = true;
			parent.image(smallRabbitToRight[frame], x, 530, 130, 150);
			if(cnt++ == 10) {
				frame++;
				frame %= 2;
				cnt -= 3;
				x += 5;
				if(x > parent.width) 
					x = 0 - 130;
			}	
		}
	}
}
