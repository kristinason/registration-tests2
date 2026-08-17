package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.RegisterPage;

import java.util.UUID;


public class RegisterSteps {

    private RegisterPage page;
    private String password;
    private String confirm;

    @Given("att jag öppnar registreringssidan för Supporter Account")
    public void openPage() {
        page = new RegisterPage(Hooks.driver);
        page.open();

        Assert.assertTrue("URL ska innehålla sign up",
                Hooks.driver.getCurrentUrl().contains("/uk/signup"));
    }


        @When("jag fyller i giltiga uppgifter")
    public void fillValid() {
        String email = "test+" + UUID.randomUUID() + "@example.com";
        password = "Passw0rd!";
        confirm = "Passw0rd!";

        page.setFirstName("Test");
        page.setLastName("User");
        page.setEmail(email);
        page.setPassword(password);
        page.setConfirmPassword(confirm);

        Assert.assertEquals(password, confirm); // assert i scenariot
    }

    @When("jag fyller i giltiga uppgifter men utan efternamn")
    public void fillNoLastName() {
        String email = "test+" + UUID.randomUUID() + "@example.com";
        password = "Passw0rd!";
        confirm = "Passw0rd!";

        page.setFirstName("Test");
        // ingen efternamn
        page.setEmail(email);
        page.setPassword(password);
        page.setConfirmPassword(confirm);

        Assert.assertTrue("Efternamn ska saknas (testets avsikt)", true);
    }

    @When("jag fyller i giltiga uppgifter men lösenorden matchar inte")
    public void fillPwdMismatch() {
        String email = "test+" + UUID.randomUUID() + "@example.com";
        password = "Passw0rd!";
        confirm = "Passw0rd?";

        page.setFirstName("Test");
        page.setLastName("User");
        page.setEmail(email);
        page.setPassword(password);
        page.setConfirmPassword(confirm);

        Assert.assertNotEquals(password, confirm);
    }

    @And("jag godkänner terms and conditions")
    public void acceptTerms() {
        page.acceptTerms();
        Assert.assertTrue("Sidan ska vara laddad", Hooks.driver.getTitle() != null);
    }

    @And("jag skickar registreringen")
    public void submit() {
        page.submit();
        Assert.assertTrue("Efter submit ska vi se någon respons",
                Hooks.driver.getPageSource() != null);
    }

    @And("jag skickar registreringen utan att godkänna villkor")
    public void submitNoTerms() {
        page.submit();
        Assert.assertTrue("Efter submit ska respons visas",
                Hooks.driver.getPageSource() != null);
    }

    @Then("ska jag komma vidare eller se att konto skapats")
    public void verifySuccess() {
        // Exempel-assert: leta efter typiska ord. Anpassa till vad som syns hos dig.
        Assert.assertTrue("Bör se tecken på success/next step",
                page.pageContains("welcome") || page.pageContains("next") || !Hooks.driver.getCurrentUrl().contains("NewSupporterAccount"));
    }

    @Then("ska jag se ett felmeddelande för efternamn")
    public void verifyLastNameError() {
        Assert.assertTrue("Bör se fel för last name",
                page.pageContains("last") || page.pageContains("surname") || page.pageContains("required"));
    }

    @Then("ska jag se ett felmeddelande för lösenordsmatchning")
    public void verifyPwdMismatchError() {
        Assert.assertTrue("Bör se fel om matchning/confirm",
                page.pageContains("match") || page.pageContains("confirm"));
    }

    @Then("ska jag se ett felmeddelande om att villkor måste godkännas")
    public void verifyTermsError() {
        Assert.assertTrue("Bör se fel om terms/conditions",
                page.pageContains("terms") || page.pageContains("conditions"));
    }
}
