package junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin;

import static junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.WebDriverFunctions.elementFor;

import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 */
public abstract class AbstractVaadinPageObject implements VaadinPageObject {

  private WebDriver webDriver;

  public AbstractVaadinPageObject(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Override
  public WebDriver getWebDriver() {
    return webDriver;
  }


  public void switchToDebugMode() {
    webDriver.get(url().get() + "?debug&restartApplication");
  }

  public void restartApplication() {
    webDriver.get(urlRestartApp().get());
  }

  public void loadPage() {
    webDriver.get(url().get());
  }

  public Function<String, WebElement> element
      = (id) -> elementFor()
      .apply(webDriver, id)
      .orElseThrow(() -> new RuntimeException("WebElement with the ID "
                                              + id + " is not available"));

}
