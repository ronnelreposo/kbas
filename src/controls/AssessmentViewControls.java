package controls;

import adapters.*;
import app.App;
import controls.body.AssessmentBodyControls;
import controls.footer.*;
import controls.header.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.database.StudentAssessmentDatabase;

public final class AssessmentViewControls implements ViewControls
{	
    private final Stage stage;
    private final Scene scene;
    private final IHeaderControls headerControls;
    private final IFooterControls footerControls;
    private final BorderPane root;

    private AssessmentViewControls ()
    {
        headerControls = (HeaderControls.Create("/img/Logo_Assessments.png"));
        footerControls = (FooterControls.Create());
        setUpRoot((root = (new BorderPane())));

        final double PREF_WIDTH = 645;
        final double PREF_HEIGHT = 530;
        setUpScene((scene =
                (new Scene((getRootControl()),
                        (PREF_WIDTH), (PREF_HEIGHT)))));
        stage = (App.AppStage);
        setUpStage(stage);
    }

    public final static ViewControls Create ()
    {
        return (new AssessmentViewControls());
    }

    @Override public final Parent getRootControl () { return (root); }
    @Override public final Scene getScene () { return (scene); }
    @Override public final Stage getStage () { return (stage); }
    @Override public final IHeaderControls getHeaderControls () { return (headerControls); }
    @Override public final IFooterControls getFooterControls () { return (footerControls); }

    private Parent setUpRoot (BorderPane root)
    {
        return (BorderPaneAdapter.Create(root)
                .setTop((headerControls.getParentContainer()))
                .setCenter(
                        (AssessmentBodyControls.Create(
                                StudentAssessmentDatabase.Create())
                        .getParentControl()))
                .setBottom((footerControls.getParentContainer()))
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
                .setMinWidth((getScene().getWidth()))
                .setMinHeight((getScene().getHeight()))
                .setScene((getScene()))
                .setTitle("Assessment View")
                .get());
    }
}