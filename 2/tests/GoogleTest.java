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

public class GoogleTest {
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
    public void testGoogleSearch() {
        driver.get("https://www.google.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Sprawdzenie czy okno cookies się pojawiło, jeśli nie test leci dalej
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Zaakceptuj wszystko')]")));
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Okienko się nie pojawiło.");
        }

        //Wpisanie w pole wyszukiwania
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.sendKeys("Selenium");
        searchBox.submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#search")));

        //Sprawdzenie wyników wyszukiwania
        List<WebElement> results = driver.findElements(By.cssSelector("div.g"));
        System.out.println("Liczba wyników znalezionych: " + results.size());
        assertTrue(results.size() > 0, "Nie znaleziono wyników wyszukiwania.");

        //Sprawdzenie czy pierwszy wynik posiada selenium w nazwie
        WebElement firstResult = results.get(0);
        assertTrue(firstResult.getText().toLowerCase().contains("selenium"), "Pierwszy wynik nie zawiera 'Selenium'");
    }
}




