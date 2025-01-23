package BaseSetUp;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Utils.CommonMethods;
import io.qameta.allure.Step;

public class BaseSetUps extends DriverFactory{
    private DriverFactory driverFactory;
	private static final Logger LOGGER = LogManager.getLogger(BaseSetUps.class);
	
	@Step("Browser is initialized")
	@BeforeTest()
	public void initializeBrowser() {
        driverFactory = new DriverFactory(); 
        driverFactory.initit_Driver();
		LOGGER.info("Browser is initialized");
	}
	
	@Step("Driver is quit")
	@AfterTest(enabled = true)
	public void quitBrower() throws IOException {
		if(driver !=null) {
			driver.quit();
			LOGGER.info("Driver is quit");
		}
	}
	
	@Step("ScreenShot is captured")
	@AfterMethod()
	public void takeScreenshot(ITestResult result) throws IOException {
		ITestNGMethod method = result.getMethod();
		if(result.getStatus() == ITestResult.FAILURE) {
			String methodName = method.getMethodName();
			CommonMethods.takeScreenShots(methodName);
		}
	}
	
	
	
	
	
}
