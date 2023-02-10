package testClasses;
import org.testng.annotations.Test;
import pages.*;
import utilities.CommonWebActions;
import utilities.TestRetry;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StandAloneTest extends BaseClass{

    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    HomePage homePage = new HomePage();
    UpdateProfilePage updatePage = new UpdateProfilePage();
    OpenAccountPage openAccountPage = new OpenAccountPage();
    TransferPage transferPage = new TransferPage();
    BillPaymentPage billPaymentPage = new BillPaymentPage();
    LoanPage loanPage = new LoanPage();
    CommonWebActions ca = new CommonWebActions(driver);

    @Test
    public void createNewUser() throws InterruptedException, IOException {
        loginPage.register();
        registerPage.createUser();
        homePage.clickLogout();
    }
//    @Test(priority = 2, retryAnalyzer = TestRetry.class)
    @Test
    public void userLogin() throws InterruptedException, IOException {
        loginPage.login();
    }

    @Test
    public void updateProfile() throws InterruptedException {
        loginPage.login();
        homePage.clickUpdateProfile();
        updatePage.updateDetails();
    }

    @Test
    public void openSavingsAccount() throws InterruptedException {
        loginPage.login();
        homePage.clickOpenAccount();
        openAccountPage.createSavingsAccount();
    }
//
    @Test
    public void transferFunds() throws InterruptedException {
        loginPage.login();
        homePage.clickTransferFunds();
        openAccountPage.createSavingsAccount();
        transferPage.transferFundToAccount();
    }

    @Test
    public void payBill() throws InterruptedException, IOException {
        loginPage.login();
        homePage.clickPayBill();
        billPaymentPage.payBill();
    }

    @Test
    public void requestLoan() throws InterruptedException {
        loginPage.login();
        homePage.clickRequestLoan();
        loanPage.requestLoanWithInsufficientBalance();
    }

    @Test
    public void logOut() throws InterruptedException {
        loginPage.login();
        homePage.clickLogout();
    }
}
