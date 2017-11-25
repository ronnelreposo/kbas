
package controls.footer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public interface IFooterControls
{
	Button getHelpButton();
	Button getDevelopersButton();
	Label getSeparatorLabel();
	HBox getParentContainer();
}

