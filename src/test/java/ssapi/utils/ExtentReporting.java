package ssapi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporting extends TestListenerAdapter
{
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;
    public static String reportPath = "C:\\Backup\\RestAPI Automation - Workspace\\SSStores_API\\test-output\\"+"Test Report";


    public void onStart(ITestContext testContext)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
        Date date = new Date();
        String filePathdate = dateFormat.format(date).toString();
        String actualReportPath = reportPath + "index.html";
        new File(actualReportPath).renameTo(new File(
                "C:\\Backup\\RestAPI Automation - Workspace\\SSStores_API\\test-output\\"+ "Automation_" + filePathdate + ".html"));

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("SSStores Test Automation Report");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
        extentReport = new ExtentReports();
        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("SS Stores", "Extent Report");
        extentReport.setSystemInfo("Windows", System.getProperty("os.name"));
        extentReport.setSystemInfo("Environment","QA");


    }

    public void onTestSuccess(ITestResult tr)
    {

        extentTest = extentReport.createTest(tr.getName());
        extentTest.log(Status.PASS,
                MarkupHelper.createLabel(tr.getName() + "Test Case passed", ExtentColor.GREEN));
        extentTest.pass(tr.getName() + "Test Case Passed");


    }

    public void onTestFailure(ITestResult tr)
    {
        extentTest = extentReport.createTest(tr.getName());
        extentTest.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
        extentTest.log(Status.FAIL,
                MarkupHelper.createLabel(tr.getThrowable() + "Test Case Failed", ExtentColor.RED));
        // Here you can add screenshot in the report for fail case

        String screenshotPath="C:\\Backup\\RestAPI Automation - Workspace\\SSStores_API\\Screenshots"+tr.getName()+".png";

        File f = new File(screenshotPath);

        if(f.exists())
        {
            try {
                extentTest.fail("Screenshot is below:" + extentTest.addScreenCaptureFromPath(screenshotPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        extentTest.fail(tr.getName() + "Test Step Failed");


    }
    public void onTestSkipped(ITestResult tr)

    {
        extentTest = extentReport.createTest(tr.getName());
        extentTest.log(Status.SKIP,
                MarkupHelper.createLabel(tr.getName() + "Test Case Skipped", ExtentColor.ORANGE));
        extentTest.skip(tr.getName() + "Test Skipped");

    }

    public void onFinish(ITestContext testContext)
    {
        extentReport.flush();
    }

}


