import processing.core.PApplet;
import processing.core.PImage;

public class Start extends PApplet{
	private PImage[] background;
	private int frame=0;
	private int x=15;
	private boolean clicked;
	private textField field;
	
	
	public Start(textField field){
		this.field = field;
		this.background = new PImage[26];
		this.background[0] = loadImage(this.getClass().getResource("/res/start/start0.png").getPath());
		this.background[1] = loadImage(this.getClass().getResource("/res/start/start1.png").getPath());
		this.background[2] = loadImage(this.getClass().getResource("/res/start/start2.png").getPath());
		this.background[3] = loadImage(this.getClass().getResource("/res/start/start3.png").getPath());
		this.background[4] = loadImage(this.getClass().getResource("/res/start/start4.png").getPath());
		this.background[5] = loadImage(this.getClass().getResource("/res/start/start5.png").getPath());
		this.background[6] = loadImage(this.getClass().getResource("/res/start/start6.png").getPath());
		this.background[7] = loadImage(this.getClass().getResource("/res/start/start7.png").getPath());
		this.background[8] = loadImage(this.getClass().getResource("/res/start/start8.png").getPath());
		this.background[9] = loadImage(this.getClass().getResource("/res/start/start9.png").getPath());
		this.background[10] = loadImage(this.getClass().getResource("/res/start/start10.png").getPath());
		this.background[11] = loadImage(this.getClass().getResource("/res/start/start11.png").getPath());
		this.background[12] = loadImage(this.getClass().getResource("/res/start/start12.png").getPath());
		this.background[13] = loadImage(this.getClass().getResource("/res/start/start13.png").getPath());
		this.background[14] = loadImage(this.getClass().getResource("/res/start/start14.png").getPath());
		this.background[15] = loadImage(this.getClass().getResource("/res/start/start15.png").getPath());	
		this.background[16] = loadImage(this.getClass().getResource("/res/start/start_c0.png").getPath());
		this.background[17] = loadImage(this.getClass().getResource("/res/start/start_c1.png").getPath());
		this.background[18] = loadImage(this.getClass().getResource("/res/start/start_c2.png").getPath());
		this.background[19] = loadImage(this.getClass().getResource("/res/start/start_c3.png").getPath());
		this.background[20] = loadImage(this.getClass().getResource("/res/start/start_c4.png").getPath());
		this.background[21] = loadImage(this.getClass().getResource("/res/start/start_c5.png").getPath());
		this.background[22] = loadImage(this.getClass().getResource("/res/start/start_c6.png").getPath());
		this.background[23] = loadImage(this.getClass().getResource("/res/start/start_c7.png").getPath());
		this.background[24] = loadImage(this.getClass().getResource("/res/start/start_c8.png").getPath());
		this.background[25] = loadImage(this.getClass().getResource("/res/start/start_c9.png").getPath());
		
	}
	public void setup(){
		size(500,700);
	}
	
	public void draw(){
		frameRate(12);
		if(clicked){
			if(this.frame>15 && this.frame<26){
				image(this.background[this.frame],0,0);
				this.frame=(this.frame+1);
			}
			if(this.frame>25){
				this.frame=25;
				image(this.background[this.frame],0,0);	
				//this.field.listView();
			}
		}
		else{
			if(this.frame<16){
				image(this.background[this.frame],0,0);
				this.frame=(this.frame+1);
			}
			else{
				x=(x+1)%10;
				if(x==5){
					image(this.background[x+10],0,0);
				}
				if(x==0){
					image(this.background[x+14],0,0);
				}
			}
		}
	}	
	public void mouseClicked(){
		if(this.frame>15)clicked=true;
	}
}
