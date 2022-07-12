package seleniumHomework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.inventoryPage;
import pageObjectsHomework.CartPage;
import pageObjectsHomework.CheckoutOverviewPage;
import pageObjectsHomework.CheckoutPage;
import pageObjectsHomework.CheckoutSuccessPage;

import java.time.Duration;

public class sauceDemoTests {

    WebDriver driver;
    WebDriverWait wait;
    public final String SAUCELABS_URL = "https://www.saucedemo.com/";
    public final String SAUCELABS_CART = "https://www.saucedemo.com/cart.html";
    public final String SAUCELABS_CHECKOUT = "https://www.saucedemo.com/checkout-step-one.html";

    @BeforeMethod
    public void setupBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void firstScenario() throws InterruptedException {

        driver.get(SAUCELABS_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        //pārbaudīt, vai lietotājs ir ielogojies
        inventoryPage inventoryPage = new inventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle().getText(), "PRODUCTS");
        Thread.sleep(2000);

        //ielikt 1 produktu grozā
        CartPage cartPage = new CartPage(driver);
        cartPage.getAdCartList().click();
        Thread.sleep(2000);

        // aiziet uz grozu
        driver.get(SAUCELABS_CART);

        // pārbaudīt, vai prece ir grozā
        Assert.assertEquals(cartPage.getRemoveButton().getText(), "REMOVE");
        Thread.sleep(2000);

        // doties uz Checkout
        driver.get(SAUCELABS_CHECKOUT);

        //ievadīt vārdu/uzvārdu/pasta indeksu
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getFirstNameInputField().sendKeys("Līva");
        checkoutPage.getLastNameInputField().sendKeys("Nazarenko");
        checkoutPage.getZipCodeInputField().sendKeys("LV-1046");
        Thread.sleep(2000);

        //doties uz CheckoutOverview lapu, pārbaudīt datus
        checkoutPage.getContinueButton().click();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(checkoutOverviewPage.getInventoryItem().getText(), "Sauce Labs Backpack");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation().getText(), "SauceCard #31337");
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation().getText(), "FREE PONY EXPRESS DELIVERY!");
        Assert.assertEquals(checkoutOverviewPage.getItemTotal().getText(), "Item total: $29.99");
        Assert.assertEquals(checkoutOverviewPage.getTax().getText(), "Tax: $2.40");
        Assert.assertEquals(checkoutOverviewPage.getTotal().getText(), "Total: $32.39");

        Thread.sleep(1000);

        //Doties uz finish lapu un pārbaudīt vai viss bija veiksmīgi

        checkoutOverviewPage.getFinishButton().click();

        CheckoutSuccessPage checkoutSuccessPage = new CheckoutSuccessPage(driver);

        Assert.assertEquals(checkoutSuccessPage.getCheckoutComplete().getText(), "CHECKOUT: COMPLETE!");
        Thread.sleep(2000);

        //doties atpakaļ uz pirmo lapu ar pogu 'Back Home'

        checkoutSuccessPage.getBackHomeButton().click();
        Assert.assertEquals(inventoryPage.getPageTitle().getText(), "PRODUCTS");
        Thread.sleep(2000);
    }

    @Test
    public void secondScenario() throws InterruptedException {

        driver.get(SAUCELABS_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        driver.get(SAUCELABS_CART);

        driver.get(SAUCELABS_CHECKOUT);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getFirstNameInputField().sendKeys(" ");
        checkoutPage.getLastNameInputField().sendKeys("Nazarenko");
        checkoutPage.getZipCodeInputField().sendKeys("LV-1046");
        Thread.sleep(2000);
        checkoutPage.getContinueButton().click();//palaižot testu, šajā vietā nenostrādā error un lapa pāriet uz Overview. No šī brīža testi ir failed. Ar roku pārbaudot, error teksts izlec un tālāk iet neļauj
        Assert.assertEquals(checkoutPage.getErrorText().getText(), "Error: First Name is required");


        checkoutPage.getFirstNameInputField().sendKeys("Līva");
        checkoutPage.getLastNameInputField().sendKeys(" ");
        checkoutPage.getZipCodeInputField().sendKeys("LV-1046");
        Thread.sleep(2000);
        checkoutPage.getContinueButton().click();
        Assert.assertEquals(checkoutPage.getErrorText().getText(), "Error: Last Name is required");

        checkoutPage.getFirstNameInputField().sendKeys("Līva");
        checkoutPage.getLastNameInputField().sendKeys("Nazarenko");
        checkoutPage.getZipCodeInputField().sendKeys(" ");
        Thread.sleep(2000);
        checkoutPage.getContinueButton().click();
        Assert.assertEquals(checkoutPage.getErrorText().getText(), "Error: Postal Code is required");
    }


    @AfterMethod
    public void finishBrowser() {
        driver.close();
    }
}




