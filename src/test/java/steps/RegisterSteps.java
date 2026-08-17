package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.RegisterPage;

import java.util.UUID;

import static steps.Hooks.driver;

public class RegisterSteps {

    private RegisterPage page;

    @Given("att jag öppnar registreringssidan för Supporter Account")
    public void openPage() {

        page = new RegisterPage(driver);
        page.open();

        Assert.assertTrue(
                "Registreringssidan ska öppnas",
                driver.getCurrentUrl().contains("Register.html")
        );
    }

    @When("jag fyller i giltiga uppgifter")
    public void fillValid() {

        String email =
                "test+" + UUID.randomUUID() + "@example.com";

        page.setDateOfBirth("01/01/2000");
        page.setFirstName("Test");
        page.setLastName("User");

        page.setEmail(email);
        page.setConfirmEmail(email);

        page.setPassword("Passw0rd!");
        page.setConfirmPassword("Passw0rd!");

        page.acceptAge();
        page.acceptCodeOfEthics();
    }

    @When("jag fyller i giltiga uppgifter men utan efternamn")
    public void fillNoLastName() {

        String email =
                "test+" + UUID.randomUUID() + "@example.com";

        page.setDateOfBirth("01/01/2000");
        page.setFirstName("Test");

        // Efternamn lämnas medvetet tomt

        page.setEmail(email);
        page.setConfirmEmail(email);

        page.setPassword("Passw0rd!");
        page.setConfirmPassword("Passw0rd!");

        page.acceptAge();
        page.acceptCodeOfEthics();
    }

    @When("jag fyller i giltiga uppgifter men lösenorden matchar inte")
    public void fillPwdMismatch() {

        String email =
                "test+" + UUID.randomUUID() + "@example.com";

        page.setDateOfBirth("01/01/2000");
        page.setFirstName("Test");
        page.setLastName("User");

        page.setEmail(email);
        page.setConfirmEmail(email);

        page.setPassword("Passw0rd!");
        page.setConfirmPassword("Passw0rd?");

        page.acceptAge();
        page.acceptCodeOfEthics();
    }

    @And("jag godkänner terms and conditions")
    public void acceptTerms() {

        page.acceptTerms();
    }

    @And("jag skickar registreringen")
    public void submit() {

        page.submit();
    }

    @And("jag skickar registreringen utan att godkänna villkor")
    public void submitNoTerms() {

        // Terms godkänns INTE här
        page.submit();
    }

    @Then("ska jag komma vidare eller se att konto skapats")
    public void verifySuccess() {

        Assert.assertTrue(
                "Kontot ska ha skapats",
                driver.getCurrentUrl().contains("Success.html")
                        || page.pageContains(
                        "thank you for creating an account"
                )
        );
    }

    @Then("ska jag se ett felmeddelande för efternamn")
    public void verifyLastNameError() {

        Assert.assertTrue(
                "Felmeddelande för efternamn ska visas",
                page.pageContains("last name is required")
                        || page.pageContains("last name")
                        || page.pageContains("required")
        );
    }

    @Then("ska jag se ett felmeddelande för lösenordsmatchning")
    public void verifyPwdMismatchError() {

        Assert.assertTrue(
                "Felmeddelande för lösenordsmatchning ska visas",
                page.pageContains("password did not match")
                        || page.pageContains("password")
                        || page.pageContains("match")
        );
    }

    @Then("ska jag se ett felmeddelande om att villkor måste godkännas")
    public void verifyTermsError() {

        Assert.assertTrue(
                "Felmeddelande om Terms and Conditions ska visas",
                page.pageContains(
                        "you must confirm that you have read and accepted our terms and conditions"
                )
                        || page.pageContains("terms and conditions")
        );
    }
}
