package controls.header;

import adapters.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public final class HeaderControls implements IHeaderControls
{
    private final Label logoLabel;
    private final Button signOutButton, printButton, spiButton, backButton;
    private final HBox parentContainer,  logoContainer, signOutContainer;
    private final String imgLogo;

    private HeaderControls (String imgLogoSource)
    {
        imgLogo = (imgLogoSource);

        setUpLogoLabel((logoLabel = (new Label())));
        defaultButtonStyle((printButton = (new Button("Print"))));
        defaultButtonStyle((spiButton = (new Button("SPI"))));
        defaultButtonStyle((backButton = (new Button("Back"))));
        defaultButtonStyle((signOutButton = (new Button("Sign Out"))));
        setUpLogoContainer((logoContainer = (new HBox(5))));
        setUpSignOutContainer(signOutContainer = (new HBox(5)));
        setUpParentContainer(parentContainer = (new HBox(5)));
    }

    public final static IHeaderControls Create (String imgLogo)
    {
            return new HeaderControls(imgLogo);
    }

    @Override public final Label getLogoLabel() { return (logoLabel); }
    @Override public final Button getPrintButton () { return (printButton); }
    @Override public final Button getSpiButton () { return (spiButton); }
    @Override public final Button getBackButton () { return (backButton); }
    @Override public final Button getSignOutButton () { return (signOutButton); }
    @Override public final HBox getLogoContainer() { return (logoContainer); }
    @Override public final HBox getSignOutContainer() { return (signOutContainer); }
    @Override public final HBox getParentContainer () { return (parentContainer); }

    private Label setUpLogoLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .setImage(imgLogo)
                .get());
    }
    private Button defaultButtonStyle (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn")
                .get());
    }
    private HBox setUpLogoContainer (HBox hBox)
    {
        return (HBoxAdapter.Create(hBox)
                .addChildren((getLogoLabel()))
                .setPadding((new Insets(5)))
                .get());
    }
    private HBox setUpSignOutContainer (HBox hBox)
    {
        return (HBoxAdapter.Create(hBox)
                .addChildren((getPrintButton()))
                .addChildren((getSpiButton()))
                .addChildren((getBackButton()))
                .addChildren((getSignOutButton()))
                .setPadding((new Insets((45), (5), (5), (5))))
                .get());
    }
    private HBox setUpParentContainer (HBox hBox)
    {
        return (HBoxAdapter.Create(hBox)
                .addChildren((getLogoContainer()))
                .addChildren((getSignOutContainer()))
                .addStyleClass("class-container-header")
                .setPadding((new Insets(5)))
                .get());
    }
}