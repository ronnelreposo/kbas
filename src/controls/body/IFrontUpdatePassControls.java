package controls.body;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Front Update Password Controls Abstraction.
 * @author Arya
 *
 */
public interface IFrontUpdatePassControls
{
    Label getUpdatePassLabel();
    Label getStudIdLabel();
    Label getCurrentPasswordLabel();
    Label getNewPasswordLabel();
    Label getConfirmPasswordLabel();
    TextField getStudIdTextField();
    TextField getCurrentPassPasswordField();
    TextField getNewPassPasswordField();
    TextField getConfirmPassPasswordField();
    Button getUpdateButton();
    HBox getHeaderContainer();
    VBox getParentContainer();
    Node getParentControl();
}
