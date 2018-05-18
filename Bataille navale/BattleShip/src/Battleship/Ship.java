package Battleship;
import java.util.ArrayList;

public class Ship {

	private Coordinate startCoord;
	private Coordinate endCoord;
	private ArrayList<Coordinate> listCoordinatedShip = new ArrayList<Coordinate>();
	int touchedShip;

	//Getters et setters

	public Coordinate getStartCoord() {
		return startCoord;
	}

	public void setStartCoord(Coordinate startCoord) {
		this.startCoord = startCoord;
	}

	public Coordinate getEndCoord() {
		return endCoord;
	}

	public void setEndCoord(Coordinate endCoord) {
		this.endCoord = endCoord;
	}

	public ArrayList<Coordinate> getListCoordinatedShip() {
		return listCoordinatedShip;
	}

	public void setListCoordinatedShip(ArrayList<Coordinate> listCoordinatedShip) {
		this.listCoordinatedShip = listCoordinatedShip;
	}

	public int getTouchedShip() {
		return touchedShip;
	}

	public void setTouchedShip(int i) {
		this.touchedShip = i;
	}

	//Constructors

	public Ship(Coordinate startCoord, Coordinate endCoord) {
		this.startCoord = startCoord;
		this.endCoord = endCoord;
		this.listCoordinatedShip = coordinatedShip(startCoord, endCoord);
		this.touchedShip = shipSize(startCoord.getCoord(), endCoord.getCoord());
	}

	//Operators

	public static int charToInt(char valeur) {
		if ((int)valeur >= (int)'A' && (int)valeur <= (int)'Z') {
			return (int)valeur - (int)'A';
		} else {
			return (int)valeur - (int)'a';
		}
	}
		//un char est en fait un nombre qui contient le code ascii du caractère
		//on passe de 'A'=65 à 'A'=0, et donc de 'B'=66  à 'B'=1, etc...



	public int shipSize(String x, String y) {
		int shipSize = 0;
		char changeCoordX[] = x.toCharArray();
		int firstCoordX = charToInt(changeCoordX[0]);
		int secondCoordX = charToInt(changeCoordX[1]);
		char changeCoordY[] = y.toCharArray();
		int firstCoordY = charToInt(changeCoordY[0]);
		int secondCoordY = charToInt(changeCoordY[1]);
		if (firstCoordX != firstCoordY && secondCoordX != secondCoordY) {
			System.out.println("ERROR");
			return shipSize;
		} else {
			if (firstCoordX == firstCoordY && secondCoordX == secondCoordY) {
				System.out.println("ERROR");
				return shipSize;
			} else {
				if (firstCoordX == firstCoordY) {
					if (secondCoordX < secondCoordY) {
						shipSize = (secondCoordY - secondCoordX) + 1;
					}
					if (secondCoordX > secondCoordY) {
						shipSize = (secondCoordX - secondCoordY) + 1;
					}
				} else {
					if (firstCoordX < firstCoordY){
						shipSize = (firstCoordY - firstCoordX) + 1;
					}
					if (firstCoordX > firstCoordY) {
						shipSize = (firstCoordX - firstCoordY) + 1;
					}
				}
			return shipSize;
			}
		}
	}

	public ArrayList<Coordinate> coordinatedShip(Coordinate x, Coordinate y) {
		ArrayList<Coordinate> listCoordinatedShip = new ArrayList<Coordinate>();
		int shipSize = shipSize(x.getCoord(), y.getCoord());
		if (shipSize != 0) {
			String firstCoordX = x.getCoord().substring(0,1);
			String secondCoordX = x.getCoord().substring(1);
			String firstCoordY = y.getCoord().substring(0,1);
			String secondCoordY = y.getCoord().substring(1);
			listCoordinatedShip.add(x);
			char a = firstCoordX.charAt(0);
			char b = firstCoordY.charAt(0);
			char c = secondCoordX.charAt(0);
			char d = secondCoordY.charAt(0);
			int i = 0;
			int j = (shipSize - 2);
			if (a == b) {
				while (i != j) {
					if (c < d) {
						c++;
						String str = Character.toString(c);
						String mot = firstCoordX + str;
						Coordinate coord = new Coordinate(mot,false);
						listCoordinatedShip.add(coord);
						i++;
					} else {
						d++;
						String str = Character.toString(d);
						String mot = firstCoordX + str;
						Coordinate coord = new Coordinate(mot,false);
						listCoordinatedShip.add(coord);
						i++;
					}
				}
			} else {
				while (i != j) {
					if (a < b) {
						a++;
						String str = Character.toString(a);
						String mot = str + secondCoordX;
						Coordinate coord = new Coordinate(mot,false);
						listCoordinatedShip.add(coord);
						i++;
					} else {
						b++;
						String str = Character.toString(b);
						String mot = str + secondCoordX;
						Coordinate coord = new Coordinate(mot,false);
						listCoordinatedShip.add(coord);
						i++;
					}
				}

			}
			listCoordinatedShip.add(y);
		}
		return listCoordinatedShip;
	}
	

	public boolean isHit(Coordinate missileCoord) {
		boolean res = false;
		for (Coordinate coord : this.getListCoordinatedShip()) {
			if (coord.getCoord().equals(missileCoord.getCoord())) {
				res = true;
			}
		}
		return res;
	}




	public boolean isDestroyed() {
		boolean res = false;
		if (this.getTouchedShip() == 0) {
			res = true;
		}
    return res;
	}

}

