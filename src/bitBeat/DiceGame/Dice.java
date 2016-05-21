package bitBeat.DiceGame;

import java.util.Random;

/**
 * Created by sure GM on 2016/5/21 18:31.
 */
public class Dice {
	//属性
	private int value1;		//1-6之间的随机数
	private int value2;
	private int value3;

	static Random rand = new Random();

	//在构造方法中，就直接把骰子初始化
	public Dice() {
		value1 = (Math.abs(rand.nextInt()))%6 + 1;
		value2 = (Math.abs(rand.nextInt()))%6 + 1;
		value3 = (Math.abs(rand.nextInt()))%6 + 1;
	}

	public int getValue1() {
		return value1;
	}

	public int getValue2() {
		return value2;
	}

	public int getValue3() {
		return value3;
	}

	public int getTotal() {
		return value1 + value2 + value3;
	}
}
