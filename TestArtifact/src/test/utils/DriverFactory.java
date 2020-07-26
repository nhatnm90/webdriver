package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	private static final String DRIVER_CHROME_NAME = "webdriver.chrome.driver";
	private static final String DRIVER_CHROME_FILE = "D:\\learn\\auto\\webdriver-driver\\chromedriver\\84\\chromedriver.exe";
	private static final String DRIVER_FIREFOX_NAME = "webdriver.chrome.driver";
	private static final String DRIVER_FIREFOX = "D:\\learn\\auto\\webdriver-driver\\geckodriver\\geckodriver.exe";
	private static final String OS_KEY = "os";
	private static final String BROWSER_KEY = "browser";

	public static WebDriver generateWebDriver() {
		WebDriver driver = null;
		if (Constant.Browser.Chrome.toString().equals(Configuration.getConfigByKey(BROWSER_KEY))) {
			if (Configuration.getConfigByKey(OS_KEY).equals(Constant.OS.Windows.toString())) {
				System.setProperty(DRIVER_CHROME_NAME, DRIVER_CHROME_FILE);
			}
			driver = new ChromeDriver();
		} else if (Constant.Browser.Firefox.toString().equals(Configuration.getConfigByKey(BROWSER_KEY))) {
			if (Configuration.getConfigByKey(OS_KEY).equals(Constant.OS.Windows.toString())) {
				System.setProperty(DRIVER_FIREFOX_NAME, DRIVER_FIREFOX);
			}
			driver = new FirefoxDriver();
		} else if (Constant.Browser.Safari.toString().equals(Configuration.getConfigByKey(BROWSER_KEY))) {
			driver = new SafariDriver();
		}
		return driver;
	}
}
