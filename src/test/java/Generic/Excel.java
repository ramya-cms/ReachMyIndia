package Generic;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;


public class Excel implements Auto_Constant {
	public static String Testdata(String Path, String Sheet, int row, int cell )
	{
		
		String v="";
		try{
			
			FileInputStream fis = new FileInputStream(Path);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(Sheet);
			Cell c = sh.getRow(row).getCell(cell);
			v = c.getStringCellValue();
			Reporter.log("Path is valid", true);
				
	}
			
		catch(Exception e)
		{
			Reporter.log("Invalid path", true);
		}
		return v;				
	}	

}
