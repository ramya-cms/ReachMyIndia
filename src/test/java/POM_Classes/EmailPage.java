package POM_Classes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Email;

public class EmailPage extends Email {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "identifierId")
    private WebElement emailInput;

    @FindBy(xpath = "//span[normalize-space()='Next']")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    @FindBy(xpath = "//a[normalize-space()='click here']")
    private WebElement checkfordeposit;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        try {
            System.out.println("Attempting login with email: " + email);
            wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
            System.out.println("Email entered: " + email);
            wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
            System.out.println("Next button clicked");
            
            Thread.sleep(15000);
            wait.until(ExpectedConditions.visibilityOf(passwordInput));
            
            if (passwordInput.isDisplayed()) {
                System.out.println("Password field is displayed.");
                passwordInput.sendKeys(password);
                System.out.println("Password entered: " + password);
                wait.until(ExpectedConditions.elementToBeClickable(passwordNextButton)).click();
                System.out.println("Password Next button clicked");
            } else {
                System.out.println("Password field is not displayed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readEmail(String subject) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(), '" + subject + "')]/ancestor::tr[contains(@class, 'zA')]")));

        String currentWindowHandle = driver.getWindowHandle();
        email.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(checkfordeposit)).click();
        driver.switchTo().window(currentWindowHandle);
    }

    public void readEmailandgetbody(String subject) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(), '" + subject + "')]/ancestor::tr[contains(@class, 'zA')]")));
        email.click();

        WebElement emailBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='adn ads']/div[2]")));
        System.out.println("Email Body:");
        System.out.println(emailBody.getText());
    }
}
