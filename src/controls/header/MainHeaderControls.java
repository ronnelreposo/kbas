package controls.header;

import adapters.*;
import controls.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public final class MainHeaderControls implements ParentContainer
{
    private final Label logoLabel;
    private final Button signOutButton;
    private final HBox logoContainer;
    private final HBox signOutContainer;
    private final HBox parentContainer;

    private MainHeaderControls ()
    {
        setUpLogoLabel (logoLabel = new Label());
        setUpSignOutButton (signOutButton = new Button("Sign Out"));
        setUpLogoContainer (logoContainer = new HBox());
        setUpSignOutContainer (signOutContainer = new HBox());
        setUpParentContainer (parentContainer = new HBox());
    }

    public static MainHeaderControls Create () { return new MainHeaderControls(); }

    private Label setUpLogoLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .setImage("/img/Samar_State_University.png")
                .get());
    }
    public final Label getLogoLabel ()
    {
        return (logoLabel);
    }

    private Button setUpSignOutButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn")
                .get());
    }
    public final Button getSignOutButton ()
    {
        return signOutButton;
    }

    private HBox setUpLogoContainer (HBox parent)
    {
        return (HBoxAdapter.Create(parent)
                .addChildren((getLogoLabel()))
                .setPadding((new Insets(5)))
                .get());
    }
    public final HBox getLogoContainer ()
    {
        return logoContainer;
    }

    private HBox setUpSignOutContainer (HBox parent)
    {
        return (HBoxAdapter.Create(parent)
                .addChildren((getSignOutButton()))
                .setPadding((new Insets(45, 5, 5, 5)))
                .get());
    }
    public final HBox getSignOutContainer ()
    {
        return signOutContainer;
    }

    private HBox setUpParentContainer (HBox parent)
    {
        return (HBoxAdapter.Create(parent)
                .addChildren((getLogoContainer()))
                .addChildren((getSignOutContainer()))
                .addStyleClass("class-container-header")
                .setPadding(new Insets(5))
                .get());
    }
    @Override public final HBox getParentContainer ()
    {
        return (parentContainer);
    }
}