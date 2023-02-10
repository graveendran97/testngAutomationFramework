package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testClasses.BaseClass;
import utilities.CommonWebActions;
import utilities.ExcelHandler;
import utilities.ExtentFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterPage extends BaseClass {

    By firstnameLocator = By.xpath("//*[@id='customer.firstName']");
    By lastnameLocator = By.xpath("//*[@id='customer.lastName']");
    By streetLocator = By.xpath("//*[@id='customer.address.street']");
    By cityLocator = By.xpath("//*[@id='customer.address.city']");
    By stateLocator = By.xpath("//*[@id='customer.address.state']");
    By zipCodeLocator = By.xpath("//*[@id='customer.address.zipCode']");
    By phoneNumberLocator = By.xpath("//*[@id='customer.phoneNumber']");
    By ssnLocator = By.xpath("//*[@id='customer.ssn']");
    By usernameLocator = By.xpath("//*[@id='customer.username']");
    By passwordLocator = By.xpath("//*[@id='customer.password']");
    By repeatedPasswordLocator = By.xpath("//*[@id='repeatedPassword']");
    By registerBtnLocator = By.xpath("//*[@value='Register']");
    By welcomeHeader = By.xpath("//*[@id='rightPanel']/h1");
    ExcelHandler excel = new ExcelHandler("./src/main/resources/dataFile.xlsx");
    List<String> colNames = new ArrayList();
    List<By> xpathList = new ArrayList();

    public void createUser(){
        try {

            colNames.addAll(Arrays.asList("Firstname","Lastname","Street","City","State","Zipcode","Phone","Ssn","Username","Password","RepeatPassword"));
            xpathList.add(firstnameLocator);
            xpathList.add(lastnameLocator);
            xpathList.add(streetLocator);
            xpathList.add(cityLocator);
            xpathList.add(stateLocator);
            xpathList.add(zipCodeLocator);
            xpathList.add(phoneNumberLocator);
            xpathList.add(ssnLocator);
            xpathList.add(usernameLocator);
            xpathList.add(passwordLocator);
            xpathList.add(repeatedPasswordLocator);

            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2);
            WebElement registerBtn = commonWebActions.getWebElement(registerBtnLocator);
            for(int i=0;i<xpathList.size();i++){
                commonWebActions.sendKeysOnWebElement(commonWebActions.getWebElement(xpathList.get(i)),excel.getCellData("register",colNames.get(i),2));
            }
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User Values are entered");
            commonWebActions.log("User Values are entered");
            commonWebActions.clickButton(registerBtn);
            commonWebActions.wait(2000);
            boolean match = driver.findElement(welcomeHeader).getText().equalsIgnoreCase("Welcome "+ excel.getCellData("register","Username",2));
            Assert.assertTrue(match);
            commonWebActions.wait(2000);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User registration was successful");
            commonWebActions.log("User registration was successful");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
