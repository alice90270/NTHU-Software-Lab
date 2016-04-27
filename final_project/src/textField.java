import java.awt.*; 
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
 
import javax.swing.*;
 
import processing.core.PImage;
 
/** Test JTextField, JPasswordField, JFormattedTextField, JTextArea */
@SuppressWarnings("serial")
public class textField extends JFrame implements Runnable{
 
    // Private variables of the GUI components
    private List list;
    private Rectangle bounds = new Rectangle(505, 725);
    private JTextField[] e = new JTextField[17];
    private JPanel[] Panel = new JPanel[17];
    private Font font;
    private Thread gameThread=null;
    private int list_index=0;
    private ArrayList<String> buffer = new ArrayList<String>();
     
    private PrintStream fileStream;
	private boolean onceflag = false;
     
     
    public textField() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(this.getClass().getResource("/res/earfong.ttc").getPath())).deriveFont(25f);
             GraphicsEnvironment ge = 
                 GraphicsEnvironment.getLocalGraphicsEnvironment();
             ge.registerFont(font);
        } catch (IOException|FontFormatException e) {
             //Handle exception
        }
         
        this.setBounds(bounds);
        this.setLayout(null);
        this.setResizable(false);
 
        this.list = new List(this);
        this.list.init();
        this.list.start();
         
        for(int i = 0; i < 13;i++){
            this.Panel[i] = new JPanel();
            this.add(Panel[i]);
            this.e[i] = new JTextField();
        }
        this.add(list);
         
         
         
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                save();
                System.exit(0);
            }
        });
         
      
        if(List.getCount()<10)	textfieldremove();
        
    }
     
    public void save(){
        try {
            this.fileStream = new PrintStream(new File("src/res/data.txt"));
            this.fileStream.println(this.list.getState());
            this.fileStream.println(this.list.getScore());
            this.fileStream.println(this.list.getTime());
            for(int i = 0;i<this.list_index;i++){
                if(!e[i].getText().equals(""))
                    this.fileStream.println(e[i].getText());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void textfieldremove() {
        for(int i = 0; i < 13; i++)
            this.Panel[i].setVisible(false);
    }
    public void textfieldadd() {
        for(int i = 0;i < list_index; i++)
            this.Panel[i].setVisible(true);
         
         
    }
    public void InitList(){
        for(int i = 0; i < 13;i++){
            this.Panel[i].setLayout(new BorderLayout());
            this.Panel[i].setBounds(78,32+37*i,307,25);
            this.Panel[i].add(e[i]);
            this.Panel[i].setVisible(false);
            this.Panel[i].setOpaque(false);
             
            e[i].setFont(font);
            e[i].setBorder(BorderFactory.createEmptyBorder());
            //e[i].setLineWrap(true);
            //e[i].setWrapStyleWord(true);
            //e[i].setOpaque(false);
            e[i].setOpaque(true);
             
            e[i].setBackground(new Color(211,255,250));
            e[i].setEnabled(false);
             
        }
         
        for(String buf : buffer) {
            e[this.list_index].setText(buf);
            e[this.list_index].setEnabled(true);
            //this.Panel[this.list_index].setVisible(true);
            if(this.list_index < 13)
                this.list_index++;
        }
    }
    public void addFileList(String buf) {
        this.buffer.add(buf);
    }
     
    public void addList(){
    	this.Panel[this.list_index].setVisible(true);
        e[this.list_index].setEnabled(this.list.get_makenewlist());
        e[this.list_index].requestFocus();
       
        this.list.set_makenewlist(false);
        if(this.list_index<13)
            this.list_index++;
    }
     
    public void removeList(int index){
        e[index].setText("");
         
        for(int i = index;i<(13-1);i++){
            e[i].setText(e[i+1].getText());
        }
        this.Panel[this.list_index-1].setVisible(false);
        e[list_index-1].setText("");
        e[list_index-1].setEnabled(false);
        this.list_index--;
        this.list.set_removelist(false);
     
         
    }
 
    public void start() {
        if(this.gameThread==null)
            this.gameThread= new Thread(this);
        this.gameThread.start();
        this.setVisible(true);
    }
     
 
 
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(Thread.currentThread()==this.gameThread){
             
            //System.out.println(this.list.get_makenewlist());
            if (this.list.get_makenewlist()) {
                this.addList();
            }
            if(this.list.removelist()){
                this.removeList(this.list.get_removeIndex());
            }
            
			if(this.list.getStartFlag()&&!onceflag ){
            	this.textfieldadd();
            	onceflag=true;
            }
                         
        }
    }
     
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable(){
 
            @Override
            public void run() {
                textField field = new textField();
                field.start();
                field.InitList();       
            }   
        });
    }
     
 
}