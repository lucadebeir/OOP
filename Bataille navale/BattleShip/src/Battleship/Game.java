package Battleship;

import java.util.Scanner;



public class Game {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
	
		System.out.println("Choississez le mode de jeu : ");
		System.out.println("      - 1 : Human VS Human");
		System.out.println("      - 2 : Human VS IA");
		System.out.println("      - 3 : IA VS IA");
		System.out.println();
		System.out.println("Votre choix : ");
		int choix = 0;
		boolean choixOk = false;
		while (choixOk == false) {
			try {
				choix = scan.nextInt();
				if (choix > 0 && choix < 4) {
					choixOk = true;
				}
			} catch (Exception e) {
				System.out.println("Entrez une coordonnée valide pour votre choix (1, 2 ou 3) !");
			}
		}
		if (choix == 1) {
			MainPlayer.main(null);
		}
		if (choix == 2) {
			MainIAVSHuman.main(null);
		}
		if (choix == 3) {
			MainIA.main(null);
		}
		scan.close();
		
	}
	

}
