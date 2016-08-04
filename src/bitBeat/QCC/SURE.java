package bitBeat.QCC;

/**
 * Created by sure GM on 2016/8/4 23:17.
 */
public class SURE {

	public static final String NAME = "(Sure GM)";
	public static final String HOBBIES = "(游泳)" + " | (骑行)" + " | (爬山)" +" | (PROGRAMMING)";

	/**
	 * 自定义contains方法，以验证基线字符串涵盖多种可能的情况
	 * @param ssTarget 目标字符串
	 * @param ssBase 基线字符串
	 *              格式为："(游泳) | (骑行) | (爬山) | (PROGRAMMING)"
	 * @return
	 */
	public static boolean contains(String ssTarget, String ssBase) {
		boolean contain = false;
		String[] sList = ssBase.replace(" ", "").split("|");
		for (String s : sList) {
			if (ssTarget.contains(s)) {
				contain = true;
				break;
			}
		}
		return contain;
	}
}
