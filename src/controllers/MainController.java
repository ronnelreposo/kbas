package controllers;

import app.App;
import functional.core.Tuple;
import views.*;

public final class MainController
{
    private final MainView view;
    private final Tuple<Integer, String> session;
    
    private MainController (Tuple<Integer, String> session)
    {
        this.session = session;
        wireUpButtonEvents(view =
                MainView.Create(App.AppStage));
    }

    public final static MainController Create (Tuple<Integer, String> session)
    {
        return (new MainController(session));
    }

    public final MainController show()
    {
        view.showStage();

        return (MainController.Create(session));
    }

    private MainController signOutAccount()
    {
        FrontController.Create().show();

        return (this);
    }
    private MainController wireUpSignOutButtonEvent (MainView view)
    {
        view.getControls()
        .getHeaderControls()
        .getSignOutButton()
        .setOnAction(e -> signOutAccount());

        return (this);
    }
    private MainController showAssessment ()
    {
        AssessmentController.Create(session).show();

        return (this);
    }
    private MainController wireUpViewAssessment (MainView view)
    {
        view.getControls()
        .getBodyControls()
        .getAssessmentButton()
        .setOnAction(e -> showAssessment());

        return (this);
    }
    private MainController showGrades ()
    {
        GradesController.Create(session).show();

        return (this);
    }
    private MainController wireUpViewGrades (MainView view)
    {
        view.getControls()
        .getBodyControls()
        .getGradesButton()
        .setOnAction(e -> showGrades());

        return (this);
    }
    private MainController wireUpButtonEvents (MainView view)
    {
        return (wireUpSignOutButtonEvent(view)
                        .wireUpViewAssessment(view)
                        .wireUpViewGrades(view));
    }
}
