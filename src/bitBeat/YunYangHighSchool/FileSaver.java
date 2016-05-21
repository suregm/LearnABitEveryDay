package bitBeat.YunYangHighSchool;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by sure GM on 2016/5/21 20:13.
 */
public class FileSaver {
	public static HSSFWorkbook wb;
	public static HSSFSheet sheet;

	public static int rowNum = 0;

	public static void save(List<Record> records)
	{
		FileSaver.prepare();
		FileSaver.write(records);
		FileSaver.saveToFile();
	}

	private static void createCell(Workbook wb, Row row, int column, String value)
	{
		Cell cell = row.createCell(column);
		cell.setCellValue(value);
	}

	private static void createHead()
	{
		Row row = sheet.createRow(rowNum);
		createCell(wb, row, 0, "name");
		createCell(wb, row, 1, "gender");
		createCell(wb, row, 2, "email");
		createCell(wb, row, 3, "QQ");
		createCell(wb, row, 4, "msn");
		createCell(wb, row, 5, "homepage");
		createCell(wb, row, 6, "regDate");
		createCell(wb, row, 7, "artNum");
		rowNum++;
	}

	public static void prepare()
	{
		wb = new HSSFWorkbook();
		sheet = wb.createSheet("numbers");
		createHead();
	}

	public static void write(List<Record> records)
	{
		for (Record record : records)
		{
			System.out.println("--->" + rowNum);

			Row row = sheet.createRow(rowNum);
			createCell(wb, row, 0, record.getName());
			createCell(wb, row, 1, record.getGender());
			createCell(wb, row, 2, record.getEmail());
			createCell(wb, row, 3, record.getQq());
			createCell(wb, row, 4, record.getMsn());
			createCell(wb, row, 5, record.getHomepage());
			createCell(wb, row, 6, record.getRegdate());
			createCell(wb, row, 7, record.getArtnum());
			rowNum++;

		}
	}

	public static void saveToFile()
	{
		try
		{
			wb.write(new FileOutputStream(new File("d:/xyz.xls")));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
