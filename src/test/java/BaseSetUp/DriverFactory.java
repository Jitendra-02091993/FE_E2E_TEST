package BaseSetUp;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	private static final Logger LOGGER = LogManager.getLogger(BaseSetUps.class);
	
	public WebDriver initit_Driver() {
//		String browser = System.getProperty("brower");
		String browser = "chrome";
		LOGGER.info("Browser initiated is :"+browser);
		if(browser!=null) {
			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new ChromeDriver();
				break;
			case "ff":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				break;
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return getDriver();
		
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
