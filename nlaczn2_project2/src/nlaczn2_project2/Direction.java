//PROJECT 2
//NICOLE LACZNY
//UIN: 655989470
//NETID: NLACZN2

package nlaczn2_project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//The Direction class represents a direction or path from one Place to another
public class Direction {
	
	private int place_id;
	private Place current_location;
	private Place to_place;
	private String place_dir;
	private int lock = 0; // lock is unlocked
	private int key_id; // the keyid needed to open lock
	
	private static int flagArtifact = 0;
	
	static int get_flagA() {
		return flagArtifact;
	}
	
	private dirType place_Dir;
	//Modify the Direction class to use an internal private enum class called dirType to keep track of what “kind” of direction each Direction item is.
	// a. Allowable values of the enumerated type are NONE, N, S, E, W, U, D, NE, NW, SE, SW, NNE, NNW, ENE, WNW, ESE, WSW, SSE, and SSW.
	// b. Each type should have two fields defined: an abbreviation such as N or NNW, and a text such as North or North-northwest.
	private enum dirType{
		NONE("NONE"), N("NORTH"), S("SOUTH"), E("EAST"), W("WEST"), U("UP"), D("DOWN"), NE("NORTHEAST"), NW("NORTHWEST"), SE("SOUTHEAST"), SW("SOUTHWEST"), NNE("NORTHNORTHEAST"), NNW("NORTHNORTHWEST"), ENE("EASTNORTHEAST"), WNW("WESTNORTHWEST"), ESE("EASTSOUTHEAST"), WSW("WESTSOUTHWEST"), SSE("SOUTHSOUTHEAST"), SSW("SOUTHSOUTHWEST"); 
		private String dirDirection;
		
		private dirType(String s) {
			dirDirection = s;
		}
		public String get_Name() {
			return this.name();
		}
		public String get_Dir() {
			return dirDirection;
		}
	}
	

	//check if Artifacts found
	
	//Direction( int ID, Place from, Place to, String dir )  A constructor for creating the Direction object. Note that all new Directions are by default unlocked.
	public Direction(Scanner scFile) {
		//place_id = ID;
		//current_location = from;
		//to_place = to;
		//place_dir = dir;
		//if (place_id > 0) {lock = 0;} // lock is unlocked
		
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
				
				if(tag[0].equalsIgnoreCase("ARTIFACTS")) {
					flagArtifact = 1;
					break;
				}
				int i =0;
				String numb= "";
				i = 0;
				String dir = "";
				int number = 0;
				//int lengthD = 0;
				int flagID = 0;
				int flagNeg = 0;
				//int flagLengthD = 0;
				int flagName = 0;
				String sentence = "";
				while(i < tag.length) {
					sentence = tag[i] + " ";
					i++;
				}
				
				i =0;	
				
//MOVE THROUGHT STRING ARRAY
				while(i < tag.length) { //System.out.println("in tag while");//System.out.print("      TAG::");	//System.out.println(tag[i]);
					boolean num = tag[i].matches("\\d+");
					boolean negNum = tag[i].contains("-");					//System.out.println(negNum);
					boolean nameExist = tag[i].matches("\\D+");
					
					//GET PLACE ID
					if (num == true) {
						numb = tag[i];
						//String neg = tag[i];
						number = Integer.parseInt(numb);		
						//System.out.println(number);	
						flagID ++; 
					}
					//GET NEGATIVE PLACE ID
					if(negNum == true) {
						numb = tag[i];
						//System.out.println(numb);
						number = Integer.parseInt(numb);	
						number = number * -1;						//System.out.println(negNum);
						//System.out.println(number);	
						flagNeg = 1;
						flagID ++;						//System.out.println(flagID);	//number = 0 - number;
					}
					//GET PLACE NAME 
					if (nameExist == true) {
						dir = dir + tag[i] + " ";							//System.out.println(dir);
						flagName = 1;
					}
					//SET PLACE ID
					if(flagID == 1) {
						place_id = number;
						//System.out.print("   PLACE ID: ");
						//System.out.println (place_id);
					}
					//SET FROM PLACEs
					if(flagID == 2) {
						current_location = Place.get_Place_List().get(number);
						//System.out.print("   FROM: ");
						//System.out.println (current_location.get_room_id()); 
					}
					//SET DIRECTION OF MOVE
					if(flagName == 1) {
						try {
							dir = dir.trim(); 
							if(dir.contains("-")) {dir.replace("-", "");}
							//System.out.println (dir );
							//place_Dir = dirType.N;
							place_Dir = dirType.valueOf(dir.toUpperCase());
							//place_Dir = dirType.valueOf(dirType.valueOf(dir.toUpperCase().replace("-", ""));
							
							} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//System.out.print("   DIRECTION: ");
						//System.out.println(place_Dir);
					}
					//SET TO PLACE
					if(flagID == 3) {
						to_place = Place.get_Place_List().get(number);
						//System.out.print("   TO: ");
						//System.out.println (to_place.get_room_id());
						if(flagNeg == 1) {
							//System.out.print("FLAG IS NEGATIVE:");
							//System.out.println(to_place.get_room_id());
							lock = 1;
							flagNeg = 0;
						}// door is locked
						if(number == 0) {
							lock = 1;
							to_place = Place.get_Place_List().get(-1);
						}
					}
					//SET KEY NUMBER NEEDED
					if(flagID == 4) {
						key_id = number;
						//break;
					}
						i++;
				}//end while(i < tag.length)
				//System.out.println(this);
				current_location.addDirection(this);
				
				break;
		
			}//end if(content.length() > 0)
			
		  }//end while loop
		
	}
		
	//match( String s ) : boolean Returns true if the String passed in matches that of the stored direction, dir. This method can be enhanced to allow any of N, n, NORTH, north, North, etc. to all match against the stored value of N, etc
//*
	public boolean match(String s) {
		//System.out.println(s);
		//System.out.println(place_dir);
		//String dir = simplifyDir(s);
		//System.out.println(place_Dir.get_Name());
		//System.out.println(place_Dir.get_Dir());
		if((s.equalsIgnoreCase(place_Dir.get_Name()))||(s.equalsIgnoreCase(place_Dir.get_Dir()))) {
			//System.out.println("its true");
			return true;
		}
		else {
			//System.out.println("its false");
			return false;
		}
	}
	
	//USE KEY TO UNLOCK DOOR
	public void useKey(Artifact a) {
		int keyPattern = a.get_key();
		a.use();
		if(key_id == keyPattern) {
			unlock();
		}
	}
	
	//lock( void ) : void Locks the Direction. 
	public void lock() {
		lock = 1;
	}
	
	//unlock( void ) : void Unlocks the Direction. 
	public void unlock() {
		lock = 0;
	}
	
	//isLocked( void ) : boolean Returns true if the Direction is locked, false otherwise.
	public boolean isLocked() {
		if(lock == 1) {return true;}
		else {return false;}
	}

	//follow(void ) : Place returns the to Place corresponding to this Direction if it is unlocked.  Otherwise it returns the from Place. 
	public Place follow() {
		//System.out.print("door is:");
		//System.out.println(lock);
		if(lock == 0) {return to_place;}
		else {return current_location;}
	}
	
	//print( ) : void Prints out all of the Direction information.  This is for debugging and testing purposes only, and will be removed later.
	public void print() {
		
	}
}
