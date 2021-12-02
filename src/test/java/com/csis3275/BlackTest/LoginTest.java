package com.csis3275.BlackTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private static WebDriver driver;
	private static Map<String, Object> vars;
	static JavascriptExecutor js;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		// Setup the Chrome Driver
		
		System.setProperty("webdriver.chrome.driver", "c:\\temp\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Setup for firefox on my local linux PC
//		System.setProperty("webdriver.gecko.driver","/tmp/geckodriver");
//		driver = new FirefoxDriver();
		
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterAll
	static void tearDown() throws Exception{
//		driver.quit();
	}

	@Test
	void badLogin() {
		driver.get("http://localhost:8080/");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("Kneale95@hotmail.ca");
		driver.findElement(By.id("password")).sendKeys("Kneale9525");
		driver.findElement(By.cssSelector("input:nth-child(3)")).click();
		
		assertEquals("Holy moly! Your credentials are wrong. Please try again", driver.findElement(By.id("mainAlertMessage")).getText());
	}
	
	@Test
	void goodLogin() {
		driver.get("http://localhost:8080/");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("Kneale95@hotmail.ca");
		driver.findElement(By.id("password")).sendKeys("Kneale95");
		driver.findElement(By.cssSelector("input:nth-child(3)")).click();
		
		assertEquals("All Tickets", driver.findElement(By.cssSelector(".container > h1")).getText());
	}
}
