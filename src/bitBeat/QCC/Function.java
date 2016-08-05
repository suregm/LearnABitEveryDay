package bitBeat.QCC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sure GM on 2016/8/4 23:17.
 */
public class Function {

	/**
	 * 自定义contains方法，以验证基线字符串涵盖多种可能的情况
	 * 2016.08.04
	 * @param ssTarget 目标字符串
	 * @param ssBase 基线字符串
	 *               格式为："(游泳) | (骑行) | (爬山) | (PROGRAMMING)"
	 * @return
	 */
	public static boolean contains_GoMX(String ssTarget, String ssBase) {
		boolean result = false;
		try {
			String[] sList = ssBase.trim().split(" \\| ");
			for (String s : sList) {
				s = s.replace("(", "").replace(")", "");
				if (ssTarget.contains(s)) {
					result = true;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("请检查基线字符串格式是否正确，谢谢。");
		}
		return result;
	}




	/**
	 * 自定义contains方法，以验证基线字符串涵盖多种可能的情况
	 * 2015.11.23
	 * @param ssTarget 目标字符串
	 * @param ssBase 基线字符串
	 *               格式为："(游泳) | (骑行) | (爬山) | (PROGRAMMING)"
	 * @return
	 */
	public static boolean contains_Sure(String ssTarget, String ssBase) {
		int count = 0;
		List<String> sList = new ArrayList<String>();
		for (int i = 0; i <= count; i++) {
			try {
				if (ssBase.contains("|")) {
					int index = ssBase.indexOf("|");
					sList.add(ssBase.substring(1, index - 2));
					ssBase = ssBase.replace("(" + sList.get(i) + ")" + " | ", "");
					count++;
				} else {
					if (ssBase.contains("(")) {
						ssBase = ssBase.replace("(", "");
					}
					if (ssBase.contains(")")) {
						ssBase = ssBase.replace(")", "");
					}
					sList.add(ssBase);
				}
			} catch (Exception e) {
				System.out.println("请检查基线字符串格式是否正确，谢谢。");
			}
		}

		boolean result = false;
		for (int i = 0; i <= count; i++) {
			if (ssTarget == null || ssTarget.equals("")) {
				break;
			} else if (ssTarget.indexOf(sList.get(i).toString()) > -1) {
				result = true;
				break;
			} else {
				result = false;
				continue;
			}
		}
		return result;
	}
}
