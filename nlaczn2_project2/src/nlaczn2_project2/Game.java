//PROJECT 2
//NICOLE LACZNY
//UIN: 655989470
//NETID: NLACZN2

package nlaczn2_project2;


import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private String game_name;
	private ArrayList<Place> place_list;
	private Place current_location;
	private int run_game = 1;
	private int startRoom ;
	private int started = 0;
	private ArrayList<Artifact> artifact_list;
	private ArrayList<Artifact> user_list;
	
	public Game(Scanner scFile) {
		int flagPlaces = 0;
		int flagDirection = 0;
		int flagArtifact = 0;
		place_list = new ArrayList<Place>();
		artifact_list = new ArrayList<Artifact>();
		
		Place NOWHERE = new Place(0,"NOWHERE","*NOWHERE: A dark room that leads to... nowhere.");
		Place EXIT = new Place(1,"Exit","*EXIT: You have left the building.");
		Place.get_Place_List().put(NOWHERE.get_room_id(), NOWHERE);
		Place.get_Place_List().put(EXIT.get_room_id(), EXIT);
		
		while(scFile.hasNextLine()) {
			//while(scFile.ha)
			String contentMain = scFile.nextLine();
			String content = contentMain.trim();
			if(content.length() > 0) {
				String[] contentNoC = content.split("//",2);
				String text = contentNoC[0];
				String spliter = "\\s";
				String[] tag = text.split(spliter);
				int i =0;
				String sentence = "";
				//System.out.println(sentence);
				//	System.out.println(sentence2);
				if(tag[0].equalsIgnoreCase("PLACES")) {
					flagPlaces = 1;
					//System.out.println("in places if");
					//for the scanner thing
				}
				//PARSE THROUGH THE PLACE DATA
				while(flagPlaces == 1) {
					Place p = new Place(scFile);
					Place.get_Place_List().put(p.get_room_id(), p); //Place p2=Place.get_place_list().get(12);//System.out.println(p2.description());
					if(started == 0) {
						startRoom = p.get_room_id();
						//System.out.println(startRoom);
						started = 1;
					}					
					if(Place.get_flagD() == 1) {
						//System.out.println("~~~~~~~~~~~in break flag places = 0");
						flagPlaces = 0;
						flagDirection = 1;
						break;
					}
				}
				//PARSE THROUGH THE DIRECTION DATA
				while(flagDirection == 1) {
					new Direction(scFile);
					if(Direction.get_flagA() == 1) {
						//System.out.println("~~~~~~~~~~~in break flag dir = 0");
						flagDirection = 0;
						flagArtifact = 1;
						break;
					}
				}
				//System.out.println("!!@!@!@!@!@!@!@!@!");
				//PARSE THROUGH THE ARTIFACT DATA
				while(flagArtifact == 1) {
					//System.out.println("###############################");
					Artifact a = new Artifact(scFile);
					int p_id = a.get_artifact_location();
					Place.getPlaceByID(p_id);
					if(Artifact.get_flagEnd() == 1) {
					//	flagDirection = 0;
						flagArtifact = 0;
					}
				}
				
			//System.out.println(content);
			
		}
		
		}
		//game_name = s;
		
	}
		
		
	public void addPlace(Place p) {
		place_list.add(p);		
	}
	public void print() {
		System.out.println(place_list.get(0));
	}
	
	public void play() {
		Scanner user_input = new Scanner(System.in);
		current_location = Place.getPlaceByID(startRoom);
		//current_location.display();
		user_list = new ArrayList<Artifact>();
		while(run_game == 1) {
			current_location.display();
			
			//current_location.get_Artifact_name(current_location);
			String input = user_input.nextLine().trim();
			String spliter = "[ ]+";
			String[] direction_requested = input.split(spliter);
			int room =  current_location.get_room_id();
			Artifact art;
			int counter = 1;
			String nameArt = "";
			while(counter < direction_requested.length) {
				nameArt = nameArt + direction_requested[counter]+ " ";
				counter++;
			}
			nameArt = nameArt.trim();
			//System.out.println(nameArt);
			if((input.equalsIgnoreCase("QUIT")) || (input.equalsIgnoreCase("EXIT"))) {
				run_game = 2;
				break;
			}
			else if(input.equalsIgnoreCase("LOOK")){
				
			}
			else if(direction_requested[0].equalsIgnoreCase("DROP")) {
				System.out.println("in DROP");
				art = current_location.get_Artifact(nameArt);
				current_location.remove_Artifact(art.name());
				//user_list.remove(art.name());
			}
			else if(direction_requested[0].equalsIgnoreCase("GET")) {
				System.out.println("in GET");
				art = current_location.get_Artifact(nameArt);
				if(art != null) {
					System.out.println("getting key");
					artifact_list.add(art);
					//user_list.(art.name());
					}
			}
			else if(direction_requested[0].equalsIgnoreCase("USE")) {
				System.out.println("in USE");
				art = current_location.get_Artifact(nameArt);
				if(art != null) {
					current_location.useKey(art);
					}
			}
			else if((input.equalsIgnoreCase("INVENTORY")) || (input.equalsIgnoreCase("INVE"))) {
				System.out.print("CURRENT INVENTORY: ");
				int x = 0;
				while(x < artifact_list.size()) {
					//System.out.print("in while ");
					System.out.print(artifact_list.get(x).name());
				x++;
				System.out.print(", ");
				}
				System.out.println("");
				System.out.println("");
				//current_location.get_Artifact_List();
				//System.out.print(user_list);
			}
			else if ((direction_requested[0].equalsIgnoreCase("GO"))){					
				current_location = current_location.followDirection(direction_requested[1]);
					if((current_location.get_room_id() == 1)) {
						System.out.println("YOU CHOSE TO EXIT");
						run_game = 2;
						break;
					}
					//
				}
			else {
				current_location = current_location.followDirection(direction_requested[0]);	
				if((current_location.get_room_id() == 1)) {
					System.out.println("YOU CHOSE TO EXIT");
					run_game = 2;
					break;
				}			
			}
		}
		System.out.println("GOODBYE");
		user_input.close();
}
}// end game class
