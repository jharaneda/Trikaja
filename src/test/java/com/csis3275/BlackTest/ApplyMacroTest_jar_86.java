package com.csis3275.BlackTest;


import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class ApplyMacroTest_jar_86 {
	private static WebDriver driver;
	private static Map<String, Object> vars;
	static JavascriptExecutor js;

	@BeforeAll
	static void setUp() {
		// Setup the Chrome Driver

		System.setProperty("webdriver.chrome.driver", "c:\\temp\\chromedriver.exe");
		driver = new ChromeDriver();

		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterAll
	static void tearDown() {
		driver.quit();
	}
	/*
	 * This method test the application of a macro which will add text into the comment section of the ticket. It is expected to work. 
	 */
	@Test
	public void applyMacro() {
		driver.get("http://localhost:8080/");
		driver.manage().window().setSize(new Dimension(1936, 1056));
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("Kneale95@hotmail.ca");
		driver.findElement(By.id("password")).sendKeys("Kneale95");
		driver.findElement(By.cssSelector("input:nth-child(3)")).click();
		
		
		driver.get("http://localhost:8080/manager/tickets/all");
		driver.findElement(By.cssSelector(".home-section")).click();
		driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(8)")).click();
	    driver.findElement(By.id("btn8")).click();
	    driver.findElement(By.id("macro")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("macro"));
	      dropdown.findElement(By.xpath("//option[. = 'Expired']")).click();
	    }
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.id("btn8")).click();
		
		
		assertEquals("Hi, We havent receive any reply from you. So we will close the ticket for now.", driver.findElement(By.cssSelector(".col:nth-child(2) > #ticketList tr:nth-child(4) > td")).getText());
		
	}
}
