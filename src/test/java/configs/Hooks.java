package configs;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import utils.StringUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static String cloudfrontUrl;
    public static String browser;

    private static final String DRIVER_PATH_TEMPLATE = "{os_path}/{driver_name}{extension}";

    private static final Logger log = Logger.getLogger(Hooks.class.getName());
    private static WebDriver driver;

    public static synchronized WebDriver openBrowser() {
        if (driver == null) {
            try {
                if (browser == null) {
                    browser = "chrome"; //chrome headless
                }
                driver = getDriver(browser);
            } catch (UnreachableBrowserException e) {
                driver = new ChromeDriver();
            } catch (WebDriverException e) {
                driver = new ChromeDriver();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            driver.get(cloudfrontUrl + "/login");
//            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            log.info("Started the browser");
        }
        return driver;
    }

    /**
     * This method only supports chrome and headless chrome for linux and windows.
     * Please update if there is any requirement related to browser or OS extension
     *
     * @param browserName
     * @return
     */
    private static WebDriver getDriver(String browserName) {
        String osPath;
        String extension;

        // Set the folder containing the driver and the driver extension
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            osPath = "src/test/resources/drivers/windows";
            extension = ".exe";
        } else /* if the OS is linux */ {
            osPath = "src/test/resources/drivers/linux";
            extension = "";
        }
        HashMap<String, String> paramSet = new HashMap<String, String>();
        paramSet.put("os_path", osPath);
        paramSet.put("extension", extension);

        switch (browserName) {
            case "chrome":
                paramSet.put("driver_name", "chromedriver");
                System.setProperty("webdriver.chrome.driver", StringUtils.mapFormat(DRIVER_PATH_TEMPLATE, paramSet));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                return new ChromeDriver(chromeOptions);
            case "ie":
                paramSet.put("driver_name", "IEDriverServer");
                System.setProperty("webdriver.ie.driver", StringUtils.mapFormat(DRIVER_PATH_TEMPLATE, paramSet));
                return new InternetExplorerDriver();
            case "firefox":
                paramSet.put("driver_name", "geckodriver");
                System.setProperty("webdriver.gecko.driver", StringUtils.mapFormat(DRIVER_PATH_TEMPLATE, paramSet));
                return new FirefoxDriver();
            case "firefox headless":
                paramSet.put("driver_name", "geckodriver");
                System.setProperty("webdriver.gecko.driver", StringUtils.mapFormat(DRIVER_PATH_TEMPLATE, paramSet));
                FirefoxOptions headlessFirefoxOptions = new FirefoxOptions();
                headlessFirefoxOptions.addArguments("-healess");
                return new FirefoxDriver(headlessFirefoxOptions);

            case "chrome headless":
                paramSet.put("driver_name", "chromedriver");
                System.setProperty("webdriver.chrome.driver", StringUtils.mapFormat(DRIVER_PATH_TEMPLATE, paramSet));

                ChromeOptions headlessChromeOptions = new ChromeOptions();
                headlessChromeOptions.addArguments("no-sandbox");
                headlessChromeOptions.addArguments("headless");
                headlessChromeOptions.addArguments("disable-dev-shm-usage");
                headlessChromeOptions.addArguments("window-size=1920,1080");
                headlessChromeOptions.addArguments("disable-infobars"); // disabling infobars
                headlessChromeOptions.addArguments("disable-extensions"); // disabling extensions
                headlessChromeOptions.addArguments("disable-gpu"); // applicable to windows os only
                headlessChromeOptions.addArguments("disable-dev-shm-usage"); // overcome limited resource problems
                headlessChromeOptions.addArguments("silent");//
                // headlessChromeOptions.
                return new ChromeDriver(headlessChromeOptions);

            default:
                paramSet.put("driver_name", "chromedriver");
                System.setProperty("webdriver.chrome.driver", StringUtils.mapFormat(DRIVER_PATH_TEMPLATE, paramSet));
                return new ChromeDriver();
        }
    }

    public static void close() {
        try {
            if (driver != null) {
                openBrowser().quit();
                log.info("Close browser");

            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close browser");
        }
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }
}
