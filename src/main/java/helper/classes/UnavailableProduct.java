package helper.classes;

public class UnavailableProduct {
	
	private String availability;
	private String productName;
	private String category;

	public UnavailableProduct(String category, String productName, String availability) {

		this.category=category;
		this.productName=productName;
		this.availability=availability;
	}

	public String getCategory() {
		return category;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getAvailability() {
		return availability;
	}
}
