package RegisterGuru99;


import cucumber.api.CucumberOptions;

import org.junit.runner.RunWith; 
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(format = {"pretty", "json:target/Destination/login/Cucumber.json", 
		"junit:target/Destination/login/Cucumber.xml", "html:target/Destination/login",
		}, monochrome= true, tags="@NegativeTest" ) 

public class Guru99RegisterationRunner {

}
