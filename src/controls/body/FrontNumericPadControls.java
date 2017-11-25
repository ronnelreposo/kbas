package controls.body;

import adapters.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Numeric Pas Controls implementation.
 * @author Arya
 *
 */
public final class FrontNumericPadControls implements IFrontNumericPadControls
{	
    private final Button clrButton, bkspButton,
    button1, button2,
    button3, button4,
    button5, button6,
    button7, button8,
    button9, button0;

    private final int MIN_PAD = 2;

    private final GridPane parentGridPane;

    private FrontNumericPadControls()
    {		
        clrButton = (createButton("C"));
        bkspButton = (createButton("<"));
        button1 = (createButton("1"));
        button2 = (createButton("2"));
        button3 = (createButton("3"));
        button4 = (createButton("4"));
        button5 = (createButton("5"));
        button6 = (createButton("6"));
        button7 = (createButton("7"));
        button8 = (createButton("8"));
        button9 = (createButton("9"));
        button0 = (createButton("0"));

        setUpGridPane(parentGridPane = (new GridPane()));
    }

    public final static IFrontNumericPadControls Create ()
    {
            return (new FrontNumericPadControls());
    }

    @Override public final Button getButton1 () { return (button1); }
    @Override public final Button getButton2 () { return (button2); }
    @Override public final Button getButton3 () { return (button3); }
    @Override public final Button getButton4 () { return (button4); }
    @Override public final Button getButton5 () { return (button5); }
    @Override public final Button getButton6 () { return (button6); }
    @Override public final Button getButton7 () { return (button7); }
    @Override public final Button getButton8 () { return (button8); }
    @Override public final Button getButton9 () { return (button9); }
    @Override public final Button getClearButton () { return (clrButton); }
    @Override public final Button getBkspButton () { return (bkspButton); }
    @Override public final Button getButton0 () { return (button0); }

    private final int BUTTON_PREF_WIDTH = 50;
    private Button defaultButtonStyle (Button button)
    {
        return (ButtonAdapter.Create(button)
                .setPrefWidth(BUTTON_PREF_WIDTH)
                .addStyleClass("class-button-numpad")
                .get());
    }
    private Button createButton (String text)
    {
        return defaultButtonStyle((new Button(text)));
    }
    private ColumnConstraints setUpNumericPadColumnConstraints ()
    {
        final ColumnConstraints constraints = (new ColumnConstraints());
        constraints.setPrefWidth(BUTTON_PREF_WIDTH);
        constraints.setHalignment(HPos.CENTER);
        return (constraints);
    }

    private GridPane setUpGridPane (GridPane gp)
    {
        final int THIRD_COL = 2;
        final int H_GAP = ((MIN_PAD) * (8));
        final int V_GAP = ((MIN_PAD) * (5));

        return (GridPaneAdapter.Create(gp)
                .addColumnConstraints(0,
                        (new ColumnConstraints [] {
                            (setUpNumericPadColumnConstraints()),
                            (setUpNumericPadColumnConstraints()),
                            (setUpNumericPadColumnConstraints())
                        }))
                .addColumnNodes(0, 0,
                        (new Node [] {
                            (getButton1()),
                            (getButton4()),
                            (getButton7()),
                            (getClearButton())
                        }))
                .addColumnNodes(0, 1,
                        (new Node [] {
                            (getButton2()),
                            (getButton5()),
                            (getButton8()),
                            (getButton0())
                        }))
                .addColumnNodes(0, THIRD_COL,
                        (new Node [] {
                            (getButton3()),
                            (getButton6()),
                            (getButton9()),
                            (getBkspButton())
                        }))
                .setHgap(H_GAP)
                .setVgap(V_GAP)
                .setPadding((new Insets(2)))
                .setAlignment((Pos.CENTER))
                .addStyleClass("class-container-numericpad")
                .get());
    }

    @Override public Node getParentControls() { return (parentGridPane); }
}