package bitBeat.QCC;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

				// 合并的单元格去合并
				cellMergedCancel(sheet);

				// 删除标记有删除线的行
				deleteStrikeRows(sheet);

				// 单元格内换行拆分
				cellNewlineCancel(sheet);

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
	 * 合并的单元格去合并
	 * @param sheet
	 */
	public static void cellMergedCancel(XSSFSheet sheet) {
		int numMerge = sheet.getNumMergedRegions();
		while (numMerge > 0) {
			CellRangeAddress region = sheet.getMergedRegion(numMerge - 1);
			if (null != region) {
				XSSFRow row = sheet.getRow(region.getFirstRow());
				XSSFCell cell = row.getCell(region.getFirstColumn());
				String value = Function.getStringNumberCellValue(cell);
				sheet.removeMergedRegion(numMerge - 1);
				for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
					cell = sheet.getRow(i).getCell(region.getFirstColumn());
					cell.setCellValue(value);
				}
			}
			numMerge = sheet.getNumMergedRegions();
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

	/**
	 * 单元格内换行拆分，形成多行，保证每行单元格内值唯一
	 * @param sheet
	 */
	public static void cellNewlineCancel(XSSFSheet sheet) {
		List<String> valueList = new ArrayList<>();

		int colIndex_IndiGrpID = 5;

		for (int numRow = 0; numRow < sheet.getLastRowNum(); numRow++) {
			if (0 == sheet.getRow(numRow).getPhysicalNumberOfCells()) {
				continue;
			} else {
				XSSFRow row = sheet.getRow(numRow);
				for (int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
					XSSFCell cell = (XSSFCell) row.getCell(numCell);
					String value = Function.getStringNumberCellValue(cell);

					int count = 0;
					for (int i = 0; i <= count; i++) {
						if (value.contains("\n")) {
							int index = value.indexOf("\n");
							valueList.add(value.substring(0, index));
							value = value.replace(valueList.get(count) + "\n", "");
						} else {
							valueList.add(value);
						}
					}

					// 插入行
					for (int j = 0; j < count; j++) {
						sheet.shiftRows(numRow + j + 1, sheet.getLastRowNum(), +1);
						sheet.createRow(numRow + j + 1);

						for (numCell = 0; numCell < row.getLastCellNum(); numCell++) {
							sheet.getRow(numRow + j + 1).createCell(numCell).setCellValue(row.getCell(numCell).getStringCellValue());
						}

						sheet.getRow(numRow + j + 1).getCell(colIndex_IndiGrpID).setCellValue(valueList.get(j + 1));
					}
					row.getCell(colIndex_IndiGrpID).setCellValue(valueList.get(0));
				}

			}
		}
	}

	public void createStyle() {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("createStyle");

		// 设置单元格字体格式
		XSSFRow rowFont = sheet.createRow(0);
		XSSFCell cellFont = rowFont.createCell(0);
		cellFont.setCellValue("Font Style");
		// 创建单元格样式对象
		XSSFCellStyle styleFont = (XSSFCellStyle) wb.createCellStyle();
		// 创建字体对象
		Font font = wb.createFont();
		font.setItalic(true);
		font.setColor(Font.COLOR_RED);
		font.setFontHeightInPoints((short)22);
		font.setFontName("XHei");
		font.setUnderline(Font.U_DOUBLE);   // 添加（Font.U_SINGLE单条下划线 / Font.U_DOUBLE双条下划线）
		font.setStrikeout(true);
		// 应用格式
		styleFont.setFont(font);
		cellFont.setCellStyle(styleFont);

		// 设置单元格边框格式
		XSSFRow rowBorder = sheet.createRow(1);
		XSSFCell cellBorder = rowBorder.createCell(1);
		cellBorder.setCellValue("Border Style");
		// 创建单元格样式对象
		XSSFCellStyle styleBorder = (XSSFCellStyle)wb.createCellStyle();
		// 设置单元格边框样式
		styleBorder.setBorderBottom(CellStyle.BORDER_HAIR);
		styleBorder.setBorderTop(CellStyle.BORDER_DASHED);
		styleBorder.setBorderLeft(CellStyle.BORDER_DOUBLE);
		styleBorder.setBorderRight(CellStyle.BORDER_THIN);
		// 设置单元格边框颜色
		styleBorder.setBottomBorderColor(new XSSFColor(Color.RED));
		styleBorder.setTopBorderColor(new XSSFColor(Color.GREEN));
		styleBorder.setLeftBorderColor(new XSSFColor(Color.BLUE));
		// 应用格式
		cellBorder.setCellStyle(styleBorder);

		// 设置单元格内容对齐方式
		XSSFRow rowAlign = sheet.createRow(2);
		XSSFCell cellAlign = rowAlign.createCell(2);
		cellBorder.setCellValue("Align Style");
		// 创建单元格样式对象
		XSSFCellStyle styleAlign = (XSSFCellStyle)wb.createCellStyle();
		// 设置单元格内容水平对其方式
		styleAlign.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格内容垂直对其方式
		styleAlign.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
		// 应用格式
		cellAlign.setCellStyle(styleAlign);


	}

}
