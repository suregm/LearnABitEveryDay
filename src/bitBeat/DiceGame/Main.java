package bitBeat.DiceGame;

import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/21 18:32.
 */
public class Main {
	public static void main(String [] args) {
		Game diceGame = new Game();
		diceGame.play(true); 		//flag true, 大赢
		diceGame.play(false);		//flag false,小赢
		diceGame.play(12);
		Scanner s = new Scanner(System.in);
		System.out.println(s.nextInt());
	}
}
