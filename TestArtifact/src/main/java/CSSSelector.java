import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.List;

public class CSSSelector {
  
	// Tìm Element để thực thi, kiểm thử là phần quan trọng, để test cho element nào thì mình phải lấy đc nó
	// Có 2 cách chính để lấy element: 
	
	// 1. Các thuộc tính cơ bản của HTML -> dễ sử dụng nhưng sẽ dễ gặp lỗi khi có nhiều hơn 1 element trùng nhau.
	// Có thể trùng do class, id (do lỗi của dev, vì đã đặt id trùng), tagname, name 
	
	// - id <input id="idTest" value="" /> 
	//	->class là classTest 
	
	// - class <input class="classTest" value="" /> 
	//	->class là classTest 
	
	// - name <input name="nameTest" value="" /> 
	//	->name là nameTest 
	
	// - tagname <h2>Title of ...</h2>
	// -> tagname là h2 
	
	// - linktext (lấy những element với thẻ a)
	// -> <a href="https://google.com">Google</a>
	
	// -> Xem những code dưới đây để minh hoạ cho cách lấy element thứ  nhất
	
	// 2. Các cách tìm kiếm nâng cao -> chính xác, đơn nhất 
	// Với cách này có thể lấy chính xác element mà mình muốn test, cách lấy sẽ khó hơn nhưng sẽ chính xác, không sợ có element trùng 
	// - CSS Selector
	// - XPath
	
	// -> Sẽ đc giới thiệu ở bài tiếp theo 
	
	// Cầu nối giữa browser và scripts test cho 1 website nào đó 
	WebDriver _driver;
	Utilities _utils;
	
	public CSSSelector() {
		if (Constant.Browser.Chrome.toString().equals(Configuration.getConfigByKey("browser"))) {
			if (Configuration.getConfigByKey("os").equals(Constant.OS.Windows.toString())) {
				System.setProperty("webdriver.chrome.driver",  "D:\\learn\\auto\\webdriver-driver\\chromedriver\\84\\chromedriver.exe");
			}
			_driver = new ChromeDriver();
		} else {
			if (Configuration.getConfigByKey("os").equals(Constant.OS.Windows.toString())) {
				System.setProperty("webdriver.chrome.driver",  "D:\\learn\\auto\\webdriver-driver\\geckodriver\\geckodriver.exe");
			}
			_driver = new FirefoxDriver();
		}

		_utils = new Utilities(_driver);
	}

	@BeforeTest
	public void init() {
		// Khai báo driver
		// Khởi tạo, mở trang web
		// Chỉ chạy 1 lần duy nhất khi bắt đầu chạy test chương trình


		// Mở website cần test 
		 _driver.get(Configuration.getHostName());
		 
		// Phóng to cửa sổ 
		_driver.manage().window().maximize();
	}
	
	@BeforeMethod
	void beforeMethod() {
		// refresh tại trang 
		_driver.navigate().refresh();
	}
	
	// Dùng CSS Selector để lấy element 
	// Dùng thuộc tính priority để sắp xếp thứ tự chạy của các test case nếu cần (không bắt buộc)
	@Test(priority=1)
	void testAddNewTask() {
		// Theo cấu trúc A-A-A 
		
		// Arrange
		int numberOfAddingTask = 2;
		
		// Action
		this._utils.addListTask(numberOfAddingTask, "test task");
		
		// Assert
		// Tìm list các task đc add vào bằng CSS Selector
		// -> lấy tất cả các elements có tagname là tr và có id bắt đầu bằng tr_
		// dấu mũ (^) lấy giá trị bắt đầu bằng ...
		int totalItem = this._driver.findElements(By.cssSelector("tr[id^=tr_")).size();
		List<WebElement> ele = this._driver.findElements(By.cssSelector("tr[id^=tr_"));
		assertEquals(numberOfAddingTask, totalItem, "Should have " + numberOfAddingTask + " row in the list task");
	}
	
	@Test(priority=2)
	void testAddMoreTask() {
		// Theo cấu trúc A-A-A 
		
		// Arrange
		int numberOfAddingTask = 2;
		
		// Action
		this._utils.addListTask(numberOfAddingTask, "test task more");
		
		// Assert
		int totalItem = _driver.findElements(By.xpath("//tr")).size() - 1;
		assertNotEquals(numberOfAddingTask, totalItem, "Should NOT have the same row number in the list task");
	}

	@Test(priority=3)
	void testBindingTaskCssSelector() {
		// Theo cấu trúc A-A-A
		// Arrange

		// 1. Thêm task vào danh sách
		this._utils.addListTask(2, "test task more");
		// 2. Tìm danh sách các task đã đc thêm
		List<WebElement> listTasks = this._driver.findElements(By.cssSelector("tr[id^=tr_"));
		// 3. Lấy task đầu tiên trong danh sách và lấy ra task name
		WebElement firstTask = listTasks.get(0);
		List<WebElement> rowContents = firstTask.findElements(By.tagName("td"));
		String expectedTaskName = rowContents.get(0).getText();

		// Action

		// 4. Tìm nút Edit tương ứng với task cần đc edit
		WebElement buttonEdit = firstTask.findElement(By.cssSelector("button[class$=btn-danger]"));
		buttonEdit.click();
		// 5. Tìm text box chứa task name cần được edit
		String actualTaskName = this._driver.findElement(By.id("txtTaskName")).getAttribute("value");

		// Assert
		assertEquals(expectedTaskName, actualTaskName, "The edited task name should be matched");
	}

	@AfterMethod
	void afterMethod() {
		this._driver.navigate().refresh();
	}
	
	
	@AfterTest
	public void close() {
		this._driver.quit();
	}

	// BeforeTest > BeforeMethod > Test > AfterMethod > AfterTest

}
