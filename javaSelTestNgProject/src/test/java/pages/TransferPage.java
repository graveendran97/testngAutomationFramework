package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import testClasses.BaseClass;
import utilities.CommonWebActions;
import utilities.ExtentFactory;

public class TransferPage extends BaseClass {

    By amountFieldLocator = By.xpath("//*[@id='amount']");
    By receiverAccLocator = By.xpath("//select[@id='toAccountId']");
    By transferBtnLocator = By.xpath("//*[@value='Transfer']");
    By transferCompleteMsg = By.xpath("//*[@id='rightPanel']/div/div/h1");
//    By ErrorMsg2 = By.xpath("//*[@id='rightPanel']/h1");

    public void transferFundToAccount() throws InterruptedException {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
//            boolean match2 = driver.findElement(ErrorMsg2).getText().equalsIgnoreCase("Error!");
//            Assert.assertFalse(match2,"Internal Server Error");
            WebElement amountField = commonWebActions.getWebElement(amountFieldLocator);
            WebElement receiverAcc = commonWebActions.getWebElement(receiverAccLocator);
            WebElement transferBtn = commonWebActions.getWebElement(transferBtnLocator);
            commonWebActions.sendKeysOnWebElement(amountField,"20");
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Transfer Amount is entered");
            commonWebActions.log("Transfer Amount is entered");
            commonWebActions.wait(1000);
            Select s = new Select(receiverAcc);
            s.selectByIndex(1);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Account is selected");
            commonWebActions.log("Account is selected");
            commonWebActions.clickButton(transferBtn);
            commonWebActions.wait(2000);
            Boolean match = driver.findElement(transferCompleteMsg).getText().equalsIgnoreCase("Transfer Complete!");
            Assert.assertTrue(match);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Transfer Funds is Complete");
            commonWebActions.log("Transfer Funds is Complete");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
