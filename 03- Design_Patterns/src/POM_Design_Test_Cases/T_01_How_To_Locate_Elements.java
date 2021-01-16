package POM_Design_Test_Cases;

import java.awt.print.Pageable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM_Design_HomePages.HomePages;
import POM_Design_HomePages.SignedIn_Page;

public class T_01_How_To_Locate_Elements {

	 WebDriver driver;
	





@BeforeTest
public  void Launch_Browser()
{
	
	String chromePath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", chromePath);
	driver = new ChromeDriver();

	driver.manage().window().maximize();
	driver.navigate().to("https://www.google.com/?hl=ar");

	driver.findElement(By.partialLinkText("Eng")).click();
}


@Test
public  void Login_With_Invalid_Email() throws InterruptedException
{
	
	HomePages page = new HomePages(driver);
	SignedIn_Page signedIn = new SignedIn_Page(driver);
	//driver.findElement(By.id("gb_70")).click();
	page.SignInButton().click();

	signedIn.SignInInput().sendKeys("asw1");

	signedIn.loginButton().click();

	Thread.sleep(2000);

	String actualResult;
	actualResult = signedIn.result().getText();

	System.out.println(actualResult);
	System.out.println(actualResult.equals("Couldn’t find your Google Account"));

}

@Test
public void Verify_Forgot_Email_URL()
{
   SignedIn_Page urlObject = new SignedIn_Page(driver);
   WebElement url = urlObject.SignInURL();
   url.click();
	//driver.findElement(By.xpath("//button[@jsname=\"Cuz2Ue\"]")).click();

	String actualResult;
	actualResult = driver.getCurrentUrl();
	
	System.out.println(actualResult);
	System.out.println(actualResult.contains("/signin/v2/usernamerecovery"));

}

@Test
public  void Google_Search() {
	driver.navigate().to("https://www.google.com/?hl=eng");
	HomePages page = new HomePages(driver);
	WebElement search = page.search();
	search.click();
	
	
	page.enterBtn("Selenium");
	page.enterBtn(Keys.ENTER);
	
	
	WebElement actual = page.result();
	System.out.println(actual.getText());
	System.out.println(actual.getText().contains("46,200,000"));
	
}


@AfterTest
public void Close_Driver()
{
	driver.quit();

}

}
