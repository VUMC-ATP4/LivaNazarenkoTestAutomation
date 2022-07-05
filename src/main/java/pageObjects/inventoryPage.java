package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class inventoryPage {
    public WebDriver driver;
    private By pageTitle = By.cssSelector("div[id='header_container'] span[class='title']");
    private By linkedinLink = By.linkText("LinkedIn");



    public inventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageTitle(){
        return driver.findElement(pageTitle);
    }

    public WebElement getLinkedinLink(){
        return driver.findElement(linkedinLink);
    }

}


