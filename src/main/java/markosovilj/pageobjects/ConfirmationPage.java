package markosovilj.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import markosovilj.AbstractComponents.AbstractComponent;

public class ConfirmationPage  extends AbstractComponent{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css = "h1[class*='primary']" )
	WebElement message;
	
	public String ConfirmationMessage() {
		String confirmation = message.getText();
		return confirmation;
	}
	

}
