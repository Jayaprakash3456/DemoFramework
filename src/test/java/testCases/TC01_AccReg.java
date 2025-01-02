package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccRegpage;
import pageObjects.Homepage;
import pageObjects.Loginpage;
import testBase.Baseclass;

public class TC01_AccReg extends Baseclass {

    @Test
    public void verify_acc_reg() {
        try {
            Loginpage lp = new Loginpage(driver);
            Homepage hp = new Homepage(driver);
            hp.clickMyAccount();
            hp.clickRegister();

            AccRegpage reg = new AccRegpage(driver);

            reg.setFirstname(randomString().toUpperCase());
            reg.setLastname(randomString().toUpperCase());
            reg.setEmail(randomString() + "@gmail.com");
            reg.setTelephone(randomNumber());

            String password = randomAlphaNumeric();
            reg.setPassword(password);
            reg.setConfirmPassword(password);

            reg.setPrivacyPolicy();
            reg.clickContinue();

            String confmsg = reg.getConfrimationMsg();
            Assert.assertEquals(confmsg, "Your Account Has Been Created!");

            lp.clickLogout();

        } catch (Exception e) {
            System.out.println("Test failed due to the following exception: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}