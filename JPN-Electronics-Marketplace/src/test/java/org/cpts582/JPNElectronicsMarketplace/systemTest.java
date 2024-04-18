package org.cpts582.JPNElectronicsMarketplace;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class systemTest {
    WebDriver driver;

    public static ChromeDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(chromeOptions);
    }

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\phear\\Downloads\\chromedriver.exe");
        driver = getChromeDriver();
    }

    void test_load_page() {
        driver.get("https://google.com");
    }


    @Test
    void test() {
        test_load_page();
    }

    @AfterEach
    void tearDown() {
        //driver.quit();
    }
}
