package classRoomSeven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.inventoryPage;
import pageObjects.LoginPage;

import java.time.Duration;
import java.util.List;

public class SeleniumTest {

    WebDriver driver;

    WebDriverWait wait;
    public final String SAUCELABS_URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setupBrowser() {
        System.out.println("Pirms testa");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }



    @Test
    public void localHTMLExerciseTest() throws InterruptedException {
        driver.get("file://C:\\Users\\dinap\\OneDrive\\Dators\\LU_LEKCIJAS\\JurisKreilisTestAutomation_2\\src\\test\\resources\\elements.html");
        WebElement descriptionTextArea = driver.findElement(By.name("description"));
        descriptionTextArea.clear();
        descriptionTextArea.sendKeys("Šisi er teksts par mani, hello hgello");
        WebElement linkElements = driver.findElement(By.linkText("Link Text"));
        linkElements.click();
        WebElement isStudentCheckbox = driver.findElement(By.id("studentID"));
        Assert.assertEquals(isStudentCheckbox.isSelected(),false);
        isStudentCheckbox.click();
        Assert.assertEquals(isStudentCheckbox.isSelected(),true);
        Select milakaKrasa = new Select(driver.findElement(By.id("colorsID")));
        milakaKrasa.selectByIndex(0);
        milakaKrasa.selectByIndex(3);
        String [] asdas  = {"asdasdas","asdasdas"};
        List<WebElement> sarakstsArKrasanm =  milakaKrasa.getOptions();

        for (int i = 0; i < sarakstsArKrasanm.size(); i++) {
            System.out.println(sarakstsArKrasanm.get(i).getText());
        }

        WebElement nospiedManiPoga = driver.findElement(By.id("checkDataID"));
        nospiedManiPoga.click();
        WebElement vissIrLabiTeksts = driver.findElement(By.id("checkDataResultID"));
        vissIrLabiTeksts.click();




    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        driver.get(SAUCELABS_URL);
        WebElement acceptedUsernamesText = driver.findElement(By.xpath("//div[@id='login_credentials']//h4"));
        System.out.println(acceptedUsernamesText.getText());
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("standard_user");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.className("submit-button"));
        loginButton.click();
//        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        WebElement pageTitleText = driver.findElement(By.cssSelector("div[id='header_container'] span[class='title']"));
        Assert.assertEquals(pageTitleText.getText(),"PRODUCTS");
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("select[data-test='product_sort_container']")));
        sortDropdown.selectByValue("hilo");
        Thread.sleep(5000);
        sortDropdown = new Select(driver.findElement(By.cssSelector("select[data-test='product_sort_container']")));
        sortDropdown.selectByIndex(1);
        WebElement linkedInLink = driver.findElement(By.linkText("LinkedIn"));
        linkedInLink.click();
        Thread.sleep(5000);
    }

    @Test
    public void errorMessagePasswordEmptyPageObjectTest(){
        driver.get(SAUCELABS_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Juris","");
        Assert.assertEquals(loginPage.getErrorText().getText(),"Epic sadface: Password is required");
    }

    @Test
    public void errorMessageUsernameEmptyPageObjectTest(){
        driver.get(SAUCELABS_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("","qwer1234");
        Assert.assertEquals(loginPage.getErrorText().getText(),"Epic sadface: Username is required");
    }

    @Test
    public void successLoginPageObject() throws InterruptedException {
        driver.get(SAUCELABS_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getUsernameInputField().sendKeys("standard_user");
        loginPage.getPasswordInputField().sendKeys("secret_sauce");
        loginPage.getLoginButton().click();
        inventoryPage inventoryPage = new inventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle().getText(),"PRODUCTS");
        inventoryPage.getLinkedinLink().click();
        Thread.sleep(5000);
    }

    @Test
    public void errorMessagePasswordEmptyTest() throws InterruptedException {
        System.out.println("TESTS");
        driver.get(SAUCELABS_URL);
        WebElement acceptedUsernamesText = driver.findElement(By.xpath("//div[@id='login_credentials']//h4"));
        System.out.println(acceptedUsernamesText.getText());
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("Juris");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("");
        WebElement loginButton = driver.findElement(By.className("submit-button"));
        loginButton.click();
        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Password is required";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void errorMessageUsernameEmptyTest() throws InterruptedException {
        System.out.println("TESTS");
        driver.get(SAUCELABS_URL);
        WebElement usernameInputField = driver.findElement(By.cssSelector("div[id='login_button_container'] input[id='user-name']"));
        usernameInputField.sendKeys("");
        WebElement passwordInputField = driver.findElement(By.name("password"));
        passwordInputField.sendKeys("qwerty123");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Username is required";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void errorMessageUsernameAndPasswordEmptyTest() throws InterruptedException {
        System.out.println("TESTS");
        driver.get(SAUCELABS_URL);
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Username is required";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void errorMessageWrongCredentials() throws InterruptedException {
        System.out.println("TESTS");
        driver.get(SAUCELABS_URL);
        WebElement usernameInputField = driver.findElement(By.cssSelector("input[id='user-name']"));
        usernameInputField.sendKeys("Juris");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("qwerty123");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualText, expectedText);
    }



    @Test
    public void myTest2() {
        System.out.println("TESTS");
        driver.get(SAUCELABS_URL);
    }

    @AfterMethod
    public void tearDownBrowser() {
        System.out.println("Pēc testa");
        driver.quit();
    }



}