package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import testClasses.BaseClass;
import utilities.CommonWebActions;
import utilities.ExtentFactory;

public class OpenAccountPage extends BaseClass {
    By accountTypeLocator = By.xpath("//*[@id='type']");
    By OpenAccountBtnLocator = By.xpath("//*[@value='Open New Account']");
    By accountOpenedMsg = By.xpath("//*[@id='rightPanel']/div/div/h1");

    public void createSavingsAccount() throws InterruptedException {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
            WebElement accountTypeSelect = commonWebActions.getWebElement(accountTypeLocator);
            WebElement OpenAccountBtn = commonWebActions.getWebElement(OpenAccountBtnLocator);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Account Type is selected");
            commonWebActions.log("Account Type is selected");
            commonWebActions.clickElement(accountTypeSelect);
            commonWebActions.wait(1000);
            Select s= new Select(accountTypeSelect);
            s.selectByValue("1");
            commonWebActions.clickButton(OpenAccountBtn);
            commonWebActions.wait(2000);
            Boolean match = driver.findElement(accountOpenedMsg).getText().equalsIgnoreCase("Account Opened!");
            Assert.assertTrue(match);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has opened new savings account");
            commonWebActions.log("User has opened new savings account");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
