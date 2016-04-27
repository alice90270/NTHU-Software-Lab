import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class Game extends JFrame{
	
	private Rectangle bounds = new Rectangle(505,720);
	private Box box;
	private int score;
	private int state;
	public Game(int score, int state){
		JFrame frame =new JFrame();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.state = state;
		this.setBounds(bounds);
		//this.setUndecorated(true);
		//this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setVisible(true);
		this.setResizable(false);
		this.score = score;
		
		
	}
	public void start(){
		this.box= new Box(this, this.score, this.state);
		this.box.init();
		this.box.start();
		this.add(box);
		this.setVisible(true);
	}

	public void exit() {
		System.exit(1);
	}
	public void addScore(int num) {
		box.addScore(num);
	}
	
	public void changeState(int state) {
		this.box.changeState(state);
	}
	public int checkState() {
		return this.box.checkState();
	}
	public int getScore() {
		return box.getScore();
	}
	
}
