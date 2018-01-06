package junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.By.id;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.HasWebDriver;

/**
 *
 */
public interface WebDriverFunctions {


  static Supplier<Optional<WebDriver>> newWebDriver() {
    return () -> {
      try {
        System.setProperty("webdriver.chrome.driver", "_data/chromedriver");
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        return Optional.of(new ChromeDriver(chromeOptions));
      } catch (Exception e) {
        e.printStackTrace();
        return empty();
      }
    };
  }

  static BiFunction<WebDriver, String, Optional<WebElement>> elementFor() {
    return (driver, id) -> ofNullable(driver.findElement(id(id)));
  }

  static Consumer<WebDriver> takeScreenShot() {
    return (webDriver) -> {
      System.out.println("takeScreenShot !!");
      //take Screenshot
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      try {
        outputStream.write(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        //write to target/screenshot-[timestamp].jpg
        final FileOutputStream out = new FileOutputStream("target/screenshot-" + LocalDateTime.now() + ".png");
        out.write(outputStream.toByteArray());
        out.flush();
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    };
  }

}
