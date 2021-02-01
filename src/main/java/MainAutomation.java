import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.IntStream;

public class MainAutomation {

    public String baseUrl = "http://esinav.osym.gov.tr/deneme";
    URL driverPath = getClass().getClassLoader().getResource("chromedriver.exe");
    public WebDriver driver ;
    WebDriverWait wait = null;

    @FindBy(id = "tutorialBaslat")
    private WebElement tutorialBaslasBtn;

    @FindBy(id = "code")
    private WebElement captchaCodeInp;

    @FindBy(id = "tcKimlik")
    private WebElement tcKimlikInp;

    @FindBy(id = "sbInput")
    private WebElement sinavaBaslaBtn;

    @FindBy(name = "isaretlenenSecenek")
    private WebElement isaretlenenSecenek;

    @FindBy(id = "slideSonraki")
    private WebElement slideSonraki;

    @BeforeTest
    public void initialConfiguration(){
        File chromeDriverFile = null;
        try {
            chromeDriverFile = new File(driverPath.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        chromeDriverFile.setExecutable(true);
        System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void goToHomePageAndOpenTutorial() {
        driver.get(baseUrl);
        tutorialBaslasBtn.click();
        String openedHandler = (String) driver.getWindowHandles().toArray()[1];
        driver.switchTo().window(openedHandler);
        captchaCodeInp.sendKeys(Constants.CAPTCHA);
        tcKimlikInp.sendKeys(Constants.TCKIMLIK);
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sinavaBaslaBtn.click();
        IntStream.range(0, 80).forEach(i -> isaretleGec());
    }
    private void isaretleGec(){
//        wait.until(ExpectedConditions.elementToBeClickable(isaretlenenSecenek)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(slideSonraki)).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isaretlenenSecenek.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slideSonraki.click();
    }
}
