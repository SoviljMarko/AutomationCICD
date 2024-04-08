package markosovilj.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import markosovilj.TestComponents.BaseTest;
import markosovilj.pageobjects.CartPage;
import markosovilj.pageobjects.CheckOutPage;
import markosovilj.pageobjects.ConfirmationPage;
import markosovilj.pageobjects.LandingPage;
import markosovilj.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page () throws IOException {
		landingPage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and  password (.+)$")
	public void Logged_in_username_and_password (String username, String password) {
		productCatalog = landingPage.loginApplicattion(username, password);
	}
	
	@When ("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException  {
		productCatalog.addProduct(productCatalog.getProductList(productName));
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_order(String productName) {
		CartPage cartPage  = productCatalog.goToCartPage();
		
		Assert.assertTrue(cartPage.cartCheck(productName));
		
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.RightCountry("unite");
		confirmationPage = checkOutPage.SubmitIt();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		Assert.assertTrue(confirmationPage.ConfirmationMessage().equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
//		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		driver.close();
	}
	
	
	
	
}
