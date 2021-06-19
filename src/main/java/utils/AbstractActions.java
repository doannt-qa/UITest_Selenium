package utils;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractActions {
    Actions actions;

    /*
     * Section 1: 1. Open Url, 2. Get title, 3. Get current url, 4. Back to previous
     * page, 5. Forward to the page, 6. Refresh page
     */

    /* Open Url */
    public void openUrl(WebDriver driver, String URL) {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /* Get title */
    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /* Get current url */
    public String getcurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    /* Back to previous page */
    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    /* Forward to the page */
    public void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    /* Refresh page */
    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    /*
     * Section 2: 1. Click to the element 2. Send-key to the text-box 3. Clear the
     * text-box 4. Select a item from the dropdown, combobox: select a specific
     * value, get first item, 5. Get text (the lable,the title, ....existed element
     * with text) 6. Checkbox 7.Radio button [TO_DO]
     *
     */
    /* Click to element */
    public void clicktoElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    /* Send-key to element */
    public void sendkeytoElement(WebDriver driver, By locator, String value) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(Keys.CLEAR);
        element.sendKeys(value);
    }

    /* Clear to element */
    public void cleartoElementControl(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
    }

    /* Select Item from the Dropdown list/ combobox */
    public void selectItemInDropDown(WebDriver driver, By locator, String itemText) {
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(itemText);
    }

    /* Get first value on the combobox */
    public String setFristItemSelected(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    /* Get text element */
    public String getTextElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    /* Check on the checkbox */
    public void checkToCheckBox(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckBox_JS(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (!element.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    /* Uncheck on checkbox */
    public void unCheckToCheckBox(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    /* AutoSuggestive Dropdown */ // (Long)
    public void autoSuggestiveDropdown(WebDriver driver, By dropdown_locator, By menu_locator, String text1,
                                       String text2) {
        driver.findElement(dropdown_locator).sendKeys(text1);
        List<WebElement> elements = driver.findElements(menu_locator);
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(text2)) {
                element.click();
            }
        }
    }

    /*
     * Section 3: Verify the elements 1. [Assert] -> by the text of element 2.
     * [boolean]-> isSelected/ isEnabled / isDislayed
     *
     */

    /* verify result by */
    public void verifyEditResult(WebDriver driver, By locator, String message) {
        WebElement element = driver.findElement(locator);
        String ActualMessage = element.getText();
        assertThat(ActualMessage, containsString(message));
    }

    /* Check element is selected */
    public boolean isSelected(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        return element.isSelected();
    }

    /* Check element is enabled */
    public boolean isEnable(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        return element.isEnabled();
    }

    /* Check element is displayed */
    public boolean isDisplayed(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        return element.isDisplayed();
    }
    /*
     * Section 4: Wait functions using ExpectedConditions 1. Wait presence, 2. Wait
     * to be visible, 3. Wait element to be clickable
     */

    /* Wait presence */
    public void waitForpresence(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    /* Wait to be visible */
    public void waitForVisible(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /* Wait element to be clickable */
    public void waitForClickable(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /*
     * Section 5: Alert Accept, Cancel, getText alert
     */
    /* Accept alert */
    public void acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /* Cancel alert */
    public void canceltAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    /* Get text alert */
    public String getTexttAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }
    /*
     * TODO: Menu -- or Xpath
     */

    public void chooseMenu(WebDriver driver, String locator, String... value) {
        String locator_ = String.format(locator, (Object[]) value);
        WebElement element = driver.findElement(By.xpath(locator_));
        element.click();
    }

    /*
     * TODO: Section 7 - The table //(Long)
     */

    /* Get Number of Rows */

    public int getNumRows(WebDriver driver, By locator) {
        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.size();
    }

    /* Get Number of Columns */
    public int getNumColumns(WebDriver driver, By locator) {
        WebElement table = driver.findElement(locator);
        List<WebElement> columns = table.findElements(By.tagName("th"));
        return columns.size();
    }

    /* Get Titles of Columns */
    public List<String> getColumnTitles(WebDriver driver, By locator) {
        WebElement table = driver.findElement(locator);
        List<WebElement> columns = table.findElements(By.tagName("th"));
        List<String> columnsTitle = columns.stream().map(s -> s.getText()).collect(Collectors.toList());
        return columnsTitle;

    }

    /* Get Value From Specific Cell */
    public String getValueFromSpecificCell(WebDriver driver, By locator, int row, int col) {
        WebElement table = driver.findElement(locator);
        String value = table.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]")).getText();
        return value;
    }

    /* Verify Value From Specific Cell With Expected Value */
    public void verifyValueFromSpecificCellWithExpectedValue(WebDriver driver, By locator, int row, int col,
                                                             String value) {
        WebElement table = driver.findElement(locator);
        String data = table.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]")).getText();
        Assert.assertTrue(data.equalsIgnoreCase(value));
    }

    /* Get Data From Specific Row by Number */
    public List<String> getDataFromSpecificRowByNumber(WebDriver driver, By locator, int row) {
        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.xpath("//tr[" + row + "]"));
        List<String> dataRow = rows.stream().map(s -> s.getText()).collect(Collectors.toList());
        return dataRow;
    }

    /* Get Data From Specific Column by Number */
    public List<String> getDataFromSpecificColumnByNumber(WebDriver driver, By locator, int col) {
        WebElement table = driver.findElement(locator);
        ArrayList<String> listData = new ArrayList<String>();
        int count = (int) table.findElements(By.xpath("//tr")).stream().count();
        for (int i = 2; i < count; i++) {
            String data = table.findElement(By.xpath("//tr[" + i + "]//td[" + col + "]")).getText();
            listData.add(data);
        }
        return listData;
    }

    /* Get Sum Value From Specific Column */
    public int getSumValueFromSpecificColumn(WebDriver driver, By locator, int col) {
        WebElement table = driver.findElement(locator);
        int sum = 0, count = (int) table.findElements(By.xpath("//tr")).stream().count();
        for (int i = 2; i < count; i++) {
            String data = table.findElement(By.xpath("//tr[" + i + "]//td[" + col + "]")).getText();
            int int_data = Integer.parseInt(data);
            sum = sum + int_data;
        }
        return sum;
    }

    /* Get Data From Specific Row By Value */
    public List<ArrayList<String>> getDataFromSpecificRowByValue(WebDriver driver, By locator, String value) {
        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.xpath("//tr//td"));
        List<ArrayList<String>> data = rows.stream().filter(s -> s.getText().equalsIgnoreCase(value))
                .map(s -> getAnotherData(s)).collect(Collectors.toList());
        return data;
    }

    public static ArrayList<String> getAnotherData(WebElement s) {
        long followingCount, precedingCount;
        followingCount = s.findElements(By.xpath("following-sibling::td")).stream().count();
        precedingCount = s.findElements(By.xpath("preceding-sibling::td")).stream().count();
        ArrayList<String> anotherData = new ArrayList<String>();
        if (precedingCount != 0) {
            for (int i = 1; i <= precedingCount; i++) {
                String data1 = s.findElement(By.xpath("preceding-sibling::td[" + i + "]")).getText();
                anotherData.add(data1);
            }
        }
        if (followingCount != 0) {
            for (int j = 1; j <= followingCount; j++) {
                String data2 = s.findElement(By.xpath("following-sibling::td[" + j + "]")).getText();
                anotherData.add(data2);
            }
        }
        return anotherData;
    }

    /*
     * TODO: Section 8 - the charts
     */
}
