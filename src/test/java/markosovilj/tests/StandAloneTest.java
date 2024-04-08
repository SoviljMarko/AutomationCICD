package markosovilj.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import markosovilj.TestComponents.BaseTest;
import markosovilj.pageobjects.CartPage;
import markosovilj.pageobjects.CheckOutPage;
import markosovilj.pageobjects.ConfirmationPage;
import markosovilj.pageobjects.OrderPage;
import markosovilj.pageobjects.ProductCatalog;

public class StandAloneTest extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";
		
		@Test(dataProvider = "getData", groups = {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException  {
		
		ProductCatalog productCatalog = landingPage.loginApplicattion(input.get("email"), input.get("password"));
		productCatalog.addProduct(productCatalog.getProductList( input.get("productName")));
		CartPage cartPage  = productCatalog.goToCartPage();
		
		Assert.assertTrue(cartPage.cartCheck(input.get("productName")));
		
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.RightCountry("unite");
		ConfirmationPage confirmationPage = checkOutPage.SubmitIt();
		Assert.assertTrue(confirmationPage.ConfirmationMessage().equalsIgnoreCase( "THANKYOU FOR THE ORDER."));
		
	}
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() {
			ProductCatalog productCatalog = landingPage.loginApplicattion("m.markovic@nesto.com", "Kraljevicmarko123");
			OrderPage orderPage = productCatalog.goToOrderPage();
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		}
		
		@DataProvider
		public Object[][] getData() throws IOException  {
			
			List<HashMap<String, String>>  data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//markosovilj//data//PurchaseOrder.json");
			
			return new Object [][] {{data.get(0)}, {data.get(1)}};
		}

//		HashMap<String, String> map = new HashMap<String, String>();
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		
//		map.put("email", "m.markovic@nesto.com");
//		map.put("password", "Kraljevicmarko123");
//		map.put("productName", "ADIDAS ORIGINAL");
//		
//		map2.put("email", "shetty@gmail.com");
//		map2.put("password", "Iamking@000");
//		map2.put("productName", "IPHONE 13 PRO");
		
		
		
		
}
