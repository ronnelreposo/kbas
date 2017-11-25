
package views;

import controls.ViewControls;
import javafx.stage.Stage;

public interface View
{
	ViewControls getControls();
	Stage showStage();
}
