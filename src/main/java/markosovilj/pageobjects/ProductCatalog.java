package markosovilj.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import markosovilj.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By productsBy = By.cssSelector(".mb-3");
	By click = By.cssSelector(".btn.w-10.rounded");
	By toastMessage = By.id("toast-container");
	
	@FindBy(css=".mb-3")
	List<WebElement> productsListed;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	

	
	public WebElement getProductList(String productName) {	
		waitForElementToAppear(productsBy);
		WebElement prod =  productsListed.stream().filter(products->products.findElement(By.tagName("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProduct(WebElement product) throws InterruptedException {
		WebElement prod = product;
		product.findElement(click).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisapear(spinner);
	}
	
}
