package stepDef;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login extends TestBase {

	
	@Given("^username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void username_and_password(String user, String pass) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    driver=getDriver();
	    driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
		driver.get("https://testbook.com/");
	   
	   driver.findElement(By.xpath("//a[contains(text(),' Login ')]")).click();
	   
	    driver.switchTo().frame("onBoardingIframeName");
	   driver.findElement(By.xpath("//input[@name='emailIDOrUName']")).sendKeys(user);
	   driver.findElement(By.xpath("//input[@name='pswd']")).sendKeys(pass,Keys.ENTER);
	   
	   
	}

	@When("^I login using above credentials$")
	public void I_login_using_above_credentials() throws Throwable {
	   
		driver.switchTo().defaultContent();
		   
	}

	@Then("^I am redirected to my home page$")
	public void I_am_redirected_to_my_home_page() throws Throwable {
	    
	waitFor(By.linkText("Tests"));
		//driver.findElement(By.linkText("Tests")).click();
		
		
	}

}
