package exam.MRinkevicius;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseOpenCartPage {
	
	private WebDriver driver;
	
	@FindBy(linkText = "My Account")
	public WebElement accountManagementButton;
	
	@FindBy(linkText = "Register")
	public WebElement registerAccountButton;

	public BaseOpenCartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void goToAccountCreationPage(){
		try{
			if(registerAccountButton.isDisplayed()){
				registerAccountButton.click();
			}
		}catch (Exception e) {
			accountManagementButton.click();
			registerAccountButton.click();
		}
	}

}
