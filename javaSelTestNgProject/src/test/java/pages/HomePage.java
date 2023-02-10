package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testClasses.BaseClass;
import utilities.CommonWebActions;
import utilities.ExtentFactory;

import java.io.IOException;

public class HomePage extends BaseClass {

    By LogoutBtnLocator = By.xpath("//*[contains(@href,'logout')]");
    By updateProfileMenuBtnLocator = By.xpath("//a[contains(@href,'updateprofile')]");
    By openAccountMenuBtnLocator = By.xpath("//a[contains(@href,'openaccount')]");
    By transferFundsMenuBtnLocator = By.xpath("//a[contains(@href,'transfer')]");
    By billPaymentMenuBtnLocator = By.xpath("//a[contains(@href,'billpay')]");
    By requestLoanMenuBtnLocator = By.xpath("//a[contains(@href,'requestloan')]");

    public void clickLogout() throws InterruptedException {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
            WebElement logoutBtn = commonWebActions.getWebElement(LogoutBtnLocator);
            commonWebActions.clickButton(logoutBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has logged out");
            commonWebActions.log("User has logged out");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void clickUpdateProfile() {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            WebElement updateProfileMenuBtn = commonWebActions.getWebElement(updateProfileMenuBtnLocator);
            commonWebActions.clickButton(updateProfileMenuBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has clicked Update profile menu button");
            commonWebActions.log("User has clicked Update profile menu button");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void clickOpenAccount() {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            WebElement openAccountMenuBtn = commonWebActions.getWebElement(openAccountMenuBtnLocator);
            commonWebActions.clickButton(openAccountMenuBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has clicked Open Account menu button");
            commonWebActions.log("User has clicked Open Account menu button");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void clickTransferFunds() {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            WebElement transferFundsMenuBtn = commonWebActions.getWebElement(transferFundsMenuBtnLocator);
            commonWebActions.clickButton(transferFundsMenuBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has clicked Transfer Funds menu button");
            commonWebActions.log("User has clicked Transfer Funds menu button");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickPayBill() throws IOException {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            WebElement billPaymentMenuBtn = commonWebActions.getWebElement(billPaymentMenuBtnLocator);
            commonWebActions.clickButton(billPaymentMenuBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has clicked Pay Bill menu button");
            commonWebActions.log("User has clicked Pay Bill menu button");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickRequestLoan() {
        try {
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            WebElement requestLoanMenuBtn = commonWebActions.getWebElement(requestLoanMenuBtnLocator);
            commonWebActions.clickButton(requestLoanMenuBtn);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "User has clicked Request Loan menu button");
            commonWebActions.log("User has clicked Request Loan menu button");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
