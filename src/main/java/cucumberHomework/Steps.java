package cucumberHomework;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.inventoryPage;
import pageObjectsHomework.*;

import java.time.Duration;

public class Steps {
    WebDriver driver;
    WebDriverWait wait;

    public final String SAUCELABSURL = "https://www.saucedemo.com/";
    public final String SAUCELABS_CART = "https://www.saucedemo.com/cart.html";
    public final String SAUCELABS_CHECKOUT = "https://www.saucedemo.com/checkout-step-one.html";

    pageObjects.LoginPage loginPage = new pageObjects.LoginPage(driver);
    pageObjects.inventoryPage inventoryPage = new inventoryPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
    CheckoutSuccessPage checkoutSuccessPage = new CheckoutSuccessPage(driver);


  @BeforeAll
    public void setupBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public void finishBrowser() {
        driver.close();
    }

    @Given("User is navigate to Soucelabs page")
    public void user_is_navigate_to_Soucelabs_page() {

      driver.get(SAUCELABSURL);
    }

    @When("user login with username {string} and password {string}")
    public void user_login_with_username_and_pasword(String username, String password) {
        loginPage.login("standard_user", "secret_sauce");
    }

    @And("user clicks login button")
    public void user_clicks_login_button() {
       loginPage.getLoginButton().click();
    }
@Given ("user see inventory page")
    public void user_see_inventory_page(){
    Assert.assertEquals(inventoryPage.getPageTitle().getText(), "PRODUCTS");
}

    @When ("user put one product in the cart")
    public void user_put_one_product_in_the_cart(){
        cartPage.getAdCartList().click();
    }

    @And ("go to cart")
    public void user_go_to_cart(){
        driver.get(SAUCELABS_CART);
    }

    @Then ("user check is the product in the cart")
    public void check_the_product_in_the_cart(){

        Assert.assertEquals(cartPage.getRemoveButton().getText(), "REMOVE");
    }

    @Given ("user navigates to checkout page")
    public void user_navigates_to_checkout_page(){
        driver.get(SAUCELABS_CHECKOUT);
    }

    @When ("user enters name, surname and postal code")
    public void user_enters_name_surname_and_postal_code(){
        checkoutPage.getFirstNameInputField().sendKeys("Līva");
        checkoutPage.getLastNameInputField().sendKeys("Nazarenko");
        checkoutPage.getZipCodeInputField().sendKeys("LV-1046");
    }

    @Then ("user navigate to checkout overview page")
    public void user_navigate_to_checkout_overview_page(){
        checkoutPage.getContinueButton().click();
    }

    @And ("check is data correct")
    public void check_is_data_correct(){
        Assert.assertEquals(checkoutOverviewPage.getInventoryItem().getText(), "Sauce Labs Backpack");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation().getText(), "SauceCard #31337");
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation().getText(), "FREE PONY EXPRESS DELIVERY!");
        Assert.assertEquals(checkoutOverviewPage.getItemTotal().getText(), "Item total: $29.99");
        Assert.assertEquals(checkoutOverviewPage.getTax().getText(), "Tax: $2.40");
        Assert.assertEquals(checkoutOverviewPage.getTotal().getText(), "Total: $32.39");
    }

    @Given ("user navigates to finish page")
    public void user_navigates_to_finish_page(){
        checkoutOverviewPage.getFinishButton().click();
    }

    @And ("check is everything was ok")
    public void check_is_everything_was_ok(){
        Assert.assertEquals(checkoutSuccessPage.getCheckoutComplete().getText(), "CHECKOUT: COMPLETE!");
    }

    @Then ("user navigates back to inventory page")
    public void user_navigates_back_to_inventory_page(){
        checkoutSuccessPage.getBackHomeButton().click();
        Assert.assertEquals(inventoryPage.getPageTitle().getText(), "PRODUCTS");
    }
}
