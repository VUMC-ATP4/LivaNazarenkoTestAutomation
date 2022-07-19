package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.LoginPage;
import pageObjectsHomework.InventoryPage;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    public final String SOUCELABS_URL = "https://www.saucedemo.com/";
    LoginPage loginPage;
    InventoryPage inventoryPage;


    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @Given("User is navigate to Soucelabs page")
    public void user_is_navigate_to_soucelabs_page() {
       driver.get(SOUCELABS_URL);

    }

    @Given("User navigates to {string}")
    public void user_navigates_to(String url) {
        driver.get(url);
    }

        @When("user enters username {string} and password {string}")
        public void user_enters_username_and_password(String username, String password) {
        loginPage.getUsernameInputField().sendKeys("standard_userand");
        loginPage.getPasswordInputField().sendKeys("secret_sauce");
    }
    @Then("user click loggin button")
    public void user_click_loggin_button() {
        loginPage.getLoginButton().click();
    }
    @Then("user sees inventory page")
    public void user_sees_inventory_page() {
        Assert.assertEquals(inventoryPage.getPageTitle().getText(), "PRODUCTS");
    }

    @Then("user sees page title {string}")
    public void userSeesPageTitlePagetitle(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }
    }
