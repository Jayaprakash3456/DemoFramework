package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage {

    /*

            Without creating this constructor, we cannot invoke the parent class constructor
            (This is inheritance reusability)

            When create page object -- we have to create 3 parts -- constructor , locators , actions methods

    */

    public Homepage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement linkMyaccount;

    @FindBy(xpath = "(//a[text()='Register'])[1]")
    WebElement linkRegister;


    public void clickMyAccount() {
        linkMyaccount.click();
    }

    public void clickRegister() {
        linkRegister.click();
    }

}
