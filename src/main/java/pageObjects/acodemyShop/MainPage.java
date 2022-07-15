package pageObjects.acodemyShop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MainPage {

  public WebDriver driver;
  String searchParam;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

   private By searchInputField = By.id("woocommerce-product-search-field-0");
    private By searchResults = By.className("product");

    public WebElement getSearchInputField(){
        return driver.findElement(searchInputField);
    }

    public void searchProduct(String searchParam) throws InterruptedException {
        getSearchInputField().clear();// vienmēr pirms meklēšanas notīrīs meklēšanas lauku
        Thread.sleep(2000);
        getSearchInputField().sendKeys(searchParam);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
    }

    public List<WebElement>getSearchResults(){//saraksts ar Webelementiem
        return driver.findElements(searchResults);
    }

}
