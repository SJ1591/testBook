package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public static WebDriver driver=null;
	public static WebDriver getDriver(){
		
		  ChromeOptions ops = new ChromeOptions();
          ops.addArguments("--disable-notifications");
         
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver(ops);
		
		return driver;
	}
	public void waitFor(By locator) {
		
	WebDriverWait wait= new WebDriverWait(driver, 20);
		
	wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}