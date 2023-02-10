package testClasses;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;

public class BaseClass{

    public static WebDriver driver;

    public String getScreenshots(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//screenshots//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }

    public String getScreenShotAsBase64(String testCaseName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"//reports//screenshots//"+testCaseName+".png";
        FileUtils.copyFile(source, new File(path));
        byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public void openBrowser(String browserName, String HeadlessState) {
        DriverFactory.setDriver(browserName,HeadlessState);
        driver = DriverFactory.getDriver();
    }

    @BeforeTest
    public WebDriver intialSetup() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//config.properties");
        prop.load(file);
        String BrowserName = prop.getProperty("browser");
        String HeadlessState = prop.getProperty("headless");
        String ApplicationUrl = prop.getProperty("applicationUrl");
        openBrowser(BrowserName, HeadlessState);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(ApplicationUrl);
        return driver;
    }

    @AfterTest
    public void clearBrowser() {
        driver.close();
    }
}
