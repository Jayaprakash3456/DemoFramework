package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;  // Add this import for PageFactory
import testBase.Baseclass;

public class MyAccountPage extends Basepage {

    // Constructor to initialize the page object
    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize the elements
    }

    // WebElement for My Account Page heading
    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    // WebElement for Logout link
    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement lnkLogout;

    // Method to check if the My Account Page exists by checking the heading
    public boolean isMyAccountPageExists() {
        try {
            return msgHeading.isDisplayed(); // Return true if heading is visible
        } catch (Exception e) {
            return false; // Return false if any exception occurs (element not found or not visible)
        }
    }

    // Method to click on the Logout link
    public void clickLogout() {
        try {
            if (lnkLogout.isDisplayed() && lnkLogout.isEnabled()) {
                lnkLogout.click(); // Perform click if the element is visible and enabled
            } else {
                throw new Exception("Logout link is not clickable.");
            }
        } catch (Exception e) {
            // Log the exception or print stack trace (optional)
            System.out.println("Error clicking Logout: " + e.getMessage());
        }
    }
}
