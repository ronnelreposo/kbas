package controls;

import adapters.*;
import app.App;
import controls.body.*;
import controls.header.*;
import controls.footer.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class FrontViewControls implements IFrontViewControls
{
    private final Scene scene;
    private final Stage stage;
    
    private final IFrontHeaderControls frontHeaderControls;
    private final IFrontNumericPadControls numPadControls;
    private final IFrontUpdatePassControls updatePassControls;
    private final IFrontFooterControls footerControls;

    private FrontViewControls ()
    {
        frontHeaderControls = (FrontHeaderControls.Create());
        numPadControls = (FrontNumericPadControls.Create());
        updatePassControls = (FrontUpdatePassControls.Create());
        footerControls = (FrontFooterControls.Create());
        stage = (App.AppStage);
        setUpScene(scene
            = (new Scene((getParent()),
                    (stage.getWidth()),
                    ((stage).getMaxHeight()))));
        setUpStage((stage));
    }

    public static IFrontViewControls Create ()
    { return (new FrontViewControls()); }

    @Override public final IFrontHeaderControls getHeaderControls()
    { return (frontHeaderControls); }
    @Override public final IFrontNumericPadControls getNumericPadControls()
    { return (numPadControls); }
    @Override public final IFrontUpdatePassControls getUpdatePassControls()
    { return (updatePassControls); }
    @Override public final IFrontFooterControls getFooterControls()
    { return (footerControls); }
    @Override public final Parent getParent()
    {
        return (BorderPaneAdapter.Create((new BorderPane()))
                .setTop((getHeaderControls().getParentContainer()))
                .setLeft((getNumericPadControls().getParentControls()))
                .setCenter((getUpdatePassControls().getParentContainer()))
                .setBottom((getFooterControls().getParentContainer()))
                .get());
    }
    @Override public final Scene getScene() { return (scene); }
    @Override public final Stage getStage() { return (stage); }
    private Scene setUpScene (Scene scene)
    {
        final ControlPropertyAdder<Scene, String> styleAdder = (s, str) ->
        {
            s.getStylesheets().add(str);

            return s;
        };
        return (styleAdder.add((scene),	("/css/style.css")));
    }
    private Stage setUpStage(Stage stage)
    {
        final int MIN_WIDTH = 300;
        final int MIN_HEIGHT = 450;
        return (StageAdapter.Create(stage)
                .setMinWidth(MIN_WIDTH)
                .setMinHeight(MIN_HEIGHT)
                .setScene(getScene())
                .get());
    }
}