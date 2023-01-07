package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test
    void nameValidationTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[name=\"name\"]")).sendKeys("Alex");
        driver.findElement(By.cssSelector("input[name=\"phone\"]")).sendKeys("+79999999999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        WebElement nameForm = driver.findElement(By.cssSelector("span[data-test-id=\"name\"]"));
        String actual = nameForm.findElement(By.className("input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.trim());
    }

    @Test
    void phoneValidationTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[name=\"name\"]")).sendKeys("Алекс");
        driver.findElement(By.cssSelector("input[name=\"phone\"]")).sendKeys("+799999999999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        WebElement phoneForm = driver.findElement(By.cssSelector("span[data-test-id=\"phone\"]"));
        String actual = phoneForm.findElement(By.className("input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.trim());
    }
}
