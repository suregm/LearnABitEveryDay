package bitBeat.QCC;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static bitBeat.YunYangHighSchool.FileSaver.wb;

/**
 * Created by sure GM on 2016/8/3 23:43.
 */
public class POI {

	public void main (String[] args) throws Exception {
		File file = new File("D://yyzx.xlsx");
		InputStream is = new FileInputStream(file);
//		XSSFWorkbook wb = new XSSFWorkbook(is);
		Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).is)
		}
	}



}
