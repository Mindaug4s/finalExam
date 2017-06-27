package exam.MRinkevicius;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class BaseTest {

	double testStartTime;
	double testEndTime;
	protected WebDriver driver;

	@BeforeMethod

	@Parameters("browserName")

	public void prepareForTest(@Optional("Firefox") String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxDriverManager.getInstance().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			ChromeDriverManager.getInstance().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			InternetExplorerDriverManager.getInstance().setup();
			driver = new InternetExplorerDriver();
		}
		testStartTime = System.currentTimeMillis();


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("http://88.119.151.54/opencartone/");

	}

	@AfterMethod
	public void closeBrowser() {

		testEndTime = System.currentTimeMillis();
		System.out.println("Time tests took: " + (testEndTime - testStartTime));

		driver.close();

	}

}
