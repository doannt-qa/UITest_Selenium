package cucumberOptions;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/json-report/cucumber.json" }, glue = {
		"stepDefinitions" }, features = { "src/test/java/features/Register.feature" }) 
public class TestRunner {
}

