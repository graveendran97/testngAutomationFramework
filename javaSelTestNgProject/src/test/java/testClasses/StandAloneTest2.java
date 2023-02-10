package testClasses;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class StandAloneTest2 extends BaseClass{

    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    HomePage homePage = new HomePage();
    UpdateProfilePage updatePage = new UpdateProfilePage();
    OpenAccountPage openAccountPage = new OpenAccountPage();
    TransferPage transferPage = new TransferPage();
    BillPaymentPage billPaymentPage = new BillPaymentPage();
    LoanPage loanPage = new LoanPage();

    @Test
    public void createNewUser() throws InterruptedException {
        loginPage.register();
        registerPage.createUser();
        homePage.clickLogout();
    }
//    @Test(priority = 2, retryAnalyzer = TestRetry.class)
    @Test(priority = 2)
    public void userLogin() throws InterruptedException {
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
