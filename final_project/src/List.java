import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
 

import javax.swing.JTextField;
 



import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.event.MouseEvent;


public class List extends PApplet {
 
    private PImage background;
    private PImage[] BoxImgs;
    private PImage[] CheckImgs;
    private PImage[] NameBtnImgs;
    private PImage[] NewListImgs;
    private PImage[] Q_AImgs;
    private PImage[] smallRabbitToRight;
    private PImage[] smallRabbitToLeft;
     
    private PImage[] boxImgs;
    private PImage[] newImgs;
    private PImage[] qaImgs;
    private PImage[] colorImgs;
    private PImage[][] bgImgs;
    private PImage choosenImg;
    private PImage qabg;
     
    private PImage item;
    private ArrayList<Integer> items = new ArrayList<Integer>();
    private int ctrl_Boximg;
    private int ctrl_checkimg;
    private int ctrl_newlistimg;
    private int ctrl_qaimg;
    private int y = 30;
    private Box box;
    private int listcnt = 0;
    private boolean added = false;
    private int frame = 0;
    private boolean checked;
    private int index;
    private PFont font;
    private ArrayList<String> texts = new ArrayList<String>();
    private boolean edit = false;
    private int edit_index;
    private Game game;
    private SmallRabbit smallrabbit;
    private boolean isGameStart = false;
    private int score = 100;
    private int smallRabbitWay = 0;
    private Random random;
    private FileReader filereader;
    private BufferedReader br;
    private int state;
    private String buf;
    private long prev_time;
    private double angrytime = 18000000;
    private textField str;
    private Scanner scanner = new Scanner(System.in);
    private textField field;
    private boolean makenewlist = false;
    private boolean boo_removelist;
    private int color = 0;
    private int bgstyle = 0;
    private boolean isQABK = false;
	private boolean start;
	private PImage[] startbg;
	private int startFrame=0;
	private int x=15;
	private boolean clicked;
	private static int count=0;
    //private AudioPlayer audioPlayer = new AudioPlayer("bgm.mp3");
	
