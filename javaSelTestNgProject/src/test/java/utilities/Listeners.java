package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testClasses.BaseClass;

import java.io.IOException;

public class Listeners extends BaseClass implements ITestListener {
    ExtentTest test;
    static ExtentReports report;
    CommonWebActions ca = new CommonWebActions(driver);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = report.createTest(iTestResult.getMethod().getMethodName());
        ExtentFactory.getInstance().setExtent(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case " + iTestResult.getMethod().getMethodName() + " is passed");
        try {
            ExtentFactory.getInstance().getExtent().pass(MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotAsBase64(iTestResult.getMethod().getMethodName())).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ca.log("End of Test\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case " + iTestResult.getMethod().getMethodName() + " is Fail");
        try {
            ExtentFactory.getInstance().getExtent().fail(iTestResult.getThrowable(),MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotAsBase64(iTestResult.getMethod().getMethodName())).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        try {
            ca.log("Start of new test run\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        report = ExtentReportNG.getReportObject();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        try {
            ca.log("End of the Test Run\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        report.flush();
    }
}
