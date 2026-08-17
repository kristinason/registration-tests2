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
        driver.get("file:///Users/kristinason/Downloads/Register/Register.html");

        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("signup_form"))
        );
    }

    private WebElement find(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public void setDateOfBirth(String value) {
        find(By.id("dp")).sendKeys(value);
    }

    public void setFirstName(String value) {
        find(By.id("member_firstname")).sendKeys(value);
    }

    public void setLastName(String value) {
        find(By.id("member_lastname")).sendKeys(value);
    }

    public void setEmail(String value) {
        find(By.id("member_emailaddress")).sendKeys(value);
    }

    public void setConfirmEmail(String value) {
        find(By.id("member_confirmemailaddress")).sendKeys(value);
    }

    public void setPassword(String value) {
        find(By.id("signupunlicenced_password")).sendKeys(value);
    }

    public void setConfirmPassword(String value) {
        find(By.id("signupunlicenced_confirmpassword")).sendKeys(value);
    }

    public void acceptTerms() {
        WebElement checkbox = find(By.id("sign_up_25"));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void acceptAge() {
        WebElement checkbox = find(By.id("sign_up_26"));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void acceptCodeOfEthics() {
        WebElement checkbox =
                find(By.id("fanmembersignup_agreetocodeofethicsandconduct"));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void submit() {
        find(By.cssSelector("input[type='submit'][name='join']")).click();
    }

    public boolean pageContains(String text) {
        return driver.getPageSource()
                .toLowerCase()
                .contains(text.toLowerCase());
    }
}
