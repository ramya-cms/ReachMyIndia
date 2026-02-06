package RMITestScript;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.eMitra;

public class eMitra_Script extends BaseTest
{
	
	private eMitra eMit;
	@Test(priority=1)
	public void applicant() throws InterruptedException
	{
		driver = getDriver();
		String loc = Excel.Testdata(Path, "Sheet5", 11 ,0);
		String dis = Excel.Testdata(Path, "Sheet5", 11, 1);
		String mun = Excel.Testdata(Path, "Sheet5", 11, 2);
		String war = Excel.Testdata(Path, "Sheet5", 11, 3);
		String pin = Excel.Testdata(Path, "Sheet5", 11, 4);
		
		
		
		
		String name = Excel.Testdata(Path, "Sheet5", 15, 0);
		String gen =  Excel.Testdata(Path, "Sheet5", 15, 1);
		String mstat= Excel.Testdata(Path, "Sheet5", 15, 2);
		String dob =  Excel.Testdata(Path, "Sheet5", 15, 3);
		String mob =  Excel.Testdata(Path, "Sheet5", 15, 4);
		String emai = Excel.Testdata(Path, "Sheet5", 15, 5);
		
		String addr =Excel.Testdata(Path, "Sheet5", 18, 0);
		String pan = Excel.Testdata(Path, "Sheet5", 18, 1);
		String aad = Excel.Testdata(Path, "Sheet5", 18, 2);
		String bnk = Excel.Testdata(Path, "Sheet5", 18, 3);
		String edu = Excel.Testdata(Path, "Sheet5", 18, 4);
		String polv =Excel.Testdata(Path, "Sheet5", 18, 5);
		String kio = Excel.Testdata(Path, "Sheet5", 18, 6);
		String prb = Excel.Testdata(Path, "Sheet5", 18, 7);
		
		String file[] = {
				"D:\\Latest Docs and Downloads Backup\\Rentalagrement3.pdf",
				"D:\\Latest Docs and Downloads Backup\\Rentalagrement3.pdf",
				"D:\\Latest Docs and Downloads Backup\\Rentalagrement3.pdf",
				"D:\\Latest Docs and Downloads Backup\\Rentalagrement3.pdf",
				"D:\\Latest Docs and Downloads Backup\\Rentalagrement3.pdf",
				"D:\\Latest Docs and Downloads Backup\\Rentalagrement3.pdf"
				};

		eMit = new eMitra(driver);
		eMit.appDetails(loc, dis, mun, war, pin);
		eMit.appInfo(name, gen, mstat, dob, mob, emai, addr, pan, aad, bnk, edu, polv, kio, prb);
		eMit.uploadMultipleFiles(file);
	}
}
