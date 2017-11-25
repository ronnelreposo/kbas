
package controls.header;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public interface IHeaderControls
{
	Label getLogoLabel();
	Button getPrintButton();
	Button getSpiButton();
	Button getBackButton();
	Button getSignOutButton();
	HBox getLogoContainer();
	HBox getSignOutContainer();
	HBox getParentContainer();
}
