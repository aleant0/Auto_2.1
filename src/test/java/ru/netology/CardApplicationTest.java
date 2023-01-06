package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardApplicationTest {

    private WebDriver driver;


    @BeforeAll
    static void setUpAll(){
        System.setProperty("webdriver.chrome.driver", "driver/chrome/chromedriver.exe");
    }

    @BeforeEach
    void seUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearsDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void positiveRequestTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[name=\"name\"]")).sendKeys("Алекс");
        driver.findElement(By.cssSelector("input[name=\"phone\"]")).sendKeys("+79999999999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String actual = driver.findElement(By.cssSelector("p[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actual.trim());
    }


}
