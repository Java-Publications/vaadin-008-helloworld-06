package junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium;

import static junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.WebDriverFunctions.newWebDriver;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.WebDriverFunctions.takeScreenShot;

import java.util.function.Function;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.openqa.selenium.WebDriver;

/**
 *
 */
public class WebDriverSeleniumExtension implements ParameterResolver, AfterTestExecutionCallback {

  static Function<ExtensionContext, Namespace> namespaceFor() {
    return (ctx) -> Namespace.create(WebDriverSeleniumExtension.class,
                                     ctx.getTestClass().get().getName(),
                                     ctx.getTestMethod().get().getName());
  }

  @Override
  public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().isAnnotationPresent(Selenium.class);
  }

  @Override
  public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    System.out.println("WebDriverSeleniumExtension.resolve ");
    final Namespace namespace = namespaceFor().apply(extensionContext);
    final ExtensionContext.Store store = extensionContext.getStore(namespace);
    return store.getOrComputeIfAbsent("webdriver", key -> newWebDriver().get().get());
  }

  @Override
  public void afterTestExecution(TestExtensionContext context) throws Exception {
    System.out.println("WebDriverSeleniumExtension.afterTestExecution ");

    final WebDriver webdriver = context
        .getStore(namespaceFor().apply(context))
        .get("webdriver", WebDriver.class);

    context.getTestException()
           .ifPresent(e -> takeScreenShot().accept(webdriver));

    webdriver.close();
    webdriver.quit();

    context
        .getStore(namespaceFor().apply(context))
        .remove("webdriver");

  }

}
