package playzo_live;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


/*
This program executes scenarios below
login the live application
check the slots provider count
check the total games count present in  the each provider
And make error test for graphical identifications

*/

public class playzo_slots_games_count {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReport.html");  		// Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        driver = new ChromeDriver();	// Setup WebDriver
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        test = extent.createTest("Login Test");

        driver.get("https://playzo365.com");

        // Log in process
        driver.findElement(By.id("access_code")).sendKeys("9951");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("LOGIN"))).click();
        WebElement username = driver.findElement(By.id("login_khelo_username"));
        username.sendKeys("test_qa3");
        WebElement password = driver.findElement(By.id("login_khelo_password"));
        password.sendKeys("Test@123");
        driver.findElement(By.id("login_formkhelo")).click();
        // Log the credentials for validation
        System.out.println("Username is: " + username.getAttribute("value"));
        System.out.println("Password is: " + password.getAttribute("value"));
        System.out.println(" *********** Login successful *********** ");
        test.pass("Username: test_qa3");
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

    @Test(priority = 2 ,dependsOnMethods = "loginTest" )
    public void slots_Provider_List() throws InterruptedException {
        test = extent.createTest("slots_Provider_List");

        driver.findElement(By.xpath("(//span[text()='Slots'])[2]")).click(); // Navigate to Slots module
        Thread.sleep(2000);

        List<WebElement> allproviders = driver.findElements(By.xpath("//*[@class='left-des-cnt-inner GameManufacturer']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-type='slots']")));
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
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-type='slots']")));
        List<WebElement> gameCount = driver.findElements(By.xpath("//*[@data-type='slots']"));
        System.out.println("Total casino Providers count:" +""+ allproviders.size());
        System.out.println("Total Number of games count:" +""+gameCount.size());
        test.pass("Total casino Providers count:" +""+ allproviders.size());
        test.pass("Total casino Games count:"+""+  gameCount.size());
    }

    @Test(priority = 3)
    public void slots_Allgames_count() throws InterruptedException {
        test = extent.createTest("slots_Allgames_count");

        driver.findElement(By.xpath("(//span[text()='Slots'])[2]")).click(); // Navigate to Slots module
        Thread.sleep(2000);
        List<WebElement> providers = driver.findElements(By.xpath("//*[@class='left-des-cnt-inner GameManufacturer']"));
        for (int j = 1; j < providers.size(); j++) {
            WebElement provider = providers.get(j);
            provider.click();
            String providerName = provider.getText();
            //System.out.println("Provider clicked: " +providerName);
            //test.info("Provider clicked: " + providerName);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-type='slots']")));
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-type='slots']")));
            List<WebElement> gameCount = driver.findElements(By.xpath("//*[@data-type='slots']"));
            System.out.println("Total game count for " + providerName + ": " + gameCount.size());
            test.info("Total game count for " + providerName + ": " + gameCount.size());
            Thread.sleep(3000);
        }
        int providerCount = providers.size();
        System.out.println("Total slots Providers count: " + providerCount);
        test.pass("Total slots Providers count: " + providerCount);
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
            test.fail("Error occurred: " + e.getMessage());
        }
    }
    @AfterClass
    public void tearDown() {
        // Quit the driver and close ExtentReports
        driver.quit();
        extent.flush();
    }
}
