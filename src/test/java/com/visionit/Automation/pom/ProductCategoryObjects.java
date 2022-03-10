package com.visionit.Automation.pom;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCategoryObjects {
	 private static final Logger logger = LogManager.getLogger(ProductCategoryObjects.class);

	    private WebDriver driver;
	    private By productNameList =By.xpath("//div[@id='block_top_menu']/ul/li");
	    
	    public ProductCategoryObjects(WebDriver driver)
	    {
	    	this.driver=driver;
	    }
	    public void ProductCategoryValidation(int NoOfCategory)
	    {
	    	List<WebElement> productList=driver.findElements(productNameList);
			int count=0;
			for(int i=0;i<productList.size();i++)
			{
				String Productname=productList.get(i).getText();

				logger.info("Name Of Products:"+i+"]"+Productname);
				count++;
				
			}
			
			Assert.assertEquals(count,NoOfCategory);
			logger.info("Categories Of Product:"+count);
		
	    }
	    public void NoOfProductCategory()
	    {
	    	List<WebElement> productList=driver.findElements(productNameList);
			
			logger.info("the displayed categories are:"+productList.size());
				
		
	    }
}
