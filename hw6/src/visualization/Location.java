package visualization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import processing.core.PVector;

public class Location {
	
	private HashMap<String, PVector> locationHashMap;
	private BufferedReader br;
	private MercatorMap mercatorMap;
	private PVector aMercator,bMercator;
	HashMap<String, ArrayList<String>>  emigrant;		//從某國移出
	HashMap<String, ArrayList<String>>  immigrant;		//移入某國
	Location() throws IOException{
		
		locationHashMap = new HashMap<String, PVector>();
		mercatorMap = new MercatorMap(991,770);	
		emigrant= new HashMap <String, ArrayList<String>> ();
		immigrant= new HashMap <String,ArrayList<String>> ();

		/*讀取csv檔*/
		br = new BufferedReader(new FileReader("src/res/MigrationData.csv"));

		String line = br.readLine();			//一次讀一行
		while ((line=br.readLine())!=null){		//直到讀完為止
			String[] ss= line.split(";");		//讀入的內容用";"切割，存到字串陣列裡
			/*
			 * ss[0]=移出的國家名稱		ss[1]=移出地點x座標		ss[2]=移出地點y座標
			 * ss[3]=移入的國家名稱		ss[4]=移入地點x座標		ss[5]=移入地點y座標
			 * ss[6]=移民人數
			 * 用Float.parseFloat將xy座標轉換成浮點數，才能使用PVector以代入麥卡托地圖轉換座標
			 * 
			 * */
			
		//如果地圖內沒有移出該地點的位置，轉換座標並加入新地點到HashMap中
			if(locationHashMap.containsKey(ss[0])==false){
					aMercator = mercatorMap.getScreenLocation(new PVector(Float.parseFloat(ss[1]), Float.parseFloat(ss[2])));
					locationHashMap.put(ss[0],aMercator);
			}			
		//如果地圖內沒有移入該地點的位置，轉換座標並加入新地點到HashMap中		
			if(locationHashMap.containsKey(ss[3])==false){
					bMercator = mercatorMap.getScreenLocation(new PVector(Float.parseFloat(ss[4]), Float.parseFloat(ss[5])));
					locationHashMap.put(ss[3],bMercator);
				}	
			
		//如果移出人數不為零，新增移入移出HashMap
			if(Integer.parseInt(ss[6])!=0){	
				buildEmigrant(ss[0],ss[3]);		//建立某國移出(emigrant)的HashMap
				buildImmigrant(ss[0],ss[3]);	//建立移入某國(immigrant)的HashMap	
			}	
			
		}
	}
	
/*建立移入(immigrant)的HashMap*/
	public void buildImmigrant(String a,String b){
		ArrayList<String> inCountry;				//各國家移入某國的各國列表
	
	//HashMapx沒有b
		if(immigrant.get(b)==null){
			inCountry = new ArrayList<String>();	//為b建構新的list
			inCountry.add(a);						//a加入空的list
			immigrant.put(b,inCountry);				//新的list放入HashMap
		}
		
	//HashMapx有b，但b的list中不含a
		else{
			if(!immigrant.get(b).contains(a)){												
				inCountry = immigrant.get(b);		//取得b的list存到inCountry
				inCountry.add(a);					//a加入list
				immigrant.put(b,inCountry);			//list放入HashMap
			}		
		}
		
	}
	
/*建立移出(emigrant)的HashMap*/
	public void buildEmigrant(String a,String b){
		ArrayList<String> outCountry;				//某國移出至各國家的各國列表

	//HashMapx沒有a
		if(emigrant.get(a)==null){
			outCountry = new ArrayList<String>();	//為a建構新的list
			outCountry.add(b);						//b加入空的list
			emigrant.put(a,outCountry);				//新的list放入HashMap
		}
		
	//HashMapx有a，但a的list中不含b	
		else{
			if(!emigrant.get(a).contains(b)){												
				outCountry = emigrant.get(a);		//取得a的list存到inCountry
				outCountry.add(b);					//b加入list
				emigrant.put(a,outCountry);			//list放入HashMap
			}		
		}
	}
	
/*取得<國家,座標>的地點HashMap*/
	public HashMap<String, PVector> getHashMap(){
		return locationHashMap;
	}
	
/*取得<國家,移出至各國的名稱列表>的移出HashMap*/
	public HashMap<String, ArrayList<String>>  getEmigrant(){
		return emigrant;
	}
	
/*取得<國家,移入自各國的名稱列表>的移入HashMap*/
	public HashMap<String, ArrayList<String>>  getImmigrant(){
		return immigrant;
	}
		
}
