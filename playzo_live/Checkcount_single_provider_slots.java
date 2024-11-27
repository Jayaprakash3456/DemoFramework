package playzo_live;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Checkcount_single_provider_slots {

	/*
	      check  game count for single provider  for slots
	      // playzo_casino_games_count
	       //playzo_slots_games_count

	 */
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://playzo365.com");
		driver.findElement(By.id("access_code")).sendKeys("9951");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("LOGIN")));
		driver.findElement(By.linkText("LOGIN")).click();
		WebElement usernameField = driver.findElement(By.id("login_khelo_username"));
		WebElement passwordField = driver.findElement(By.id("login_khelo_password"));
		usernameField.sendKeys("test_qa2");
		passwordField.sendKeys("Test@123");
		driver.findElement(By.id("login_formkhelo")).click();

		System.out.println("Username is: " + usernameField.getAttribute("value"));
		System.out.println("Password is: " + passwordField.getAttribute("value"));
		System.out.println("------Login success------");
		Thread.sleep(6000);
		try {
			WebElement popup = driver.findElement(By.id("close_butnn_off"));
			if (popup.isDisplayed()) {
				popup.click();
			}
		} catch (NoSuchElementException e) {
			System.out.println("No pop-up found.");
		}
		driver.findElement(By.xpath("(//span[text()='Slots'])[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='onlyplay']")));
		Thread.sleep(2000);
		WebElement clickProvider = driver.findElement(By.xpath("//span[text()='mascot']"));
		clickProvider.click();
		String providerName = clickProvider.getText();
		System.out.println("Selected provider: " + providerName);
		Thread.sleep(2000);
		List<WebElement> allGames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@data-type='slots']")));
		for (int i = 0; i < allGames.size(); i++) {
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long lastScrollPosition = 0;
		long currentScrollPosition = 0;
		WebDriverWait waitForScroll = new WebDriverWait(driver, Duration.ofSeconds(5));
		while (true) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			waitForScroll.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
			currentScrollPosition = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");
			if (currentScrollPosition == lastScrollPosition) {
				break;
			}
			lastScrollPosition = currentScrollPosition;
			Thread.sleep(2000);
		}
		List<WebElement> allGamesAfterScroll = driver.findElements(By.xpath("//*[@data-type='slots']"));
		System.out.println("Total number of games count: " + allGamesAfterScroll.size());
		driver.quit();
	}
}



