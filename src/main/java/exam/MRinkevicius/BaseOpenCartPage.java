package exam.MRinkevicius;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseOpenCartPage {
	
	protected WebDriver driver;
	
	@FindBy(linkText = "My Account")
	public WebElement accountManagementButton;
	


	public BaseOpenCartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void goToProductCategoryByName(String categoryName){
		
		driver.findElement(By.linkText(categoryName)).click();

	}

}
