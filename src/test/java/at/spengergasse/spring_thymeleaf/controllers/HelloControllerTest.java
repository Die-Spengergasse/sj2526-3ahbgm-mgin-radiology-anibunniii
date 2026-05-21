package at.spengergasse.spring_thymeleaf.controllers;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {
    private WebDriver driver;
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void index() {
        driver.get("http://localhost:8080");
        String body = driver.findElement(By.tagName("body")).getText();
        assertTrue(body.contains("Welcome to Radiology Management"));
    }

    @Test
    void navbarIsVisible() {
        driver.get("http://localhost:8080");
        assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());

    }
}