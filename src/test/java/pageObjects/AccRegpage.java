package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
    In the page object class we should not do any validations.
    validations should be always do inside the testcase. not inside the page object classes,


    When create page object -- we have to create 3 parts -- constructor , locators , actions methods

*/
public class AccRegpage extends Basepage {


    public AccRegpage(WebDriver driver) {
        super(driver);
    }

    /*
@FindBy(xpath="//input[@id='input-lastname']")
@FindBy(xpath="//input[@id='input-firstname']")
@FindBy(xpath="//input[@id='input-email']")
@FindBy(xpath="//input[@name='agree']")
@FindBy(xpath="//button[normalize-space()='Continue']")
@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
@FindBy(xpath="//input[@id='input-telephone']")
@FindBy(xpath="//input[@id='input-password']")
@FindBy(xpath="//input[@id='input-confirm']")
    */


    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement msgConfirmation;




    public void setFirstname(String fname) {
        txtFirstname.sendKeys(fname);
        System.out.println("Entered firstname: " + txtFirstname.getAttribute("value"));
    }

    public void setLastname(String lname) {
        txtLastname.sendKeys(lname);
        System.out.println("Entered lastname: " + txtLastname.getAttribute("value"));
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
        System.out.println("Entered email: " + txtEmail.getAttribute("value"));
    }

    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);
        System.out.println("Entered TelephoneNumber: " + txtTelephone.getAttribute("value"));
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
        System.out.println("Entered password: " + txtPassword.getAttribute("value"));
    }

    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd);
        System.out.println("Entered confirmpassword: " + txtConfirmPassword.getAttribute("value"));
    }

    public void setPrivacyPolicy() {
        chkPolicy.click();
    }

    public void clickContinue() {
        btnContinue.click();


//        sol2
//        btnContinue.submit();

//        sol3
//        Actions act = new Actions(driver);
//        act.moveToElement(btnContinue).click().perform();

//        sol4
//        JavascriptExecutor js =(JavascriptExecutor)driver;
//        js.executeScript("arguments[0].click();", btnContinue);

//        sol5
//        btnContinue.sendKeys(Keys.RETURN);

//        sol6
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("btnContinue"))).click();


    }

    public String getConfrimationMsg() {
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }



}
