package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsHomePage 
{
	private WebDriver driver;
	
	@FindBy(id="shopping_cart_container")	
	private WebElement basket;
	
	@FindBy(xpath="//span[@class='title']")	
	private WebElement prodLabel;
	
	@FindBy(xpath="//div[@data-test='inventory-item']")
	private List<WebElement> cartItems;
	
	@FindBy(xpath="//div[@data-test='inventory-item']//button[contains(.,'Add to cart')]")	
	private List<WebElement> cartButtons;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	private WebElement shopBagLink;
	
	@FindBy(xpath="//div[@class='inventory_item_price']")
	private List<WebElement> bagItemsCosts;
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement menuButton;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement logoutLink;
	
	private By item1_CartButton;
	private By itemCost;
	
	
	
	public SwagLabsHomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public boolean verifyShoppingPageExistence()
	{
		return basket.isDisplayed();
	}
	public String getProductLabelText()
	{
		return prodLabel.getText();
	}
	public int getShoppingItemsCount()
	{
		return cartItems.size();
	}
	public int getAddCartButtonsCount()
	{
		return cartButtons.size();
	}
	public List<WebElement> getHomePageCartButtonsList()
	{
		return cartButtons;
	}
	public void addItemToCart(String itemname)	
	{
		
		String[] words = itemname.split(" ");
		String lastWord = words[words.length-1].toLowerCase();
		
		String xpathText1 = "//div[text()='";
		String xpathText2 = "']/following::button[contains(@id,'"+lastWord+"')]";
		String fullXpath = xpathText1+itemname+xpathText2;
		item1_CartButton = By.xpath(fullXpath);
		driver.findElement(item1_CartButton).click();
		
	}
	public String findItemCost(String itemName)
	{
		String xpathText1 = "//div[text()='";
//		String xpathText2 = "']/following::div[@class='inventory_item_price']/child::text()[2]";
//		']/ancestor::div[@class='inventory_item_label']/following::div[2]/text()[2]
		String xpathText2 = "']/ancestor::div[@class='inventory_item_label']/following::div[2]";
		
		String fullXpath = xpathText1+itemName+xpathText2;
		itemCost = By.xpath(fullXpath);
		return driver.findElement(itemCost).getText();
		
	}
	public void clickShopBag()
	{
		shopBagLink.click();
	}
	public List<WebElement> getShopBagItemsCosts()
	{
//		bagItemsCosts = By.xpath("//div[@class='inventory_item_price']/text()[2]");
		return bagItemsCosts;
	}
	public void clickThreeLinesMenuButton()
	{
		menuButton.click();
	}
	public void clickLogoutLink()
	{
		logoutLink.click();
	}
}
