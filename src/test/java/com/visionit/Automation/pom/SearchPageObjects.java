package com.visionit.Automation.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPageObjects {
	private static final Logger logger = LogManager.getLogger(SearchPageObjects.class);

	private WebDriver driver;
	private By searchBox=By.id("search_query_top");
	private By searchButton = By.name("submit_search");
	private By searchResult=By.xpath("//div[@id='center_column']/h1/span");

	//Section 3: Paratmerize the constuctor
	public SearchPageObjects(WebDriver driver){
		this.driver = driver;
	}
	
	public void SearchBoxFunctionality(String Pname)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver,20);
		WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBox));
		elementSearchBox.sendKeys(Pname);
		logger.info("Value entered in search box: " + Pname);
	
	}
	public void ClickOnSearchButton() 
	{
		driver.findElement(searchButton).click();
		logger.info("Clicked on Search Button");
	}

	public void ValidateProductSearchResult(String Pname)
	{
		String displayResult=driver.findElement(searchResult).getText();
		Assert.assertEquals(displayResult,Pname);
		logger.info("Result is displayed is :"+displayResult);

	}
//	public void SearchBoxValidtion()
//	{
//		if (driver.findElement(searchBox).isDisplayed())
//		{
//			Assert.assertTrue(true);
//			logger.info("Search Box is displayed is displayed");	           
//		}
//		else
//		{
//			logger.fatal("Search Box is not displayed is not displayed");
//			Assert.fail("Search Box is not displayed ");
//		}
//	}



}
