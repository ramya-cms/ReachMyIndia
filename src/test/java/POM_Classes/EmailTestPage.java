package POM_Classes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class EmailTestPage extends Base_Page {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath="//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement cont;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement login;

    @FindBy(xpath="//a[normalize-space()='Go to inbox']")
    private WebElement inbox;

    @FindBy(xpath = "//a[normalize-space()='click here']")
    private WebElement checkfordeposit;

    @FindBy(xpath="//a[@class='_btn_8oj2v_5 _secondary_8oj2v_51 _btnRound_8oj2v_237 _iconNoChildren_8oj2v_13']")
    private WebElement back;
    
    public EmailTestPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        try {
            System.out.println("Attempting login with email: " + email);
            wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
            System.out.println("Email entered: " + email);

            wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
            System.out.println("Next button clicked");

            wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
            System.out.println("Password entered: " + password);
            
            wait.until(ExpectedConditions.elementToBeClickable(login)).click();
            System.out.println("Password Next button clicked");
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readEmail(String subject) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(inbox)).click();
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//p[normalize-space()='ReachMyIndia Application Approved']")));
        System.out.println("Subject : RMI Approved");
       
        email.click();
        
        String currentWindowHandle = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(checkfordeposit)).click();
        System.out.println("Security Deposit link clicked");

        Set<String> windowHandles = driver.getWindowHandles();
        String newWindowHandle = null;
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                newWindowHandle = windowHandle;
                break;
            }
        }

        if (newWindowHandle != null) {
            driver.switchTo().window(newWindowHandle);
            System.out.println("Switched to new tab");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,550)", "");
            Thread.sleep(50000);

            driver.switchTo().window(currentWindowHandle);
            System.out.println("Switched back to original tab");
        } else {
            System.out.println("New tab not found");
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(back)).click();
        Thread.sleep(1000);
        driver.navigate().refresh();
    }

   /* public void readEmailandgetbody(String subject) {
        driver.navigate().refresh();
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//p[normalize-space()='ReachMyIndia Center Details']")));
        email.click();

        WebElement emailBody = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//tbody/tr[4]/td[1]")));
        System.out.println("Email Body:");
        System.out.println(emailBody.getText());
    }*/
    
    public String readEmailandgetbody(String emailSubject) {
        int attempts = 0;
        while (attempts < 3) {
            try {
            	 WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
            	            By.xpath("//p[normalize-space()='ReachMyIndia Center Details']")));
            	        email.click();

                // Explicit wait to ensure the element is present and visible
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement emailBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[4]/td[1]")));
                
                return emailBodyElement.getText(); // Return the email body content
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException encountered, retrying... " + attempts);
            }
        }
        throw new RuntimeException("Failed to retrieve email body after multiple attempts.");
    }
}
