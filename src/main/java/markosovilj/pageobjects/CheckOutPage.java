package markosovilj.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import markosovilj.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countrySelect;
	
	@FindBy(xpath="//section/button/span")
	List<WebElement> countryOptions;
	
	@FindBy (css="a[class*='submit']")
	WebElement submit;
	
	public void RightCountry(String country) {
		countrySelect.sendKeys(country);
		List<WebElement> options = countryOptions;
		options.stream().filter(s->s.getText().equals("United Kingdom")).findFirst().orElse(null).click();
	}
	
	public ConfirmationPage SubmitIt() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	
}
