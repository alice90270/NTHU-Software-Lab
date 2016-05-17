package visualization;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class MyApplet extends PApplet{
	
	private PImage worldMapImage;
	//private MercatorMap mercatorMap;
	private boolean clicked=false;
	private PVector point;

/*��l��*/
	public void setup(){
		smooth();
		/*
		 *  A safer way to read the target image, by using getPath().
		 *  Note that the path is different from "../res/WorldMap.jpeg".		 
		 *  Be careful of the parameters for MercatorMap constructor.
		 *  Need to be exactly the same size of the Map image. 
		 *  
		 *  */
		worldMapImage = loadImage(this.getClass().getResource("/res/WorldMap.jpeg").getPath());
		size(worldMapImage.width, worldMapImage.height);
	}

	
	public void draw(){
		ArrayList<String> emigrantList,immigrantList;
		PVector origin,destination;
	
	/*�e�a�Ϩ�I���W*/
		image(worldMapImage, 0, 0, width, height);
		
	/*�N�Ҧ��a�I�e��a�ϤW*/
		try {
			Location l = new Location();
		/*�j�騫�X�U�Ӱ�a*/
			for (String key : l.getHashMap().keySet()) {		
				String name=key;									//name����a�W��
				PVector v=l.getHashMap().get(key); 					//v���a�I�y��
				drawCountry(name, v);								//�e�X�U����I
				getDistance(v);										//�P�_�ƹ���a�I���Z��
			
			/*�ƹ����ʨ�a�I�P���b�|��*/
				if(getDistance(v)<3){								//�ƹ��b�b�|5�H��
					savePoint(v);									//�x�s��e��m���y��
					
				/*���U�ƹ���=>��ܲ��J�����u*/
					if(clicked){
					/*�e�X���J���|*/
						immigrantList = l.getImmigrant().get(name);	//���o�qname���X�ܦU�ꪺ��a�M��
						for (String il:immigrantList) {				//���X(emigrant)��a�M��
							origin=v;								//���o�X�o�a�y��
							destination = l.getHashMap().get(il);	//���o�ت��a�y��
							
						/*�Τ��I���ʵe�A�C�C�e�X��̶����s�u*/
							int d = (int)dist(origin.x, origin.y, destination.x, destination.y);//�����o���I�����Z���A�ϱon���j��
							for(float n=d ;n>=1;n--){											//n=1�ɡA�e�X�Ӫ��u����d
								drawConnection(origin,destination,n);							//�H��n�v����p�A�e���u�V��	
							}
				        }
					/*�L�X��a�W��(����)*/
						textAlign(CENTER);
						fill(158, 0, 79,225);
						text(name, v.x, v.y-10);					
					}
				
				/*�ƹ�������=>��ܲ��X�����u*/
					else{
					/*�e�X���X���|*/
						emigrantList = l.getEmigrant().get(name);	//���o�qname���X�ܦU�ꪺ��a�M��
						for (String el:emigrantList) {				//���X(emigrant)��a�M��
							origin=v;								//���o�X�o�a�y��
							destination = l.getHashMap().get(el);	//���o�ت��a�y��
							
						/*�Τ��I���ʵe�A�C�C�e�X��̶����s�u*/
							int d = (int)dist(origin.x, origin.y, destination.x, destination.y);//�����o���I�����Z���A�ϱon���j��
							for(float n=d ;n>=1;n--){											//n=1�ɡA�e�X�Ӫ��u����d
								drawConnection(origin,destination,n);							//�H��n�v����p�A�e���u�V��		
							}					
						}
					/*�L�X��a�W��(�Ŧ�)*/
						textAlign(CENTER);
						fill(102, 34, 0,225);
						text(name, v.x, v.y-10);		
					}
				}
				
			/*��ƹ������e��m���P���d��~=>�Nclicked���s�]�m*/
				if(point!=null && getDistance(point)>3){ 
					clicked=false;															
				}
			}		
		} 
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
/*�e�@�Ӱ�a�����I*/
	public void drawCountry(String name, PVector place){
		fill(0, 100, 150, 100);
		stroke(23, 114, 232, 150);
		ellipse(place.x, place.y, 8, 8);		
	}
	
/*�e�@��source��target�����|*/
	public void drawConnection(PVector source, PVector target, float d){
		if(clicked){									//���U�ƹ��n��ܲ��J���|
			stroke(255, 128, 206, 100);					//���J�e�����u
		}
		else stroke(255, 209, 5, 100);					//���X�e���u
		
		//���ܼ�d�h�����|�������I
		line(source.x, source.y, (float)source.x+(target.x-source.x)/d, (float)source.y+(target.y-source.y)/d);	
	}

/*�ˬd�O�_���U�ƹ�*/
	public void mouseClicked(MouseEvent e){
		clicked=true;
	}
	
/*���o�ƹ��P�a�I���Z��*/
	public int getDistance(PVector v){
		return (int) dist(mouseX,mouseY,v.x,v.y);
	}
	
/*�x�s��e��m*/
	public void savePoint(PVector v){
		point=v;		
	}

}
