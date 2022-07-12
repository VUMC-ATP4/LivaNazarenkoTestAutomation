package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    public static WebDriver driver;
    private By addToCartList = By.id("add-to-cart-sauce-labs-backpack");
    private By removeButton = By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getAdCartList(){

        return driver.findElement(addToCartList);
    }
    public WebElement getRemoveButton(){
        return driver.findElement(removeButton);
    }
}
