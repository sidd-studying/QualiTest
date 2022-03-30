package utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumInitializer extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public static Properties config = new Properties();

    static {
        try {
            String configProperty = System.getProperty("user.dir") + "/src/test/resources/properties/config.properties";
            InputStream input = new FileInputStream(configProperty);
            config.load(input);
            switch (config.getProperty("BROWSER")) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "internetexplorer":
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.get("https://testscriptdemo.com/");
            driver.manage().window().maximize();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Quits the web driver.
     */
    public static void tearDownWebDriver() {
        driver.quit();
    }
}
