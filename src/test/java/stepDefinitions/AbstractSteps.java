package stepDefinitions;

import configs.Hooks;
import configs.ProfileConfig;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import pageObjects.CommonActions;
import utils.AbstractActions;

@SpringBootTest(classes = { ProfileConfig.class })
public class AbstractSteps extends AbstractActions {

    public WebDriver driver = Hooks.openBrowser();
    CommonActions commonActions = new CommonActions(driver);

    @Given("Open url")
    public void open_url() throws Throwable {
        commonActions.openURL(Hooks.cloudfrontUrl);
        Thread.sleep(3000);
    }
}
