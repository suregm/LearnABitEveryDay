package bitBeat;

/**
 * Created by sure GM on 2016/5/21 20:01.
 * This programme is try to solve the vrp with the pso method.
 */
public class CVRP_PSO {
	private int k = 5;//stands for the number of cars;
	private static int m = 20;//stands for the number of coustomers;
	public int gen = 1000;
	private static int popsize = 30;
	private double c1 = 0.382;
	private double c2 = 0.618;
	private double w = 0.5;
	private double[] demand = {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 5, 4, 5, 6, 3, 4, 5, 6, 7, 1};
	private int capacity = 10;

	public static void main(String[] args) {
		CVRP_PSO aa = new CVRP_PSO();
		aa.c1 = 10;
		aa.CalFit(pop[1].chrom);
	}

	private class chromosome {
		int[] chrom = new int[m];
		long fitness;
		long objection;
		int[] pbest = new int[chrom.length];
	}

	private static chromosome[] pop = new chromosome[popsize];//注意这个数组的结构；

	public CVRP_PSO() {
		for (int i = 0; i < popsize; i++) {
			pop[i] = new chromosome();

			int[] number = new int[m];
			for (int j = 0; j < m; j++)
				number[j] = j;
			int temp = m;
			for (int j = 0; j < m; j++) {
				int r = (int) (Math.random() * temp);
				try {
					pop[i].chrom[j] = number[r];
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					System.out.println("维数超出");
				}
				number[r] = number[temp - 1];
				temp--;

			}
		}
		for (int i = 0; i < popsize; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(pop[i].chrom[j] + " ");
			System.out.println();
		}

	}

	private void CalFit(int[] chrom) {
		for (int i = 0; i < chrom.length; i++) {
			double sum = 0;
			sum += demand[chrom[i]];
			if ((sum < capacity) || (sum + demand[chrom[i + 1]] > capacity)) {
				System.out.println(i);
				sum = 0;
			}
		}
	}

}
