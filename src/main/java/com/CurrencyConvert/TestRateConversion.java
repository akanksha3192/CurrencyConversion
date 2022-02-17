package com.CurrencyConvert;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;



/**
 * @author Akanksha Singh
 *
 */
public class TestRateConversion {
 
	static WebDriver driver;

	@DataProvider(name = "test-data")
  	public Object[][] dataProvFunc(){
        	return new Object[][]{
              	{"EUR-Euro","USD-US Dollar"},{"EUR-Euro","GBP-British Pound"},{"EUR-Euro","INR-Indian Rupee"},{"EUR-Euro","AUD-Australian Dollar"},{"EUR-Euro","CAD-Canadian Dollar"}
        	};
  	}
  
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "C:/Users/Akanksha/Downloads/chromedriver_win32//chromedriver.exe");
	  
	  //Opening the main page 
	  WebDriver driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://www.xe.com/currencyconverter/");
	  
	  //Escaping out the pop up window
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.switchTo().defaultContent(); 
	 
	  }
	   
 


@Test(dataProvider ="test-data")
public void TestCurrencyConversion(String baseCurrency, String counterCurrency) {
	//Verifying the page title of the window
	WebElement PageTitle = driver.findElement(By.xpath("//*[@id='__next']/div[2]/div[2]/section/div[1]/h1"));
	assertTrue(PageTitle.getText().equals("XE Currency Converter"));
	
	//Selecting the base currency
	WebElement From_BaseCurrency = driver.findElement(By.xpath("//*[@id='from']"));
	From_BaseCurrency.sendKeys(baseCurrency);
	From_BaseCurrency.sendKeys(Keys.ENTER);
	
	//Selecting the counter currency
	WebElement To_CounterCurrency = driver.findElement(By.xpath("//*[@id='to']"));
	To_CounterCurrency.sendKeys(counterCurrency);
	To_CounterCurrency.sendKeys(Keys.ENTER);

	//Clicking on submit button
	WebElement submitButton = driver.findElement(By.xpath("//*[@id='ucc_go_btn_svg']"));
	submitButton.click();
	//
	WebElement conversionRate = driver.findElement(By.xpath("//*[@id='ucc-container']/span[2]/span[2]"));
	
	//This assert should be customized and it should get the Pound rate on that moment from an external system
	assertTrue(conversionRate.getText().equals(conversionRate.getText()));	

}
  
  
  @AfterClass
  public void afterClass() {
		driver.quit();	
  
  }

}