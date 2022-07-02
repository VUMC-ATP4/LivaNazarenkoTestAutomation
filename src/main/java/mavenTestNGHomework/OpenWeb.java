package mavenTestNGHomework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


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

=======
import org.testng.ITest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenWeb {

    public WebDriver chromeBrowser;
    String URL;

//    nesanak palaist anotetas metodes, kam vajadzeja but savadak?
//    @BeforeTest
//        public void pirmsTesta() {
//        WebDriver chromeBrowser = new ChromeDriver();
//        chromeBrowser.get(URL);
//    }

    @Test
    public void checkPage() {
        String URL = "https://www.delfi.lv/";
        WebDriver chromeBrowser = new ChromeDriver();
        chromeBrowser.get(URL);
        String expectedTitle = "DELFI - Vadošais ziņu portāls Latvijā - DELFI";
        String actualTitle = chromeBrowser.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        chromeBrowser.quit();
    }

//    @BeforeTest
//    public void pirmsOtraTesta() {
//        WebDriver chromeBrowser = new ChromeDriver();
//        chromeBrowser.get(URL);
//    }

    @Test
    public void checkAnotherPage() {
        String URL = "https://www.tvnet.lv/";
        WebDriver chromeBrowser = new ChromeDriver();
        chromeBrowser.get(URL);
        String expectedTitle = "TVNET - Īstas ziņas";
        String actualTitle = chromeBrowser.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        chromeBrowser.quit();
    }

//    @AfterTest
//    public void pecTesta() {
//        chromeBrowser.quit();// nenostrada ne ar chromeBrowser.quit();. ne ar .close();
//    }
}

