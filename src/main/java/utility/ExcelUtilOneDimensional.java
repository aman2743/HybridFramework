package utility;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtilOneDimensional {
	@DataProvider(name="DataSupplierNew",parallel = true,indices = {0})
	public Object[][] DataSupplier(String Path) {
		File f = new File(".\\src\\test\\resources\\TestDataOneDimensional.xlsx");
		FileInputStream fis = null;
		Object[][] obj = null;
		try {
			fis = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");
			int rowCount = ws.getLastRowNum();
			int colCount = ws.getRow(0).getLastCellNum();
			obj = new Object[rowCount][colCount];
//			System.out.println(rowCount + ":" + colCount);
			for (int r = 0; r <rowCount; r++) {
				XSSFRow row = ws.getRow(r+1);
				for (int c = 0; c < colCount; c++) {
					XSSFCell cell = row.getCell(c);
					switch (cell.getCellType()) {
					case STRING:
						obj[r][c] = cell.getStringCellValue();
						break;
					case NUMERIC:
						obj[r][c] = (int)cell.getNumericCellValue();
						break;
					case BOOLEAN:
						obj[r][c] = cell.getBooleanCellValue();
						break;
					default:
						System.out.println("invalid Date");
					}
				}
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
