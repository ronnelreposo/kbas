package views;

import controls.*;
import javafx.stage.Stage;

public final class AssessmentView implements View
{
    private final ViewControls controls;

    private AssessmentView ()
    {
        controls = (AssessmentViewControls.Create());
    }

    public final static AssessmentView Create () { return (new AssessmentView()); }

    @Override public final ViewControls getControls () { return (controls); }

    @Override public final Stage showStage ()
    {
        return (Data.showable.show(controls.getStage()));
    }
}
