package Battleship;

import java.util.ArrayList;
import java.util.Random;

public class IALevelTwo extends IA implements IsPlayer {
	
	private Random r = new Random();
	
	public IALevelTwo(String nomJoueur, ArrayList<Ship> flotteShip, ArrayList<Coordinate> listAttack,
			ArrayList<String> listTouched, ArrayList<String> listAttack2, int scoreJoueur, ArrayList<String> listTouch,
			ArrayList<String> listNextMissile, boolean tirPrec) {
		super(nomJoueur, flotteShip, listAttack, listTouched, listAttack2, scoreJoueur, listTouch, listNextMissile, tirPrec);
	}
	
	@Override
	public Coordinate missileCoord(IsPlayer player) {
		Coordinate coord = new Coordinate(null,false);
		ArrayList<String> listTouched = new ArrayList<String>();
		int n;
		boolean tirP = false;
		if (this.isTirPrec() == false || this.isTirPrec() == true && (this.getListNextMissile().isEmpty() == true)) {
			String x = this.generateCoord();
			while (this.getListAttack2().contains(x) == true) {
				x = this.generateCoord();
			}
			coord.setCoord(x);
			if (player.resultTir(coord).equals("TOUCHÉ")) {
				tirP = true;
				this.setListNextMissile(this.generateListAttackUn(coord));
			}
		}
		if (this.isTirPrec() == true && (this.getListNextMissile().isEmpty() == false)) {
			n = r.nextInt(this.getListNextMissile().size());
			String x = this.getListNextMissile().get(n);
			coord.setCoord(x);
			if (player.resultTir(coord).equals("TOUCHÉ") || player.resultTir(coord).equals("COULÉ")) {
				listTouched = this.getListTouched();
				listTouched.add(coord.getCoord());
				this.setListTouched(listTouched);
				if (player.resultTir(coord).equals("TOUCHÉ")) {
					tirP = true;
					this.setListNextMissile(this.generateListAttackUn(coord));
				} else {
					tirP = false;
					this.setListNextMissile(null);
				}
			} else {
				if (this.isTirPrec() == true && (this.getListNextMissile().isEmpty() == false)) {
					tirP = true;
					this.getListNextMissile().remove(coord.getCoord());
					this.setListNextMissile(this.getListNextMissile());
				}
			}
		}
		this.setTirPrec(tirP);
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
