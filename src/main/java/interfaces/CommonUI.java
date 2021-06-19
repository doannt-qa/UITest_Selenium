package interfaces;

import org.openqa.selenium.By;

public class CommonUI {

    public static By TXT_INFO = By.xpath("//input[@id='%s']");
    public static By TABLE = By.xpath("//table");
    public static By ROWS = By.xpath("//table/tbody/tr");
    public static By COLS = By.xpath("//table/tbody/tr/td");
    public static By COMBO_INFO = By.xpath("//div[contains(text(),'%s')]");
    public static By SEARCH_VALUE = By.xpath("//li[contains(text(),'%s')]");
    public static By ITEM_STATUS = By.xpath("//li[contains(text(),'%s')]");
    public static By MENU_TAB = By.xpath("//div[contains(text(),'%s')]");
    public static By ICON_ACTIONS = By.xpath("//i[@class='%s']");
    public static By INFO_DETAIL = By.xpath("//div[contains(text(),'%s')]");
    public static By LINK_DETAIL = By.xpath("//a[@title='%s']");

}
