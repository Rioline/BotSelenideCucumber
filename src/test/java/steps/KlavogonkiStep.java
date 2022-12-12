package steps;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

public class KlavogonkiStep {

  private final SelenideElement closePopUp = $x(
          "//*[@id='howtoplay']/div[2]/div/table/tbody/tr[2]/td[2]/p[5]/input");
  private final SelenideElement startGame = $x(
          "//a[@id='host_start']");
  private final SelenideElement highlightedWord = $x(
          "//span[@id='typefocus']");
  private final SelenideElement inputField = $x(
          "//input[@id='inputtext']");
  private final SelenideElement afterFocus = $x(
          "//span[@id='afterfocus']");
  private final SelenideElement count = $x(
      "/html/body/div[6]/div/div/div[1]/table/tbody/tr/td[1]/div/table/tbody/tr/td/div[2]/div[3]/div[4]/div/div[2]/div[3]/div[2]/span[1]/span");

  private String getCurrentWord() {
    return highlightedWord.getText().replaceAll("c","\u0441").replaceAll("o", "\u043E");
  }

  @When("Start the game")
  public void startTheGame() {
    closePopUp.click();
    if (startGame.isDisplayed()) {
      startGame.click();
    }
  }

  @And("Wait for a start the game")
  public void waitForAStartTheGame() {
    highlightedWord.click();
  }

  @And("Enter the highlighted world")
  public void enterTheHighlightedWorld() {
    while (true) {
      String currentWord = getCurrentWord();
      String focusSymbol = afterFocus.getText();
      inputField.sendKeys(currentWord);
      if (focusSymbol.equals(".")) {
        inputField.sendKeys(".");
        break;
      }
      inputField.sendKeys(Keys.SPACE);
    }
  }

  @Then("Game is ended and symbol in minute more {int}")
  public void gameIsEndedAndSymbolInMinuteMore(int minValue) {
    String res  = count.getText();
    int resNumber = Integer.parseInt(res);
    System.out.printf(res);
    Assertions.assertTrue(resNumber > minValue);

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
