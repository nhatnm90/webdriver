import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author nhatnguyen
 *
 */
public class WebDriverUtilities {
	private WebDriver _driver;
	public WebDriverUtilities(WebDriver driver) {
		this._driver = driver;
	}

	/*
	 * Gọi để add task vào cho chương trình có thể khởi tạo task ban đầu
	 * @param taskName tên của task cần tạo
	 */
	public void addTask(String taskName) {
		this.buttonClick("btnAddNewTask");
		this._driver.findElement(By.id("txtTaskName")).sendKeys(taskName);
		this.buttonClick("btnSubmitTask");
	}

	/*
	* Gọi để thực hiện sực kiện click cho 1 button theo id
	* @param buttonId id của button cần thực hiện sự kiện click
	*/
	public void buttonClick(String buttonId) {
		this._driver.findElement(By.id(buttonId)).click();
	}
}
