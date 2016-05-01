package game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import processing.core.PApplet;

public class TopBar extends JPanel {
	
	JLabel label;
	private int pass;
	private float height=5000;
	
	public TopBar(Rectangle bounds){
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBounds(bounds);
		this.setBackground(new Color(63,90,108));
		this.initComponents();
		this.setVisible(true);
		this.requestFocus();
	
	}
	
	/**
	 * A method for initializing what top bar contains.
	 */
	private void initComponents(){
		this.label = new JLabel();
		this.setBar(pass,height);
		this.label.setFont(new Font("Arial", 0, 30));
		this.label.setForeground(new Color(222,217,214));
		this.add(label);
	}
	
	public void setBar(int pass, float height){
		this.pass=pass;
		this.height=height;
		label.setText("Rock count: "+ this.pass +", Height: " + this.height+"m");
		this.label.setFont(new Font("Arial", 0, 30));
		this.label.setForeground(new Color(222,217,214));
		
		//System.out.println("Rock count: "+this.pass+", Height: "+this.height+"m");
	
	}
	
}
