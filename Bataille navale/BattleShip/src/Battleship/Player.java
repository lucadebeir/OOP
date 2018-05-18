package Battleship;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements IsPlayer {
	
	static Scanner scan = new Scanner(System.in);

	private String nomJoueur;
	private ArrayList<Ship> flotteShip = new ArrayList<Ship>();
	private ArrayList<Coordinate> listAttack = new ArrayList<Coordinate>();
	private ArrayList<String> listAttack2 = new ArrayList<String>();
	private ArrayList<String> listTouched = new ArrayList<String>();
	private int scoreJoueur;

	//constructeur

	public Player(String nomJoueur, ArrayList<Ship> flotteShip, ArrayList<Coordinate> listAttack, ArrayList<String> listTouched, ArrayList<String> listAttack2, int scoreJoueur) {
		this.nomJoueur = nomJoueur;
	    this.flotteShip = flotteShip;
	    this.listAttack = listAttack;
	    this.listTouched = listTouched;
	    this.listAttack2 = listAttack2;
	    this.scoreJoueur = scoreJoueur;
	}

	//getters et setters

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public ArrayList<Coordinate> getListAttack() {
		return listAttack;
	}

	public void setListAttack(ArrayList<Coordinate> listAttack) {
		this.listAttack = listAttack;
	}

	public ArrayList<Ship> getFlotteShip() {
		return flotteShip;
	}

	public void setFlotteShip(ArrayList<Ship> flotteShip) {
		this.flotteShip = flotteShip;
	}
	
	public ArrayList<String> getListTouched() {
		return listTouched;
	}

	public void setListTouched(ArrayList<String> listTouched) {
		this.listTouched = listTouched;
	}

	public ArrayList<String> getListAttack2() {
		return listAttack2;
	}

	public void setListAttack2(ArrayList<String> listAttack2) {
		this.listAttack2 = listAttack2;
	}

	public int getScoreJoueur() {
		return scoreJoueur;
	}

	public void setScoreJoueur(int scoreJoueur) {
		this.scoreJoueur = scoreJoueur;
	}
	
	//methods
	
	public String lireTruc() {
        while(!scan.hasNext()) {
               scan.next();
               System.out.print("Le truc doit être un string...\nRecommencez : ");
        }
        return scan.next();
	}
	
	public boolean rentreDansGrille(Ship ship) {
		Coordinate x = ship.getStartCoord();
		Coordinate y = ship.getEndCoord();
		boolean resultat = true;
		int result1;
		int result2;
		String firstCoordX = x.getCoord().substring(0,1);
		String secondCoordX = x.getCoord().substring(1);
		String firstCoordY = y.getCoord().substring(0,1);
		String secondCoordY = y.getCoord().substring(1);
		boolean res = false;
		try {
			result1 = Integer.parseInt(secondCoordX);
			result2 = Integer.parseInt(secondCoordY);
			if (result1 > 0 && result2 > 0) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Entrez une coordonnée valide (ex : B3, B10..)");
		}
		if (res == true) {
			char a = firstCoordX.charAt(0);
			char b = firstCoordY.charAt(0);
			int e = Ship.charToInt(a);
			int f = Ship.charToInt(b);
			int g = Integer.parseInt(secondCoordX) - 1;
			int h = Integer.parseInt(secondCoordY) - 1;
			int shipSize = ship.shipSize(x.getCoord(), y.getCoord());
			for(Ship s : getFlotteShip()) {
				for(Coordinate c : s.coordinatedShip(s.getStartCoord(), s.getEndCoord())) {
					for(Coordinate coord : ship.coordinatedShip(ship.getStartCoord(), ship.getEndCoord())) {
						if (c.getCoord().equals(coord.getCoord()) && shipSize != 0 && e<76 && f<76 && g<10 && h<10) {
							resultat = false;
						}
					}
				}
			}
		}
		return resultat;
	}
	
	public boolean attackPossible(String missileCoord, Player player) {
		int result;
		boolean res = false;
		boolean resultat = false;
		String firstCoord = missileCoord.substring(0,1);
		String secondCoord = missileCoord.substring(1);
		try {
			result = Integer.parseInt(secondCoord);
			if (result > 0) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Entrez un tir valide (ex : B3, B10..)");
		}
		if (res == true) {
			char a = firstCoord.charAt(0);
			int c = Integer.parseInt(secondCoord) - 1;
			for(Ship ship : player.getFlotteShip()) {
				for(Coordinate coord : ship.getListCoordinatedShip()) {
					String coord1 = coord.getCoord().substring(0,1);
					char coordChar = coord1.charAt(0);
					String coord2 = coord.getCoord().substring(1);
					int coordInt = Integer.parseInt(coord2);
					if (coordChar == a && coordInt == c) {
						resultat = true;
					}
				}
			}
		}
		return resultat;
	}

	public void addAttack(Coordinate missileCoord, Player player) {
		if (attackPossible(missileCoord.getCoord(), player) == true) {
			if (alreadyTouched(missileCoord.getCoord()) == false) {
				listAttack = getListAttack();
				listAttack.add(missileCoord);
				setListAttack(listAttack);
			}
	    }
	  }
	  
	public void addShip(Ship ship) {
		if (rentreDansGrille(ship) == true) {
			ArrayList<Ship> s = this.getFlotteShip();
			s.add(ship);
			this.setFlotteShip(s);	
		}
	}
	
	public ArrayList<String> allCoordinatedShip() {
		ArrayList<String> allCoordinatedShip = new ArrayList<String>();
		for(Ship s : this.getFlotteShip()) {
			for (Coordinate c : s.getListCoordinatedShip()) {
				allCoordinatedShip.add(c.getCoord());
			}
		}
		return allCoordinatedShip;
	}

	public void getGridShip() {
		System.out.print("    ");
		for(char i = 'A'; i <= 'J'; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println();
		for(int j = 1; j <= 10; j++) {
			if (j < 10) {
				System.out.print(j + "   ");
			} else {
				System.out.print(j + "  ");
			}
			for(char i = 'A'; i <= 'J'; i++) {
				String mot1 = Character.toString(i);
				String mot2 = Integer.toString(j);
				String mot = mot1 + mot2;
				if (allCoordinatedShip().contains(mot)) {
					System.out.print("X" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void getGridAttack() {
		System.out.print("    ");
		for(char i = 'A'; i <= 'J'; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println();
		for(int j = 1; j <= 10; j++) {
			if (j < 10) {
				System.out.print(j + "   ");
			} else {
				System.out.print(j + "  ");
			}
			for(char i = 'A'; i <= 'J'; i++) {
				String mot1 = Character.toString(i);
				String mot2 = Integer.toString(j);
				String mot = mot1 + mot2;
				if (alreadyTouched(mot) == true && getListTouched().contains(mot) == true) {
					System.out.print("X" + " ");
				}
				if (alreadyTouched(mot) == true && getListTouched().contains(mot) == false) {
					System.out.print("O" + " ");
				}
				if (alreadyTouched(mot) == false && getListTouched().contains(mot) == false) {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	//specifies if the coordinate touches a boat
	public boolean coordPossible(String mot) {
		boolean res = true;
		for (String c : this.allCoordinatedShip()) {
			if (c.equals(mot)) {
				res = false;
			}
		}
		return res;
	}
	
	//specify if the coordinate has already been given
	public boolean alreadyTouched(String s) {
		boolean res = false;
		for (Coordinate c : this.getListAttack()) {
			if (s.equals(c.getCoord())) {
				res = true;
			}
		}
		return res;
	}

	@Override
	public Coordinate missileCoord(IsPlayer player) {
		Coordinate coord = new Coordinate(null,false);;
		System.out.println(getNomJoueur() + ", entres la coordonnée de votre missile : ");
		String missileCoord = lireTruc();
		if (alreadyTouched(missileCoord) == false) {
			coord.setCoord(missileCoord);
		} else {
			for (Coordinate c : getListAttack()) {
				if (c.getCoord().equals(missileCoord)) {
					coord = c;
				}
			}
		}
		System.out.println(" ");
		while(coord.rentreDansGrilleAttack() == false) {
			System.out.println("Redonnes une nouvelle coordonnée (soit tu as déjà tiré ici, soit c'est une mauvaise coordonnée) : ");
			System.out.println(" ");
			missileCoord = lireTruc();
			if (alreadyTouched(missileCoord) == false) {
				coord = new Coordinate(null,false);
				coord.setCoord(missileCoord);
			} else {
				for (Coordinate c : getListAttack()) {
					if (c.getCoord().equals(missileCoord)) {
						coord = c;
					}
				}
			}
		}
		return coord;
	}

	@Override
	public String resultTir(Coordinate coord) {
		String resT = "RATÉ";
		for(Ship ship : getFlotteShip()) {
			if (ship.isHit(coord) == true) {
				if (coord.isTouched() == true) {
					resT = "Déjà TOUCHÉ";
				} else {
					ship.setTouchedShip(ship.getTouchedShip() - 1);
					resT = "TOUCHÉ";
					coord.setTouched(true);
					if (ship.isDestroyed() == true) {
						resT = "COULÉ";
					}
				}
			}
		}
		return resT;
	}

}
