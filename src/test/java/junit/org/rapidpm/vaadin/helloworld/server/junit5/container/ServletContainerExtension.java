package junit.org.rapidpm.vaadin.helloworld.server.junit5.container;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.vaadin.helloworld.server.Main;

import static java.lang.System.out;

/**
 *
 */
public class ServletContainerExtension implements BeforeEachCallback, AfterEachCallback {

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    out.println("ServletContainerExtension.beforeEach ");
    out.flush();
    Main.start();
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    out.println("ServletContainerExtension.afterEach ");
    out.flush();
    Main.shutdown();
  }

}