    public List(textField field) {
    	this.startbg = new PImage[26];
		this.startbg[0] = loadImage(this.getClass().getResource("/res/start/start0.png").getPath());
		this.startbg[1] = loadImage(this.getClass().getResource("/res/start/start1.png").getPath());
		this.startbg[2] = loadImage(this.getClass().getResource("/res/start/start2.png").getPath());
		this.startbg[3] = loadImage(this.getClass().getResource("/res/start/start3.png").getPath());
		this.startbg[4] = loadImage(this.getClass().getResource("/res/start/start4.png").getPath());
		this.startbg[5] = loadImage(this.getClass().getResource("/res/start/start5.png").getPath());
		this.startbg[6] = loadImage(this.getClass().getResource("/res/start/start6.png").getPath());
		this.startbg[7] = loadImage(this.getClass().getResource("/res/start/start7.png").getPath());
		this.startbg[8] = loadImage(this.getClass().getResource("/res/start/start8.png").getPath());
		this.startbg[9] = loadImage(this.getClass().getResource("/res/start/start9.png").getPath());
		this.startbg[10] = loadImage(this.getClass().getResource("/res/start/start10.png").getPath());
		this.startbg[11] = loadImage(this.getClass().getResource("/res/start/start11.png").getPath());
		this.startbg[12] = loadImage(this.getClass().getResource("/res/start/start12.png").getPath());
		this.startbg[13] = loadImage(this.getClass().getResource("/res/start/start13.png").getPath());
		this.startbg[14] = loadImage(this.getClass().getResource("/res/start/start14.png").getPath());
		this.startbg[15] = loadImage(this.getClass().getResource("/res/start/start15.png").getPath());	
		this.startbg[16] = loadImage(this.getClass().getResource("/res/start/start_c0.png").getPath());
		this.startbg[17] = loadImage(this.getClass().getResource("/res/start/start_c1.png").getPath());
		this.startbg[18] = loadImage(this.getClass().getResource("/res/start/start_c2.png").getPath());
		this.startbg[19] = loadImage(this.getClass().getResource("/res/start/start_c3.png").getPath());
		this.startbg[20] = loadImage(this.getClass().getResource("/res/start/start_c4.png").getPath());
		this.startbg[21] = loadImage(this.getClass().getResource("/res/start/start_c5.png").getPath());
		this.startbg[22] = loadImage(this.getClass().getResource("/res/start/start_c6.png").getPath());
		this.startbg[23] = loadImage(this.getClass().getResource("/res/start/start_c7.png").getPath());
		this.startbg[24] = loadImage(this.getClass().getResource("/res/start/start_c8.png").getPath());
		this.startbg[25] = loadImage(this.getClass().getResource("/res/start/start_c9.png").getPath());
	
    	this.field = field;
   
        this.background = new PImage();
        this.background = loadImage(this.getClass()
                .getResource("/res/Box/listBackground.png").getPath());
 
        this.BoxImgs = new PImage[2];
        this.BoxImgs[0] = loadImage(this.getClass()
                .getResource("/res/Box/BOX.png").getPath());
        this.BoxImgs[1] = loadImage(this.getClass()
                .getResource("/res/Box/BOXChoose.png").getPath());
 
         
        this.boxImgs = new PImage[9];
        this.boxImgs[0] = loadImage(this.getClass().getResource("/res/boxButton/BOX_bg.png").getPath());
        this.boxImgs[1] = loadImage(this.getClass().getResource("/res/boxButton/BOX_blue.png").getPath());
        this.boxImgs[2] = loadImage(this.getClass().getResource("/res/boxButton/BOX_green.png").getPath());
        this.boxImgs[3] = loadImage(this.getClass().getResource("/res/boxButton/BOX_indigo.png").getPath());
        this.boxImgs[4] = loadImage(this.getClass().getResource("/res/boxButton/BOX_orange.png").getPath());
        this.boxImgs[5] = loadImage(this.getClass().getResource("/res/boxButton/BOX_pink.png").getPath());
        this.boxImgs[6] = loadImage(this.getClass().getResource("/res/boxButton/BOX_purple.png").getPath());
        this.boxImgs[7] = loadImage(this.getClass().getResource("/res/boxButton/BOX_red.png").getPath());
        this.boxImgs[8] = loadImage(this.getClass().getResource("/res/boxButton/BOX_yellow.png").getPath());
         
        this.newImgs = new PImage[9];
        this.newImgs[0] = loadImage(this.getClass().getResource("/res/newButton/NEW_bg.png").getPath());
        this.newImgs[1] = loadImage(this.getClass().getResource("/res/newButton/NEW_blue.png").getPath());
        this.newImgs[2] = loadImage(this.getClass().getResource("/res/newButton/NEW_green.png").getPath());
        this.newImgs[3] = loadImage(this.getClass().getResource("/res/newButton/NEW_indigo.png").getPath());
        this.newImgs[4] = loadImage(this.getClass().getResource("/res/newButton/NEW_orange.png").getPath());
        this.newImgs[5] = loadImage(this.getClass().getResource("/res/newButton/NEW_pink.png").getPath());
        this.newImgs[6] = loadImage(this.getClass().getResource("/res/newButton/NEW_purple.png").getPath());
        this.newImgs[7] = loadImage(this.getClass().getResource("/res/newButton/NEW_red.png").getPath());
        this.newImgs[8] = loadImage(this.getClass().getResource("/res/newButton/NEW_yellow.png").getPath());
         
        this.qaImgs = new PImage[9];
        this.qaImgs[0] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_bg.png").getPath());
        this.qaImgs[1] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_blue.png").getPath());
        this.qaImgs[2] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_green.png").getPath());
        this.qaImgs[3] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_indigo.png").getPath());
        this.qaImgs[4] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_orange.png").getPath());
        this.qaImgs[5] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_pink.png").getPath());
        this.qaImgs[6] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_purple.png").getPath());
        this.qaImgs[7] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_red.png").getPath());
        this.qaImgs[8] = loadImage(this.getClass().getResource("/res/qaButton/Q&A_yellow.png").getPath());
         
        this.colorImgs = new PImage[9];
        this.colorImgs[0] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_bg.png").getPath());
        this.colorImgs[1] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_blue.png").getPath());
        this.colorImgs[2] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_green.png").getPath());
        this.colorImgs[3] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_indigo.png").getPath());
        this.colorImgs[4] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_orange.png").getPath());
        this.colorImgs[5] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_pink.png").getPath());
        this.colorImgs[6] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_purple.png").getPath());
        this.colorImgs[7] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_red.png").getPath());
        this.colorImgs[8] = loadImage(this.getClass().getResource("/res/colorButton/COLOR_yellow.png").getPath());
         
        this.choosenImg = new PImage();
        this.choosenImg = loadImage(this.getClass().getResource("/res/choosenButton/under.png").getPath());
         
        this.bgImgs = new PImage[9][3];
        this.bgImgs[0][0] = loadImage(this.getClass().getResource("/res/bgImg/List_bg.png").getPath());
        this.bgImgs[0][1] = loadImage(this.getClass().getResource("/res/bgImg/List_bgdot.png").getPath());
        this.bgImgs[0][2] = loadImage(this.getClass().getResource("/res/bgImg/List_bgstripe.png").getPath());
         
        this.bgImgs[1][0] = loadImage(this.getClass().getResource("/res/bgImg/List_blue.png").getPath());
        this.bgImgs[1][1] = loadImage(this.getClass().getResource("/res/bgImg/List_bluedot.png").getPath());
        this.bgImgs[1][2] = loadImage(this.getClass().getResource("/res/bgImg/List_bluestripe.png").getPath());
         
        this.bgImgs[2][0] = loadImage(this.getClass().getResource("/res/bgImg/List_green.png").getPath());
        this.bgImgs[2][1] = loadImage(this.getClass().getResource("/res/bgImg/List_greendot.png").getPath());
        this.bgImgs[2][2] = loadImage(this.getClass().getResource("/res/bgImg/List_greenstripe.png").getPath());
         
        this.bgImgs[3][0] = loadImage(this.getClass().getResource("/res/bgImg/List_indigo.png").getPath());
        this.bgImgs[3][1] = loadImage(this.getClass().getResource("/res/bgImg/List_indigodot.png").getPath());
        this.bgImgs[3][2] = loadImage(this.getClass().getResource("/res/bgImg/List_indigostripe.png").getPath());
         
        this.bgImgs[4][0] = loadImage(this.getClass().getResource("/res/bgImg/List_orange.png").getPath());
        this.bgImgs[4][1] = loadImage(this.getClass().getResource("/res/bgImg/List_orangedot.png").getPath());
        this.bgImgs[4][2] = loadImage(this.getClass().getResource("/res/bgImg/List_orangestripe.png").getPath());
         
        this.bgImgs[5][0] = loadImage(this.getClass().getResource("/res/bgImg/List_pink.png").getPath());
        this.bgImgs[5][1] = loadImage(this.getClass().getResource("/res/bgImg/List_pinkdot.png").getPath());
        this.bgImgs[5][2] = loadImage(this.getClass().getResource("/res/bgImg/List_pinkstripe.png").getPath());
         
        this.bgImgs[6][0] = loadImage(this.getClass().getResource("/res/bgImg/List_purple.png").getPath());
        this.bgImgs[6][1] = loadImage(this.getClass().getResource("/res/bgImg/List_purpledot.png").getPath());
        this.bgImgs[6][2] = loadImage(this.getClass().getResource("/res/bgImg/List_purplestripe.png").getPath());
         
        this.bgImgs[7][0] = loadImage(this.getClass().getResource("/res/bgImg/List_red.png").getPath());
        this.bgImgs[7][1] = loadImage(this.getClass().getResource("/res/bgImg/List_reddot.png").getPath());
        this.bgImgs[7][2] = loadImage(this.getClass().getResource("/res/bgImg/List_redstripe.png").getPath());
         
        this.bgImgs[8][0] = loadImage(this.getClass().getResource("/res/bgImg/List_yellow.png").getPath());
        this.bgImgs[8][1] = loadImage(this.getClass().getResource("/res/bgImg/List_yellowdot.png").getPath());
        this.bgImgs[8][2] = loadImage(this.getClass().getResource("/res/bgImg/List_yellowstripe.png").getPath());
         
        this.qabg = new PImage();
        this.qabg = loadImage(this.getClass().getResource("/res/Q&Abk.png").getPath());
         
        this.CheckImgs = new PImage[16];
        for (int i = 1; i < 17; i++) {
            if (i < 10)
                this.CheckImgs[i - 1] = loadImage(this
                        .getClass()
                        .getResource(
                                "/res/Box/check0" + String.valueOf(i) + ".png")
                        .getPath());
            else
                this.CheckImgs[i - 1] = loadImage(this
                        .getClass()
                        .getResource(
                                "/res/Box/check" + String.valueOf(i) + ".png")
                        .getPath());
        }
 
        this.NameBtnImgs = new PImage[3];
        this.NameBtnImgs[0] = loadImage(this.getClass()
                .getResource("/res/Box/NAMEbutton.png").getPath());
        this.NameBtnImgs[1] = loadImage(this.getClass()
                .getResource("/res/Box/NAMEbutton1.png").getPath());
        this.NameBtnImgs[2] = loadImage(this.getClass()
                .getResource("/res/Box/NAMEbutton2.png").getPath());
 
        this.NewListImgs = new PImage[2];
        this.NewListImgs[0] = loadImage(this.getClass()
                .getResource("/res/Box/NEW.png").getPath());
        this.NewListImgs[1] = loadImage(this.getClass()
                .getResource("/res/Box/NEWChoose.png").getPath());
 
        this.item = new PImage();
        this.item = loadImage(this.getClass().getResource("/res/Box/item.png")
                .getPath());
 
        this.Q_AImgs = new PImage[2];
        this.Q_AImgs[0] = loadImage(this.getClass()
                .getResource("/res/Box/Q&A.png").getPath());
        this.Q_AImgs[1] = loadImage(this.getClass()
                .getResource("/res/Box/Q&AChoose.png").getPath());
 
        this.font = new PFont();
        this.font = createFont(this.getClass().getResource("/res/earfong.ttc")
                .getPath(), 30);
 
        this.smallRabbitToRight = new PImage[2];
        this.smallRabbitToRight[0] = loadImage(this.getClass()
                .getResource("/res/Box/walkR1.png").getPath());
        this.smallRabbitToRight[1] = loadImage(this.getClass()
                .getResource("/res/Box/walkR2.png").getPath());
 
        this.smallRabbitToLeft = new PImage[2];
        this.smallRabbitToLeft[0] = loadImage(this.getClass()
                .getResource("/res/Box/walkL1.png").getPath());
        this.smallRabbitToLeft[1] = loadImage(this.getClass()
                .getResource("/res/Box/walkL2.png").getPath());
 
        this.smallrabbit = new SmallRabbit(this, this.smallRabbitToRight,
                this.smallRabbitToLeft);
 
        this.random = new Random();
 
        try {
            //this.filereader = new FileReader(this.getClass()
                //  .getResource("/res/data.txt").getPath());
            this.br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(this.getClass().getResource("/res/data.txt").getPath()),"UTF8"));
            this.state = Integer.parseInt(br.readLine());
            this.score = Integer.parseInt(br.readLine());
            // System.out.println(Long.parseLong(br.readLine()));
            this.prev_time = Long.parseLong(br.readLine());
            while ((buf = br.readLine()) != null) {
                this.field.addFileList(buf);
                items.add(y);
                y = y + 37;
                listcnt++;
            }
 
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        
         
 
    }
 
    public void setup() {
        size(500, 700);
       //audioPlayer.play();
    }
 
    public void draw() {
        // println("x:"+mouseX+" y:"+mouseY);
    	if(!start){
    		frameRate(12);
    		
    		
    		if(clicked){
    			if(this.startFrame>15 && this.startFrame<26){
    				image(this.startbg[this.startFrame],0,0);
    				this.startFrame=(this.startFrame+1);
    			}
    			if(this.startFrame>25){
    				this.startFrame=25;
    				image(this.startbg[this.startFrame],0,0);
    				if(count<15) {
    					count++;
    					//System.out.println(count);
    				}
    				else start=true;
    			}
    		}
    		else{
    			if(this.startFrame<16){
    				image(this.startbg[this.startFrame],0,0);
    				this.startFrame=(this.startFrame+1);
    			}
    			else{
    				x=(x+1)%10;
    				if(x==5){
    					image(this.startbg[x+10],0,0);
    				}
    				if(x==0){
    					image(this.startbg[x+14],0,0);
    				}
    			}
    		}
    	}
    	else{
    		frameRate(30);
    		
    		if(isQABK && count==15) {
    			image(this.qabg, 0, 0);
    		}
    		else {
    			initiate();
    			boxState();
    			this.smallrabbit.drawRabbit(this.smallRabbitWay);
    			if (!keyPressed) {
    				smallRabbitWay = 0;
    			}
     
    			buttonLighted();
    			if (added) {
    				addList();
    			}
    			if (!added) {
    				for (int i = 0; i < items.size(); i++) {
    					image(this.item, 30, items.get(i));
    				}
    			}
    			if (checked) {
    				checkAnimation(index);
    			}
    			if (edit) {
                // cursorDisplay();
    			}
       // textDisplay();
    			mousehovered();
    		}
    	}
    }
 
    /*
     * public void cursorDisplay(){ float cw = 0; if(!texts.isEmpty()) cw =
     * textWidth(texts.get(edit_index));
     * line(78+cw,30+35*edit_index,78+cw,55+38*edit_index); } public void
     * textDisplay(){ for(int i=0;i<texts.size();i++){ fill(255, 172, 255, 500);
     * textFont(font,25); text(texts.get(i),78,55+37*i); } }
     */
    public void mousehovered() {
    	if(count==15){
    		this.field.textfieldadd();
    		for (int i = 0; i < texts.size(); i++) {
    			if (mouseX >= 78 && mouseX <= 385 && mouseY >= 30 + 35 * i
                    && mouseY <= 55 + 38 * i) {
    				cursor(TEXT);
    				break;
    			} else {
    				cursor(ARROW);
    			}
    		}
    	}
    	
        
    }
 
    public void mouseClicked(MouseEvent e) {
    	if(startFrame>15) clicked=true;
    	
    	if(count==15){
    		
    		if(isQABK) {
    			isQABK = false;
    			this.field.textfieldadd();
        }
        else {
            if (mouseX >= 400 && mouseX <= 465 && mouseY >= 30 && mouseY <= 120) {
                if (!isGameStart) {
                    game = new Game(score, state);
                    game.start();
                    isGameStart = true;
                } else {
                    game.dispose();
                    isGameStart = false;
                    this.state = game.checkState();
                    this.score = game.getScore();
                }
     
            } else if (mouseX >= 400 && mouseX <= 465 && mouseY >= 140
                    && mouseY <= 230) {
                if (listcnt < 13) {
                    added = true;
                    makenewlist = true;
                    listcnt++;
                }
            } else if (mouseX >= 400 && mouseX <= 465 && mouseY >= 250
                    && mouseY <= 340) {
                isQABK = true;
                this.field.textfieldremove();
     
            } else if (mouseX >= 400 && mouseX <= 465 && mouseY >= 360 && mouseY <= 450){
                this.color = this.random.nextInt(9);
                this.bgstyle = this.random.nextInt(3);
                System.out.println(this.color);
            }
            else {
                edit = false;
            }
            for (int i = 0; i < items.size(); i++) {
                if (mouseX >= 40 && mouseX <= 55 && mouseY >= 40 + 37 * i
                        && mouseY <= 55 + 37 * i) {
                    checked = true;
                    index = i;
                }
            }
        }
    	}
        
        /*
         * for(int i=0;i<texts.size();i++){
         * if(mouseX>=78&&mouseX<=385&&mouseY>=30+35*i&&mouseY<=55+38*i){
         * edit=true; edit_index=i;
         * 
         * } }
         */
 
    }
 
    public void buttonLighted() {
        if (mouseX >= 400 && mouseX <= 465 && mouseY >= 30 && mouseY <= 120) {
            image(choosenImg, 400, 30);
            image(boxImgs[this.color], 400, 30);
        }
        else if (mouseX >= 400 && mouseX <= 465 && mouseY >= 140
                && mouseY <= 230) {
            image(choosenImg, 400, 140);
            image(newImgs[this.color], 400, 140);
        }
        else if (mouseX >= 400 && mouseX <= 465 && mouseY >= 250
                && mouseY <= 340) {
            image(choosenImg, 400, 250);
            image(qaImgs[this.color], 400, 250);
        }
        else if (mouseX >= 400 && mouseX <= 465 && mouseY >=360 && mouseY <=450) {
            image(choosenImg, 400, 360);
            image(colorImgs[this.color], 400, 360);
        }
    }
 
    public void addList() {
        items.add(y);
        y = y + 37;
        added = false;
        // texts.add("");
        edit = true;
 
    }
 
    public boolean get_makenewlist() {
        return this.makenewlist;
    }
 
    public void set_makenewlist(boolean boo) {
        this.makenewlist = boo;
    }
 
    public boolean removelist() {
        return this.boo_removelist;
    }
 
    public void set_removelist(boolean boo) {
        this.boo_removelist = boo;
    }
 
    public int get_removeIndex() {
        return this.index;
    }
 
    public void checkAnimation(int i) {
        if (this.frame < 15) {
            this.frame = (this.frame + 1);
            image(this.CheckImgs[this.frame], 30, 30 + i * 37);
        } else {
            this.frame = 0;
            this.checked = false;
            remove(index);
            this.boo_removelist = true;
        }
    }
 
    public void remove(int index) {
        initiate();
        items.remove(index);
        // texts.remove(index);
        for (int i = 0; i < items.size(); i++) {
            items.set(i, 30 + i * 37);
 
        }
        y -= 37;
        listcnt--;
        score += 5;
        if (game != null) 
            game.addScore(5);
 
         
    }
 
    public void initiate() {
        image(this.bgImgs[this.color][this.bgstyle], 0, 0);
        image(this.boxImgs[this.color], 400, 30);
        image(this.newImgs[this.color], 400, 140);
        image(this.qaImgs[this.color], 400, 250);
        image(this.colorImgs[this.color], 400, 360);
        //System.out.println(this.color);
    }
 
    public void keyPressed() {
 
        if (keyCode == RIGHT) {
            smallRabbitWay = 2;
        } else if (keyCode == LEFT) {
            smallRabbitWay = 1;
        } else {
            smallRabbitWay = 0;
        }
 
    }
    public static int getCount(){
    	return count;
    }
     
    public int getState(){
        return this.state;
    }
     
    public int getScore(){
        return this.score;
    }
     
    public long getTime(){
        return this.prev_time;
    }
    
    public boolean getStartFlag(){
    	return this.start;
    }
 
    public void boxState() {
 
        if (game != null) {
            if (game.checkState() == 0) {
                if ((int) (System.currentTimeMillis() - prev_time) > angrytime) {
                    this.state = random.nextInt(3) + 1;
                    game.changeState(state);
                    angrytime = 1000 * 60 * 60 * random.nextInt(10);
                }
            } else {
                prev_time = System.currentTimeMillis();
            }
        }
    }
 
}