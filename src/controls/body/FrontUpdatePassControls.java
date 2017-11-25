package controls.body;

import adapters.*;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public final class FrontUpdatePassControls implements IFrontUpdatePassControls
{	
    private final Label updatePassHeaderLabel;
    private final Label studIdLabel;
    private final Label currentPasswordLabel;
    private final Label newPasswordLabel;
    private final Label confirmPasswordLabel;
    private final TextField studIdTextField;
    private final PasswordField currentPassPasswordField,
            newPassPasswordField,
            confirmPassPasswordField;
    private final Button updateButton;
    private final HBox updatePassHBox;
    private final VBox updatePassParentContainerVBox;

    private final int MIN_PAD;

    private final GridPane parentGridPane;

    private FrontUpdatePassControls()
    {
        MIN_PAD = 2;

        setUpPassHeaderLabel(
                (updatePassHeaderLabel = (new Label(" Update Password "))));
        setUpDefaultLabel(
                (studIdLabel = (new Label("Student ID: "))));
        setUpDefaultLabel(
                (currentPasswordLabel = (new Label("Current Password: "))));
        setUpDefaultLabel(
                (newPasswordLabel = (new Label("New Password: "))));
        setUpDefaultLabel(
                (confirmPasswordLabel = (new Label("Confirm Password: "))));
        setUpStudIdTextField(
                setUpDefaultTextInputControl(
                        (studIdTextField = (new TextField()))));
        setUpCurrentPassPasswordField(
                        setUpDefaultTextInputControl(
                                (currentPassPasswordField = (new PasswordField()))));
        setUpNewPassPasswordField(
                setUpDefaultTextInputControl(
                        (newPassPasswordField = (new PasswordField()))));
        setUpConfirmPassPasswordField(
                setUpDefaultTextInputControl(
                        (confirmPassPasswordField = (new PasswordField()))));
        setUpUpdateButton(
                (updateButton = (new Button(" Update "))));
        setUpHeaderContainer(
                setDefaultHBox(
                        (updatePassHBox = (new HBox(MIN_PAD)))));
        setUpGridPane((parentGridPane = (new GridPane())));
        setUpUpdatePassParentContainer(
                (updatePassParentContainerVBox = (new VBox(MIN_PAD))));
    }

    public final static IFrontUpdatePassControls Create ()
    { return (new FrontUpdatePassControls()); }

    @Override public final Label getUpdatePassLabel ()
    { return (updatePassHeaderLabel); }
    @Override public Label getStudIdLabel ()
    { return (studIdLabel); }
    @Override public Label getCurrentPasswordLabel ()
    { return (currentPasswordLabel); }
    @Override public Label getNewPasswordLabel ()
    { return (newPasswordLabel); }
    @Override public Label getConfirmPasswordLabel ()
    { return (confirmPasswordLabel); }
    @Override public final TextField getStudIdTextField ()
    { return (studIdTextField); }
    @Override public final TextField getCurrentPassPasswordField ()
    { return (currentPassPasswordField); }
    @Override public final TextField getNewPassPasswordField ()
    { return (newPassPasswordField); }
    @Override public final TextField getConfirmPassPasswordField ()
    { return (confirmPassPasswordField); }
    @Override public final Button getUpdateButton ()
    { return (updateButton); }
    @Override public final HBox getHeaderContainer ()
    { return (updatePassHBox); }
    @Override public final VBox getParentContainer ()
    { return (updatePassParentContainerVBox); }
    @Override public Node getParentControl()
    { return (parentGridPane); }

    private Label setUpPassHeaderLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .labelStyleAdder("class-lbl-updatePass-infoView")
                .get());
    }
    private Label setUpDefaultLabel (Label label)
    {
        return (LabelAdapter.Create(label)
                .labelStyleAdder("class-label")
                .get());
    }
    private TextInputControl setDefaultTextFieldStyle (TextInputControl textField)
    {
        return (TextInputControlAdapter.Create(textField)
                .setStyleClass("class-textfield-secondary")
                .get());
    }
    private TextInputControl setUpDefaultTextInputControl (TextInputControl textField)
    {
        return setDefaultTextFieldStyle(textField);
    }
    private TextInputControl setUpPromptTextInputControl
    (TextInputControl textInputControl, String text)
    {
        return (TextInputControlAdapter.Create(textInputControl)
                .setPromptText(text)
                .get());
    }
    private TextInputControl setUpStudIdTextField (TextInputControl textInputControl)
    {
        return setUpPromptTextInputControl((textInputControl),
                ("Student ID..."));
    }
    private TextInputControl setUpCurrentPassPasswordField (TextInputControl textInputControl)
    {
        return setUpPromptTextInputControl((textInputControl),
                ("Current Password..."));
    }
    private TextInputControl setUpNewPassPasswordField (TextInputControl textInputControl)
    {
        return setUpPromptTextInputControl((textInputControl),
                ("New Password..."));
    }
    private TextInputControl setUpConfirmPassPasswordField (TextInputControl textInputControl)
    {
        return setUpPromptTextInputControl((textInputControl),
                ("Confirm Password..."));
    }
    private Button setUpUpdateButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-button")
                .get());
    }

    private ColumnConstraints setUpLabelColumnConstraints ()
    {
        final ColumnConstraints constraints = (new ColumnConstraints());
        constraints.setPrefWidth(150);
        constraints.setHalignment(HPos.RIGHT);

        return (constraints);
    }
    private ColumnConstraints setUpDataColumnConstraints ()
    {
        final ColumnConstraints constraints = (new ColumnConstraints());
        constraints.setPrefWidth(200);

        return (constraints);
    }

    private GridPane setUpGridPane (GridPane gp)
    {
        return (GridPaneAdapter.Create(gp)
                .setVgap(MIN_PAD)
                .addColumnConstraints(0,
                        (new ColumnConstraints [] {
                            (setUpLabelColumnConstraints()),
                            (setUpDataColumnConstraints())
                        }))
                .addColumnNodes((0), (0),
                        (new Node [] {
                            (getStudIdLabel()),
                            (getCurrentPasswordLabel()),
                            (getNewPasswordLabel()),
                            (getConfirmPasswordLabel())
                        }))
                .addColumnNodes((0), (1),
                        (new Node [] {
                            (getStudIdTextField()),
                            (getCurrentPassPasswordField()),
                            (getNewPassPasswordField()),
                            (getConfirmPassPasswordField()),
                            (getUpdateButton())
                        }))
                .get());
    }

    private Pos defaultPosition ()
    {
        return (Pos.CENTER_LEFT);
    }
    private HBox setDefaultPositionLeft (HBox hBox)
    {
        return (HBoxAdapter.Create(hBox)
                .setAlignment((defaultPosition()))
                .get());
    }
    private Insets uniformInsets ()
    {
        return (new Insets(MIN_PAD));
    }
    private HBox setDefaultInsets (HBox hBox)
    {
        return (HBoxAdapter.Create(hBox)
                .setPadding(uniformInsets())
                .get());
    }
    private HBox setDefaultHBox (HBox hBox)
    {
        return setDefaultInsets(setDefaultPositionLeft(hBox));
    }
    private HBox setUpHeaderContainer (HBox hBox)
    {
        return setDefaultPositionLeft(
                (HBoxAdapter.Create(hBox)
                        .addChildren((getUpdatePassLabel()))
                        .setPadding((uniformInsets()))
                        .addStyleClass("class-container-updatePassHeader")
                        .get()));
    }
    private VBox setUpUpdatePassParentContainer (VBox vBox)
    {
        return (VBoxAdapter.Create(vBox)
                .addChildren(getHeaderContainer())
                .addChildren(getParentControl())
                .setPadding(uniformInsets())
                .setAlignment((Pos.TOP_CENTER))
                .addStyleClass(("class-container-updatePass"))
                .get());
    }
}