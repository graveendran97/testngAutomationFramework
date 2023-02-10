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

public class UpdateProfilePage extends BaseClass {

    By updateStreetLocator = By.xpath("//*[@id='customer.address.street']");
    By updateCityLocator = By.xpath("//*[@id='customer.address.city']");
    By updateStateLocator = By.xpath("//*[@id='customer.address.state']");
    By updateZipCodeLocator = By.xpath("//*[@id='customer.address.zipCode']");
    By updateProfileBtnLocator = By.xpath("//*[@value='Update Profile']");
    By profileUpdatedMsg = By.xpath("//*[@id='rightPanel']/div/div/h1");
//    By ErrorMsg1 = By.xpath("//*[@id='rightPanel']/h1");
    ExcelHandler excel = new ExcelHandler("./src/main/resources/dataFile.xlsx");
    List<String> colNames = new ArrayList();
    List<By> xpathList = new ArrayList();

    public void updateDetails() throws InterruptedException {
        try {
            colNames.addAll(Arrays.asList("Street","City","State","Zipcode"));
            xpathList.add(updateStreetLocator);
            xpathList.add(updateCityLocator);
            xpathList.add(updateStateLocator);
            xpathList.add(updateZipCodeLocator);
            CommonWebActions commonWebActions = new CommonWebActions(driver);
            commonWebActions.wait(2000);
//            boolean match2 = driver.findElement(ErrorMsg1).getText().equalsIgnoreCase("Error!");
//            Assert.assertFalse(match2,"Internal Server Error");
            for(int i=0;i<xpathList.size();i++){
                commonWebActions.sendKeysOnWebElement(commonWebActions.getWebElement(xpathList.get(i)),excel.getCellData("update",colNames.get(i),2));
            }
            WebElement updateProfileBtn = commonWebActions.getWebElement(updateProfileBtnLocator);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "New values are entered to update profile");
            commonWebActions.log("New values are entered to update profile");
            commonWebActions.clickButton(updateProfileBtn);
            commonWebActions.wait(2000);
            boolean match = driver.findElement(profileUpdatedMsg).getText().equalsIgnoreCase("Profile Updated");
            commonWebActions.log("Profile Updated");
            Assert.assertTrue(match,"Profile has been updated");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
