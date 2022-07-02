package mavenTestNGHomework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.*;

public class OpenWeb {

    WebDriver chromeBrowser;
    String URL;

    @BeforeMethod
        public void pirmsTesta() {
        chromeBrowser = new ChromeDriver();
        chromeBrowser.get(URL);
    }

        @Test
        public void checkPage() {
            String URL = "https://www.delfi.lv/";
            String expectedTitle = "DELFI - Vadošais ziņu portāls Latvijā - DELFI";
            String actualTitle = chromeBrowser.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle);
        }

        @Test
        public void checkAnotherPage() {
            String URL = "https://www.tvnet.lv/";
            String expectedTitle = "TVNET - Īstas ziņas";
            String actualTitle = chromeBrowser.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle);
        }

    @AfterMethod
    public void pecTesta() {
        chromeBrowser.close();
    }
    }

