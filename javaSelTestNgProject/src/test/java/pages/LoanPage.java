package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import testClasses.BaseClass;
import utilities.CommonWebActions;
import utilities.ExtentFactory;

public class LoanPage extends BaseClass {

    By loanAmountLocator = By.xpath("//*[@id='amount']");
    By downPaymentLocator = By.xpath("//*[@id='downPayment']");
    By applyLoanBtnLocator = By.xpath("//input[@value='Apply Now']");
    By loanStatusResult = By.xpath("//*[@id='loanStatus']");

    public void requestLoanWithInsufficientBalance() throws InterruptedException {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
            WebElement loanAmountField = commonWebActions.getWebElement(loanAmountLocator);
            WebElement downPaymentField = commonWebActions.getWebElement(downPaymentLocator);
            WebElement applyLoanBtn = commonWebActions.getWebElement(applyLoanBtnLocator);

            commonWebActions.sendKeysOnWebElement(loanAmountField,"10000");
            commonWebActions.sendKeysOnWebElement(downPaymentField,"5000");
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Loan Amount and down payment amount is entered");
            commonWebActions.log("Loan Amount and down payment amount is entered");
            commonWebActions.clickButton(applyLoanBtn);
            commonWebActions.wait(2000);
            Boolean match = driver.findElement(loanStatusResult).getText().equalsIgnoreCase("Denied");
            Assert.assertTrue(match);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Transfer Funds is Denied");
            commonWebActions.log("Transfer Funds is Denied");

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
