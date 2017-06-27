package exam.MRinkevicius;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.providers.TestDataProviders;
import explicit.waits.ExplicitWaits;
import helper.classes.UnavailableProduct;

public class ProductAvailabilityTest extends BaseTest {

	private HomePage homePage;
	private ProductCatalogImpl productCatalog;
	private List<String> productList = new ArrayList<String>();
	private String expectedAvailabilityStatus = "In Stock";
	private List<UnavailableProduct> unavailableProducts;
	private ExplicitWaits waits;

	/**
	 * @Test - Test checks, if all Products in specific categories (provided by
	 *       parameter), are available
	 * @param categoryName
	 *            - this parameter is read from a data file, and used for
	 *            further test implementation as the name of the category;
	 * @throws Exception
	 */
	@Test(dataProvider = "ProductCategoriesDataProviderA", dataProviderClass = TestDataProviders.class)
	public void testIfAllProductsAreAvailable(String categoryName) throws Exception {

		waits = new ExplicitWaits(driver);
		boolean allProductsAvailable = true;
		boolean isCurrentProductAvailable = true;
		unavailableProducts = new ArrayList<UnavailableProduct>();

		homePage = new HomePage(driver);
		productCatalog = new ProductCatalogImpl(driver);

		homePage.goToProductCategoryByName(categoryName);
		productList = productCatalog.getProductNameList();

		for (String currentProduct : productList) {
			productCatalog.navigateToProductByName(currentProduct);
			waits.waitForJavascript();
			isCurrentProductAvailable = productCatalog.getProductAvailabilityStatus()
					.contains(expectedAvailabilityStatus);

			if (!isCurrentProductAvailable) {
				unavailableProducts.add(new UnavailableProduct(categoryName, currentProduct,
						productCatalog.getProductAvailabilityStatus()));
				allProductsAvailable = false;

			}

			homePage.goToProductCategoryByName(categoryName);
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
