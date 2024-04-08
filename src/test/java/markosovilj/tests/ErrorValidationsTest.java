package markosovilj.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import markosovilj.TestComponents.BaseTest;
import markosovilj.TestComponents.Retry;
import markosovilj.pageobjects.CartPage;
import markosovilj.pageobjects.CheckOutPage;
import markosovilj.pageobjects.ConfirmationPage;
import markosovilj.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException  {
		
		landingPage.loginApplicattion("m.markovic@nesto.com", "KKraljevicmarko123");
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException  {
	
	String productName = "ADIDAS ORIGINAL";
	
	ProductCatalog productCatalog = landingPage.loginApplicattion("m.markovic@nesto.com", "Kraljevicmarko123");
	productCatalog.addProduct(productCatalog.getProductList(productName));
	CartPage cartPage  = productCatalog.goToCartPage();
	
	Assert.assertTrue(cartPage.cartCheck("ADIDAS ORIGINAL"));
	
}

}
