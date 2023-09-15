package tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	WebDriver driver;
	
	@BeforeMethod
	public void setup(){
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	@Test(priority = 1)
	public void LoginWithValidCredentials() {

	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Login")).click();
	driver.findElement(By.id("input-email")).sendKeys("rajesh123@gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("rajesh@123");
	driver.findElement(By.cssSelector("input[type='submit']")).click();
	Assert.assertTrue(driver.findElement(By.linkText("Edit your account informationABC")).isDisplayed());
	
}
	
	@Test(priority = 2)
	public void LoginWithInvalidCredentials() {
		
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Login")).click();
	driver.findElement(By.id("input-email")).sendKeys("rajesh123@"+generateTimeStamp()+"gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("rajesh@123");
	driver.findElement(By.cssSelector("input[type='submit']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText().contains("Warning: No match for E-Mail Address and/or Password."));
}
	
	@Test(priority = 3,dependsOnMethods = {"LoginWithValidCredentials"})
	public void LoginWithoutCredentials() {

	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Login")).click();
	driver.findElement(By.id("input-email")).sendKeys("");
	driver.findElement(By.id("input-password")).sendKeys("");
	driver.findElement(By.cssSelector("input[type='submit']")).click();
	//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText().contains("Warning: No match for E-Mail Address and/or Password."));
}
	
	public String generateTimeStamp() {
		
		Date date = new Date();
		return date.toString().replace(" ","_").replace(":","_");
		
	}
	
}
