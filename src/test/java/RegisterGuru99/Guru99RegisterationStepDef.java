package RegisterGuru99;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class Guru99RegisterationStepDef {

	WebDriver dr;
	String expectedmsg;
	String emailId;
	String output;
	
	//For invoking Web Driver(this will run before "before" with order 1)
	@Before(order=0)
	public void setUp(){ 
		String driverPath = "C:\\Geckodriver\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
	    dr = new FirefoxDriver(); 
	    System.out.println("Driver exe"); 
	   } 
	//For opening Guru99 Bank Application
	@Before(order=1)
	public void setp() {
		dr.get("http://demo.guru99.com/V4/"); 
	}
	
	//Given of Scenario To check visit here link
	@Given("^Navigate to Guru99Bank Login page$")
	public void Navigate_to_Guru99Bank_Login_page() { 
		//printing the title of page on console
        System.out.println(dr.getTitle());
	}
	
	//When of Scenario To check visit here link
	@When("^user clicks on visit here link$")
	public void user_clicks_on_visit_here_link() {
		//clicking the visit her link
		dr.findElement(By.cssSelector("body > div:nth-child(30) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
	}
	
	//Then of scenario To check visit here link
	@Then("^it should navigate to registeration page$")
	public void it_should_navigate_to_registeration_page() {
		//validating if user on reg page by checking the heading of form
		String str = dr.findElement(By.cssSelector("body > form:nth-child(18) > table:nth-child(6) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > h2:nth-child(1)")).getText();
		str = str.replace("\n", " ").replace("\r", "");
		System.out.println(str);
		Assert.assertTrue(str.contains("Enter your email address to get access details to demo site"));
	}
	
	//Given of Scenarios: Successful Registeration, Unsuccessful Registration, Validate email id
	@Given("^User on Registeration page$")
	public void User_on_Registeration_page() {
		
		//clicking on visit here link for going to registration page
		dr.findElement(By.cssSelector("body > div:nth-child(30) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
		
	}
	
	//When of Scenarios: Successful Registeration, Unsuccessful Registeration, Validate email id
	@When("^user enters email id \"([^\"]*)\"$")
	public void user_enters_email_id(String email) {
		
		//validating the email id
		emailId = email;
		//Regex for validating the "@" and "." in email.
		String regex = "^(.+)@(.+)\\.(.+)$";  //"^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$"; //
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		Boolean mat = matcher.matches();
		System.out.println(mat);
		if(mat) {
			output = "valid";
		}
		else {
			output = "invalid";
		}
		dr.findElement(By.name("emailid")).sendKeys(email);
			
	}
	
	//And of Scenario: Successful Registeration
	@And("^email id is valid and clicks submit$")
	public void email_id_is_valid_and_clicks_submit() {
		//submitting the valid email id by clicking on submit button
		dr.findElement(By.name("btnLogin")).click();
	}
	
	//Then of Scenario: Successful Registeration
	@Then("^registeration is successful and gets valid login credentails$")
	public void registeration_is_successful_and_gets_valid_login_credentails() throws IOException {
		
		//printing the login credentials on console
		System.out.println("User Id: " + dr.findElement(By.cssSelector("body > table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)")).getText());
		System.out.println("Password: " + dr.findElement(By.cssSelector("body > table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2)")).getText());

		//taking the screenshot of user login credentials and storing on local disk
		TakesScreenshot scrShot =((TakesScreenshot)dr);
    	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    	File DestFile=new File("C:\\ScreenshotsSelenium\\" +emailId+ ".png");
    	FileUtils.copyFile(SrcFile, DestFile);
	} 
	
	//But of Scenario: Unsuccessful Registeration
	@But("^email id is invalid$")
	public void email_id_is_invalid() {
		//email id is invalid so expected msg in popup should be "Email ID is not valid"
		expectedmsg = "Email ID is not valid";
		
	}
	
	//Then of Scenario: Unsuccessful Registeration
	@Then("^invalid email popup comes$")
	public void invalid_email_popup_comes() {
		//asserting the error msg
		String msg = dr.findElement(By.cssSelector("#message9")).getText();
		System.out.println(msg);
		Assert.assertTrue(msg.contains(expectedmsg));
	}

	//Then of Scenario: Validate email id
	@Then("^validated email id \"([^\"]*)\"$")
	public void validated_email_id(String result) throws IOException {
		System.out.println("result: " + result + "output: " + output);
		
		//email id is valid then follow the positive scenario of successful registeration
		if(output.equalsIgnoreCase("valid")) {
			 email_id_is_valid_and_clicks_submit();
			 registeration_is_successful_and_gets_valid_login_credentails();
		}
		
		//email id is invalid then follow the negative scenario of unsuccessful registeration
		else {
			email_id_is_invalid();
			invalid_email_popup_comes();
		}
	}
	
	//For closing Web Driver
	@After(order =0)
    public void close() {
    	dr.close();
    	System.out.println("driver closed successfully");
    }
	
	//For closing Guru99 Bank Application (this will run before "after" with order 0)
    @After(order = 1)
    public void sucess() {
    	System.out.println("executed successfully");
    }
}
