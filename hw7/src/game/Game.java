package game;

import java.awt.Rectangle;
import javax.swing.JFrame;

public class Game extends JFrame{

	private Rectangle bounds = new Rectangle(800,530);
	private TopBar topBar;
	private GameScene gameScene;
	private EndPanel endPanel;
	private WinPanel winPanel;

	/**
	 * Constructor of a game 
	 */
	public Game(){
		super("Tappy Plane");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(bounds);
		this.setVisible(true);
	}
	
	/**
	 * Initialize what a game contains, including a top bar and a game scene
	 */
	public void start(){
		this.topBar = new TopBar(new Rectangle(bounds.width, 50));
		
		this.gameScene = new GameScene(this);
		this.gameScene.init();
		this.gameScene.start();
		
		this.add(topBar);
		this.add(gameScene);
		this.setVisible(true);
	}

	/**
	 * A method invoked when plane crashed
	 */
	public void gameOver() {
		this.remove(this.gameScene);
		this.gameScene.destroy();
		this.endPanel = new EndPanel();
		this.endPanel.init();
		this.endPanel.start();
		this.add(this.endPanel);
		this.setVisible(true);
	}

	/**
	 * A method invoked when the plane successfully fly pass 20 rocks
	 */
	public void win() {
		this.remove(this.gameScene);
		this.gameScene.destroy();
		this.winPanel = new WinPanel();
		this.winPanel.init();
		this.winPanel.start();
		this.add(this.winPanel);
		this.setVisible(true);
	}
}
