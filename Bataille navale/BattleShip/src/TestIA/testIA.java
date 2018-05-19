package TestIA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class testIA {
	
	public static void main(String[] args) {
		
		int cout = 0;
		int scoreZ = 0;
		int scoreO = 0;
		int scoreZe = 0;
		int scoreT = 0;
		int scoreOne = 0;
		int scoreTwo = 0;
		
		for (cout = 0; cout < 100; cout++) {
			Battleship.LevelZeroVSLevelUn.main(null);
			scoreZ = scoreZ + Battleship.LevelZeroVSLevelUn.cout1;
			scoreO = scoreO + Battleship.LevelZeroVSLevelUn.cout2;
		}
				
		for (cout = 0; cout < 100; cout++) {
			Battleship.LevelZeroVSLevelTwo.main(null);
			scoreZe = scoreZe + Battleship.LevelZeroVSLevelTwo.cout1;
			scoreT = scoreT + Battleship.LevelZeroVSLevelTwo.cout2;
		}
				
		for (cout = 0; cout < 100; cout++) {
			Battleship.LevelOneVSLevelTwo.main(null);
			scoreOne = scoreOne + Battleship.LevelOneVSLevelTwo.cout1;
			scoreTwo = scoreTwo + Battleship.LevelOneVSLevelTwo.cout2;
		}
		
		FileWriter writer = null;
		try {
			//File f;
			writer = new FileWriter(new File("." + File.separator + "ai_proof.csv"));
			writer.write("AI Name1; Score1; AI Name2; Score2;");
			writer.append("\n");
			
			writer.write("AI LevelZero;" + scoreZ + ";AI LevelOne;" + scoreO + ";\nAI LevelZero;" + scoreZe
					+ ";AI LevelTwo;" + scoreT + ";\nAI LevelOne;" + scoreOne + ";AI LevelTwo;" + scoreTwo + ";");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
