package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Loginpage;
import pageObjects.MyAccountPage;
import testBase.Baseclass;

public class TC02_Login extends Baseclass {

    @Test
    public void verify_login() throws InterruptedException {

        Loginpage lp = new Loginpage(driver);
        lp.clickMyAccount();
        lp.clicklogin();
        lp.setEmail(p.getProperty("Email"));
        lp.setPassword(p.getProperty("Password"));
        lp.clickLogin(); // click Login button

        try {

            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();
            Assert.assertTrue(targetPage, "Login failed: My Account page is not displayed.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }

        lp.clickLogout();

    }
}


