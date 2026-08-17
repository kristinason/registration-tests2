package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {

        driver.get("https://www.playhq.com/uk/signup");


        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));


        String page = driver.getPageSource().toLowerCase();
        if (page.contains("verify you are human") || page.contains("just a moment")) {
            throw new RuntimeException("Testet kan inte fortsätta eftersom Cloudflare blockerar Selenium..");
        }


        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));
    }

    private WebElement findAny(By... locators) {
        for (By by : locators) {
            try {
                WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                if (el.isDisplayed()) return el;
            } catch (TimeoutException ignored) { }
        }
        throw new NoSuchElementException("Element not found.");
    }

    public void setFirstName(String v) {
        findAny(By.id("firstName")).sendKeys(v);
    }

    public void setLastName(String v) {
        findAny(By.id("lastName")).sendKeys(v);
    }

    public void setEmail(String v) {
        findAny(By.id("email")).sendKeys(v);
    }

    public void setPassword(String v) {
        findAny(By.id("password")).sendKeys(v);
    }

    public void setConfirmPassword(String v) {
        findAny(By.id("confirmPassword")).sendKeys(v);
    }

    public void acceptTerms() {
        WebElement cb = findAny(By.id("terms"));
        if (!cb.isSelected()) cb.click();
    }

    public void submit() {
        findAny(By.cssSelector("button[type='submit']")).click();
    }

    public boolean pageContains(String text) {
        return driver.getPageSource().toLowerCase().contains(text.toLowerCase());
    }
}
