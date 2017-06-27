package exam.MRinkevicius;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogImpl {

	private WebDriver driver;
	private Iterator<WebElement> productIterator;
	private List<String> productNameList = new ArrayList<String>();

	@FindBy(css = "h4>a")
	public List<WebElement> displayedProductList;

	@FindBy(xpath = "//li[contains(text(), 'Availability')]")
	public WebElement availabilityStatusField;

	public ProductCatalogImpl(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getDisplayedProductList() {
		if (displayedProductList.isEmpty()) {
			displayedProductList = driver.findElements(By.cssSelector("h4>a"));
		}

		return displayedProductList;
	}



	public List<String> getProductNameList() {
		productIterator = getDisplayedProductList().iterator();
		while (productIterator.hasNext()) {
			productNameList.add(productIterator.next().getText());
		}
		return productNameList;
	}
	
	public void navigateToProductByName(String productName){
		
		driver.findElement(By.linkText(productName)).click();
		
	}
	
	public String getProductAvailabilityStatus(){
		return availabilityStatusField.getText();
	}

}
