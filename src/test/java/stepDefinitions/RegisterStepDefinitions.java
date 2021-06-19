package stepDefinitions;

import configs.Hooks;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.RegisterAction;
import utils.AbstractActions;

public class RegisterStepDefinitions extends AbstractActions {

//    private static WebDriver driver = Hooks.openBrowser();
    public WebDriver driver = Hooks.openBrowser();
    RegisterAction register = new RegisterAction(driver);

    @When("^I enter (.+), (.+) and (.+)$")
    public void i_enter_and(String name, String email, String password) throws Throwable {
        register.inputName(name);
        register.inputEmail(email);
        register.inputPassword(password);
    }

    @And("^I check the check-box \"([^\"]*)\"$")
    public void i_check_the_checkbox_something(String value) throws Throwable {
        register.checkCheckMe();
    }

    @And("^I select the item \"([^\"]*)\" in the listbox$")
    public void i_select_the_item_something_in_the_listbox(String value) throws Throwable {
        register.selectGender(value);
    }

    @And("^I choose the radio button \"([^\"]*)\"$")
    public void i_choose_the_radio_button_something(String strArg1) throws Throwable {
        register.selectJob();
    }

    @And("^I input my birthday in the date of birth field$")
    public void i_input_my_birthday_in_the_date_of_birth_field() throws Throwable {
        throw new PendingException();
    }

    @And("^I click submit button$")
    public void i_click_submit_button() throws Throwable {
        register.clickSubmit();
    }

    @Then("^I receive a successful notification \"([^\"]*)\"$")
    public void i_receive_a_successful_notification_something(String message) throws Throwable {
        register.vefifySuccessMessage(message);
        Thread.sleep(3000);
        register.refresh(driver);
        ;
    }

    @Then("^close the browser$")
    public void close_the_browser() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Hooks.close();
        Thread.sleep(3000);
    }
}
