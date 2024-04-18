package org.cpts582.JPNElectronicsMarketplace;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class systemTest {
    WebDriver driver;
    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    void openPage() {
        driver.get("https://google.com");
    }

    @Test
    void test_open_page() {
        openPage();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
