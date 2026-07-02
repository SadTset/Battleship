package ch.bbw.m319.battleship;
import ch.bbw.m319.battleship.api.BattleshipArena;
import ch.bbw.m319.battleship.api.BattleshipField;
import ch.bbw.m319.battleship.api.BattleshipPlayer;
import ch.bbw.m319.battleship.api.ShipPosition;
import java.util.Random;
import java.util.ArrayList;

public class TsetPlayer implements BattleshipPlayer{
	
private final String name;

	public TsetPlayer(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		// let it play against itself
		BattleshipArena.playOnce(new TsetPlayer("player1"), new TsetPlayer("player2"));
	}


	public ShipPosition getRandomShipPosition() {

		var allPositions = new ArrayList<ShipPosition>();
		var fields = BattleshipField.values();
		for (int i = 0; i < fields.length; i++) {
			if (i % 3 < 2) allPositions.add(new ShipPosition(fields[i], fields[i + 1]));
    		if (i / 3 < 2) allPositions.add(new ShipPosition(fields[i], fields[i + 3]));
}
return allPositions.get(new Random().nextInt(allPositions.size()));
}
	
private void print(String msg) {
		System.out.print(name + ": " + msg);
	}

@Override
public ShipPosition placeYourShip() {
    return getRandomShipPosition();
}

@Override
public BattleshipField takeAim() {
	print("What's your target? ");
	try { Thread.sleep(1000); } catch (InterruptedException e) {}
    return BattleshipField.values()[new Random().nextInt(BattleshipField.values().length)];
	
}

@Override 
public void outcomeOfYourTurn(BattleshipField targetedField, boolean isHit) {
	System.out.println("Your shot at " + targetedField + " did " + (isHit ? "HIT!" : "miss..."));
}
@Override
	public void gameFinished(ShipPosition ship, boolean youHaveWon) {
		print(youHaveWon ? "WINNER! " : "LOSER! ");
	}

}