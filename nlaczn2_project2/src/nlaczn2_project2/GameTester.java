//PROJECT 2
//NICOLE LACZNY
//UIN: 655989470
//NETID: NLACZN2

package nlaczn2_project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		System.out.println("Project 2 - Nicole Laczny - nlaczn2 - 655989470");
		System.out.println("");
		Scanner scFile = null;
		try {
			//scFile = new Scanner(new File("C:\\Users\\nflbu\\Desktop\\GameTesterProj2.Gdf"));
			scFile = new Scanner(new File("C:\\Users\\nflbu\\Desktop\\GameTesterProj2.Gdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		Game g = new Game(scFile);
		g.play();
		scFile.close();
	}

}
