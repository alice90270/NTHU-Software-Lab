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

/*初始化*/
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
	
	/*畫地圖到背景上*/
		image(worldMapImage, 0, 0, width, height);
		
	/*將所有地點畫到地圖上*/
		try {
			Location l = new Location();
		/*迴圈走訪各個國家*/
			for (String key : l.getHashMap().keySet()) {		
				String name=key;									//name為國家名稱
				PVector v=l.getHashMap().get(key); 					//v為地點座標
				drawCountry(name, v);								//畫出各國圓點
				getDistance(v);										//判斷滑鼠跟地點的距離
			
			/*滑鼠移動到地點感應半徑內*/
				if(getDistance(v)<3){								//滑鼠在半徑5以內
					savePoint(v);									//儲存當前位置的座標
					
				/*按下滑鼠時=>顯示移入的路線*/
					if(clicked){
					/*畫出移入路徑*/
						immigrantList = l.getImmigrant().get(name);	//取得從name移出至各國的國家清單
						for (String il:immigrantList) {				//走訪(emigrant)國家清單
							origin=v;								//取得出發地座標
							destination = l.getHashMap().get(il);	//取得目的地座標
							
						/*用分點做動畫，慢慢畫出兩者間的連線*/
							int d = (int)dist(origin.x, origin.y, destination.x, destination.y);//先取得兩點間的距離，使得n不大於它
							for(float n=d ;n>=1;n--){											//n=1時，畫出來的線等於d
								drawConnection(origin,destination,n);							//隨著n逐漸減小，畫的線越長	
							}
				        }
					/*印出國家名稱(紅紫)*/
						textAlign(CENTER);
						fill(158, 0, 79,225);
						text(name, v.x, v.y-10);					
					}
				
				/*滑鼠未按時=>顯示移出的路線*/
					else{
					/*畫出移出路徑*/
						emigrantList = l.getEmigrant().get(name);	//取得從name移出至各國的國家清單
						for (String el:emigrantList) {				//走訪(emigrant)國家清單
							origin=v;								//取得出發地座標
							destination = l.getHashMap().get(el);	//取得目的地座標
							
						/*用分點做動畫，慢慢畫出兩者間的連線*/
							int d = (int)dist(origin.x, origin.y, destination.x, destination.y);//先取得兩點間的距離，使得n不大於它
							for(float n=d ;n>=1;n--){											//n=1時，畫出來的線等於d
								drawConnection(origin,destination,n);							//隨著n逐漸減小，畫的線越長		
							}					
						}
					/*印出國家名稱(褐色)*/
						textAlign(CENTER);
						fill(102, 34, 0,225);
						text(name, v.x, v.y-10);		
					}
				}
				
			/*當滑鼠移到當前位置的感應範圍外=>將clicked重新設置*/
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
	
/*畫一個國家的圓點*/
	public void drawCountry(String name, PVector place){
		fill(0, 100, 150, 100);
		stroke(23, 114, 232, 150);
		ellipse(place.x, place.y, 8, 8);		
	}
	
/*畫一條source至target的路徑*/
	public void drawConnection(PVector source, PVector target, float d){
		if(clicked){									//按下滑鼠要顯示移入路徑
			stroke(255, 128, 206, 100);					//移入畫粉紅線
		}
		else stroke(255, 209, 5, 100);					//移出畫黃線
		
		//用變數d去找到路徑中的分點
		line(source.x, source.y, (float)source.x+(target.x-source.x)/d, (float)source.y+(target.y-source.y)/d);	
	}

/*檢查是否按下滑鼠*/
	public void mouseClicked(MouseEvent e){
		clicked=true;
	}
	
/*取得滑鼠與地點的距離*/
	public int getDistance(PVector v){
		return (int) dist(mouseX,mouseY,v.x,v.y);
	}
	
/*儲存當前位置*/
	public void savePoint(PVector v){
		point=v;		
	}

}
