package controls;

import adapters.*;
import app.App;
import controls.body.GradesBodyControls;
import controls.footer.*;
import controls.header.*;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.abstractions.IGradesModel;

public final class GradesViewControls implements ViewControls
{
    private final Stage stage;
    private final Scene scene;
    private final IHeaderControls headerControls;
    private final IFooterControls footerControls;
    private final BorderPane root;
    
    private GradesViewControls (ObservableList<IGradesModel> list)
    {
        stage = (App.AppStage);
        
        headerControls = (HeaderControls.Create("/img/Logo_Grades.png"));
        footerControls = (FooterControls.Create());
        
        setUpRoot((root = (new BorderPane())), (list));

        final double PREF_WIDTH = 645;
        final double PREF_HEIGHT = 530;
        setUpScene((scene =
                (new Scene((getRootControl()),
                        (PREF_WIDTH), (PREF_HEIGHT)))));

        setUpStage(stage);
    }

    public static ViewControls Create (ObservableList<IGradesModel> list)
    {
        return (new GradesViewControls(list));
    }

    @Override public final Parent getRootControl () { return (root); }
    @Override public final Stage getStage () { return (stage); }
    @Override public final Scene getScene () { return (scene); }
    @Override public final IHeaderControls getHeaderControls () { return (headerControls); }
    @Override public final IFooterControls getFooterControls () { return (footerControls); }

    private Stage setUpStage (Stage stage)
    {
        return (StageAdapter.Create(stage)
                .setTitle("Grades View")
                .setScene((getScene()))
                .setMinWidth((getScene().getWidth()))
                .setMinHeight((getScene().getHeight()))
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
    private Parent setUpRoot (BorderPane root, ObservableList<IGradesModel> list)
    {		
        return (BorderPaneAdapter.Create(root)
                    .setTop((headerControls
                            .getParentContainer()))
                    .setCenter((GradesBodyControls.Create(list)
                            .getParentContainer()))
                    .setBottom((footerControls
                            .getParentContainer()))
                .get());
    }
}
