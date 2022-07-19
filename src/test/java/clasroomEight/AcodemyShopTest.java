package clasroomEight;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;
import pageObjects.acodemyShop.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pageObjectsHomework.CartPage.driver;

public class AcodemyShopTest {

    WebDriver driver;
    WebDriverWait wait;
    public final String ACODEMY_SHOP_URL = "http://shop.acodemy.lv/";

    @BeforeMethod
    public void setupBrowser() throws MalformedURLException {
        System.out.println("Pirms testa");
//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setCapability("platformName", "Windows 10");
//        browserOptions.setCapability("browserVersion", "latest");
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("build", "<your build id>");
//        sauceOptions.put("name", "<your test name>");
//        browserOptions.setCapability("sauce:options", sauceOptions);

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://192.168.217.39:4444"), chromeOptions);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void searchForItemTest() throws InterruptedException {

        driver.get(ACODEMY_SHOP_URL);

        MainPage mainPage = new MainPage(driver);
        //zemākās divas rindiņas vienmēr ies kopā, tādēļ no šū var uztaisīt metodi, lai nav katru reizi jāpārrkasta. Metode izveidota pageObject MainPage
//        mainPage.getSearchInputField().sendKeys("Beanie");
//        new Actions(driver).sendKeys(Keys.ENTER).perform(); // šitais simulē Enter nospiešanu uz klaviatūras
        mainPage.searchProduct("Beanie");// tagad iekavās var norādīt visādus meklēšanas vārdus
        Thread.sleep(2000);
        Assert.assertEquals(mainPage.getSearchResults().size(), 2);
        List<WebElement> searchResults = mainPage.getSearchResults();
        for (WebElement element : searchResults) { // katram webelementam <> sarakstā mēs kaut ko izdarīsim
            System.out.println(element.findElement(By.cssSelector("h2")).getText());
            System.out.println(element.findElement(By.cssSelector("price")).getText());
        }


    }
        @Test
        public void searchForItemTestTwo() throws InterruptedException {
            driver.get(ACODEMY_SHOP_URL);

            MainPage mainPage = new MainPage(driver);
            mainPage.searchProduct("T-Shirt");
            Thread.sleep(2000);
            // System.out.println("Search results count: " + mainPage.getSearchResults().size());
            Assert.assertEquals(mainPage.getSearchResults().size(), 3);
            Thread.sleep(2000);
            System.out.println("Debug");
        }

        @Test
        public void switchTabTest(){

            driver.get("https://www.w3schools.com/");
            System.out.println("Open tabs: " + driver.getWindowHandles().size());// šitais ir, lai redzētu, cik vispār ir atvētas tab
            driver.findElement(By.id("accept-choices")).click();
            driver.findElement(By.cssSelector("a[title='W3Schools on LinkedIn']")).click();
            System.out.println("Open tabs: " + driver.getWindowHandles().size());
// liksim Selenium pārslēgties no viena taba uz otru
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());// ar šo dabū sarakstu ar tabu unikālajiem ID, ko sapratīs Selenium
            driver.switchTo().window(tabs.get(1));
            System.out.println(driver.getTitle());
            driver.close();// aizver konkrēto tabu

            System.out.println("Debug");

        }

        @Test
        public void javaScriptExecutorExampleTest() throws InterruptedException {
        driver.get("https://www.lu.lv/");
            WebElement consentButton = driver.findElement(By.cssSelector("button[id='ccm__footer__consent-bar-submit']"));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", consentButton);
            driver.findElement(By.className("footer__up")).click();
            Thread.sleep(2000);
            System.out.println();
            driver.findElement(By.className("footer__up")).click();

            WebElement element = driver.findElement(By.linkText("Kontakti"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(2000);
            WebElement menu = driver.findElement(By.className("menuContainer"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", menu);
            Thread.sleep(2000);

        }
 @Test
 public void hoverTest() throws InterruptedException {
     driver.get("https://www.w3schools.com/howto/howto_css_dropdown.asp");
     driver.findElement(By.id("accept-choices")).click();
     WebElement hoverMeButton = driver.findElement(By.cssSelector("div.dropdown2 button"));
     new Actions(driver).moveToElement(hoverMeButton).perform();
     Thread.sleep(2000);
     driver.findElement(By.linkText("Link 1")).click();

 }

 @Test
 public void seleniumDocTest() throws InterruptedException {
     driver.get("https://www.selenium.dev/documentation/");
     new Actions(driver)
             .keyDown(Keys.CONTROL)
             .sendKeys("k")
             .perform();

     Thread.sleep(3000);
 }

 @Test
 public void keyboardClickTest() throws InterruptedException {
     driver.get("https://www.microsoft.com/applied-sciences/projects/anti-ghosting-demo");
     driver.findElement(By.id("clickToUseButton")).click();
     new Actions(driver)
             .keyDown(Keys.CONTROL)
             .keyDown(Keys.ALT)
             .perform();
     Thread.sleep(5000);

     new Actions(driver)
             .keyUp(Keys.ALT)
             .perform();
     Thread.sleep(5000);

     new Actions(driver)
             .sendKeys("L")
             .sendKeys("Ī")
             .sendKeys("V")
             .sendKeys("A")
             .perform();
     Thread.sleep(5000);

 }
    @AfterMethod
    public void tearDownBrowser() {
        System.out.println("Pēc testa");
        driver.quit();
    }
}
// video 02:37:49