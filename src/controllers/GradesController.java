
package controllers;

import views.View;
import abstractions.ControlPropertySetter;
import adapters.ButtonAdapter;
import controllers.printing.GradesPrintingTemplate;
import controllers.printing.PrintSetting;
import functional.core.Tuple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import models.abstractions.IGradesModel;
import models.abstractions.IStudentGrade;
import models.data.GradesModel;
import models.database.StudentGradeDatabase;
import views.GradesView;

public final class GradesController
{	
    private final View view;
    private final ControlPropertySetter<Button, EventHandler<ActionEvent>> onAction;
    private final StudentGradeDatabase database;
    private final Tuple<Integer, String> session;
    
    private GradesController (Tuple<Integer, String> session)
    {
        database = (StudentGradeDatabase.Instance());
        this.session = session;
        view = (GradesView.Create((getGrades(database.GetAll(session.second())))));
        
        onAction = (button, actionEvent) ->
        {
            button.setOnAction(actionEvent);
            
            return button;
        };
        wireUpButtonEvents(view);
    }

    public final static GradesController Create (Tuple<Integer, String> session)
    {
        return (new GradesController(session));
    }

    public final GradesController show()
    {
        view.showStage();

        return (GradesController.Create(session));
    }

    private GradesController wireUpPrintButtonEvent (View view)
    {
        onAction.set(view.getControls().getHeaderControls().getPrintButton(),
        e -> printGrades());

        return (this);
    }
    private GradesController wireUpSpiButtonEvent (View view)
    {
        onAction.set(view.getControls().getHeaderControls().getSpiButton(),
        e -> System.out.println("Grades SPI"));

        return (this);
    }
    private GradesController wireUpBackButtonEvent (View view)
    {
        onAction.set(view.getControls().getHeaderControls().getBackButton(),
        e -> showMainView());

        return (this);
    }
    private GradesController wireUpSignUpButtonEvent (View view)
    {
        onAction.set(view.getControls().getHeaderControls().getSignOutButton(),
        e -> signOutAccount());

        return (this);
    }
    private GradesController wireUpButtonEvents (View view)
    {	
        return (wireUpPrintButtonEvent(view)
                        .wireUpSpiButtonEvent(view)
                        .wireUpBackButtonEvent(view)
                        .wireUpSignUpButtonEvent(view));
    }
    private GradesController printGrades ()
    {
        ((new Thread(() -> {
            
            final Button disabledButton =
                    disableButton((view.getControls()
                            .getHeaderControls()
                    .getPrintButton()));
            
            PrintSetting.Create().print((GradesPrintingTemplate.Create(session)));
            
            enableButton(disabledButton);
        
        }))).start();

        return (this);
    }
    private GradesController showMainView ()
    {
        MainController.Create(session).show();

        return (this);
    }
    private GradesController signOutAccount ()
    {
        FrontController.Create().show();

        return (this);
    }

    private ObservableList<IGradesModel>
        addToList (ObservableList<IGradesModel> list, IGradesModel model)
    {
            list.add(model);
            return (list);
    }
    private IGradesModel transformToModel (IStudentGrade studentGrade)
    {
        return (GradesModel.Create()
                        .setSubjectName("name not available.")
                        .setSubjectCode((studentGrade.getSubject().getCode()))
                        .setMidTerm((studentGrade.getMidTerm()))
                        .setFinalTerm((studentGrade.getFinalTerm()))
                        .setRemark((studentGrade.getRemark()))
                        .setEmployeeId((studentGrade.getEmployee().getID())));
    }
    private ObservableList<IGradesModel>
        forgeList(int from, ObservableList<IStudentGrade> list,
                    ObservableList<IGradesModel> modelList)
    {
        return (((from) > ((list.size()) - (1))) ? (modelList) :
                (forgeList(((from) + (1)), (list),
                        (addToList((modelList),
                                (transformToModel((list.get(from)))))))));
    }
    private ObservableList<IGradesModel> getGrades(ObservableList<IStudentGrade> list)
    {
        return (forgeList((0), (list), (FXCollections.observableArrayList())));
    }
    
    private Button disableButton (Button button)
    {
        button.disableProperty().set(true);
        
        return (ButtonAdapter.Create(button).get());
    }
    private Button enableButton (Button button)
    {
        button.disableProperty().set(false);
        
        return (ButtonAdapter.Create(button).get());
    }
}