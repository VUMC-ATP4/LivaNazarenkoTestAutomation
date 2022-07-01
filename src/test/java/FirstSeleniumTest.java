
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class FirstSeleniumTest {

    @Test
    public void checkPageTitle(){

        String URL = "https://www.saucedemo.com/";
        WebDriver chromeBrowser = new ChromeDriver();
        chromeBrowser.get(URL);// aizej uz norādīto adresi
        String expectedTitle = "Swag Labs";
        String actualTitle = chromeBrowser.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        chromeBrowser.quit();
    }
}
