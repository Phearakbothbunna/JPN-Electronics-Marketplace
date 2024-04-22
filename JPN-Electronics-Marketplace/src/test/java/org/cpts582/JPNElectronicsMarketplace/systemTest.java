package org.cpts582.JPNElectronicsMarketplace;

import io.github.bonigarcia.wdm.WebDriverManager;
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
//      driver = new FirefoxDriver();
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

    void upload_product(String name, int price, String description, String url, String contact){

        WebElement nameField = driver.findElement(By.name("productName"));
        WebElement priceField = driver.findElement(By.name("productPrice"));
        WebElement descriptionField = driver.findElement(By.name("productDescription"));
        WebElement urlField = driver.findElement(By.name("productImgUrl"));
        WebElement contactField = driver.findElement(By.name("contactInfo"));


        nameField.sendKeys(name);
        priceField.sendKeys(String.valueOf(price));
        descriptionField.sendKeys(description);
        urlField.sendKeys(url);
        
        contactField.sendKeys(contact);
//        WebElement btn_submit = driver.findElement(By.id("btn_submit"));
//        btn_submit.click();
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
                "2060009988");
        // Testing register by checking for redirection to login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Test
    void test_register_fail(){
        openRegisterPage();
        // The register will fail if the password requirement is not met
        register("testlogin@gmail.com", "Iron Man", "123123", "123123",
                "2060009988");
        // Testing register by checking for redirection to login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // If it fails, it will stay on the same page
        wait.until(ExpectedConditions.urlContains("/register"));
    }

//    // Test logout
    @Test
    void test_logout(){
        openLoginPage();
        login("1@gmail.com", "123456");
        WebElement logoutButton = driver.findElement(By.className("btn_logout"));
        logoutButton.click();
        // On the login page, there's a text telling the user to login. So if we can find this text,
        // it means the user logout successfully
        WebElement navbarText = driver.findElement(By.id("login-text"));
        Assertions.assertEquals("Login to start browsing!", navbarText.getText());
    }

//    // Test upload product
    @Test
    void test_upload_product(){
        openLoginPage();
        login("2@gmail.com", "123456");

        WebElement btn_upload = driver.findElement(By.className("btn_upload"));
        btn_upload.click();

        upload_product("Alienware x16 R2 Gaming Laptop", 2699, "Brand new! White color. Intel Core Ultra 7, RTX 4060 8GB GDDR6.",
                "https://m.media-amazon.com/images/I/51vIYXHQaQL._AC_UF894,1000_QL80_.jpg",
                "Facebook: JohnDoe, Phone: 2061231212");

        // Testing upload by checking for redirection back to the homepage
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/home"));
    }

    // Test user's own listing
    @Test
    void test_user_listing(){
        openLoginPage();
        login("1@gmail.com", "123456");
        WebElement user = driver.findElement(By.className("user_login"));
        user.click();
        // Find the heading for "MY LISTINGS" page
        WebElement myListing = driver.findElement(By.className("user_listing"));
        // The test will pass if we can find the heading "MY LISTINGS"
        Assertions.assertEquals("MY LISTINGS", myListing.getText());

    }
//
//    // Test view product contact info
    @Test
    void test_view_contact(){
        openLoginPage();
        login("1@gmail.com", "123456");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Find the view contact info button
        // I am using "wait" to ensure that the page fully load before trying to find the button
        WebElement btn_contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".view_contact")));
        btn_contact.click();
        // Find the contact information that should be displayed
        // If it's not empty, the test should pass
        WebElement contact_info = driver.findElement(By.className("contact_display"));
        Assertions.assertNotNull(contact_info.getText());
    }

    //    @Test
//    void login_field_visible(){
//        openLoginPage();
//        WebElement emailField = driver.findElement(By.id("formBasicEmail"));
//        WebElement passwordField = driver.findElement(By.id("formBasicPassword"));
//
//        Assertions.assertTrue(emailField.isDisplayed());
//        Assertions.assertTrue(passwordField.isDisplayed());
//
//    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
