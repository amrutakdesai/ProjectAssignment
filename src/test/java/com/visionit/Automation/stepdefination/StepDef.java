package com.visionit.Automation.stepdefination;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.visionit.Automation.core.WebDriverFactory;
import com.visionit.Automation.pom.LogoPageObjects;
import com.visionit.Automation.pom.ProductCategoryObjects;
import com.visionit.Automation.pom.SearchPageObjects;
import com.visionit.Automation.pom.SocialMediaObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {

	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	String base_url = "http://automationpractice.com";
	int implicit_wait_timeout_in_sec = 20;
	Actions act;

	/***************************************************************************************************************************/

	private static final Logger logger = LogManager.getLogger(StepDef.class);

	SearchPageObjects searchPageObjects;
	SocialMediaObjects socialMediaObjects;
	ProductCategoryObjects productCategoryObjects;
	LogoPageObjects logoPageObjects ;

	/***************************************************************************************************************************/

	@Before
	public void setup(Scenario scn) throws Exception
	{
		this.scn=scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getDriverForBrowser(browserName);
		logger.info("Browser is invoked.");
		scn.log("Browser is invoked.");



		searchPageObjects = new SearchPageObjects(driver);
		socialMediaObjects=new SocialMediaObjects(driver);
		productCategoryObjects=new ProductCategoryObjects(driver);
		logoPageObjects=new LogoPageObjects(driver);
	}
	/***************************************************************************************************************************/


	@Given("Navigate to url")
	public void navigate_to_url() 
	{
		WebDriverFactory.navigateToUrl(base_url);
		String TitleOfLandingPageVariable="My Store";
		scn.log("Navigated to the Url:"+base_url);
		Assert.assertEquals(TitleOfLandingPageVariable,driver.getTitle());	

	}
	@Then("Verify the url")
	public void verify_the_url()
	{
		String actual=driver.getCurrentUrl();
		String expected="http://automationpractice.com/index.php";
		Assert.assertEquals(expected, actual);  
		scn.log("Validation Of Current Url of the Page is SuccessFull:"+actual);
		logger.info("Current Url of the Page:"+actual);


	}
	//Logo functinality

	@Then("Test the Height {string} and Width {string}")
	public void test_the_height_and_width(String height, String width) {
		logoPageObjects.LogoIsDisplayed();
		scn.log("Logo is displayed:");

		logoPageObjects.LogoSizeValidation(height,width);

		scn.log("Validation of logo is successfull:"+"Width Of Logo is:"+width+"\nHeight of Logo is:"+height);
		logger.info("Validation of logo is successfull:"+"Width Of Logo is:"+width+"\nHeight of Logo is:"+height);

	}
	//Product Functionality implementation

	@And("Test whether the Home page has {int} product categories")
	public void test_whether_the_home_page_has_product_categories(int NoOfProductInCategory) {
		productCategoryObjects.ProductCategoryValidation(NoOfProductInCategory);
		scn.log("Validation Of Product Categories On Home Page:"+NoOfProductInCategory);

	}

	@Then("Test Home page must contains only three Product categories")
	public void test_home_page_must_contains_only_three_product_categories(){
		productCategoryObjects.NoOfProductCategory();
		scn.log("Home Page Contains Only 3 Product Categories");


	}
	//SearchProductFuctionality
	@When("User Search for product {string}")
	public void user_search_for_product(String Pname) {

		searchPageObjects.SearchBoxFunctionality(Pname);
		scn.log("Entered "+Pname+" in Search Box");

		searchPageObjects.ClickOnSearchButton();
		scn.log("Clicked on Search Button");
		scn.log("Product Searched: " + Pname);


	}
	@Then("Test Search Product contains is displyed result {string}")
	public void test_search_product_contains_is_displyed_result(String Pname) {
		searchPageObjects.ValidateProductSearchResult(Pname);
		scn.log("Search Result Validation Successfull:"+Pname);

	}


	//Social media Functionality

	@When("User click On SocialMedia Link")
	public void user_click_on_social_media_link() {

		socialMediaObjects.ClickOnButton();
		scn.log("Clicked on Button:");
	}



	@Then("Verify a URL containing Parameters {string}")
	public void verify_a_url_containing_parameters(String UrlContains) {
		WebDriverFactory.switchedBrowserToTab();
		socialMediaObjects.UrlValidation(UrlContains);
		scn.log("Current Url is:"+driver.getCurrentUrl());
		scn.log("Current Url Contains this "+UrlContains);

	}

	@Then("Verify Account Name {string}")
	public void verify_account_name(String AcName) {
		socialMediaObjects.AccountNameValidation(AcName);
		scn.log("Validation Successfull Account name is:"+AcName);
		
	}






	@After (order=1)
	public void cleanUp()
	{
		//driver.quit();
		WebDriverFactory.quitDriver();
		scn.log("Browser Closed");
	}
	@After(order=2)
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) 
		{
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}
		else
		{
			scn.log("Test case is passed, no screen shot captured");
		}
	}


}
