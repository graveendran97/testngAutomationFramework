package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer {
     int count = 0;
     int maxTry = 2;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count < maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}
