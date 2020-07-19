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
	
	public void addListTask(int numberOfTask, String taskPrefix) {
		for (int i = 0; i < numberOfTask; i++) {
			String taskName = taskPrefix + "_"+ java.util.UUID.randomUUID();
			addTask(taskName);
		}
	}
	
	public void addTask(String taskName) {
		WebElement btnAddNewTask = this._driver.findElement(By.id("btnAddNewTask"));
		btnAddNewTask.click();
		WebElement txtTaskName = this._driver.findElement(By.id("txtTaskName"));
		txtTaskName.sendKeys(taskName);
		WebElement btnSubmitTask = this._driver.findElement(By.id("btnSubmitTask"));
		btnSubmitTask.click();
	}
}
