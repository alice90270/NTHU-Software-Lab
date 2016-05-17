package visualization;
import java.io.IOException;
import javax.swing.JFrame;

/*請從這裡開始執行*/
public class JFrameWithApplet {
	
	public static void main(String args[]) throws IOException{
		
		MyApplet myApplet = new MyApplet();
		myApplet.init();
		myApplet.start();
		
		JFrame myFrame = new JFrame("Assignment 7");
		myFrame.setContentPane(myApplet);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(991, 770);
		myFrame.setVisible(true);
		myFrame.setResizable(false);		//將Frame設定為不可改變大小
		
	}
	
}
