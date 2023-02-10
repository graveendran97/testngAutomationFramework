package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import testClasses.BaseClass;
import utilities.CommonWebActions;
import utilities.ExcelHandler;
import utilities.ExtentFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends BaseClass{

    By registerBtnLocator = By.xpath("//*[contains(@href,'register')]");
    By usernameLocator = By.xpath("//input[@name='username']");
    By passwordLocator = By.xpath("//input[@name='password']");
    By loginBtnLocator = By.xpath("//input[@value='Log In']");
    By homePageHeader = By.xpath("//*[@id='leftPanel']/h2");
//    By ErrorMsg = By.xpath("//*[@id='rightPanel']/h1");
    ExcelHandler excel = new ExcelHandler("./src/main/resources/dataFile.xlsx");
    List<String> colNames = new ArrayList();
    List<By> xpathList = new ArrayList();

    public void register() throws InterruptedException {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
            WebElement registerBtn = commonWebActions.getWebElement(registerBtnLocator);
            commonWebActions.clickElement(registerBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Register button was clicked");
            commonWebActions.log("Register button was clicked");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void login() throws InterruptedException {
        try {
            colNames.addAll(Arrays.asList("Username","Password"));
            xpathList.add(usernameLocator);
            xpathList.add(passwordLocator);
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            if(commonWebActions.isElementPresent(loginBtnLocator)) {
                WebElement loginBtn = commonWebActions.getWebElement(loginBtnLocator);
                for(int i=0;i<xpathList.size();i++){
                    commonWebActions.sendKeysOnWebElement(commonWebActions.getWebElement(xpathList.get(i)),excel.getCellData("login",colNames.get(i),2));
                }
                ExtentFactory.getInstance().getExtent().log(Status.PASS, "Username and password was entered");
                commonWebActions.log("Username and password was entered");
                commonWebActions.clickElement(loginBtn);
                ExtentFactory.getInstance().getExtent().log(Status.PASS, "Login Button was clicked");
                commonWebActions.log("Login Button was clicked");
                commonWebActions.wait(2000);
                boolean match = driver.findElement(homePageHeader).getText().equalsIgnoreCase("Account Services");
                Assert.assertTrue(match);
                commonWebActions.wait(2000);
//                boolean match2 = driver.findElement(ErrorMsg).getText().equalsIgnoreCase("Error!");
//                Assert.assertFalse(match2,"Internal Server Error");
            }
            else{
                ExtentFactory.getInstance().getExtent().log(Status.PASS, "User is already logged In");
                commonWebActions.log("User is already logged In");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
