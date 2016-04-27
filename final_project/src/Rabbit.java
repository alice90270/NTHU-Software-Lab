import java.util.Random;
 
import processing.core.PApplet;
import processing.core.PImage;
 
public class Rabbit {
     
    private PApplet parent;
    private PImage[] rabbitimages;
    private PImage[] actions;
    private int x, y, w, h;
    private int frame;
    public int time;
     
    public Rabbit(PApplet parent, PImage[] rabbitimages) {
        this.parent = parent;
        this.rabbitimages = rabbitimages;
        this.x = 100;
        this.y = 170;
        this.w = rabbitimages[0].width;
        this.h = rabbitimages[0].height;
        this.frame = 0;
    }
 
    public void display(int state) {
        this.frame = 0;
        this.parent.image(rabbitimages[state], x, y, w, h);
    }
     
    public void Action(PImage[] rabbitaction) {
        this.frame++;
        this.frame %= rabbitaction.length;
        this.parent.image(rabbitaction[frame], x, y, w, h);
         
    }
     
     
     
}