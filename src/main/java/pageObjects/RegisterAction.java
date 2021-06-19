package pageObjects;

import org.openqa.selenium.WebDriver;
import interfaces.RegisterUI;
import utils.AbstractActions;

public class RegisterAction extends AbstractActions {

    public WebDriver driver;

    public RegisterAction(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL(String url) {
        openUrl(driver, url);
    }


    public void inputName(String name) {
        sendkeytoElement(driver, RegisterUI.Name_textbox, name);
    }

    public void inputEmail(String email) {
        sendkeytoElement(driver, RegisterUI.Email_texbox, email);
    }

    public void inputPassword(String password) {
        sendkeytoElement(driver, RegisterUI.Password_texbox, password);
    }

    public void checkCheckMe() {
        checkToCheckBox(driver, RegisterUI.Checkme_checkbox);
    }

    public void selectGender(String value) {
        selectItemInDropDown(driver, RegisterUI.Gender_listbox, value);
    }

    public void selectJob() {
        //TODO
        checkToCheckBox(driver, RegisterUI.Job_radiobutton);
    }

    public void clickSubmit() {
        clicktoElement(driver, RegisterUI.Submit_button);
    }

    public void vefifySuccessMessage(String message) {
        verifyEditResult(driver, RegisterUI.register_success_message, message);
    }
}
