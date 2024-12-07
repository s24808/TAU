package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WikipediaTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "firefox");

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Filip\\IdeaProjects\\SeleniumProject\\WebDriver\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\Users\\Filip\\IdeaProjects\\SeleniumProject\\WebDriver\\msedgedriver.exe");
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            default:
                System.setProperty("webdriver.geckodriver", "C:\\Users\\Filip\\IdeaProjects\\SeleniumProject\\WebDriver\\geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testWikipediaSearch() {
        driver.get("https://www.wikipedia.org");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Znajdź pole wyszukiwania i wpisz frazę
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        searchBox.sendKeys("Selenium");
        searchBox.submit();

        // Poczekaj na pojawienie się wyników wyszukiwania
        WebElement firstHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
        assertTrue(firstHeading.getText().contains("Selenium"), "Strona wyników wyszukiwania nie zawiera oczekiwanego nagłówka.");
    }
}