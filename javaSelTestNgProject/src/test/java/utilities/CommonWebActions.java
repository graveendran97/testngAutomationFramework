package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testClasses.BaseClass;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonWebActions{

    private WebDriver driver;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
    Date date = new Date();
    String actualDate = format.format(date);

    public CommonWebActions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement elements = null;
        elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", elements);
    }

    public void clickElement(WebElement element) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", element);
    }

    public WebElement getWebElement(By locator) {
        return this.driver.findElement(locator);
    }

    public void log(String Message) throws IOException {
        File file = new File(System.getProperty("user.dir")+"//logs//logFile.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("Log ->>> "+actualDate+" [ INFO ] "+ Message);
        writer.newLine();
        System.out.println("Log ->>> "+actualDate+" [ INFO ] "+ Message);
        writer.close();
    }



    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeysOnWebElement(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void maximizePage() {
        this.driver.manage().window().maximize();
    }

    public void refreshPage() {
        this.driver.navigate().refresh();
    }

    public void navigateBack() {
        this.driver.navigate().back();
    }

    public void wait(int timeInSeconds) throws InterruptedException {
        Thread.sleep(timeInSeconds);
    }
}
