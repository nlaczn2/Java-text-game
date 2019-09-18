//PROJECT 2
//NICOLE LACZNY
//UIN: 655989470
//NETID: NLACZN2

package nlaczn2_project2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Place {
	
	private int place_id;
	private String place_name;
	private ArrayList<Direction> direction_list;
	private String desc;
	
	private ArrayList<Artifact> artifact_list;
	private static HashMap<Integer,Place> place_list = new HashMap<Integer,Place>();
	private static int flagDirection = 0;
	
	static int get_flagD() {
		return flagDirection;
	}
	
	public Place(int ID, String name, String description) {
		place_id = ID;
		place_name = name;
		desc = description;
		direction_list = new ArrayList<Direction>();
	}
	
	//Place(int ID, String name, String description )  A constructor for creating the Place object. Each place ID must be unique within the Game. Names are typically short phrases ( Potions Lab ), and descriptions are typically multi-line descriptions of the Place. For now the descriptions will also indicate which directions are available from this place.
	public Place(Scanner scFile) {
		direction_list = new ArrayList<Direction>();
		artifact_list = new ArrayList<Artifact>();
		
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
			//String sentence = "";
			//System.out.println(sentence); //	System.out.println(sentence2);
			//for the scanner thing
			if(tag[0].equalsIgnoreCase("DIRECTIONS")) {
				flagDirection = 1;
				break;
			}
//				System.out.println(" ");
//				System.out.println("in DIR if");
//			//	System.out.println(sentence);
//				System.out.println(tag[0]);
//				System.out.print(tag[1]);
//				//System.out.println(contentNoC[0]);//System.out.println(content);
//			}	
//			if(tag[0].equalsIgnoreCase("ARTIFACTS")) {
//				System.out.println(" ");
//				System.out.println("in ARTI if");//System.out.println(sentence);
//				System.out.println(tag[0]);
//				System.out.print(tag[1]);
//				//System.out.println(contentNoC[0]);//System.out.println(content);
//			}
			//System.out.println(" "); System.out.println("in places if"); System.out.println(sentence);
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
					//GET PLACE NAME 
				if (nameExist == true) {
					nameRoom = nameRoom + tag[i] + " ";	//System.out.println(nameRoom);
					flagName = 1;
				}
					i++;
			}//end while(i < tag.length)
			if((flagID == 1 )&&(flagName == 1)) {
				place_id = number;
				place_name = nameRoom; //System.out.println("PLACE ID: ");
				//System.out.println(place_id);
				//System.out.println("PLACE NAME: ");
				//System.out.println(place_name);
			}

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
					//GET PLACE ID
					if (num == true) {
						numb = tag[i];
						number = Integer.parseInt(numb);	//System.out.println(numb);
						//System.out.println(p_id);
						flagID = 1; 
					}
					i++;
				}//end while(i < tag.length)
//				 if(flagID == 1) {
//					//System.out.println("NUMLINES: ");
//					//System.out.println(number);
//				}
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
					wholeDesc = wholeDesc + nameDes + "\n";		//System.out.print("DESCRIPTION: "); //System.out.println(wholeDesc);
				}
				flagD = 0;
			}//
			desCounter++;
			}//end while(desCounter < number) 
		
			if(wholeDesc != null) {desc = wholeDesc;
			//System.out.println(desc);	//int lineCounter = 0;//while(lineCounter <= decLength) {
			break;
					}//exit place class
			
		}//end if(content.length() > 0)
		
	  }//end while loop
		
	}//end place class
	
	//get hashmap
	static Map<Integer,Place> get_Place_List(){
		return place_list;
	}
	
	public static Place getPlaceByID(int p_id) {
		return place_list.get(p_id);
	}
	
	//name( void ) : String Returns the name.
	public String name() {
		return place_name;
	}
	
	//gets the id of the room
		public int get_room_id() {
			return place_id;
		}

	//description( void ) : String Returns the description.
	public String description() {
		return desc;
	}
	
	//addDirection( Direction ) : void adds a Direction object to this Places collection of Directions. 
	public void addDirection(Direction d) {
		direction_list.add(d);	
	}
	
	//addArtifact( Artifact ) : void – adds an Artifact object to this Place’s collection of Artifacts
	public void addArtifact(Artifact a) {
		artifact_list.add(a);
	//	Place
	}
	
	//DROP artifact – Inverse of GET, if the artifact is in the player’s inventory
		public void remove_Artifact(String s) {
			int x = 0;
			while(x < artifact_list.size()) {
				System.out.println(artifact_list.get(x).name().equalsIgnoreCase(s));
				if(artifact_list.get(x).name().equalsIgnoreCase(s) == true) {
					artifact_list.remove(s);
				}
			x++;
			}
		}
	
	//GET artifact – Will check to see if the named artifact is present and attainable, and if so,	will transfer it from the Place to the player’s inventory.
	public Artifact get_Artifact(String s) {
		int x = 0;
		while(x < artifact_list.size()) {
			//System.out.println(artifact_list.get(x).name().equalsIgnoreCase(s));
			String name = artifact_list.get(x).name();
			if(name.equalsIgnoreCase(s)) {
				return artifact_list.get(x);
			}
		x++;
		}
		return null;

	}
	//USE artifact – Call the use( ) method of the artifact. At this point the only usable artifact are those that have a non-zero keyPattern value.

	// useKey( Artifact ) : void – Passes the artifact to the useKey( ) method of all Directions presen in this Place.
		public void useKey(Artifact a) {
			int x = 0;
			while(x < direction_list.size()) {
				//System.out.println(artifact_list.get(x).useKey(a));	
				direction_list.get(x).useKey(a);
				x++;
			}
		}
	
	
	
	//INVENTORY or INVE – List the player’s inventory of artifacts, providing the value and 	“mobility” of each, but not keyValues. You may express mobility in any desired units, such	as pounds, kilograms, cubic inches, or a made-up unit of your own. Also report totals.
	public void get_Artifact_List() {
		int x = 0;
		while(x < artifact_list.size()) {
			System.out.println(artifact_list.get(x).name());
		x++;
		}
	}
	
	//followDirection( String ) : Place Checks to see if this place has a valid unlocked Direction corresponding to the String passed, and if so, returns the Place arrived at by following that Direction. Otherwise it simply returns itself, i.e. this.
	public Place followDirection(String s) {
		int i = 0;
		int flag = 1;
		int counter = 0;		//System.out.println(s);		//System.out.print("in follow direction function- ");		//System.out.println(direction_list.get(i).match(s));		//System.out.print("flag is:");		//System.out.println(flag);
			
		while(flag == 1 && counter < direction_list.size()) {
			//System.out.print("flag is:");
			//System.out.println(flag);
			if(direction_list.get(i).match(s)) {
				flag = 2;
				//System.out.print("flag is:");
				//System.out.println(flag);
				return direction_list.get(i).follow();
				}	
			i++;
			counter++;
		}
		System.out.print("Can't go that way");
		//System.out.println(direction_list.get(i).follow().get_room_id()); 
		return this;
	}

	//print( ) : void  Prints out all of the Place information. This is for debugging and testing purposes only, and will be removed later.
	public void print() {
		
	}
	
	// print out the stored description of the current Place. 
	public void display() {
		System.out.println(desc);
		//System.out.println(artifact_list.size());
		int x = 0;
		while(x < artifact_list.size()) {
			System.out.println(artifact_list.get(x).name());
			System.out.println(artifact_list.get(x).description());
			x++;
		}
		//for(Artifact a: artifact_list){
		//	System.out.println(a.name());
		//	System.out.println(a.description());
		//}

	}

	
}
