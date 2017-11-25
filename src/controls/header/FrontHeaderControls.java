package controls.header;

import adapters.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Header Controls.
 * @author Arya
 *
 */
public final class FrontHeaderControls implements IFrontHeaderControls
{	
    private final Label logoLabel;
    private final TextField studentIdTextField;
    private final PasswordField studPasswordPasswordField;
    private final Button signInButton;
    private final HBox containerLogo;
    private final HBox containerSignIn;
    private final HBox containerHeaderView;

    private FrontHeaderControls ()
    {		
        setUpLogoLabel((logoLabel = (new Label())));
        setUpSignInIdTextField((studentIdTextField = (new TextField())));
        setUpSignInPasswordPasswordFIeld((studPasswordPasswordField =
                (new PasswordField())));
        setUpSignInButton((signInButton = (new Button(" Sign In "))));
        setUpContainerLogoHBox((containerLogo = (new HBox(2))));
        setUpContainerSignInHBox((containerSignIn = (new HBox(2))));
        setUpParentContainer((containerHeaderView = (new HBox(2))));
    }

    public final static IFrontHeaderControls Create ()
    { return (new FrontHeaderControls()); }

    @Override public final Label getLogoLabel () { return (logoLabel); }
    @Override public final TextField getSignInIdTextField ()
    { return (studentIdTextField); }
    @Override public final PasswordField getSignInPassPasswordField ()
    { return (studPasswordPasswordField); }
    @Override public final Button getSignInButton ()
    { return (signInButton); }
    @Override public final HBox getContainerLogoHBox ()
    { return (containerLogo); }
    @Override public final HBox getContainerSignInHBox ()
    { return (containerSignIn); }
    @Override public final HBox getParentContainer ()
    { return (containerHeaderView); }

    private Insets uniformInsets()
    {
        return (new Insets(2));
    }
    private HBox defaultBoxPadding (HBox box)
    {
        return (HBoxAdapter.Create(box)
                .setPadding((uniformInsets()))
                .get());
    }
    private HBox defaultBoxAlignment (HBox box)
    {
        return (HBoxAdapter.Create(box)
                .setAlignment((Pos.CENTER_LEFT))
                .get());
    }
    private Label setUpLogoLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .setImage("/img/Samar_State_University_Logo.png")
                .get());
    }

    private TextInputControl setUpDefaultTextInputControl
        (TextInputControl textInputControl)
    {
        final int PREF_WIDTH = 150;
        final int MIN_HEIGHT = 34;
        return (TextInputControlAdapter.Create(textInputControl)
                .setMinHeight(MIN_HEIGHT)
                .setPrefWidth(PREF_WIDTH)
                .setFocusTraversable(false)
                .setStyleClass("class-textfield")
                .get());
    }
    private TextInputControl setUpSignInIdTextField
        (TextInputControl textInputControl)
    {
        return setUpDefaultTextInputControl(
                TextInputControlAdapter.Create(textInputControl)
                        .setPromptText("Student ID...")
                        .get());
    }
    private TextInputControl setUpSignInPasswordPasswordFIeld
        (TextInputControl textInputControl)
    {
        return setUpDefaultTextInputControl(
                TextInputControlAdapter.Create(textInputControl)
                        .setPromptText("Password...")
                        .get());
    }
    private Button setUpSignInButton (Button button)
    {
            return (ButtonAdapter.Create(button)
                            .addStyleClass("class-button")
                            .get());
    }
    private HBox setUpContainerLogoHBox (HBox hBox)
    {
        return (defaultBoxPadding(
                (defaultBoxAlignment(
                        (HBoxAdapter.Create(hBox)
                                .addChildren((getLogoLabel()))
                                .get())))));
    }
    private HBox setUpContainerSignInHBox (HBox hBox)
    {
        return (defaultBoxPadding(
                (HBoxAdapter.Create(hBox)
                        .addChildren((getSignInIdTextField()))
                        .addChildren((getSignInPassPasswordField()))
                        .addChildren((getSignInButton()))
                        .setAlignment((Pos.CENTER)))
                        .get()));
    }
    private HBox setUpParentContainer (HBox hBox)
    {
        return (defaultBoxAlignment(
                (defaultBoxPadding(
                        (HBoxAdapter.Create(hBox)
                                .addChildren(getContainerLogoHBox())
                                .addChildren(getContainerSignInHBox())
                                .addStyleClass("class-container-header")
                                .get())))));
    }
}