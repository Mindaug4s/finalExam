package exam.MRinkevicius;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.providers.TestDataProviders;
import helper.classes.UnavailableProduct;

public class ProductAvailabilityTest extends BaseTest {

	private BaseOpenCartPage basePage;
	private ProductCatalogImpl productCatalog;
	private List<String> productList = new ArrayList<String>();
	private String expectedAvailabilityStatus = "In Stock";
	private List<UnavailableProduct> unavailableProducts;

	@Test(priority = 0, dataProvider = "ProductCategoriesDataProviderA", dataProviderClass = TestDataProviders.class)
	public void testIfAllProductsAreAvailable(String categoryName) throws Exception {

		boolean allProductsAvailable = true;
		boolean isCurrentProductAvailable = true;
		unavailableProducts = new ArrayList<UnavailableProduct>();

		basePage = new BaseOpenCartPage(driver);
		productCatalog = new ProductCatalogImpl(driver);

		basePage.goToProductCategoryByName(categoryName);
		productList = productCatalog.getProductNameList();

		for (String currentProduct : productList) {
			productCatalog.navigateToProductByName(currentProduct);
			isCurrentProductAvailable = productCatalog.getProductAvailabilityStatus()
					.contains(expectedAvailabilityStatus);

			if (!isCurrentProductAvailable) {
				unavailableProducts.add(new UnavailableProduct(categoryName, currentProduct,
						productCatalog.getProductAvailabilityStatus()));
				allProductsAvailable = false;

			}

			basePage.goToProductCategoryByName(categoryName);
		}

		if (!unavailableProducts.isEmpty()) {
			System.out.println("Following products are not available: ");
			for (UnavailableProduct currentProduct : unavailableProducts) {
				System.out.printf("%nProduct, named %s in Category %s, is not avaialable - %s",
						currentProduct.getProductName(), currentProduct.getCategory(),
						currentProduct.getAvailability());
			}
		}

		Assert.assertTrue(allProductsAvailable, "Some products in Category " + categoryName + " is not available");



	}

}
