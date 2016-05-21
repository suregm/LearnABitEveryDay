package bitBeat.DiceGame;

/**
 * Created by sure GM on 2016/5/21 18:31.
 */

//玩掷骰子游戏类 封装
public class Game {
	//属性
	private Dice dice1;		//甲
	private Dice dice2;		//乙

	//构造方法
	public Game() {
		dice1 = new Dice();
		dice2 = new Dice();
	}

	//方法，根据两个人确定的比较规则判断输赢
	//flag：true表示比大，false表示比小
	public void play(boolean flag) {
		if (flag) {
			if(dice1.getTotal() > dice2.getTotal()) {
				System.out.println("甲赢，他的点数是: " +dice1.getTotal());
			}	else if(dice1.getTotal() < dice2.getTotal()) {
				System.out.println("乙赢，他的点数是: " +dice2.getTotal());
			}	else {
				System.out.println("平局");
			}
		}	else {
			if(dice1.getTotal() > dice2.getTotal()) {
				System.out.println("乙赢，他的点数是: " +dice2.getTotal());
			}	else if(dice1.getTotal() < dice2.getTotal()) {
				System.out.println("甲赢，他的点数是: " +dice1.getTotal());
			}	else {
				System.out.println("平局");
			}
		}
	}

	//重载
	public void play(int value) {
		if(Math.abs(dice1.getTotal() - value) > Math.abs(dice2.getTotal() - value)) {
			System.out.println("乙赢，他的点数是: " +dice2.getTotal());
		}	else if(Math.abs(dice1.getTotal() - value) < Math.abs(dice2.getTotal() - value)) {
			System.out.println("甲赢，他的点数是: " +dice1.getTotal());
		}	else {
			System.out.println("平局");
		}
	}
}
