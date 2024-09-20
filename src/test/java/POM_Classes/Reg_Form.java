package POM_Classes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Generic.Auto_Constant;
import Generic.Base_Page;

public class Reg_Form extends Base_Page implements Auto_Constant {
    private static  WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "ApplicantName")
    private WebElement appName;

    @FindBy(xpath = "//input[@id='PanCardNumber']")
    private WebElement Pan;

    @FindBy(id = "DateOfBirth")
    private WebElement Dob;

    @FindBy(xpath = "//select[@id='Gender']")
    private WebElement gen;

    @FindBy(xpath = "//select[@id='State']")
    private WebElement state;

    @FindBy(how = How.XPATH, using = "//select[@id='District']")
    private WebElement dist;

    @FindBy(xpath = "//input[@id='CommunicationAddress1']")
    private WebElement pradr;

    @FindBy(xpath = "//input[@id='CommunicationAddressPinCode']")
    private WebElement pincode;

    @FindBy(xpath = "//*[@id='IsAddressSame']")
    private WebElement spaddr;

    @FindBy(xpath = "//input[@id='IsAccept']")
    private WebElement cond;

    @FindBy(xpath = "//input[@id='btnRegistration']")
    private WebElement cont;
    
    @FindBy(xpath="//body/div[@class='loadingoverlay']/div[@class='loadingoverlay_element']/div[1]//*[name()='svg']")
    private WebElement maskedScreenId;
    
    @FindBy(css="tbody tr:nth-child(7) td:nth-child(1) div:nth-child(1)")
    private WebElement receiptElementId;
    
    
    
    public Reg_Form(WebDriver driver) {
    	this.driver=driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void details(String aname, String pan, String dob, String paddr, String pincde) throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(appName)).sendKeys(aname);
        wait.until(ExpectedConditions.visibilityOf(Pan)).sendKeys(pan);
        wait.until(ExpectedConditions.visibilityOf(Dob)).sendKeys(dob);	 

        Select sel = new Select(gen);
        sel.selectByIndex(1);

        Thread.sleep(2000);

        Select sel1 = new Select(state);
        sel1.selectByVisibleText("Karnataka");

        Thread.sleep(2000);

        Select drop = new Select(dist);
        drop.selectByVisibleText("Bengaluru Urban");

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,350)", "");

        wait.until(ExpectedConditions.visibilityOf(pradr)).sendKeys(paddr);
        wait.until(ExpectedConditions.visibilityOf(pincode)).sendKeys(pincde);

        js2.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(spaddr)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cond)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
        
     // Wait for the masked screen to appear
     	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30000));
                
    }
             

    public static void screenshot() throws IOException, InterruptedException 
    {
        JavascriptExecutor js4 = (JavascriptExecutor) driver;
        js4.executeScript("window.scrollTo(0, 0);");
        
        Long innerHeight = (Long) js4.executeScript("return window.innerHeight;");
        Long scroll = innerHeight;

        Long scrollHeight = (Long) js4.executeScript("return document.documentElement.scrollHeight;");
        scrollHeight = scrollHeight + scroll;

        try {
            do {
            	File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destination = new File(SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]")) + ".png");
                FileUtils.copyFile(screenshot, destination);
                js4.executeScript("window.scrollTo(0, " + innerHeight + ");");
                innerHeight = innerHeight + scroll;
            } 
            while (scrollHeight >= innerHeight);
            System.out.println("ScreenShot Captured");
        } catch (IOException e) {
            System.out.println("Unable to capture ScreenShot: " + e.getMessage());
        }
    }
}