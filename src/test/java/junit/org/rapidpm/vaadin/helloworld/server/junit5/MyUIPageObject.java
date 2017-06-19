package junit.org.rapidpm.vaadin.helloworld.server.junit5;

import static org.rapidpm.vaadin.helloworld.server.MyUI.BUTTON_ID;
import static org.rapidpm.vaadin.helloworld.server.MyUI.INPUT_ID_A;
import static org.rapidpm.vaadin.helloworld.server.MyUI.INPUT_ID_B;
import static org.rapidpm.vaadin.helloworld.server.MyUI.OUTPUT_ID;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.AbstractVaadinPageObject;

/**
 *
 */
public class MyUIPageObject extends AbstractVaadinPageObject {


  public Supplier<WebElement> button = () -> element.apply(BUTTON_ID);
  public Supplier<WebElement> output = () -> element.apply(OUTPUT_ID);
  public Supplier<WebElement> inputA = () -> element.apply(INPUT_ID_A);
  public Supplier<WebElement> inputB = () -> element.apply(INPUT_ID_B);


  public MyUIPageObject(WebDriver webDriver) {
    super(webDriver);
  }
}
