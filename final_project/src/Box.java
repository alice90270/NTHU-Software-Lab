import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.UIManager;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Box extends PApplet{
	
	private Game parentFrame;
	private PImage[] background;
	private PImage[] RabbitImgs;
	private PImage[] EatImgs;
	private PImage[] BathImgs;
	private PImage[] ZzzImgs;
	private PImage[] PokeImgs;
	private PImage[] PlayImgs;
	private PImage coin;
	private int score;
	private Rabbit rabbit;
	private boolean clicked = false;
	private int event;
	private int bg;
	private int animationCount = 0;
	private PFont font;
	private int state;
	private boolean isHappy = false;
	private boolean zeroAnimation = false;
	public Box(Game parentFrame, int score, int state){
		
		this.parentFrame = parentFrame;
		this.background = new PImage[6];
		this.background[0] = loadImage(this.getClass().getResource("/res/background.png").getPath());
		this.background[1] = loadImage(this.getClass().getResource("/res/background1.png").getPath());
		this.background[2] = loadImage(this.getClass().getResource("/res/background2.png").getPath());
		this.background[3] = loadImage(this.getClass().getResource("/res/background3.png").getPath());
		this.background[4] = loadImage(this.getClass().getResource("/res/background4.png").getPath());
		this.background[5] = loadImage(this.getClass().getResource("/res/background5.png").getPath());
		
		this.RabbitImgs = new PImage[5];
		this.RabbitImgs[0] = loadImage(this.getClass().getResource("/res/happy.png").getPath());
		this.RabbitImgs[1] = loadImage(this.getClass().getResource("/res/hungry.png").getPath());
		this.RabbitImgs[2] = loadImage(this.getClass().getResource("/res/dirty.png").getPath());
		this.RabbitImgs[3] = loadImage(this.getClass().getResource("/res/tired.png").getPath());
		this.RabbitImgs[4] = loadImage(this.getClass().getResource("/res/angry.png").getPath());
		
		
		this.EatImgs = new PImage[10];
		this.EatImgs[0] = loadImage(this.getClass().getResource("/res/eat1.png").getPath());
		this.EatImgs[1] = loadImage(this.getClass().getResource("/res/eat2.png").getPath());
		this.EatImgs[2] = loadImage(this.getClass().getResource("/res/eat3.png").getPath());
		this.EatImgs[3] = loadImage(this.getClass().getResource("/res/eat4.png").getPath());
		this.EatImgs[4] = loadImage(this.getClass().getResource("/res/eat5.png").getPath());
		this.EatImgs[5] = loadImage(this.getClass().getResource("/res/eat6.png").getPath());
		this.EatImgs[6] = loadImage(this.getClass().getResource("/res/eat7.png").getPath());
		this.EatImgs[7] = loadImage(this.getClass().getResource("/res/eat8.png").getPath());
		this.EatImgs[8] = loadImage(this.getClass().getResource("/res/eat9.png").getPath());
		this.EatImgs[9] = loadImage(this.getClass().getResource("/res/eat10.png").getPath());
		
		this.BathImgs = new PImage[2];
		this.BathImgs[0] = loadImage(this.getClass().getResource("/res/bath1.png").getPath());
		this.BathImgs[1] = loadImage(this.getClass().getResource("/res/bath2.png").getPath());
		
		this.ZzzImgs = new PImage[2];
		this.ZzzImgs[0] = loadImage(this.getClass().getResource("/res/ZZZ1.png").getPath());
		this.ZzzImgs[1] = loadImage(this.getClass().getResource("/res/ZZZ2.png").getPath());
		
		this.PokeImgs = new PImage[2];
		this.PokeImgs[0] = loadImage(this.getClass().getResource("/res/poke1.png").getPath());
		this.PokeImgs[1] = loadImage(this.getClass().getResource("/res/poke2.png").getPath());
		
		this.PlayImgs = new PImage[2];
		this.PlayImgs[0] = loadImage(this.getClass().getResource("/res/play1.png").getPath());
		this.PlayImgs[1] = loadImage(this.getClass().getResource("/res/play2.png").getPath());
		
		this.coin = new PImage();
		this.coin = loadImage(this.getClass().getResource("/res/coin.png").getPath());
		
		this.font = new PFont();
		this.font = createFont(this.getClass().getResource("/res/font.ttf").getPath(), 30);
		
		this.score = score;
		this.state = state;
		
	}	
	
	
	public void setup(){
		smooth();
		size(500, 700);
		frameRate(10);
		
		this.rabbit = new Rabbit(this, this.RabbitImgs);

	}
	public void draw(){
		
		
		frameRate(60);
		image(background[this.event], 0, 0);
		if(!this.zeroAnimation)
			drawScore();
		
		
		if(!clicked){
			this.rabbit.display(state);
			buttonLighted();
			this.bg = 0;
		}
		
		if(clicked && !this.zeroAnimation) {
			if(animationCount++ == 8){
				clicked=false;
				animationCount = 0;
			}
			
				//drawEvent(0);
			frameRate(3);
			if(this.bg == 1) {
				rabbit.Action(this.EatImgs);
				if(this.state == 1)
					this.state = 0;
			}
			else if(this.bg == 2) {
				rabbit.Action(this.BathImgs);
				if(this.state == 2)
					this.state = 0;
			}
			else if(this.bg == 3) {
				rabbit.Action(this.ZzzImgs);
				if(this.state == 3)
					this.state = 0;
			}
			else if(this.bg == 4) {
				rabbit.Action(this.PokeImgs);
			}
			else if(this.bg == 5) {
				rabbit.Action(this.PlayImgs); 
				if(this.state == 4)
					this.state = 0;
			}
			
		}
		else if(clicked && this.zeroAnimation) {
            frameRate(30);
            if(animationCount++ == 10){
                this.zeroAnimation = false;
                clicked=false;
                animationCount = 0;
            }
            this.rabbit.display(state);
            image(coin, 20, 0);
            fill(255,160,211, 500);
            //textAlign(CENTER);
            textFont(font, 45);
            text("x ", 120, 80);
            if(this.animationCount % 2 == 0)
                text(this.score, 160, 75);
            else
                text(this.score, 160, 85);
		}
		 
	}
	
	public void mouseClicked(MouseEvent e){
		this.clicked = true;
		if(score == 0) {
			this.zeroAnimation = true;
		}
		else {
			if(this.bg == 0) {
				if(mouseX>=20 && mouseX<=90 && mouseY>=600 && mouseY<=670)this.bg=1;
				else if(mouseX>=115 && mouseX<=190 && mouseY>=600 && mouseY<=670)this.bg=2;
				else if(mouseX>=210 && mouseX<=285 && mouseY>=600 && mouseY<=670)this.bg=3;
				else if(mouseX>=310 && mouseX<=375 && mouseY>=600 && mouseY<=670)this.bg=4;
				else if(mouseX>=410 && mouseX<=475 && mouseY>=600 && mouseY<=670)this.bg=5;
				else {
					clicked = false;
					this.score += 5;
				}
				this.score -= 5;
			}
		}
	}
	
	public void buttonLighted(){
		this.event=0;
		if(mouseX>=20 && mouseX<=90 && mouseY>=600 && mouseY<=670)this.event=1;
		if(mouseX>=115 && mouseX<=190 && mouseY>=600 && mouseY<=670)this.event=2;
		if(mouseX>=210 && mouseX<=285 && mouseY>=600 && mouseY<=670)this.event=3;
		if(mouseX>=310 && mouseX<=375 && mouseY>=600 && mouseY<=670)this.event=4;
		if(mouseX>=410 && mouseX<=475 && mouseY>=600 && mouseY<=670)this.event=5;
	}

	public void drawScore() {
		image(coin,20, 0);
        fill(255,160,211, 500);
		//textAlign(CENTER);
		textFont(font, 45);
		text("x " + score, 120, 80);
	}
	
	public void addScore(int num) {
		this.score += num;
	}
	
	public void changeState(int state) {
		this.state = state;
	}
	
	public int checkState() {
		return this.state;
	}
	public int getScore() {
		return this.score;
	}
}
