package controls.footer;

import adapters.*;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Front Footer Controls.
 * @author Arya
 *
 */
public final class FrontFooterControls implements IFrontFooterControls
{	
    private final Label buttonSeparatorLabel;
    private final Button helpButton, developersButton;
    private final HBox parentContainerHBox;

    private FrontFooterControls ()
    {
        setUpHelpButton((helpButton = (new Button("Help"))));
        setUpButtonSeparatorLabel((buttonSeparatorLabel = (new Label("|"))));
        setUpDevelopersButton((developersButton = (new Button("Developers"))));
        setUpParentContainer((parentContainerHBox = (new HBox(10))));

        wireUpButtonEvents();
    }

    public final static IFrontFooterControls Create () { return (new FrontFooterControls()); }

    @Override public final Button getHelpButton () { return (helpButton); }
    @Override public final Button getDevelopersButton () { return (developersButton); }
    @Override public final Label getButtonSeparatorLabel () { return (buttonSeparatorLabel); }
    @Override public final HBox getParentContainer () { return (parentContainerHBox); }

    private Button setUpHelpButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn-help").get());
    }
    private Button setUpDevelopersButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn-developers").get());
    }
    private Label setUpButtonSeparatorLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .labelStyleAdder("class-label-footer")
                .get());
    }
    private HBox setUpParentContainer (HBox hbox)
    {
        return (HBoxAdapter.Create(hbox)
                .addChildren(getHelpButton())
                .addChildren(getButtonSeparatorLabel())
                .addChildren(getDevelopersButton())
                .addStyleClass("class-container-bottom")
                .setAlignment(Pos.CENTER_RIGHT)
                .setPadding((new Insets((2))))
                .get());
    }
    private Button getButtonSource (ActionEvent ev)
    {
        return ((Button) ev.getSource());
    }
    private IFrontFooterControls wireUpButtonEvents ()
    {
        final String initText = "Clicked: Button ";

        helpButton.setOnAction(e -> {
                System.out.println((initText) + (getButtonSource(e).getText()));
        });

        developersButton.setOnAction(e -> {
                System.out.println((initText) + (getButtonSource(e).getText()));
        });

        return (this);
    }
}