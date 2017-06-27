package data.providers;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

	@DataProvider(name = "ProductCategoriesDataProviderA")

	public static Object[][] getValidDistrictAndCountiesDataA() {

		return TestDataFileReader.fillArrayWithDataFromFile(LinksToDataFiles.firstListOfProductCategories);

	}

	@DataProvider(name = "ProductCategoriesDataProviderB")

	public static Object[][] getValidDistrictAndCountiesDataB() {

		return TestDataFileReader.fillArrayWithDataFromFile(LinksToDataFiles.secondListOfProductCategories);

	}

}
