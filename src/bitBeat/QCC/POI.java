package bitBeat.QCC;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
							String cellValue = Function.getStringNumberCellValue(cell);
						}
					}
				}
			}
		}

		FileOutputStream os = new FileOutputStream("D://yyzx.xlsx");
		wb.write(os);
		os.close();
	}

	/**
	 * 返回构造列标题的列索引
	 * @param sheet
	 * @return
	 */
	public static ArrayList<Integer> HeaderList(XSSFSheet sheet) {
		ArrayList<Integer> headerList = new ArrayList<Integer>();
		Record record = new Record();
		String[] recordArray = new String[]{record.getColHeaderName0(), record.getColHeaderName1(), record.getColHeaderName2(), record.getColHeaderName3(), record.getColHeaderName4()};
		int colIndex = -1;

		XSSFRow row = sheet.getRow(0);
		for (String recordItem : recordArray) {
			for (int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
				if (Function.contains_GoMX(Function.getStringNumberCellValue(row.getCell(numCell)), recordItem) {
					colIndex = numCell;
					break;
				}
			}
		}

		return headerList;
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
	 * 单元格向下填充
	 * @param sheet
	 */
	public static void cellFillDown(XSSFSheet sheet) {
		for (int i = 0; i < sheet.getco)
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

	/**
	 * 设置单元格格式
	 * @param wb
	 */
	public void createStyle(XSSFWorkbook wb) {
		XSSFSheet sheet = wb.createSheet("Create Cell Style");

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

		// 设置单元格自动换行格式
		XSSFRow rowWrap = sheet.createRow(1);
		XSSFCell cellWrap = rowFont.createCell(1);
		cellWrap.setCellValue("Wrap Style");
		// 创建单元格样式对象
		XSSFCellStyle styleWrap = (XSSFCellStyle)wb.createCellStyle();
		styleWrap.setWrapText(true);
		cellWrap.setCellStyle(styleWrap);

		// 设置单元格边框格式
		XSSFRow rowBorder = sheet.createRow(2);
		XSSFCell cellBorder = rowBorder.createCell(2);
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

		// 设置单元格的高度和宽度
		XSSFRow rowSize = sheet.createRow(3);
		rowSize.setHeightInPoints(26);  // 设置行高
		XSSFCell cellSize = rowSize.createCell(3);
		cellSize.setCellValue("Size Style");
		String cellSizeValue = "设置Size Style";   // 字符串的长度为12，表示该字符串中有12个字符，忽略中英文
		// 设置单元格的长度为cellSizeValue的长度。而sheet.setColumnWidth使用cellSizeValue的字节数
		// cellSizeValue.getBytes().length == 14
		sheet.setColumnWidth(3, (cellSizeValue.getBytes().length) * 256);

		// 设置单元格内容对齐方式
		XSSFRow rowAlign = sheet.createRow(4);
		XSSFCell cellAlign = rowAlign.createCell(4);
		cellBorder.setCellValue("Align Style");
		// 创建单元格样式对象
		XSSFCellStyle styleAlign = (XSSFCellStyle)wb.createCellStyle();
		// 设置单元格内容水平对其方式
		styleAlign.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格内容垂直对其方式
		styleAlign.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
		// 应用格式
		cellAlign.setCellStyle(styleAlign);

		// 设置单元格合并
		XSSFRow rowRegion = sheet.createRow(5);
		XSSFCell cellRegion = rowAlign.createCell(5);
		cellRegion.setCellValue("Region Style");
		CellRangeAddress region = new CellRangeAddress(5, 6, 5, 6);
		sheet.addMergedRegion(region);
		// 创建单元格样式对象
		XSSFCellStyle styleRegion = (XSSFCellStyle)wb.createCellStyle();
		styleRegion.setAlignment(CellStyle.ALIGN_CENTER);
		styleRegion.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellRegion.setCellStyle(styleRegion);
	}

	/**
	 * create a library of cell styles
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		DataFormat df = wb.createDataFormat();

		CellStyle style;
		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("header", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("header_date", style);

		Font font1 = wb.createFont();
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font1);
		styles.put("cell_b", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font1);
		styles.put("cell_b_centered", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setFont(font1);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("cell_b_date", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setFont(font1);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("cell_g", style);

		Font font2 = wb.createFont();
		font2.setColor(IndexedColors.BLUE.getIndex());
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font2);
		styles.put("cell_bb", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setFont(font1);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("cell_bg", style);

		Font font3 = wb.createFont();
		font3.setFontHeightInPoints((short)14);
		font3.setColor(IndexedColors.DARK_BLUE.getIndex());
		font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font3);
		style.setWrapText(true);
		styles.put("cell_h", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setWrapText(true);
		styles.put("cell_normal", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		styles.put("cell_normal_centered", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setWrapText(true);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("cell_normal_date", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setIndention((short)1);
		style.setWrapText(true);
		styles.put("cell_indented", style);

		style = createBorderedStyle(wb);
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styles.put("cell_blue", style);

		return styles;
	}

	private static CellStyle createBorderedStyle(Workbook wb){
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}

}

class Record {
	private String colHeaderName0;
	private String colHeaderName1;
	private String colHeaderName2;
	private String colHeaderName3;
	private String colHeaderName4;

	public String getColHeaderName0() {
		return this.colHeaderName0;
	}

	public void setColHeaderName0(String colHeaderName0) {
		this.colHeaderName0 = colHeaderName0;
	}

	public String getColHeaderName1() {
		return this.colHeaderName1;
	}

	public void setColHeaderName1(String colHeaderName1) {
		this.colHeaderName1 = colHeaderName1;
	}

	public String getColHeaderName2() {
		return this.colHeaderName2;
	}

	public void setColHeaderName2(String colHeaderName2) {
		this.colHeaderName2 = colHeaderName2;
	}

	public String getColHeaderName3() {
		return this.colHeaderName3;
	}

	public void setColHeaderName3(String colHeaderName3) {
		this.colHeaderName3 = colHeaderName3;
	}

	public String getColHeaderName4() {
		return this.colHeaderName4;
	}

	public void setColHeaderName4(String colHeaderName4) {
		this.colHeaderName4 = colHeaderName4;
	}

	public Record() {

	}

	public Record(String colHeaderName0, String colHeaderName1, String colHeaderName2, String colHeaderName3, String colHeaderName4) {
		this.colHeaderName0 = colHeaderName0;
		this.colHeaderName1 = colHeaderName1;
		this.colHeaderName2 = colHeaderName2;
		this.colHeaderName3 = colHeaderName3;
		this.colHeaderName4 = colHeaderName4;
	}
}
