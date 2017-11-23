package myGroupId.myArtifactId;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
	 
public class YourTest {
    protected WebDriver driver;
    protected static String startUrl;
 
    @SuppressWarnings("deprecation")
	@BeforeMethod
    @Parameters("browser")
    public void prepareTest(String browser){
 
        startUrl = "https://tut.by";
 
        if(browser.equalsIgnoreCase("FF"))
        {
        	System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");
            driver = new FirefoxDriver();
        } 
        else if(browser.equalsIgnoreCase("IE"))
        {
            System.out.println("IE webdriver would be used");
            System.setProperty("webdriver.ie.driver", "c:\\IEDriverServer.exe");
            DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
            capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new InternetExplorerDriver(capab);
        }
        else if(browser.equalsIgnoreCase("CH"))
        {
            System.out.println("Chrome webdriver would be used");
            System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
            driver = new ChromeDriver();
        }
 
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }
 
    @AfterMethod
    public void closeBrowser(){
        takeScreenshot();
        driver.close();
        driver.quit();
    }
 
    private void takeScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
 
            Files.copy(scrFile, new File("target\\screenshot_" + this.getClass().getName() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    protected void setImplicitlyWaitTimeout(long seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}