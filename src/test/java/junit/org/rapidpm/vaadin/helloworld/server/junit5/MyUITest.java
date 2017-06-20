package junit.org.rapidpm.vaadin.helloworld.server.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.Selenium;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.container.ServletContainerExtension;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.WebDriverSeleniumExtension;

/**
 *
 */

//Order is important top / down
@ExtendWith(ServletContainerExtension.class)
@ExtendWith(WebDriverSeleniumExtension.class)
public class MyUITest {


  @BeforeEach
  void init(@Selenium WebDriver webDriver) {
    System.out.println("MyUITest.init.webDriver = " + webDriver);
    this.pageObject = new MyUIPageObject(webDriver);
  }

  private MyUIPageObject pageObject;

  @Test
  @DisplayName("My 1st Vaadin JUnit 5 test! 😎")
  void test001(TestInfo testInfo) {

    pageObject.loadPage();

    pageObject.inputA.get().sendKeys("5");
    pageObject.inputB.get().sendKeys("5");

    final WebElement btn = pageObject.button.get();
    btn.click();
    String value = pageObject.output.get().getAttribute("value");
    Assertions.assertEquals("10", value);
  }
}
