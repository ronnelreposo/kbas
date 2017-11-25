package controls.header;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Front Header Controls Abstraction.
 * @author Arya
 *
 */
public interface IFrontHeaderControls
{
    Label getLogoLabel();
    TextField getSignInIdTextField();
    PasswordField getSignInPassPasswordField();
    Button getSignInButton();
    HBox getContainerLogoHBox();
    HBox getContainerSignInHBox();
    HBox getParentContainer();	
}
