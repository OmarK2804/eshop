package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)

class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String createProductUrl;
    private String productListUrl;


    @BeforeEach
    void setupTest() {
        createProductUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        productListUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(createProductUrl);

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Sampo Cap Bambang");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("100");

        WebElement button = driver.findElement(By.tagName("button"));
        button.click();

        driver.get(productListUrl);

        WebElement productListTable = driver.findElement(By.tagName("table"));
        assertTrue(productListTable.getText().contains("Sampo Cap Bambang"));
        assertTrue(productListTable.getText().contains("100"));
    }
}