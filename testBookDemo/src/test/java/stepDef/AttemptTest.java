package stepDef;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AttemptTest extends TestBase {

	HashMap<String,String> answer_selected=new HashMap<String,String>();
	HashMap<String,Integer> option_number=new HashMap<String,Integer>();
	By nextQ=By.xpath("//button[contains(@ng-click,'saveAndNextBtnPressed') and contains(text(),'Next')]");
	
	@Given("^that I select any of the added test to attempt$")
	public void that_I_select_any_of_the_added_test_to_attempt() throws Throwable {
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Tests")).click();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,550)", "");
		
		WebElement start_test=driver.findElement(By.xpath("//div[@class='start-test']//following-sibling::button"));
		try {
			start_test.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='text' and contains(text(),'Actual Exam')]")).click();

			driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			
			
			
			try{
				By next_btn=By.xpath("//button[text()='Next']");
				
					if(driver.findElement(next_btn).isDisplayed()) {
					waitFor(next_btn);
					driver.findElement(next_btn).click();
				   }
				
				By language=By.id("language");
				WebElement lan=driver.findElement(language);
				if(lan.isDisplayed()) {
					Select lang=new Select(lan);
					lang.selectByVisibleText("English");
				}
			}
			catch(Exception x) {
				System.out.println("Skipped");
			}
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
			waitFor(By.className("test-instructions-declaration"));
			driver.findElement(By.className("test-instructions-declaration")).click();
			driver.findElements(By.xpath("//button[contains(text(),'I have read the instructions') or contains(text(),'ready to begin')]")).get(1).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Resuming the Test that You left");
		}
	
	}

	@When("^I attempt the test by answering the questions$")
	public void I_attempt_the_test_by_answering_the_questions() throws Throwable {
	    
		int count=0;
		do{
			By opt=By.xpath("//input[@type='radio']/following-sibling::div[@class='mar-l24 ng-binding']");
			
			List<WebElement> options=driver.findElements(opt);
			WebElement Ques_no=driver.findElement(By.xpath("//div[contains(text(),'Que')]"));
			Random rd= new Random();
			int indx=rd.nextInt(4);
			waitFor(opt);
			options.get(indx).click();
			
			option_number.put(Ques_no.getText(),indx);
			
			answer_selected.put(Ques_no.getText(),options.get(indx).getText()+ " for "+ Ques_no.getText());
			waitFor(nextQ);
			driver.findElement(nextQ).click();
			count++;
		}
		while(count<=5);
		
		
	}

	@When("^I Submit the test$")
	public void I_Submit_the_test() throws Throwable {
	    
		List<WebElement> submit_btns=driver.findElements(By.xpath("//button[text()='Submit Test']"));
	  
		for(WebElement btn: submit_btns) {
			if(btn.isDisplayed() && btn.isEnabled()) {
				btn.click();
				waitFor(By.xpath("//button[text()='Yes']"));
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
				waitFor(By.xpath("//a[text()='Continue']"));
				driver.findElement(By.xpath("//a[text()='Continue']")).click();
				break;
			}
		}
	}

	@Then("^after submission I must see the answers that I selected along with solutions$")
	public void after_submission_I_must_see_the_answers_that_I_selected_along_with_solutions() throws Throwable {
	   
		By next=By.xpath("//button[contains(text(),'Next')]");
		int count=0;
		do {
			
			WebElement Ques_nos=null;
			try{
				
				List<WebElement> options=driver.findElements(By.xpath("//li[contains(@ng-class,'incorrect-option')]/label/div[@class='ng-binding']"));
				By que=By.xpath("//div[contains(text(),'Que')]");
				waitFor(que);
				Ques_nos=driver.findElement(que);
				
				System.out.println(Ques_nos.getText());
				int indx=option_number.get(Ques_nos.getText());
				
				String x=options.get(indx).getText();
				String y=answer_selected.get(Ques_nos.getText());
				System.out.println(x);
				System.out.println(y);
				
				if(y.contains(x)){
					System.out.println(answer_selected.get(Ques_nos.getText()));
					
					driver.findElements(next).get(1).click();
					
					count++;	
			}
			}
			catch(Exception e) {
				System.out.println(Ques_nos.getText()+ " was not attempted in this session");
				
				
				driver.findElements(next).get(1).click();
				count++;
				
			}
			
			//check if the options is present or not
			
	
						
		}while(count<=5);
	
driver.close();	
	}

}
