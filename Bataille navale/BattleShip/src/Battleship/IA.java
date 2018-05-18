package Battleship;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Player {

	private Random r = new Random();
	private ArrayList<String> listTouch;
	private ArrayList<String> listNextMissile;
	private boolean tirPrec;
	
	public IA(String nomJoueur, ArrayList<Ship> flotteShip, ArrayList<Coordinate> listAttack,
			ArrayList<String> listTouched, ArrayList<String> listAttack2, int scoreJoueur,
			ArrayList<String> listTouch, ArrayList<String> listNextMissile, boolean tirPrec) {
		super(nomJoueur, flotteShip, listAttack, listTouched, listAttack2, scoreJoueur);
		this.listTouch = listTouch;
		this.listNextMissile = listNextMissile;
		this.tirPrec = tirPrec;
	}
	
	public ArrayList<String> getListTouch() {
		return listTouch;
	}

	public void setListTouch(ArrayList<String> listTouch) {
		this.listTouch = listTouch;
	}

	public ArrayList<String> getListNextMissile() {
		return listNextMissile;
	}

	public void setListNextMissile(ArrayList<String> listNextMissile) {
		this.listNextMissile = listNextMissile;
	}

	public boolean isTirPrec() {
		return tirPrec;
	}

	public void setTirPrec(boolean tirPrec) {
		this.tirPrec = tirPrec;
	}

	public String generateLetter() {
		String chars = "ABCDEFGHIJ";
	    String pass = "";
	    int i = (int) Math.floor(Math.random() * 10);
	    pass += chars.charAt(i);
	    return pass;
	}
	
	public String generateCoord() {
		r = new Random();
		int n = r.nextInt(11);
		while (n == 0) {
			n = r.nextInt(11);
		}
		String x2 = Integer.toString(n);
		String x1 = this.generateLetter();
		String x = x1 + x2;
		return x;
	}
	
	public ArrayList<Coordinate> coordinated(Coordinate x, Coordinate y, int j) {
		ArrayList<Coordinate> listCoordinatedShip = new ArrayList<Coordinate>();
		String firstCoordX = x.getCoord().substring(0,1);
		String secondCoordX = x.getCoord().substring(1);
		String firstCoordY = y.getCoord().substring(0,1);
		listCoordinatedShip.add(x);
		char a = firstCoordX.charAt(0);
		char b = firstCoordY.charAt(0);
		int c = Integer.parseInt(secondCoordX);
		int i = 0;
		if (a == b) {
			while (i != j) {
				c++;
				String str = Integer.toString(c);
				String mot = firstCoordX + str;
				Coordinate coord = new Coordinate(mot,false);
				listCoordinatedShip.add(coord);
				i++;
			}
		} else {
			while (i != j) {
				a++;
				String str = Character.toString(a);
				String mot = str + secondCoordX;
				Coordinate coord = new Coordinate(mot,false);
				listCoordinatedShip.add(coord);
				i++;
			}

		}
		listCoordinatedShip.add(y);
		return listCoordinatedShip;
	}
	
	public ArrayList<String> generateList(int i, Coordinate c) {
		ArrayList<String> listPossible = new ArrayList<String>();
		Coordinate coord = new Coordinate(null,false);
		String firstCoord = c.getCoord().substring(0,1);
		String secondCoord = c.getCoord().substring(1);
		char a = firstCoord.charAt(0);
		int b = Integer.parseInt(secondCoord);
		int j = 0;
		while(i!=j) {
			a++;
			j++;
		}
		String str = Character.toString(a);
		a = firstCoord.charAt(0);
		String mot = str + secondCoord;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.allCoordinatedShip().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		j = 0;
		while(i!=j) {
			a--;
			j++;
		}
		str = Character.toString(a);
		mot = str + secondCoord;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.allCoordinatedShip().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		j = 0;
		while(i!=j) {
			b++;
			j++;
		}
		str = Integer.toString(b);
		b = Integer.parseInt(secondCoord);
		mot = firstCoord + str;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.allCoordinatedShip().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		j = 0;
		while(i!=j) {
			b--;
			j++;
		}
		str = Integer.toString(b);
		b = Integer.parseInt(secondCoord);
		mot = firstCoord + str;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.allCoordinatedShip().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		return listPossible;
	}
	
	public ArrayList<String> generateListAttackUn(Coordinate c) {
		ArrayList<String> listPossible = new ArrayList<String>();
		Coordinate coord = new Coordinate(null,false);
		String firstCoord = c.getCoord().substring(0,1);
		String secondCoord = c.getCoord().substring(1);
		char a = firstCoord.charAt(0);
		int b = Integer.parseInt(secondCoord);
		int j = 0;
		while(1!=j) {
			a++;
			j++;
		}
		String str = Character.toString(a);
		a = firstCoord.charAt(0);
		String mot = str + secondCoord;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.getListAttack2().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		j = 0;
		while(1!=j) {
			a--;
			j++;
		}
		str = Character.toString(a);
		mot = str + secondCoord;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.getListAttack2().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		j = 0;
		while(1!=j) {
			b++;
			j++;
		}
		str = Integer.toString(b);
		b = Integer.parseInt(secondCoord);
		mot = firstCoord + str;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.getListAttack2().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		j = 0;
		while(1!=j) {
			b--;
			j++;
		}
		str = Integer.toString(b);
		b = Integer.parseInt(secondCoord);
		mot = firstCoord + str;
		coord = new Coordinate(null,false);
		coord.setCoord(mot);
		if (coord.rentreDansGrilleAttack() == true && this.getListAttack2().contains(coord.getCoord()) == false) {
			listPossible.add(mot);
		}
		return listPossible;
	}

}
