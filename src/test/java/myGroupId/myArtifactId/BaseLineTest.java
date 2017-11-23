package myGroupId.myArtifactId;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
	 
public class BaseLineTest extends YourTest{
	 
	private static final String LOGIN = "ontraveltest4";
    private static final String PASSWORD = "ontraveltest";

	@Override
    @BeforeMethod
    @Parameters("browser")
    public void prepareTest(@Optional("FF") String browser){
        super.prepareTest(browser);
    }
 
    @Test
    public void baseLineTest(){
        driver.get(startUrl);
        driver.findElement(By.className("enter")).click();
        driver.findElement(By.name("login")).sendKeys(LOGIN);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        if (driver.findElement(By.id("memory")).isSelected()) {
        	driver.findElement(By.id("memory")).click();
    	};
        driver.findElement(By.xpath("//input[contains(@class, 'auth__enter')]")).click();
        driver.findElement(By.id("mainmenu")).findElement(By.partialLinkText("Почта")).click();
        if (!driver.findElements(By.xpath("//a[contains(@class, 'new-hr-auth-Form_Button-enter')]")).isEmpty()) {
        	driver.findElement(By.xpath("//a[contains(@class, 'new-hr-auth-Form_Button-enter')]")).click();
        }
        if (!driver.findElements(By.xpath("//span[contains(@class, 'passport-Domik-Account-Link')]")).isEmpty()) {
        	driver.findElement(By.xpath("//span[contains(@class, 'passport-Domik-Account-Link')]")).click();
        }
        driver.findElement(By.name("login")).sendKeys(LOGIN);
        driver.findElement(By.name("passwd")).sendKeys(PASSWORD);
        driver.findElement(By.className("passport-Checkbox-Label")).click();
        driver.findElement(By.className("passport-Button-Text")).click();
        System.out.println("Входящих (всего): " + driver.findElement(By.className("mail-NestedList-Item-Info-Extras")).getText().substring(3));
        System.out.println("Входящих (непрочитанных): " + driver.findElement(By.className("mail-NestedList-Item-Info-Link-Text")).getText());
        driver.findElement(By.className("mail-User-Name")).click();
        driver.findElement(By.partialLinkText("Выход")).click();
    }
}