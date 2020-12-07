package DemoCart;
import cucumber.api.CucumberOptions;

import org.junit.runner.RunWith; 
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(format = {"pretty", "json:target/Destination/democart/Cucumber.json", 
		"junit:target/Destination/democart/Cucumber.xml", "html:target/Destination/democart",
		}, monochrome= true, strict = true, dryRun = false) 

public class DemoCartRunner {

}
