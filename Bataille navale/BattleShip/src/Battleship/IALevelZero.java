package Battleship;

import java.util.ArrayList;

public class IALevelZero extends IA implements IsPlayer {
	
	public IALevelZero(String nomJoueur, ArrayList<Ship> flotteShip, ArrayList<Coordinate> listAttack,
			ArrayList<String> listTouched, ArrayList<String> listAttack2, int scoreJoueur, ArrayList<String> listTouch,
			ArrayList<String> listNextMissile, boolean tirPrec) {
		super(nomJoueur, flotteShip, listAttack, listTouched, listAttack2, scoreJoueur, listTouch, listNextMissile, tirPrec);

	}

	@Override
	public Coordinate missileCoord(IsPlayer player) {
		String x = this.generateCoord();
		Coordinate coord = new Coordinate(x,false);
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
					if (ship.isDestroyed() == true) {
						resT = "COULÉ";
					}
				}
			}
		}
		return resT;
	}

}
