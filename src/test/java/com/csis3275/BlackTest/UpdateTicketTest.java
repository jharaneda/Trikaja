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

public class UpdateTicketTest {

	private static WebDriver driver;
	private static Map<String, Object> vars;
	static JavascriptExecutor js;

	@BeforeAll
	static void setUp() {
		// Setup the Chrome Driver

		System.setProperty("webdriver.chrome.driver", "c:\\temp\\chromedriver.exe");
		driver = new ChromeDriver();

		// Setup for firefox on my local linux PC
//			System.setProperty("webdriver.gecko.driver","/tmp/geckodriver");
//			driver = new FirefoxDriver();

		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterAll
	static void tearDown() {
		driver.quit();
	}

	@Test
	void updateTicket() {
		driver.get("http://localhost:8080/");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("Kneale95@hotmail.ca");
		driver.findElement(By.id("password")).sendKeys("Kneale95");
		driver.findElement(By.cssSelector("input:nth-child(3)")).click();
		
		
		driver.get("http://localhost:8080/manager/tickets/all");
		driver.manage().window().setSize(new Dimension(1936, 1056));
		driver.findElement(By.cssSelector(".home-section")).click();
	    driver.findElement(By.id("btn8")).click();
		driver.findElement(By.id("floatingTextarea")).click();
		driver.findElement(By.id("floatingTextarea")).sendKeys("THIS IS A COMMENT FROM BLACK TEST: HELLO WORLD");
		driver.findElement(By.cssSelector(".btn")).click();
		
		assertEquals("Updated Ticket 8", driver.findElement(By.id("mainAlertMessage")).getText());
	}
}
