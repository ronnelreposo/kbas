package controls.body;

import adapters.*;
import controls.ParentContainer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public final class MainBodyControls implements ParentContainer
{
    private final Button btnAssessment, btnGrades;
    private final HBox containerCenterView;

    private MainBodyControls()
    {
        setUpAssessmentButton(btnAssessment = (new Button()));
        setUpGradesButton(btnGrades = (new Button()));
        setUpParentContainer(containerCenterView = (new HBox(50)));
    }

    public final static MainBodyControls Create () { return new MainBodyControls(); }
    
    public final Button getAssessmentButton () { return btnAssessment; }
    public final Button getGradesButton () { return btnGrades; }

    @Override public HBox getParentContainer () { return containerCenterView; }

    private Button setUpDefaultButton (Button button)
    {
        return (ButtonAdapter.Create(button)
                .addStyleClass("class-btn-img")
                .setAlignment(Pos.BASELINE_LEFT)
                .get());
    }
    private HBox setUpParentContainer (HBox hbox)
    {
        return (HBoxAdapter.Create(hbox)
                .addChildren((getAssessmentButton()))
                .addChildren((getGradesButton()))
                .setAlignment(Pos.CENTER)
                .setPadding((new Insets((85), (55), (50), (55))))
                .get());
    }
    private Button setUpAssessmentButton (Button button)
    {
        return (setUpDefaultButton(
                ButtonAdapter.Create(button)
                        .setGraphicImage("/img/Assessments.jpg")
                        .get()));
    }
    private Button setUpGradesButton (Button button)
    {
        return (setUpDefaultButton(
                ButtonAdapter.Create(button)
                        .setGraphicImage("/img/Grades.jpg")
                        .get()));
    }
}
