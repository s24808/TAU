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

public class AmazonTest {
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
    public void testAmazonLaptopSearchAndProductDetails() {
        driver.get("https://www.amazon.com");

        //Oczekiwanie na załadaowanie strony
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //Wpisz laptop w pole wyszukiwania
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("Laptop");
        searchBox.submit();

        //Sprawdzenie wyników wyszukiwania
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot div.s-result-item")));
        assertTrue(results.size() > 0, "Nie znaleziono wyników wyszukiwania.");

        //Wejście w pierwszy wynik
        WebElement firstResultLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.a-link-normal.s-underline-text.s-underline-link-text.s-link-style.a-text-normal")));
        firstResultLink.click();

        //Sprawdzenie czy tytuł produktu jest widoczny
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
        assertTrue(productTitle.isDisplayed(), "Tytuł produktu nie jest widoczny na stronie.");

        //Sprawdzenie czy na stronie jest widoczny przycisk dodaj do koszyka
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
        assertTrue(addToCartButton.isDisplayed(), "Przycisk dodaj do koszyka nie jest widoczny na stronie.");

        //Sprawdzenie, czy cena produktu jest widzoczna na stronie
        WebElement productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-price")));
        assertTrue(productPrice.isDisplayed(), "Cena produktu nie jest widoczna na stronie.");

        //Sprawdzenie czy recenzje są widoczne na stronie
        WebElement reviewSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reviewsMedley")));
        assertTrue(reviewSection.isDisplayed(), "Sekcja z recenzjami nie jest widoczna na stronie produktu.");
    }
}