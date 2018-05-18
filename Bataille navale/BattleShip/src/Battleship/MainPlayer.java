package Battleship;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class MainPlayer {
	
	Random r = new Random();

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

	    //initialisation de la partie


		Player J1 = initJoueur();
		Player J2 = initJoueur();
		Player current = J2;
		Player enemy = J1;
		Player player;

	    //partie

		Coordinate coord = new Coordinate(null,false);
		ArrayList<String> listTouched = new ArrayList<String>();
		ArrayList<Coordinate> listAttack = new ArrayList<Coordinate>();

		String resT;
		
		boolean res = false;

		while (res == false) {
	    
			player = current;
			current = enemy;
			enemy = player;
			
			System.out.println(" ");
			coord = current.missileCoord(enemy);
			if (current.getListAttack2().contains(coord.getCoord()) && current.getListTouched().contains(coord.getCoord())) {
				coord.setTouched(true);
			}
			resT = enemy.resultTir(coord);
			System.out.println(resT);
			if (resT.equals("TOUCHÉ")) {
				current.setScoreJoueur(J1.getScoreJoueur() + 1);
				listTouched = current.getListTouched();
				listTouched.add(coord.getCoord());
				current.setListTouched(listTouched);
				coord.setTouched(true);
			}
			
			listAttack = current.getListAttack();
			listAttack.add(coord);
			current.setListAttack(listAttack);
			System.out.println("Score de " + J1.getNomJoueur() + " : " + J1.getScoreJoueur());
			System.out.println("Score de " + J2.getNomJoueur() + " : " + J2.getScoreJoueur());
			System.out.println(" ");
			System.out.println("Grille d'attaque de " + enemy.getNomJoueur() + " :");
			System.out.println(" ");
			System.out.println("Les coordonnées de bâteau touchées -> X");
			System.out.println("Les coordonnées de bâteau non touchées -> O");
			System.out.println(" ");
			enemy.getGridAttack();
			System.out.println(" ");
		}
			
		if (current.getScoreJoueur() == 17 || enemy.getScoreJoueur() == 17) {
			res = true;
		}
		
		if (current.getScoreJoueur() == 17) {
			System.out.println(current.getNomJoueur() + " a gagné !");
			System.out.println(" ");
		} else {
			System.out.println(enemy.getNomJoueur() + " a gagné !");
			System.out.println(" ");
		}
		
	}



	public static Player initJoueur() {
		int nbreShip = 0;
		System.out.println(" ");
		int scoreJoueur = 0;
		ArrayList<Ship> flotteJoueur = new ArrayList<Ship>();
		ArrayList<Coordinate> listAttack = new ArrayList<Coordinate>();
		ArrayList<String> listTouched = new ArrayList<String>();
		ArrayList<String> listAttack2 = new ArrayList<String>();

		System.out.println("Prénom du joueur : ");
		String nomJoueur = scan.next();
		
		Player joueur = new Player(nomJoueur, flotteJoueur, listAttack, listTouched, listAttack2, scoreJoueur);


		while (nbreShip<5) {
			boolean check;
			if (nbreShip == 0) {
				check = false;
				while (check == false) {
		        		System.out.println(" ");
		        		System.out.println("Places your first boat: the Destroyer (on 2 consecutive squares) !");
					System.out.println(" ");
					Ship a = initShip(joueur);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (joueur.rentreDansGrille(a) == true) {
						if (b == 2) {
							flotteJoueur.add(a);
							joueur.addShip(a);
							System.out.println(" ");
							joueur.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						} else {
							System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Destroyer !");
						}
					} else {
		    			System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Destroyer !");
					}
				}
			}
		    if (nbreShip == 1) {
		    		check = false;
		        while (check == false) {
					System.out.println(" ");
					System.out.println("Places your first boat: the Submarine (on 3 consecutive squares) !");
					System.out.println(" ");
					Ship a = initShip(joueur);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (joueur.rentreDansGrille(a) == true) {
						if (b == 3) {
							flotteJoueur.add(a);
							joueur.addShip(a);
							System.out.println(" ");
							joueur.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						} else {
							System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Submarine !");
						}
					} else {
						System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Submarine !");
					}
		        }
		    }
		    if (nbreShip == 2) {
		    		check = false;
		        while (check == false) {
					System.out.println(" ");
					System.out.println("Places your first boat: the Cruiser (on 3 consecutive squares) !");
					System.out.println(" ");
					Ship a = initShip(joueur);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (joueur.rentreDansGrille(a) == true) {
						if (b == 3) {
							flotteJoueur.add(a);
							joueur.addShip(a);
							System.out.println(" ");
							joueur.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						} else {
							System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Cruiser !");
						}
					} else {
						System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Cruiser !");
					}
		        }
		    }
		    if (nbreShip == 3) {
		    		check = false;
		        while (check == false) {
					System.out.println(" ");
					System.out.println("Places your first boat: the Battleship (on 4 consecutive squares) !");
					System.out.println(" ");
					Ship a = initShip(joueur);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (joueur.rentreDansGrille(a) == true) {
						if (b == 4) {
							flotteJoueur.add(a);
							joueur.addShip(a);
							System.out.println(" ");
							joueur.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						} else {
							System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Battleship !");
						}
					} else {
						System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Battleship !");
					}	
		        	}
		    	}
		    if (nbreShip == 4) {
		        check = false;
		        while (check == false) {
		        		System.out.println(" ");
		        		System.out.println("Places your first boat: the Carrier (on 5 consecutive squares) !");
					System.out.println(" ");
					Ship a = initShip(joueur);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (joueur.rentreDansGrille(a) == true) {
						if (b == 5) {
							flotteJoueur.add(a);
							joueur.addShip(a);
							System.out.println(" ");
							joueur.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						} else {
							System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Carrier !");
						}
					} else {
						System.out.println("Mauvaises coordonnées, redonnes deux nouvelles coordonnées pour le Carrier !");
					}
		        }
		    	}
		}

		return joueur;


	}

	
	public static Ship initShip(Player player) {
		System.out.println("Entrez la coordonnée de départ de votre bateau : ");
		String x = scan.next();
		Coordinate coord1 = new Coordinate(x,false);
		while(coord1.validCoordinate(x) == false) {
			System.out.println("Mauvaise coordonnée. Entrez une nouvelle coordonnée de départ de votre bateau : ");
			x = scan.next();
			coord1.setCoord(x);
		}
		System.out.println("Entrez la coordonnée de fin de votre bateau : ");
		String y = scan.next();
		Coordinate coord2 = new Coordinate(y,false);
		while(coord2.validCoordinate(y) == false) {
			System.out.println("Mauvaise coordonnée. Entrez une nouvelle coordonnée de départ de votre bateau : ");
			y = scan.next();
			coord2.setCoord(y);
		}
		Ship a = new Ship(coord1,coord2);
		return a;
	}

}
