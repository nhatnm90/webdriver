import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	private static final String FILE_CONFIG = "config.properties";
	private static final String DRIVER_CHROME = "D:\\learn\\auto\\webdriver-driver\\chromedriver\\84\\chromedriver.exe";
	private static final String DRIVER_FIREFOX = "D:\\learn\\auto\\webdriver-driver\\geckodriver\\geckodriver.exe";
	private static final String DRIVER_SAFARI = "";

	public static WebDriver generateWebDriver() {
		WebDriver driver = null;
		if (Constant.Browser.Chrome.toString().equals(Configuration.getConfigByKey("browser"))) {
			if (Configuration.getConfigByKey("os").equals(Constant.OS.Windows.toString())) {
				System.setProperty("webdriver.chrome.driver", DRIVER_CHROME);
			}
			driver = new ChromeDriver();
		} else if (Constant.Browser.Firefox.toString().equals(Configuration.getConfigByKey("browser"))) {
			if (Configuration.getConfigByKey("os").equals(Constant.OS.Windows.toString())) {
				System.setProperty("webdriver.chrome.driver", DRIVER_FIREFOX);
			}
			driver = new FirefoxDriver();
		} else if (Constant.Browser.Safari.toString().equals(Configuration.getConfigByKey("browser"))) {
			if (Configuration.getConfigByKey("os").equals(Constant.OS.Windows.toString())) {
				System.setProperty("webdriver.chrome.driver", DRIVER_SAFARI);
			}
			driver = new SafariDriver();
		}
		return driver;
	}
}
