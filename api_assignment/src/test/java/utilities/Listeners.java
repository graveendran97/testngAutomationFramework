package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testClasses.BaseClass;

import java.io.IOException;

public class Listeners extends BaseClass implements ITestListener {
    ExtentTest test;
    static ExtentReports report;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = report.createTest(iTestResult.getMethod().getMethodName());
        ExtentFactory.getInstance().setExtent(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case " + iTestResult.getMethod().getMethodName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case " + iTestResult.getMethod().getMethodName() + " is Fail");
        ExtentFactory.getInstance().getExtent().fail(iTestResult.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        report = ExtentReportNG.getReportObject();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        report.flush();
    }
}

