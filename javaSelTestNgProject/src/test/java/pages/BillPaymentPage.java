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

public class BillPaymentPage extends BaseClass {

    By payeeNameLocator = By.xpath("//*[@name='payee.name']");
    By payeeStreetLocator = By.xpath("//*[@name='payee.address.street']");
    By payeeCityLocator = By.xpath("//*[@name='payee.address.city']");
    By payeeStateLocator = By.xpath("//*[@name='payee.address.state']");
    By payeeZipCodeLocator = By.xpath("//*[@name='payee.address.zipCode']");
    By payeePhoneNumberLocator = By.xpath("//*[@name='payee.phoneNumber']");
    By payeeAccountNumberLocator = By.xpath("//*[@name='payee.accountNumber']");
    By payeeAccountVerifyLocator = By.xpath("//*[@name='verifyAccount']");
    By payeeAmountLocator = By.xpath("//*[@name='amount']");
    By sendPaymentBtnLocator = By.xpath("//*[@value='Send Payment']");
    By billPaymentConfirm = By.xpath("//*[@id='rightPanel']/div/div[2]/h1");
    ExcelHandler excel = new ExcelHandler("./src/main/resources/dataFile.xlsx");
    List<String> colNames = new ArrayList();
    List<By> xpathList = new ArrayList();

    public void payBill() throws InterruptedException {
        try {
            colNames.addAll(Arrays.asList("Firstname","Street","City","State","Zipcode","Phone","Account","AccountRepeat","Amount"));
            xpathList.add(payeeNameLocator);
            xpathList.add(payeeStreetLocator);
            xpathList.add(payeeCityLocator);
            xpathList.add(payeeStateLocator);
            xpathList.add(payeeZipCodeLocator);
            xpathList.add(payeePhoneNumberLocator);
            xpathList.add(payeeAccountNumberLocator);
            xpathList.add(payeeAccountVerifyLocator);
            xpathList.add(payeeAmountLocator);
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
            for(int i=0;i<xpathList.size();i++){
                commonWebActions.sendKeysOnWebElement(commonWebActions.getWebElement(xpathList.get(i)),excel.getCellData("payee",colNames.get(i),2));
            }
            WebElement sendPaymentBtn = commonWebActions.getWebElement(sendPaymentBtnLocator);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Payee Details are entered");
            commonWebActions.log("Payee Details are entered");
            commonWebActions.clickButton(sendPaymentBtn);
            commonWebActions.wait(2000);
            boolean match = driver.findElement(billPaymentConfirm).getText().equalsIgnoreCase("Bill Payment Complete");
            Assert.assertTrue(match);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Bill payment was successful");
            commonWebActions.log("Bill payment was successful");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
