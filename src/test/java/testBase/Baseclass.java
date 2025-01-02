package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Baseclass {

    public WebDriver driver;
    public Properties p;
    @BeforeClass
    @Parameters({"browser"})
    public void setup(String br) throws IOException {
        // Initialize the Properties object here to make sure it's the instance variable
        p = new Properties();

        // Loading config.properties file
        try (FileReader file = new FileReader("./src/config.properties")) {
            p.load(file);

        } catch (IOException e) {
            e.printStackTrace();
        }


        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser");
                return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Implicit wait
        driver.manage().window().maximize();
      //  driver.get("https://tutorialsninja.com/demo/");
        driver.get(p.getProperty("AppURL1"));// reading url from properties file
        System.out.println(driver.getCurrentUrl());
        System.out.println("Opening the application");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Quit the WebDriver and close the browser window
            System.out.println("Browser closed");
        }
    }

    // Random string generation methods
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);  // Generate a random 5-character alphabetic string
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);  // Generate a random 10-digit numeric string
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(4) + RandomStringUtils.randomNumeric(4);  // Random alphanumeric string
    }
}
