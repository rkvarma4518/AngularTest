package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class BaseClass {
	public static NgWebDriver ngWebDriver;
	public static JavascriptExecutor jsDriver;
	public static WebDriverWait wait;
	public static PropertyReader prop;
	public static WebDriver driver;

	public static WebDriver getDriver() throws Exception {

		String browserName = PropertyReader.getValue("browser");
		System.out.println(browserName);
		System.out.println("ChromeDriver version: " + ((RemoteWebDriver) driver).getCapabilities().getVersion());


		if (browserName.equals("chrome")) {
			if (driver == null) {
				System.out.println("----------------------------------------------------------------------------");
			}
			// Set the path to your local chromedriver.exe
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			ChromeOptions options = new ChromeOptions();
			// Optional: remove headless if not required
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-gpu");  // Disable GPU if not available
			options.addArguments("--window-size=1920,1080");  // Set a window size to avoid resolution-related issues

			// driver = new ChromeDriver(options);
			try {
			    driver = new ChromeDriver(options);
			} catch (Exception e) {
			    e.printStackTrace();
			}

			if (driver == null) {
				System.out.println("----------------------------------------------------------------------------");
			}
			System.out.println("Chrome browser launched");
		} else if (browserName.equals("firefox")) {
			// Add Firefox driver initialization here if needed
			throw new Exception("Firefox not yet supported.");
		} else {
			throw new Exception("Browser not supported: " + browserName);
		}

		// Ensure driver is initialized before using JavascriptExecutor
		jsDriver = (JavascriptExecutor) driver;
		ngWebDriver = new NgWebDriver(jsDriver);

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(PropertyReader.getValue("url"));

		// Wait for Angular requests to finish
		ngWebDriver.waitForAngularRequestsToFinish();

		return driver;
	}

	protected void WaitUntilElementVisible(WebElement element) throws Exception {
		prop = new PropertyReader();
		wait = new WebDriverWait(driver, Duration.ofSeconds(prop.getTimeout()));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void WaitUntilElementClickable(WebElement element) throws Exception {

		prop = new PropertyReader();
		wait = new WebDriverWait(driver, Duration.ofSeconds(prop.getTimeout()));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
