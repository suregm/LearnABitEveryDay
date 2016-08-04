package bitBeat.QCC;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by sure GM on 2016/8/3 23:43.
 */
public class POI {

	public static void main (String[] args) throws Exception {
		File file = new File("D://yyzx.xlsx");
		InputStream is = new FileInputStream(file); // 获取输入流
//		Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010 都可以处理
		XSSFWorkbook wb = new XSSFWorkbook(is); // 2007格式XSSF，2003格式HSSF

		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
			if (0 == wb.getSheetAt(numSheet).getPhysicalNumberOfRows()) {
				continue;
			} else {
				XSSFSheet sheet = wb.getSheetAt(numSheet);

				// 删除标记有删除线的行
				deleteStrikeRows(sheet);

				for (int numRow = 0; numRow < sheet.getLastRowNum(); numRow++) {
					if (0 == sheet.getRow(numRow).getPhysicalNumberOfCells()) {
						continue;
					} else {
						XSSFRow row = sheet.getRow(numRow);
						for (int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
							XSSFCell cell = row.getCell(numCell);

						}
					}
				}
			}
		}
	}

	/**
	 * 删除标记有删除线的行
	 * @param sheet
	 */
	public static void deleteStrikeRows(XSSFSheet sheet) {
		for (int numRow = 0; numRow < sheet.getLastRowNum(); numRow++) {
			if (0 == sheet.getRow(numRow).getPhysicalNumberOfCells()) {
				continue;
			} else {
				XSSFRow row = sheet.getRow(numRow);
				for (int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
					XSSFCell cell = (XSSFCell) row.getCell(numCell);
					XSSFCellStyle style = cell.getCellStyle();
					XSSFFont font = style.getFont();
					boolean isStrikeout = font.getStrikeout();

					if (isStrikeout) {
						sheet.removeRow(row);
						sheet.shiftRows(numRow + 1, sheet.getLastRowNum(), -1);
						numRow--;   // 避免跳行
					}
				}
			}
		}
	}

}
