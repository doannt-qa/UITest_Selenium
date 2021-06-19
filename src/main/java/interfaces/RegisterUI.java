package interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterUI {

    public WebDriver driver;
    public static By Name_textbox = By.cssSelector("input.form-control[name='name']"); 
    public static By Email_texbox = By.cssSelector("input.form-control[name='email']");
    public static By Password_texbox = By.cssSelector("input#exampleInputPassword1");
    public static By Checkme_checkbox = By.cssSelector("input.form-check-input[type='checkbox']");
    public static By Gender_listbox = By.cssSelector("select#exampleFormControlSelect1");
    public static By Job_radiobutton = By.cssSelector("input.form-check-input[type='radio'][id='inlineRadio2']");
    public static By Submit_button = By.cssSelector("input.btn-success[type='submit']");
    // The Form has been submitted successfully!.
    public static By register_success_message = By.cssSelector("div.alert.alert-success.alert-dismissible");

}
