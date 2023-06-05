package Generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;

public BaseTest (){
	prop = new Properties();
	
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\ConfigFile\\Config.properties";
		FileInputStream fin;
		try {
			fin=new FileInputStream(path);
			prop.load(fin);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
		   e.printStackTrace();
		}
  }
public static void intialize() {
	String strBrowser = prop.getProperty("browser");
	if(strBrowser.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	}
	else if (strBrowser.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
	}
	else if(strBrowser.equalsIgnoreCase("gecko")) {
		driver =new FirefoxDriver();
		
	}
		
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.get(prop.getProperty("url"));

}
public String readObjectPath(String objName) throws IOException {
	String objPath="";
	String path=System.getProperty("user.dir")+"//src//test//resources//dataFiles//LoginPage.xlsx";
	//HSSF
	
	
	FileInputStream fin;
	XSSFWorkbook workbook=null;
	try {
		fin=new FileInputStream(path);
		workbook =new XSSFWorkbook(fin);
	}
	catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	catch(IOException e) {
	   e.printStackTrace();
	}
	XSSFSheet LoginSheet = workbook.getSheet("loginPage");
	int numRows = LoginSheet.getLastRowNum();
	for(int i=1;i<=numRows;i++) {
		XSSFRow row = LoginSheet.getRow(i);
		if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName)) {
			objPath = row.getCell(1).getStringCellValue();
		}
			
	}
	
	return objPath;
	
}
 @DataProvider(name="logindata")
 public Object [][] getData() throws CsvValidationException, IOException{
	String path=System.getProperty("user.dir")+"//src//test//resources//dataFiles//logindata.csv";
	CSVReader reader=new CSVReader (new FileReader(path));
	String cols[];
	ArrayList <Object> dataList=new ArrayList<Object>();
	while((cols=reader.readNext()) !=null){
		Object record[]= {cols[0],cols[1]};
		dataList.add(record);
	}
	reader.close();
	return dataList.toArray(new Object[dataList.size()][]);
 }
public static void CloseBrowser() {
	driver.close();
	
	
}
	
}

