package DemoCart;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class DemoCartStepdef {
	String na;
	WebDriver dr;
	
	//For invoking Web Driver(this will run before "before" with order 1)
	@Before(order=0)
	public void setUp(){ 
		String driverPath = "C:\\Geckodriver\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverPath);
        dr = new FirefoxDriver(); 
        System.out.println("hey 1"); 
	} 
	
	//For opening democart application
	@Before(order =1)
	public void setp(){ 
		dr.get("http://demo.opencart.com/"); 
		System.out.println("hey 2");
	} 
	
	//Given of Scenarios: To test navigation to iPhone, To test navigation to nikon Camera, 
	//Searching products
	//gets the title of demo cart page
    @Given("^user on democart page$")
    public void user_on_democart_page(){
    	System.out.println(dr.getTitle());
    }
    
  //When of Scenario: To test navigation to iPhone
    //click on the phone from navigation bar then selects iPhone
    @When("^user click on phone then iphone$")
    public void user_click_on_phone_then_iphone() {
    	dr.findElement(By.cssSelector("ul.nav > li:nth-child(6) > a:nth-child(1)")).click();
    	dr.findElement(By.cssSelector("div.product-layout:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1)")).click();
    	
    }
    
  //When of Scenario: To test navigation to nikon Camera
  //click on the camera from navigation bar then selects nikon camera
    @When("^user click on camera then nikon$")
    public void user_click_on_camera_then_nikon() {
        // Express the Regexp above with the code you wish you had
    	dr.findElement(By.cssSelector("ul.nav > li:nth-child(7) > a:nth-child(1)")).click();
    	dr.findElement(By.cssSelector("div.product-layout:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1)")).click();
    	
    }
    
    //And of Scenarios: To test navigation to iPhone, To test navigation to nikon Camera, 
	//Searching products
    @And ("^navigate back to home page$")
    public void navigate_back_to_home_page(){
    	dr.findElement(By.cssSelector(".breadcrumb > li:nth-child(1) > a:nth-child(1)")).click();
    }
    
    //Then of Scenarios: To test navigation to iPhone, To test navigation to nikon Camera 
    @Then("^navigation successful$")
    public void navigation_successful(){
        String title = dr.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.toLowerCase().contains("your store"));
        System.out.println("navigation Successful");
    }
    
    //When of Scenario: Searching products
    //enter the product name in search textbox to search product
    @When("^I entered product \"([^\"]*)\"$")
    public void I_entered_product(String name) {
    	na = name;
    	dr.findElement(By.name("search")).sendKeys(name);;
    	dr.findElement(By.cssSelector(".btn-default")).click();
    	
    }
    
    //And of Scenario: Searching products
    @And ("^select the product$")
    public void select_the_product(){
    	dr.findElement(By.name("description")).click();
    	dr.findElement(By.cssSelector("#button-search")).click();
    }
    
    //And of Scenario: Searching products
    //add the product to cart
    @And("^add it to cart$")
    public void add_it_to_cart() {
    	//if searched product is notebook then use below to add it to cart
    	if(na.equalsIgnoreCase("notebook")) {
    		dr.findElement(By.cssSelector("div.product-layout:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > button:nth-child(1)")).click();
    	}
    	
    	//if searched product is imac then use below to add it to cart
    	else if(na.equalsIgnoreCase("imac")) {
    		dr.findElement(By.cssSelector(".button-group > button:nth-child(1)")).click();
            
    	}
    	
    	//if searched product is tablet then use below to add it to cart
    	else {
    		dr.findElement(By.cssSelector(".button-group > button:nth-child(1)")).click();
    	}
    	dr.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    	WebDriverWait wait=new WebDriverWait(dr, 100);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert")));
    	
    }
 
    //Then of Scenario: Searching products
    //Verify the cart total count is "1" or not
    @Then("^added succesfully$")
    public void added_succesfully() {
    	String total = dr.findElement(By.id("cart-total")).getText().substring(0,1);
    	System.out.println("Total items in cart: " + total);
    	Assert.assertTrue(total.contains("1"));
    	System.out.println("successful");
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
