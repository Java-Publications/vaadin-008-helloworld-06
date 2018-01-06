package junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static java.lang.System.out;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.WebDriverFunctions.newWebDriver;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.selenium.WebDriverFunctions.takeScreenShot;

/**
 *
 */
public class WebDriverSeleniumExtension implements ParameterResolver, AfterTestExecutionCallback {

  public static final String WEBDRIVER_KEY = "webdriver";

  static Function<ExtensionContext, Namespace> namespaceFor() {
    return (ctx) -> Namespace.create(WebDriverSeleniumExtension.class,
                                     ctx.getTestClass().get().getName(),
                                     ctx.getTestMethod().get().getName()
    );
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().isAnnotationPresent(Selenium.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    out.println("WebDriverSeleniumExtension.resolve ");
    out.flush();
    final Namespace              namespace = namespaceFor().apply(extensionContext);
    final ExtensionContext.Store store     = extensionContext.getStore(namespace);
    return store.getOrComputeIfAbsent(WEBDRIVER_KEY, key -> newWebDriver().get().get());

  }


  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    out.println("WebDriverSeleniumExtension.afterTestExecution ");
    out.flush();

    final WebDriver webdriver = context
        .getStore(namespaceFor().apply(context))
        .get(WEBDRIVER_KEY, WebDriver.class);

    context.getExecutionException()
           .ifPresent(e -> takeScreenShot().accept(webdriver));

    webdriver.close();
    webdriver.quit();

    context
        .getStore(namespaceFor().apply(context))
        .remove(WEBDRIVER_KEY);
  }

}
