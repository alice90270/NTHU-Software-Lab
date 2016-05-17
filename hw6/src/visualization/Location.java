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
	HashMap<String, ArrayList<String>>  emigrant;		//�q�Y�겾�X
	HashMap<String, ArrayList<String>>  immigrant;		//���J�Y��
	Location() throws IOException{
		
		locationHashMap = new HashMap<String, PVector>();
		mercatorMap = new MercatorMap(991,770);	
		emigrant= new HashMap <String, ArrayList<String>> ();
		immigrant= new HashMap <String,ArrayList<String>> ();

		/*Ū��csv��*/
		br = new BufferedReader(new FileReader("src/res/MigrationData.csv"));

		String line = br.readLine();			//�@��Ū�@��
		while ((line=br.readLine())!=null){		//����Ū������
			String[] ss= line.split(";");		//Ū�J�����e��";"���ΡA�s��r��}�C��
			/*
			 * ss[0]=���X����a�W��		ss[1]=���X�a�Ix�y��		ss[2]=���X�a�Iy�y��
			 * ss[3]=���J����a�W��		ss[4]=���J�a�Ix�y��		ss[5]=���J�a�Iy�y��
			 * ss[6]=�����H��
			 * ��Float.parseFloat�Nxy�y���ഫ���B�I�ơA�~��ϥ�PVector�H�N�J���d���a���ഫ�y��
			 * 
			 * */
			
		//�p�G�a�Ϥ��S�����X�Ӧa�I����m�A�ഫ�y�Шå[�J�s�a�I��HashMap��
			if(locationHashMap.containsKey(ss[0])==false){
					aMercator = mercatorMap.getScreenLocation(new PVector(Float.parseFloat(ss[1]), Float.parseFloat(ss[2])));
					locationHashMap.put(ss[0],aMercator);
			}			
		//�p�G�a�Ϥ��S�����J�Ӧa�I����m�A�ഫ�y�Шå[�J�s�a�I��HashMap��		
			if(locationHashMap.containsKey(ss[3])==false){
					bMercator = mercatorMap.getScreenLocation(new PVector(Float.parseFloat(ss[4]), Float.parseFloat(ss[5])));
					locationHashMap.put(ss[3],bMercator);
				}	
			
		//�p�G���X�H�Ƥ����s�A�s�W���J���XHashMap
			if(Integer.parseInt(ss[6])!=0){	
				buildEmigrant(ss[0],ss[3]);		//�إ߬Y�겾�X(emigrant)��HashMap
				buildImmigrant(ss[0],ss[3]);	//�إ߲��J�Y��(immigrant)��HashMap	
			}	
			
		}
	}
	
/*�إ߲��J(immigrant)��HashMap*/
	public void buildImmigrant(String a,String b){
		ArrayList<String> inCountry;				//�U��a���J�Y�ꪺ�U��C��
	
	//HashMapx�S��b
		if(immigrant.get(b)==null){
			inCountry = new ArrayList<String>();	//��b�غc�s��list
			inCountry.add(a);						//a�[�J�Ū�list
			immigrant.put(b,inCountry);				//�s��list��JHashMap
		}
		
	//HashMapx��b�A��b��list�����ta
		else{
			if(!immigrant.get(b).contains(a)){												
				inCountry = immigrant.get(b);		//���ob��list�s��inCountry
				inCountry.add(a);					//a�[�Jlist
				immigrant.put(b,inCountry);			//list��JHashMap
			}		
		}
		
	}
	
/*�إ߲��X(emigrant)��HashMap*/
	public void buildEmigrant(String a,String b){
		ArrayList<String> outCountry;				//�Y�겾�X�ܦU��a���U��C��

	//HashMapx�S��a
		if(emigrant.get(a)==null){
			outCountry = new ArrayList<String>();	//��a�غc�s��list
			outCountry.add(b);						//b�[�J�Ū�list
			emigrant.put(a,outCountry);				//�s��list��JHashMap
		}
		
	//HashMapx��a�A��a��list�����tb	
		else{
			if(!emigrant.get(a).contains(b)){												
				outCountry = emigrant.get(a);		//���oa��list�s��inCountry
				outCountry.add(b);					//b�[�Jlist
				emigrant.put(a,outCountry);			//list��JHashMap
			}		
		}
	}
	
/*���o<��a,�y��>���a�IHashMap*/
	public HashMap<String, PVector> getHashMap(){
		return locationHashMap;
	}
	
/*���o<��a,���X�ܦU�ꪺ�W�٦C��>�����XHashMap*/
	public HashMap<String, ArrayList<String>>  getEmigrant(){
		return emigrant;
	}
	
/*���o<��a,���J�ۦU�ꪺ�W�٦C��>�����JHashMap*/
	public HashMap<String, ArrayList<String>>  getImmigrant(){
		return immigrant;
	}
		
}
