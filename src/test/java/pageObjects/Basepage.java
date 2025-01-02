package pageObjects;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/*
    All the base object classes should be derived from the basepage. why done like this ? bcoz achieve reusability
*/

public class Basepage {

    WebDriver driver;

    public Basepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
