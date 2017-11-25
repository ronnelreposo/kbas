package controls.footer;

import adapters.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public final class FooterControls implements IFooterControls
{
    private final Button helpButton, developersButton;
    private final Label separatorLabel;
    private final HBox parentContainerHBox;

    private FooterControls ()
    {
        setUpHelpButton(helpButton = (new Button("Help")));
        setUpDevButton(developersButton = (new Button("Developers")));
        setUpSeparatorLabel(separatorLabel = (new Label("|")));
        setUpParentContainer(parentContainerHBox = (new HBox(10)));

        wireUpButtonEvents();
    }

    public final static IFooterControls Create () { return (new FooterControls()); }

    private Label setUpSeparatorLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .labelStyleAdder("class-label")
                .get());
    }
    private HBox setUpParentContainer (HBox parent)
    {
        return HBoxAdapter.Create(parent)
                .addChildren((getHelpButton()))
                .addChildren((getSeparatorLabel()))
                .addChildren((getDevelopersButton()))
                .addStyleClass("class-container-bottom")
                .setAlignment((Pos.TOP_RIGHT))
                .setPadding((new Insets((10), (55), (10), (55))))
                .get();
    }
    private Button setUpHelpButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn-help")
                .get());
    }
    private Button setUpDevButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn-developers")
                .get());
    }
    private FooterControls wireUpHelpButtonEvent ()
    {
        helpButton.setOnAction(e -> System.out.println(" Help - Ran Sistema! "));

        return this;
    }
    private FooterControls wireUpDeveloperEvent ()
    {
        developersButton.setOnAction(e -> System.out.println(" Developer - Ran Sistema! "));

        return this;
    }
    private FooterControls wireUpButtonEvents ()
    {
        return wireUpHelpButtonEvent()
                        .wireUpDeveloperEvent();
    }

    @Override public final Button getHelpButton () { return (helpButton); }
    @Override public final Button getDevelopersButton () { return (developersButton); }
    @Override public final Label getSeparatorLabel () { return (separatorLabel); }
    @Override public final HBox getParentContainer () { return (parentContainerHBox); }
}