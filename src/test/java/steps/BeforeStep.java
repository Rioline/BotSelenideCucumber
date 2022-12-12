package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;

public class BeforeStep {

  @Given("Open site {string}")
  public void openWebSite(String url) {
    Configuration.timeout = 60000;
    Configuration.browser = "edge";
    Selenide.open(url);
  }
}
