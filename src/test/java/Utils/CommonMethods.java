package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import BaseSetUp.BaseSetUps;
import PageLocators.LocatorManager;

public class CommonMethods extends BaseSetUps {
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	private static final Logger LOGGER = LogManager.getLogger(CommonMethods.class);
	static Properties prop;
	static Actions act = new Actions(driver);
	static By locator;
	static LocatorManager locatorManager = new LocatorManager(driver);
	
	public static void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public static void launch_Browser() {
		driver.get(get_URL());
		LOGGER.info("url is launched");
		Cookie cookie = new Cookie("ucuTaF#X0724", "D9VMWXj$gYXx29Paa6!9");
		driver.manage().addCookie(cookie);
		driver.navigate().refresh();
		LOGGER.info("Browser is launched");
	}

	public static String get_URL() {
//		String env = System.getProperty("ENV");
		String env = "QA";
		prop = ConfigReader.read_PropertiesFile();
		switch (env.toUpperCase()) {
		case "QA":
			return prop.getProperty("QA_Url_Monthly");
		case "STA":
			return prop.getProperty("STA_Url_Monthly");
		default:
			return prop.getProperty("QA_Url_Monthly");
		}
	}

	public static void clickOnElementIfVisible(String fieldName) {
	    locator = locatorManager.getLocator(fieldName);
	    WebElement ele = driver.findElement(locator);
	    try {       
	        // Log the visibility status
	        boolean status = checkIfElementIsDisplayed(fieldName);
	        Assert.assertTrue(status, "Element should be visible");
	        // Click on the element if it is visible
	        scrollIntoView(fieldName); // Scroll to the element before clicking
	        ele.click();
	        LOGGER.info("Element is clicked: " + fieldName);
	    } catch (NoSuchElementException e) {
	        LOGGER.error("Element not found: " + fieldName, e);
	        throw new RuntimeException("Element is not found: " + fieldName, e);
	    } catch (Exception e) {
	        LOGGER.error("An error occurred while clicking on the element: " + fieldName, e);
	        throw e; // Re-throw the exception for further handling if needed
	    }
	}

	public static boolean checkIfElementIsDisplayed(String filedName) {
		try {
			locator = locatorManager.getLocator(filedName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			WebElement ele = driver.findElement(locator);
			boolean status = ele.isDisplayed();
			Assert.assertEquals(status, true);
			LOGGER.info("Element is visible: " + filedName + " = " + status);
			return status;
		}catch(Exception e) {
	        LOGGER.error("An error occurred while clicking on the element: " + filedName, e);
	        throw e; // Re-throw the exception for further handling if needed
		}
	}
	
	public static void scrollIntoView(String filedName) {
		locator = locatorManager.getLocator(filedName);
		WebElement ele = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ele);
		try {
	        Thread.sleep(100); // Wait for 3 seconds (use with caution)
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt(); // Restore interrupted status
	        LOGGER.error("Thread was interrupted during sleep", e);
	    }
	}
	
	public static void clickOnAllFourDoc() {
		List<String> locatorsFields;
		try {
		locatorsFields = new ArrayList<>(Arrays.asList("fscs_Doc","savings_Doc","kfsc_Doc","privacy_Doc"));
		String parentWindowHandle = driver.getWindowHandle();
		for(String filedName : locatorsFields) {
			clickOnElementIfVisible(filedName);
			driver.switchTo().window(parentWindowHandle);
		}
		}catch(Exception e) {
	        LOGGER.error("An error occurred while clicking on the element: ", e);
	        throw e; // Re-throw the exception for further handling if needed
		}
	}
	
	public static void sendKeys(String fieldName, String value) {
		try {
		boolean status = checkIfElementIsDisplayed(fieldName);
		locator = locatorManager.getLocator(fieldName);
		WebElement ele = driver.findElement(locator);
		if(status == true) {
			ele.clear();
			ele.sendKeys(value);
			LOGGER.info(value+" is send to element: "+fieldName);
		}
		}catch(Exception e) {
			throw new ElementNotInteractableException("Element is not interactable");
		}
	}
	
	public static void enterOtp() {
		sendKeys("otp1", "2");
		sendKeys("otp2", "5");
		sendKeys("otp3", "1");
		sendKeys("otp4", "2");
		sendKeys("otp5", "9");
		sendKeys("otp6", "9");
	}
	
	public static void takeScreenShots(String methodName) throws IOException {
		// Cast the driver to TakesScreenshot
        TakesScreenshot sc = (TakesScreenshot) driver;

        // Capture the screenshot and store it in a temporary file
        File screenshotFile = sc.getScreenshotAs(OutputType.FILE);

        // Create the destination file
        File destinationFile = new File("./ScreenShots/"+methodName+".jpg");

        // Copy the screenshot to the specified location
        FileUtils.copyFile(screenshotFile, destinationFile);
        
        System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
	}
	
	public static By getLocator(String elementFieldName, String replace) {
		// Get the locator from LocatorManager
		By locator = locatorManager.getLocator(elementFieldName);
		// Check if the locator is null
		if (locator == null) {
			throw new IllegalArgumentException("Locator not found for: " + elementFieldName);
		}
		// Assuming that 'locator' is an XPath or CSS selector, modify it
		String locatorString = locator.toString();
		// Extracting the actual XPath/CSS selector from By object
		String modifiedLocatorString = locatorString.replace("By.xpath: ", "").replace("replace", replace);
		// Create a new By object based on modified string
		By finalElement = By.xpath(modifiedLocatorString); // Or use By.cssSelector(modifiedLocatorString) if it's CSS
		return finalElement;
	}
	
	public static void selectAddressViaLocate(String locateInputBox,String searchLocate,String locateList, String requiredLocate) {
		sendKeys(locateInputBox, searchLocate);
		String searching_For_Locate="";
		By locator = locatorManager.getLocator(locateList);
		List<WebElement> ele = driver.findElements(locator);
		for(WebElement e : ele) {
			searching_For_Locate = e.getText();
//			System.out.println(searching_For_Locate+"<-------- locate");
			if(searching_For_Locate.contains(requiredLocate.toUpperCase())) {
				e.click();
				break;
			}
		}
		LOGGER.info(searching_For_Locate+" address selected");
	}
	
	public static void clearInputTextBox(String element) {
		locator = locatorManager.getLocator(element);
		WebElement ele = driver.findElement(locator);
		boolean status = checkIfElementIsDisplayed(element);
		if(status == true) {
			ele.click();
			ele.clear();
			wait(3);
			LOGGER.info("this input text box is cleared: "+element);
		}
	}
	
	public static void selectCountryCode(int countryCode) {
		checkIfElementIsDisplayed("country_Code_DropDwn");
		clickOnElementIfVisible("country_Code_DropDwn");
		WebElement ele = driver.findElement(By.xpath("//li[@id=\"code-autocomplete-option-"+countryCode+"\"]"));
		ele.click();
	}

}
