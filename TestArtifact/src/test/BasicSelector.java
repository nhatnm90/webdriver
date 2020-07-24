import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utils.Configuration;
import utils.DriverFactory;
import utils.Utils;

import static org.testng.Assert.*;

public class BasicSelector {
  
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
	Utils _utils;
	
	public BasicSelector() {
		// Tạo đối tượng driver tương ứng với browser cần test
		_driver = DriverFactory.generateWebDriver();
		_utils = new Utils(_driver);
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
	
	// Tìm element bằng cách lấy theo tagname 
	@Test
	void testTitleName() {
		// Arrange + Action
		String expectedTitle = "Daily tasks";
		WebElement titleName = this._driver.findElement(By.tagName("h2"));
		
		// Assert
		assertEquals(expectedTitle, titleName.getText(), "The title should be " + expectedTitle);
	}
	
	// Tìm element bằng cách lấy theo className và id  
	@Test
	void testLoadComponent() {
		// Arrange + Action
		WebElement groupSearch = this._driver.findElement(By.className("input-group"));
		WebElement textboxSearch = this._driver.findElement(By.id("txtSearch"));
		WebElement buttonSearch = this._driver.findElement(By.id("btnSearch"));
		WebElement buttonClear = this._driver.findElement(By.id("btnClear"));
		
		// Assert
		assertNotNull(groupSearch, "The groupSearch should be existed");
		assertNotNull(textboxSearch, "The textboxSearch should be existed");
		assertNotNull(buttonSearch, "The buttonSearch should be existed");
		assertNotNull(buttonClear, "The buttonClear should be existed");
	}
	
	// Tìm element bằng cách lấy theo name  
	@Test
	void testLoadSortDefaultSortDescription() {
		// Arrange
		String expectedSortDesc = "LEVEL - DESC";
		
		// Action
//		WebElement labelSort = this._driver.findElement(By.name("lbSortDesc"));
		WebElement labelSort = this._driver.findElement(By.cssSelector("span[id=spSort]"));
		// Assert
		assertNotNull(labelSort, "The labelSort should be existed");
		assertEquals(expectedSortDesc, labelSort.getText(), "The default value of label Sort should be " + expectedSortDesc);
	}

	// dùng thuộc tính priority để sắp xếp thứ tự chạy của các test case nếu cần (không bắt buộc)
	@Test(priority=1)
	void testAddNewTask() {
		// Theo cấu trúc A-A-A 
		
		// Arrange
		int numberOfAddingTask = 2;
		
		// Action
		this._utils.addListTask(numberOfAddingTask, "test task");
		
		// Assert
		int totalItem = _driver.findElements(By.xpath("//tr")).size() - 1;
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
