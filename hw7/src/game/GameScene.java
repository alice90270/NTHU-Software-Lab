package game;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScene extends PApplet{
	
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	public static final int MARGIN_TOP = 50;
	public static final int NUM_OF_ROCKS = 20;
	private PImage[] planeImgs;
	private PImage backgroundImg, rockImg, rockDownImg;
	private Plane plane;
	private Queue<Rock> rocks;
	private Game parentFrame;

	private TopBar topBar;
	public static int pass;
	public static float currentH=5000;
	private int i=0;
	
	/**
	 * Constructor of a game scene.
	 * 
	 * @param  parentFrame | the JFrame that owns this game scene
	 */
	public GameScene(Game parentFrame , TopBar topbar){
		/*傳入Game中的topbar*/
		this.topBar = topbar ;
		
		this.parentFrame = parentFrame;
		this.planeImgs = new PImage[3];
		this.planeImgs[2] = loadImage(this.getClass().getResource("/res/TappyPlane/PNG/Planes/planeGreen1.png").getPath());
		this.planeImgs[1] = loadImage(this.getClass().getResource("/res/TappyPlane/PNG/Planes/planeGreen2.png").getPath());
		this.planeImgs[0] = loadImage(this.getClass().getResource("/res/TappyPlane/PNG/Planes/planeGreen3.png").getPath());
		this.backgroundImg = loadImage(this.getClass().getResource("/res/TappyPlane/PNG/background.png").getPath());
		this.rockImg = loadImage(this.getClass().getResource("/res/TappyPlane/PNG/rockGrass.png").getPath());
		this.rockDownImg = loadImage(this.getClass().getResource("/res/TappyPlane/PNG/rockGrassDown.png").getPath());
		
	}
	
	public void setup(){
		size(800, 530);
		frameRate(60);
		
		this.plane = new Plane(this, this.planeImgs);
		this.rocks = new LinkedList<Rock>();
		
		Random random = new Random();
		for(int i=0 ; i<NUM_OF_ROCKS; i++){
			if(random.nextInt(2)%2==0)
				this.rocks.offer( new Rock(this, this.rockDownImg, width + i*400, Rock.DownRock) );
			else
				this.rocks.offer( new Rock(this, this.rockImg, width + i*400, Rock.NormalRock) );
		}
		
	}
	
	public void draw(){
		background(255, 255, 255);
		image(this.backgroundImg, 0, 50, this.backgroundImg.width, this.backgroundImg.height);
		
		checkRocks();
		
		//更新 topbar 12 次/秒
		i=(i+1)%5;
		if(i==0){
		this.topBar.setBar(pass,currentH);
		}
		
		/* The game is controlled by pressing SPACE to maintain the height of the plane.*/
		if(keyPressed&&key==' '){
			this.plane.setMovement(Plane.UP);
			currentH = (float) 10000*(1-this.plane.getY()/530f);
		}
		else{
			this.plane.setMovement(Plane.DOWN);
			currentH = (float) 10000*(1-this.plane.getY()/530f);
		}
		this.plane.display();

		for(Rock rock : this.rocks)
			rock.display();
		
		if(isCrashed())
			this.parentFrame.gameOver();
	}
	
	/**
	 * A method for checking whether the plane crashed or not
	 * 
	 * The method will first check the plane hit the top or the ground, if so, return true.
	 * After that, the method will check the plane overlapped to a rock or not, if so, return true.
	 * If there's no remaining rock, the player wins.
	 * Otherwise, return false.
	 *
	 * @return true if the plane is crashed
	 */
	public boolean isCrashed(){
		
		int planeY = this.plane.getY();
		int planeX = this.plane.getX();
		int planeW = this.plane.getW();
		int planeH = this.plane.getH();
		
		if( planeY < 50 || planeY+planeH > height )
			return true;
		
		Rock rock = this.rocks.peek();
		if(rock==null){
			this.parentFrame.win();
			return false;
		}
		for (int j=planeY ; j<planeY+planeH ; j++){
			for (int i=planeX+planeW-10;i<planeX+planeW;i++){
				if( get(i, j)==rock.getColor() )
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Keep removing the passed rock.
	 */
	public void checkRocks(){
		Rock rock =this.rocks.peek();
		if( rock.getX()+rock.getW()<0 ){
			this.rocks.remove();
			/*移除石頭時=>經過石頭*/
			pass++;								//經過的石頭數增加
			this.topBar.setBar(pass,currentH);	//更新TOPBAR資訊
			//System.out.println("pass= "+pass);
		}
	}
}
