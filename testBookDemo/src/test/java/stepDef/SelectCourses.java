package stepDef;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SelectCourses extends TestBase {
	Actions act= new Actions(driver);
	ArrayList<String> tabs=null;
	
	@Given("^that you are present on test page$")
	public void that_you_are_present_on_test_page() throws Throwable {
	  driver.findElement(By.id("headerCourseSelector")).click();
		
		act
		.moveToElement(driver.findElement(By.linkText("+ More Courses")))
		.click().build().perform();
		//hello
}

	@When("^I select the courses and click enroll$")
	public void I_select_the_courses_and_click_enroll() throws Throwable {
	   
		driver.switchTo().frame("onBoardingIframeName");
		driver.findElement(By.xpath("//h4[contains(@data-target,'#courseGroup_1')]")).click();
		// ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,250)", "");
		
		waitFor(By.xpath("//a[@href='ssc']"));
		driver.findElement(By.xpath("//a[@href='ssc']")).click();
		
		tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    
		
	}

	@Then("^I must see the free courses available for test$")
	public void I_must_see_the_free_courses_available_for_test() throws Throwable {
	    
		try{
			//check to see if already enrolled 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(@ng-disabled,'isEnrollingInCourse')]")).click();
		}
		catch(Exception e){
			//System.out.println(e.getMessage());
			System.out.println("Already enrolled");
		}
	   
	
	}
	

	@Then("^I am able to add that test using add my test button$")
	public void I_am_able_to_add_that_test_using_add_my_test_button() throws Throwable {
		
	
		/*WebElement free_link=driver.findElement(By.xpath("//li[@rel='free_tests']"));
		if(free_link.)
			free_link.click();*/
		Thread.sleep(3000);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)", "");
		List<WebElement>tests=driver.findElements(By.xpath("//*[contains(text(),'Add to') or contains(text(),'My Tests')]"));
		
		   for(WebElement x: tests){
			   
			   if(x.isEnabled() && x.isDisplayed() && !x.getText().equalsIgnoreCase("Go to my tests")){
				   
				   x.click();
				   break;
			   }
		   }
		
		

	}


}
