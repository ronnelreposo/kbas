package controls;

import controls.body.IFrontUpdatePassControls;
import controls.body.IFrontNumericPadControls;
import controls.header.IFrontHeaderControls;
import controls.footer.IFrontFooterControls;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface IFrontViewControls {

    IFrontHeaderControls getHeaderControls();
    IFrontNumericPadControls getNumericPadControls();
    IFrontUpdatePassControls getUpdatePassControls();
    IFrontFooterControls getFooterControls();
    Parent getParent();
    Scene getScene();
    Stage getStage();
}