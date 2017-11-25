package controls.footer;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Front Footer Controls Abstraction.
 * @author Arya
 *
 */
public interface IFrontFooterControls
{	
    Button getHelpButton();
    Button getDevelopersButton();
    Label getButtonSeparatorLabel();
    HBox getParentContainer();
}
 