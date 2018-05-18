package Battleship;

import java.util.ArrayList;
import java.util.Random;

public class LevelZeroVSLevelTwo {
	
	public static void main(String[] args) {
		
		int cout = 0;
		int cout1 = 0;
		int cout2 = 0;
		IA J1;
		IA J2;
		
		while (cout != 100) {
			
			J1 = initIALevelZero("IALevelTwo");
			J2 = initIALevelTwo("IALevelZero");

		    //partie

			Coordinate coord = new Coordinate(null,false);
			ArrayList<String> listTouched = new ArrayList<String>();
			ArrayList<Coordinate> listAttack = new ArrayList<Coordinate>();
			ArrayList<String> listAttack2 = new ArrayList<String>();

			String resT;
			
			boolean res = false;
			boolean tour = false;
			
			cout++;

			while (res == false) {
				coord = new Coordinate(null,false);
				if (tour == false) {
					tour = true;
					coord = J1.missileCoord(J2);
					if (J1.getListAttack2().contains(coord.getCoord()) && J1.getListTouched().contains(coord.getCoord())) {
						coord.setTouched(true);
					}
					System.out.println("Vous avez tiré en : " + coord.getCoord());
					System.out.println(" ");
					resT = J2.resultTir(coord);
					System.out.println(resT);
					if (resT.equals("TOUCHÉ") || resT.equals("COULÉ")) {
						J1.setScoreJoueur(J1.getScoreJoueur() + 1);
						listTouched = J1.getListTouched();
						listTouched.add(coord.getCoord());
						J1.setListTouched(listTouched);
						coord.setTouched(true);
					};
					listAttack = J1.getListAttack();
					listAttack.add(coord);
					J1.setListAttack(listAttack);
					listAttack2 = J1.getListAttack2();
					listAttack2.add(coord.getCoord());
					J1.setListAttack2(listAttack2);
					System.out.println("Score de " + J1.getNomJoueur() + " : " + J1.getScoreJoueur());
					System.out.println("Score de " + J2.getNomJoueur() + " : " + J2.getScoreJoueur());
					System.out.println(" ");
					System.out.println("Grille d'attaque de " + J2.getNomJoueur() + " :");
					System.out.println(" ");
					System.out.println("Les coordonnées de bâteau touchées -> X");
					System.out.println("Les coordonnées de bâteau non touchées -> O");
					System.out.println(" ");
					J2.getGridAttack();
					System.out.println(" ");
					if (J1.getScoreJoueur() == 17 || J2.getScoreJoueur() == 17) {
						res = true;
					}
				} else {
					tour = false;
					coord = J2.missileCoord(J1);
					if (J2.getListAttack2().contains(coord.getCoord()) && J2.getListTouched().contains(coord.getCoord())) {
						coord.setTouched(true);
					}
					System.out.println("Vous avez tiré en : " + coord.getCoord());
					System.out.println(" ");
					resT = J1.resultTir(coord);
					System.out.println(resT);
					if (resT.equals("TOUCHÉ") || resT.equals("COULÉ")) {
						J2.setScoreJoueur(J2.getScoreJoueur() + 1);
						listTouched = J2.getListTouched();
						listTouched.add(coord.getCoord());
						J2.setListTouched(listTouched);
						coord.setTouched(true);
					}
					listAttack = J2.getListAttack();
					listAttack.add(coord);
					J2.setListAttack(listAttack);
					listAttack2 = J2.getListAttack2();
					listAttack2.add(coord.getCoord());
					J2.setListAttack2(listAttack2);
					System.out.println("Score de " + J1.getNomJoueur() + " : " + J1.getScoreJoueur());
					System.out.println("Score de " + J2.getNomJoueur() + " : " + J2.getScoreJoueur());
					System.out.println(" ");
					System.out.println("Grille d'attaque de " + J1.getNomJoueur() + " :");
					System.out.println(" ");
					System.out.println("Les coordonnées de bâteau touchées -> X");
					System.out.println("Les coordonnées de bâteau non touchées -> O");
					System.out.println(" ");
					J1.getGridAttack();
					System.out.println(" ");
					if (J1.getScoreJoueur() == 17 || J2.getScoreJoueur() == 17) {
						res = true;
					}
				}
				
			}
				
			if (J1.getScoreJoueur() == 17) {
				System.out.println(J1.getNomJoueur() + " a gagné !");
				System.out.println(" ");
				System.out.println(J1.getListTouched());
				System.out.println(" ");
				cout1++;
			}
			if (J2.getScoreJoueur() == 17){
				System.out.println(J2.getNomJoueur() + " a gagné !");
				System.out.println(" ");
				System.out.println(J2.getListTouched());
				System.out.println(" ");
				cout2++;
			}
		}
		System.out.println(cout1);
		System.out.println(cout2);
		if (cout1 < cout2) {
			System.out.println("Le level Two de l'IA bat le level Zero !");
		} else {
			System.out.println("Le level Zero de l'IA bat le level Two !");
		}
	}
	
