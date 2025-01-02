package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage {

    public Loginpage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement linkMyaccount;

    @FindBy(xpath = "(//a[text()='Login'])[1]")
    WebElement linklogin;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;


     //  (//a[text()='Logout'])[2]

    @FindBy(xpath = "(//a[text()='Logout'])[2]")
    WebElement linklogout;


    public void clickMyAccount() {
        linkMyaccount.click();
    }

    public void clicklogin() {
        linklogin.click();
    }

    public void setEmail(String email) {
        txtEmailAddress.sendKeys(email);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public void clickLogout() {
        linklogout.click();
    }

}
