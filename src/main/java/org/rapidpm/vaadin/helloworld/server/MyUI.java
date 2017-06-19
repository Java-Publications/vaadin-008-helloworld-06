package org.rapidpm.vaadin.helloworld.server;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 *
 */
public class MyUI extends UI {

  public static final String BUTTON_ID = "buttonID";
  public static final String INPUT_ID_A = "input_A_ID";
  public static final String INPUT_ID_B = "input_B_ID";
  public static final String OUTPUT_ID = "outputID";

  @Override
  protected void init(VaadinRequest request) {
    final HorizontalLayout layout = new HorizontalLayout();

    final TextField inputA = new TextField();
    inputA.setId(INPUT_ID_A);
    final TextField inputB = new TextField();
    inputB.setId(INPUT_ID_B);

    final Button button = new Button("click me");
    button.setId(BUTTON_ID);

    final TextField output = new TextField();
    output.setReadOnly(true);
    output.setId(OUTPUT_ID);

    layout.addComponents(inputA, new Label("+"), inputB, button, output);

    button.addClickListener(
        event -> output.setValue(inputA.getValue() + inputB.getValue()));
    setContent(layout);
  }
}