	public static IALevelZero initIALevelZero(String nomJoueur) {
		int nbreShip = 0;
		System.out.println(" ");
		int scoreIA = 0;
		ArrayList<Ship> flotteIA = new ArrayList<Ship>();
		ArrayList<Coordinate> listAttack = new ArrayList<Coordinate>();
		ArrayList<String> listTouched = new ArrayList<String>();
		ArrayList<String> listAttack2 = new ArrayList<String>();
		ArrayList<String> listTouch = new ArrayList<String>();
		ArrayList<String> listNextMissile = new ArrayList<String>();
		boolean tirPrec = false;
		
		IALevelZero IA0 = new IALevelZero(nomJoueur, flotteIA, listAttack, listTouched, listTouch, scoreIA, listAttack2, listNextMissile, tirPrec);

		while (nbreShip<5) {
			boolean check;
			if (nbreShip == 0) {
				check = false;
				while (check == false) {
					System.out.println(" ");
					Ship a = initShipIALevelZero(IA0, 1);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (IA0.rentreDansGrille(a) == true) {
						if (b == 2) {
							IA0.addShip(a);
							System.out.println(" ");
							IA0.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}
				}
			}
		    if (nbreShip == 1) {
		    		check = false;
		        while (check == false) {
		        	System.out.println(" ");
					Ship a = initShipIALevelZero(IA0, 2);
					System.out.println(a);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					System.out.println(b);
					System.out.println(IA0.rentreDansGrille(a));
					if (IA0.rentreDansGrille(a) == true) {
						if (b == 3) {
							IA0.addShip(a);
							System.out.println(" ");
							IA0.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}	
		        }
		    }
		    if (nbreShip == 2) {
		    		check = false;
		        while (check == false) {
		        		System.out.println(" ");
					Ship a = initShipIALevelZero(IA0, 2);
					System.out.println(a);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					System.out.println(b);
					if (IA0.rentreDansGrille(a) == true) {
						if (b == 3) {
							IA0.addShip(a);
							System.out.println(" ");
							IA0.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}
		        }
		    }
		    if (nbreShip == 3) {
		    		check = false;
		        while (check == false) {
		        		System.out.println(" ");
					Ship a = initShipIALevelZero(IA0, 3);
					System.out.println(a);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					System.out.println(b);
					if (IA0.rentreDansGrille(a) == true) {
						if (b == 4) {
							IA0.addShip(a);
							System.out.println(" ");
							IA0.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}	
		        	}
		    	}
		    if (nbreShip == 4) {
		        check = false;
		        while (check == false) {
		        		System.out.println(" ");
					Ship a = initShipIALevelZero(IA0, 4);
					System.out.println(a);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					System.out.println(b);
					if (IA0.rentreDansGrille(a) == true) {
						if (b == 5) {
							IA0.addShip(a);
							System.out.println(" ");
							IA0.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}
		        }
		    	}
		}

		return IA0;
	}
	
	public static Ship initShipIALevelZero(IALevelZero IA0, int size) {
		Random r = new Random();
		String x = IA0.generateCoord();
		while (IA0.allCoordinatedShip().contains(x) == true) {
			x = IA0.generateCoord();
		}
		
		Coordinate coord1 = new Coordinate(x,false);
		
		ArrayList<String> list = IA0.generateList(size, coord1);
		while (list.size() == 0) {
			x = IA0.generateCoord();
			while (IA0.allCoordinatedShip().contains(x) == true) {
				x = IA0.generateCoord();
			}
			coord1.setCoord(x);
			
			list = IA0.generateList(size, coord1);
		}
		System.out.println(x);
		System.out.println(list);
		int n = r.nextInt(list.size());
		String y = list.get(n);
		System.out.println(y);
		Coordinate coord2 = new Coordinate(y,false);
		
		Ship a = new Ship(coord1,coord2);
		return a;
	}
	
	public static IALevelTwo initIALevelTwo(String nomJoueur) {
		int nbreShip = 0;
		System.out.println(" ");
		int scoreIA = 0;
		ArrayList<Ship> flotteIA = new ArrayList<Ship>();
		ArrayList<Coordinate> listAttack = new ArrayList<Coordinate>();
		ArrayList<String> listTouched = new ArrayList<String>();
		ArrayList<String> listAttack2 = new ArrayList<String>();
		ArrayList<String> listTouch = new ArrayList<String>();
		ArrayList<String> listNextMissile = new ArrayList<String>();
		boolean tirPrec = false;
		
		
		IALevelTwo IA1 = new IALevelTwo(nomJoueur, flotteIA, listAttack, listTouched, listTouch, scoreIA, listAttack2, listNextMissile, tirPrec);


		while (nbreShip<5) {
			boolean check;
			if (nbreShip == 0) {
				check = false;
				while (check == false) {
					System.out.println(" ");
					Ship a = initShipIALevelTwo(IA1, 1);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (IA1.rentreDansGrille(a) == true) {
						if (b == 2) {
							IA1.addShip(a);
							System.out.println(" ");
							IA1.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}
				}
			}
		    if (nbreShip == 1) {
		    		check = false;
		        while (check == false) {
		        	System.out.println(" ");
					Ship a = initShipIALevelTwo(IA1, 2);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (IA1.rentreDansGrille(a) == true) {
						if (b == 3) {
							IA1.addShip(a);
							System.out.println(" ");
							IA1.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}	
		        }
		    }
		    if (nbreShip == 2) {
		    		check = false;
		        while (check == false) {
		        		System.out.println(" ");
					Ship a = initShipIALevelTwo(IA1, 2);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (IA1.rentreDansGrille(a) == true) {
						if (b == 3) {
							IA1.addShip(a);
							System.out.println(" ");
							IA1.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}
		        }
		    }
		    if (nbreShip == 3) {
		    		check = false;
		        while (check == false) {
		        		System.out.println(" ");
					Ship a = initShipIALevelTwo(IA1, 3);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (IA1.rentreDansGrille(a) == true) {
						if (b == 4) {
							IA1.addShip(a);
							System.out.println(" ");
							IA1.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}	
		        	}
		    	}
		    if (nbreShip == 4) {
		        check = false;
		        while (check == false) {
		        		System.out.println(" ");
					Ship a = initShipIALevelTwo(IA1, 4);
					int b = a.shipSize(a.getStartCoord().getCoord(), a.getEndCoord().getCoord());
					if (IA1.rentreDansGrille(a) == true) {
						if (b == 5) {
							IA1.addShip(a);
							System.out.println(" ");
							IA1.getGridShip();
							System.out.println(" ");
							nbreShip++;
							check = true;
						}
					}
		        }
		    	}
		}

		return IA1;
	}
	
	public static Ship initShipIALevelTwo(IALevelTwo IA1, int size) {
		Random r = new Random();
		String x = IA1.generateCoord();
		while (IA1.allCoordinatedShip().contains(x) == true) {
			x = IA1.generateCoord();
		}
		
		Coordinate coord1 = new Coordinate(x,false);
		
		ArrayList<String> list = IA1.generateList(size, coord1);
		while (list.size() == 0) {
			x = IA1.generateCoord();
			while (IA1.allCoordinatedShip().contains(x) == true) {
				x = IA1.generateCoord();
			}
			coord1.setCoord(x);
			
			list = IA1.generateList(size, coord1);
		}
		System.out.println(x);
		System.out.println(list);
		int n = r.nextInt(list.size());
		String y = list.get(n);
		System.out.println(y);
		Coordinate coord2 = new Coordinate(y,false);
		
		Ship a = new Ship(coord1,coord2);
		return a;
	}

}

