import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 */
 
/**
 * @author nhatnguyen
 *
 */
public class Utilities {
	private WebDriver _driver;
	public Utilities(WebDriver driver) {
		this._driver = driver;
	}

	/*
	* Gọi để thêm 1 số task vào trong danh sách
	* @param numberOfTask số lượng task cần thêm vào
	* @param taskPrefix tiếp đầu ngữ của mỗi task
	* Task name đc tạo tự động theo cấu trúc tiếp đầu ngữ _ ramdom GUID ID
	*/
	public void addListTask(int numberOfTask, String taskPrefix) {
		for (int i = 0; i < numberOfTask; i++) {
			String taskName = taskPrefix + "_"+ java.util.UUID.randomUUID();
			addTask(taskName);
		}
	}

	/*
	* Gọi để thực hiện sực kiện click cho 1 button theo id
	* @param buttonId id của button cần thực hiện sự kiện click
	*/
	public void buttonClick(String buttonId) {
		this._driver.findElement(By.id(buttonId)).click();
	}

	/*
	* Gọi để chương trình dừng lại đợi trước khi thực hiện thao tác tiếp theo
	* @param miliseconds phần trăm giây ngừng lại 1 * 1000 = 1s
	*/
	public void sleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		}
		catch (InterruptedException ex) {
		}
	}

	/*
	 * Gọi để add task vào cho chương trình có thể khởi tạo task ban đầu
	 * @param taskName tên của task cần tạo
	 */
	private void addTask(String taskName) {
		this.buttonClick("btnAddNewTask");
		this._driver.findElement(By.id("txtTaskName")).sendKeys(taskName);
		this.buttonClick("btnSubmitTask");
	}
}
