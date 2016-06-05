package bitBeat;

import java.util.Arrays;

/**
 * Created by sure GM on 2016/6/5 15:28.
 */
public class BinarySearch {
	public static void main(String[] args) {
	    int[] arr = new int[]{1, 7, 3, 8, 2};
		System.out.println("以字符串的形式输出数组：" + Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("排序后的数组：" + Arrays.toString(arr));
		System.out.println("7的索引是：" + Arrays.binarySearch(arr, 7));  // 二分法查找索引
		Arrays.fill(arr, 0);
		System.out.println("填充后的数组：" + Arrays.toString(arr));
	}

	public int binarySearch_sure(int[] arr) {
		return 0;
	}
}
