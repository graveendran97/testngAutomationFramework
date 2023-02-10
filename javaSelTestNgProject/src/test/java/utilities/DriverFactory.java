package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    public static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(String browserName, String headless) {
        WebDriver driverName;
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                driverName = getFirefoxDriver(headless);
                break;
            case "EDGE":
                driverName = getEdgeDriver(headless);
                break;
            case "IE":
                driverName = getIEDriver(headless);
                break;
            default:
                driverName = getChromeDriver(headless);
        }
        driverThreadLocal.set(driverName);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static WebDriver getFirefoxDriver(String headless) {
        WebDriverManager.firefoxdriver().setup();
        if(headless.equalsIgnoreCase("yes"))
        {
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            return new FirefoxDriver(options);
        }
        else {
            WebDriverManager.chromedriver().setup();
            return new FirefoxDriver();
        }
    }

    public static WebDriver getChromeDriver(String headless) {
        WebDriverManager.chromedriver().setup();
        if(headless.equalsIgnoreCase("yes"))
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            return new ChromeDriver(chromeOptions);
        }
        else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

    public static WebDriver getEdgeDriver(String headless) {
        WebDriverManager.edgedriver().setup();
        if(headless.equalsIgnoreCase("yes"))
        {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");
            return new EdgeDriver(edgeOptions);
        }
        else {
            WebDriverManager.chromedriver().setup();
            return new EdgeDriver();
        }
    }

    public static WebDriver getIEDriver(String headless) {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }
}