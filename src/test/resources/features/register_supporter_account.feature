Feature: Registrera Supporter Account
  Som en besökare vill jag kunna registrera ett Supporter Account
  så att jag kan använda tjänsten.

  Background:
    Given att jag öppnar registreringssidan för Supporter Account

  Scenario: Skapa användare – allt går som förväntat och ett konto skapas
    When jag fyller i giltiga uppgifter
    And jag godkänner terms and conditions
    And jag skickar registreringen
    Then ska jag komma vidare eller se att konto skapats

  Scenario: Skapa användare – efternamn saknas
    When jag fyller i giltiga uppgifter men utan efternamn
    And jag godkänner terms and conditions
    And jag skickar registreringen
    Then ska jag se ett felmeddelande för efternamn

  Scenario: Skapa användare – lösenord matchar inte
    When jag fyller i giltiga uppgifter men lösenorden matchar inte
    And jag godkänner terms and conditions
    And jag skickar registreringen
    Then ska jag se ett felmeddelande för lösenordsmatchning

  Scenario: Skapa användare – terms and conditions är inte godkänt
    When jag fyller i giltiga uppgifter
    And jag skickar registreringen utan att godkänna villkor
    Then ska jag se ett felmeddelande om att villkor måste godkännas
