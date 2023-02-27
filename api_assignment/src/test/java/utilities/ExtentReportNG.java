package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportNG {

    public static ExtentReports getReportObject(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);
        String reportPath = System.getProperty("user.dir") + "//Reports//ExecutionReport"+actualDate+".html";
        ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
        sparkReport.config().setReportName("Web Automation Results");
        sparkReport.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReport);
        return extent;
    }
}
