package markosovilj.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import markosovilj.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='infoWrap'] h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutEle;
	
	public Boolean cartCheck(String productName) {
		Boolean isInCart = cartProducts.stream().anyMatch(products->products.getText().equals(productName));
		return isInCart;
	}
	
	public CheckOutPage checkOut() {
		checkOutEle.click();
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
	
	
	
}
