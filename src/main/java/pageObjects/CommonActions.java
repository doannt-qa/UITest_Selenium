package pageObjects;

import org.openqa.selenium.WebDriver;
import utils.AbstractActions;

public class CommonActions extends AbstractActions {
    public WebDriver driver;

    public CommonActions(WebDriver driver){
        this.driver = driver;
    }
    public void openURL(String url) {
        openUrl(driver, url);
    }
}
