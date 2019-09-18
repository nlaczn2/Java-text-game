//PROJECT 2
//NICOLE LACZNY
//UIN: 655989470
//NETID: NLACZN2

package nlaczn2_project2;

import java.util.ArrayList;
import java.util.Scanner;

public class Artifact {
	
	private String artifact_name;
	private String artifact_desc;
	private int place_id;
	private int value;
	private int artifact_id; 
	private int artifact_mobi; 
	private int keyPattern;
	
	private static int flagEnd = 0;
	
	static int get_flagEnd() {
		return flagEnd;
	}
	
	//Create the Artifact class. Each Artifact should contain a name, a description, a value, a mobility, and a keyPattern.
	public Artifact(String name, String description, int ID, int mobility, int keyPat) {
		artifact_name = name;
		artifact_desc = description;
		artifact_id = ID; 
		artifact_mobi = mobility; 
		keyPattern = keyPat;
	}
	
	//PARSE THROUGH FILE
	public Artifact(Scanner scFile) {
		
		if(scFile.hasNextLine() == false) {flagEnd = 1;}
		
		while(scFile.hasNextLine()) {			
			//BREAK STRING APART FROM BLANK LINES
			String contentMain = scFile.nextLine();
			String content = contentMain.trim();
			//REMOVE COMMENTS 	//IF NOT EMPTY SKIP
			String[] contentNoC = content.split("//");
			String text = contentNoC[0];
			
	//////////////////////////////////////////////////////////////////GET ID AND PLACE NAME//////////////////////
			if(text.length() > 0) {
				//REMOVE SPACES
				String spliter = "\\s";
				String[] tag = text.split(spliter);
				int i =0;
				String numb= "";
				i = 0;
				String nameRoom = "";
				int number = 0;
				int lengthD = 0;
				int flagID = 0;
				int flagLengthD = 0;
				int flagName = 0;
				while(i < tag.length) { //System.out.println("in tag while");
					boolean num = tag[i].matches("\\d+");
					boolean nameExist = tag[i].matches("\\D+");
						
					//GET PLACE ID
					if (num == true) {
						numb = tag[i];
						number = Integer.parseInt(numb);	//System.out.println(numb);	//System.out.println(p_id);
						flagID = 1; 
					}
						i++;
				}
				if(flagID == 1) {
					place_id = number;
					//System.out.print("PLACE ID: ");
					//System.out.println(place_id);
					
				}

	///////////////////////////////////////////GET Artifact info///////////
				contentMain = scFile.nextLine();
				content = contentMain.trim();
				//IF NOT EMPTY SKIP
				if(content.length() > 0) {
					//REMOVE COMMENTS
					contentNoC = content.split("//");
					text = contentNoC[0];
					//REMOVE SPACES
					spliter = "\\s";
					tag = text.split(spliter);
					i =0;
					numb= "";
					nameRoom = "";
					String artName = "";
					String dir = "";
					number = 0;
					int flagNeg = 0;
					flagID = 0;
					while(i < tag.length) { //System.out.println("in tag while"); //System.out.print("TAG::");	//					System.out.println(tag[i]);
						boolean num = tag[i].matches("\\d+");	//					System.out.println(num);
						boolean negNum = tag[i].matches("-\\d+");							//System.out.println(negNum);
						boolean hif = tag[i].contains("\\\\D+[-]\\\\D+");						//System.out.println(hif);
						boolean nameExist = tag[i].matches("\\D+");						//System.out.println(nameExist);
						//GET PLACE ID
						if (num == true) {
							numb = tag[i];							//String neg = tag[i];
							number = Integer.parseInt(numb);								//System.out.println(number);	
							flagID ++; 
						}
						if (negNum == true) {
							numb = tag[i];							//String neg = tag[i];
							number = Integer.parseInt(numb);								//System.out.println(number);	
							flagID ++; 
							flagNeg = 1;
						}
							//GET PLACE NAME 
						if(hif == true) {
							dir = dir + tag[i] + " ";								//System.out.println("in if");//System.out.println(dir);
							flagName = 1;
						}
						if (nameExist == true) {							//System.out.println("in if");
							dir = dir + tag[i] + " ";					//System.out.println("in if");	//System.out.println(dir);
							flagName = 1;
						}
						//System.out.print("FLAG::");	//System.out.println(flagID);
						if(flagID == 1) {
							artifact_id = number;
							//System.out.print("   ARTIFACT ID: ");
							//System.out.println (artifact_id);
						}
						if(flagID == 2) {
							//current_location = Place.get_place_list().get(number);
							value = number;
							//System.out.print("   VALUE: ");
							//System.out.println(value);
						}
						if(flagName == 1) {
							artifact_name = dir;
							//System.out.print("   Artifact name: ");
							//System.out.println(artifact_name);
						}
						if(flagID == 3) {
							artifact_mobi = number;
							//System.out.print("   Artifact MOBI: ");
							//System.out.println(artifact_mobi);
						}
						
						if(flagID == 4) {
							keyPattern = number;
							//System.out.print("   Key Pattern: ");
							//System.out.println(keyPattern);
						}
						i++;
					}//end while(i < tag.length)
				}
				
				flagID= 0;
///////////////////////////////////////////GET LENGTH OF DESCRIPTION///////////
				contentMain = scFile.nextLine();
				content = contentMain.trim();
				//IF NOT EMPTY SKIP
				if(content.length() > 0) {
					//REMOVE COMMENTS
					contentNoC = content.split("//");
					text = contentNoC[0];
					//REMOVE SPACES
					spliter = "\\s";
					tag = text.split(spliter);
					i =0;
					numb= "";
					nameRoom = "";
					number = 0;
					flagID = 0;
					while(i < tag.length) { //System.out.println("in tag while");
							boolean num = tag[i].matches("\\d+");		
							//GET decs length
							if (num == true) {
								numb = tag[i];
								number = Integer.parseInt(numb);	//System.out.println(numb);
								//System.out.println(p_id);
								flagID = 1; 
							}
							i++;
					}
				}
				int desCounter = 0;
				int flagD = 0;
				String wholeDesc = "";

	//////////////////////////////////////////////////////////////GET DESCRIPTION//////////////////////////			
			while(desCounter < number) {
					//System.out.println("IN WHILE DESCRIPTION");
				String contentDes = scFile.nextLine();
				String contentD = contentDes.trim();
				//System.out.println(contentD);
				//IF NOT EMPTY SKIP
				if(contentD.length() > 0) {
					//REMOVE COMMENTS
					String[] contentDNoC = contentD.split("//");
					String textD = contentDNoC[0];
					//REMOVE SPACES
					String spliterD = "\\s";
					String[] tagD = textD.split(spliterD);
					i =1;
					String sentenceD = "";
								
					while(i < tagD.length) {
						sentenceD = sentenceD + tagD[i] + " ";
						i++;
					}//System.out.println(" "); System.out.println("in places if"); System.out.println(sentence);//String numbD= "";
					i = 0;
					String nameDes = "";
					while(i < tagD.length) { //System.out.println("in tag while"); //boolean numD = tagD[i].matches("\\d+");
						boolean nameDExist = tagD[i].matches("\\D+");
						//GET PLACE NAME 
						if (nameDExist == true) {
							nameDes = nameDes + tagD[i] + " "; //System.out.println("nameDes: "); //System.out.println(nameDes);
							flagD = 1;
						}
							i++;
					}//end while(i < tag.length)
					
					if(flagD == 1) {
						wholeDesc = wholeDesc + nameDes + "\n ";		//System.out.print("DESCRIPTION: "); //System.out.println(wholeDesc);
					}
					flagD = 0;
				}//
				desCounter++;
				}//end while(desCounter < number) 
			
				if(wholeDesc != null) {
					artifact_desc = wholeDesc;
					Place temp = Place.getPlaceByID(place_id);
					temp.addArtifact(this);
				//System.out.println(artifact_desc);	//int lineCounter = 0;//while(lineCounter <= decLength) {
				break;
						}//exit place class
				
			}//end if(content.length() > 0)
			
		  }
		
	}
	
	//value( void ) : int – Returns the value of the artifact.
	public int vlaue() {
		return value;
	}
	
	//name( void ) : String – returns the name of the artifact.
	public String name() {
		//System.out.println(artifact_name);
		return artifact_name.trim();
	}
	
	//GET ROOM ITEM IS FOUND
	public int get_artifact_location() {
		return place_id;
	}
	
	//description( void ) : String – returns the description of the artifact
	public String description() {
		return artifact_desc.trim();
	}
	
	//ARTIFACT ID
	public int get_artifact_id() {
		return artifact_id;
	}
	
	//size( void ) : int, or weight( void ) : int – Returns the movability value.
	public int size() {
		return artifact_mobi;
	}
	
	//ARTIFACT KEY/PATTERN
	public int get_key() {
		return keyPattern;
	}
	
	//use( void ) : void – “Uses” the artifact. In the case of keys, this will involve getting the current place from the Game class, and then passing the artifact to the useKey( ) method of the current Place.
	public void use() {
		//
		System.out.println("used artifact/key");
		//
	}
	
	public void display() {
		System.out.println(artifact_desc);
	}
	public void display_artifact() {
		System.out.println(artifact_name);
	}
}
