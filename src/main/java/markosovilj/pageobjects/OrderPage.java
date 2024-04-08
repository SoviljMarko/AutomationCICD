package markosovilj.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import markosovilj.AbstractComponents.AbstractComponent;

public class OrderPage  extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean VerifyOrderDisplay(String productName) {
			Boolean isInCart = productNames.stream().anyMatch(products->products.getText().equalsIgnoreCase(productName));
			return isInCart;
	}

}
