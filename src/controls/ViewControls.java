
package controls;

import controls.footer.IFooterControls;
import controls.header.IHeaderControls;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface ViewControls
{
	Parent getRootControl();
	Stage getStage();
	Scene getScene();
	IHeaderControls getHeaderControls();
	IFooterControls getFooterControls();
}
