package org.cpts582.JPNElectronicsMarketplace;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class systemTest {
    WebDriver driver;
    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
    }

    void openLoginPage(){
        driver.get("http://localhost:3000/login");
    }
    void openRegisterPage(){
        driver.get("http://localhost:3000/register");
    }
    String get_user_logged_in(){
        WebElement user_login = driver.findElement(By.className("user_login"));
        return user_login.getText();
    }

    String get_login_error(){
        WebElement login_error = driver.findElement(By.className("error-message"));
        return login_error.getText();
    }

    // login function
    void login(String email, String pwd) {
        WebElement emailField = driver.findElement(By.id("formBasicEmail"));
        WebElement passwordField = driver.findElement(By.id("formBasicPassword"));
        emailField.sendKeys(email);
        passwordField.sendKeys(pwd);
        WebElement loginButton = driver.findElement(By.cssSelector(".btn_login"));
        loginButton.click();
    }

    void register(String email, String uname, String pwd, String confPwd, String contact){
        WebElement emailField = driver.findElement(By.id("formBasicEmail"));
        WebElement unameField = driver.findElement(By.id("formUsername"));
        WebElement pwdField = driver.findElement(By.id("formBasicPassword"));
        WebElement confirmPwdField = driver.findElement(By.id("formBasicConfirmPassword"));
        WebElement contactField = driver.findElement(By.id("formUserContactInfo"));
        emailField.sendKeys(email);
        unameField.sendKeys(uname);
        pwdField.sendKeys(pwd);
        confirmPwdField.sendKeys(confPwd);
        contactField.sendKeys(contact);
        WebElement registerButton = driver.findElement(By.cssSelector(".btn_submit"));
        registerButton.click();

    }

    @Test
    void login_field_visible(){
        openLoginPage();
        WebElement emailField = driver.findElement(By.id("formBasicEmail"));
        WebElement passwordField = driver.findElement(By.id("formBasicPassword"));

        Assertions.assertTrue(emailField.isDisplayed());
        Assertions.assertTrue(passwordField.isDisplayed());

    }

    // Test correct credentials login
    @Test
    void test_login_success(){
        openLoginPage();
        login("1@gmail.com", "123456");
        Assertions.assertNotNull(get_user_logged_in());
    }

    // Test incorrect credentials login
    @Test
    void test_login_fail(){
        openLoginPage();
        login("wrongemail@gmail.com", "wrong_pwd");
        String login_error = get_login_error();
        Assertions.assertEquals("Unable to login...check email/password", login_error);
    }

    // Test register
    @Test
    void test_register_success(){
        openRegisterPage();
        // We can only register user with an email that doesn't already exist in the database
        // So we need to ensure that each time the email is unique so that we can run test multiple times
        String timestamp = String.valueOf(System.currentTimeMillis());
        String newEmail = "ironman+" + timestamp + "@gmail.com";
        register(newEmail, "Iron Man", "%Abc123123", "%Abc123123",
                "Phone: 2060009988");
        // Testing register by checking for redirection to login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Test
    void test_register_fail(){
        openRegisterPage();
        // The register will fail if the password requirement is not met
        register("testlogin@gmail.com", "Iron Man", "123123", "123123",
                "Phone: 2060009988");
        // Testing register by checking for redirection to login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // If it fails, it will stay on the same page
        wait.until(ExpectedConditions.urlContains("/register"));
    }
//
//    // Test logout
//    @Test
//    void test_logout(){
//
//    }
//
//    // Test upload product
//    @Test
//    void test_upload_product(){
//
//    }
//
//    // Test view product contact info
//    @Test
//    void test_view_contact(){
//
//    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


}
