package controls;

import adapters.*;
import controls.body.MainBodyControls;
import controls.footer.*;
import controls.header.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class MainViewControls
{
    private final Stage stage;
    private final MainHeaderControls headerControls;
    private final MainBodyControls bodyControls;
    private final IFooterControls footerControls;
    private final BorderPane root;
    private final Scene scene;

    private MainViewControls (Stage s)
    {
        stage = (s);

        headerControls = (MainHeaderControls.Create());
        bodyControls = (MainBodyControls.Create());
        footerControls = (FooterControls.Create());
        setUpRoot((root = (new BorderPane())));

        final double PREF_WIDTH = 645;
        final double PREF_HEIGHT = 530;
        setUpScene((scene =
                (new Scene((getRootControl()),
                        (PREF_WIDTH),
                        (PREF_HEIGHT)))));

        setUpStage(stage);
    }

    public final static MainViewControls Create (Stage stage)
    {
        return (new MainViewControls(stage));
    }

    private Parent setUpRoot (BorderPane root)
    {
        return (BorderPaneAdapter.Create(root)
                .setTop((headerControls
                        .getParentContainer()))
                .setCenter((bodyControls
                        .getParentContainer()))
                .setBottom((footerControls
                        .getParentContainer()))
                .get());
    }
    private Scene setUpScene (Scene scene)
    {
        final ControlPropertyAdder<Scene, String> styleAdder = (s, str) ->
        {
            s.getStylesheets().add(str);

            return s;
        };
        return (styleAdder.add((scene),	("/css/style.css")));
    }
    private Stage setUpStage (Stage stage)
    {
        return (StageAdapter.Create(stage)
                .setTitle("Main View")
                .setScene((getScene()))
                .setMinHeight((getScene().getHeight()))
                .setMinWidth((getScene().getWidth()))
                .get());
    }

    public final Parent getRootControl () { return (root); }
    public final Stage getStage () { return (stage); }
    public final Scene getScene () { return (scene); }
    public final MainHeaderControls getHeaderControls () { return (headerControls); }
    public final MainBodyControls getBodyControls () { return (bodyControls); }
    public final IFooterControls getFooterControls () { return (footerControls); }
}