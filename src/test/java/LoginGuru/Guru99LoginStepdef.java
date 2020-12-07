package LoginGuru;

import java.util.List;

//import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import io.cucumber.datatable.DataTable;



//import cucumber.annotation.en.And;
//import cucumber.annotation.en.Given;
//import cucumber.annotation.en.Then;
//import cucumber.annotation.en.When;
//import cucumber.table.DataTable;

public class Guru99LoginStepdef {

	WebDriver dr;
	@Before(order =1)
	public void fun1() {
		System.out.println("Hey 1");
	}
	@Before(order =2)
	public void fun2() {
		System.out.println("Hey 12");
	}
    @Given("^Navigate to Guru99Bank Login page$")
    public void navigate(){
           String driverPath = "C:\\Geckodriver\\geckodriver.exe";
           System.setProperty("webdriver.gecko.driver", driverPath);
           dr=new FirefoxDriver();
           dr.get("http://demo.guru99.com/V4/"); 
           System.out.println(dr.getTitle());
           }
    @When("^username and password are entered$")
    public void username_and_password_are_entered(DataTable uc) {
    	List<List<String>> ucdata = uc.raw();
        // Express the Regexp above with the code you wish you had
         dr.findElement(By.name("uid")).sendKeys(ucdata.get(0).get(0));
           dr.findElement(By.name("password")).sendKeys(ucdata.get(0).get(1));
        
    }
    
    
    @And ("^both are correct$")
    public void login(){
          
           dr.findElement(By.name("btnLogin")).click();
          // dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
           }
    @Then("^Login is successful$")
    public void verifySuccessful(){
        String mId = dr.findElement(By.xpath("//table//tr[@class='heading3']")).getText();
        System.out.println(mId);
        Assert.assertTrue(mId.toLowerCase().contains("manger id : mngr296614"));
        System.out.println("Login Successful");
          }

}
