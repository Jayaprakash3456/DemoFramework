package playzo_live;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


@Test
public class playzo_casino_games_count {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	/*
	test status -- pass, fail , skip , info

	built in methods -

	startTest:	Executes preconditions of test case
	endTest:	Executes postconditions of test case
	Log: 		logs the status of each test step onto the html report being generated 
	Flush:		Erase any previous data on a relevangt report and creates a whole new report

	syntax : 	test.log(LogStatus.PASS,"TEST PASSED");		


 // Take a screenshot of the button after login
	    WebElement loginpage = driver.findElement(By.xpath("(//div[@id='__layout'])[1]"));
	    File srcfile  = loginpage.getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(srcfile, new File(System.currentTimeMillis()+("C:\\Users\\Jayaprakash\\Desktop\\Auto\\img2.png" )));
	    test.log(Status.PASS, "Login page screenshot captured: " + srcfile.getAbsolutePath());



	 */
	@BeforeClass
	public void setUp() {
		// Initialize ExtentReports
		ExtentSparkReporter spark = new ExtentSparkReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

		// Initialize the ChromeDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test(priority = 1)
	public void loginTest() throws InterruptedException, IOException {
		test = extent.createTest("Login Test");

		driver.get("https://playzo365.com");

		// Log in process
		driver.findElement(By.id("access_code")).sendKeys("9951");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("LOGIN"))).click();
		WebElement username = driver.findElement(By.id("login_khelo_username"));
		username.sendKeys("test_qa2");
		WebElement password = driver.findElement(By.id("login_khelo_password"));
		password.sendKeys("Test@123");
		Thread.sleep(2000);
		driver.findElement(By.id("login_formkhelo")).click();
		// Log the credentials for validation
		System.out.println("Username is: " + username.getAttribute("value"));
		System.out.println("Password is: " + password.getAttribute("value"));
		System.out.println(" *********** Login successful *********** ");
		test.pass("Username: test_qa2");
		test.pass("Password: Test@123");
		test.pass(" *********** Login successful *********** ");
		
		Thread.sleep(6000);
		// Handling potential pop-up
		WebElement popup = driver.findElement(By.id("close_butnn_off"));
		if (popup.isDisplayed()) {
			popup.click();
		}
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void casino_Provider_List() {
		test = extent.createTest("Casino_Provider_List");
		driver.findElement(By.xpath("(//span[text()='Live Casino'])[2]")).click();
		// Wait for elements to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> allproviders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='left-des-cnt-inner cls_provider_casinonew_desk_clk']")));
		allproviders.remove(0); // Removing the first entry as it might not be a provider
		for (WebElement allGame : allproviders) {
			String text = allGame.getText();
			System.out.println(text);
			test.pass(text);
		}
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			long lastScrollPosition = 0;
			long currentScrollPosition = 0;
			while (true) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
				currentScrollPosition = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");
				if (currentScrollPosition == lastScrollPosition) {
					break;
				}
				lastScrollPosition = currentScrollPosition;
				Thread.sleep(2000);
			}

		} catch (InterruptedException e) {
			test.fail("Page is not scrollable or interrupted: " + e.getMessage());
		}
		List<WebElement> allgames = driver.findElements(By.xpath("//*[@class='filterDiv Roulette casinoLink new_border_fil']"));
		System.out.println("Total casino Providers count:" +""+ allproviders.size());
		System.out.println("Total Number of games count:" +""+allgames.size());
		test.pass("Total casino Providers count:" +""+ allproviders.size());
		test.pass("Total casino Games count:"+""+  allgames.size());
	}

	@Test(priority =3 )
	public void casino_Allgames_count() throws InterruptedException {
		test = extent.createTest("casino_Allgames_count");
		driver.findElement(By.xpath("(//span[text()='Live Casino'])[2]")).click();
		Thread.sleep(2000);
		// List of providers
		List<WebElement> providers = driver.findElements(By.xpath("//*[@class='left-des-cnt-inner cls_provider_casinonew_desk_clk']"));
		for (int j = 1; j < providers.size(); j++) {
			WebElement provider = providers.get(j);
			provider.click();
			String providerName = provider.getText();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='filterDiv Roulette casinoLink new_border_fil']")));

			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				long lastScrollPosition = 0;
				long currentScrollPosition = 0;

				while (true) {
					js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
					wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
					currentScrollPosition = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");
					if (currentScrollPosition == lastScrollPosition) {
						break;
					}
					lastScrollPosition = currentScrollPosition;
					Thread.sleep(2000); // Allow for content to load before scrolling again
				}

			} catch (InterruptedException e) {
				test.fail("Page is not scrollable or interrupted: " + e.getMessage());
			}

			// Wait for page to load after scrolling
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='filterDiv Roulette casinoLink new_border_fil']")));

			// Count and print the total number of games for the current provider
			List<WebElement> gameCount = driver.findElements(By.xpath("//*[@class='filterDiv Roulette casinoLink new_border_fil']"));
			System.out.println("Total game count for " + providerName + "-" +gameCount.size());
			test.pass("Total game count for " + providerName + "-" +gameCount.size());
			Thread.sleep(3000);
		}
	}


	@Test(priority = 4)
	public void errorTest() {
		test = extent.createTest("Error Test");

		// Simulating an error scenario
		try {
			// Simulate error here (you can replace with actual failure condition)
			throw new Exception("Error Identification");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			test.log(Status.WARNING,"This test case it throws a warning message");
			test.log(Status.FAIL,"Fail Test");	
			test.fail("Error occurred: " + e.getMessage());
		}
	}

	@Test(priority = 5)
	public void ValidateTitle() {
		test.log(Status.INFO,"validateTitle is started");
		String title = driver.getTitle();
		System.err.println(title);
		test.log(Status.PASS,title);	
	}

	@Test(priority = 6)
	public void skipTest() {
		test.log(Status.SKIP,"Skip Test");
	}	

	@AfterClass
	public void tearDown() {
		// Close the browser
		if (driver != null) {
			driver.quit();
			test.info("Browser closed");
		}
		extent.flush();  // Save the report
	}
}



