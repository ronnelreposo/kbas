
package controllers;

import adapters.ButtonAdapter;
import controllers.printing.*;
import functional.core.Tuple;
import javafx.scene.control.Button;
import views.*;
import views.View;

public final class AssessmentController
{
    private final View view;
    private final Tuple session;
    private AssessmentController (Tuple session)
    {
        this.session = session;
        wireUpButtonEvents(view = (AssessmentView.Create()));
    }

    public final static AssessmentController Create (Tuple session)
    {
        return (new AssessmentController(session));
    }

    public final AssessmentController show()
    {
        view.showStage();

        return (AssessmentController.Create(session));
    }

    private AssessmentController wireUpPrintButtonEvent (View view)
    {
        view.getControls()
        .getHeaderControls()
        .getPrintButton()
        .setOnAction(e -> printAssessment());

        return (this);
    }
    private AssessmentController wireUpSpiButtonEvent (View view)
    {
        view.getControls()
        .getHeaderControls()
        .getSpiButton()
        .setOnAction(e -> System.out.println("Assessment SPI"));

        return (this);
    }
    private AssessmentController wireUpBackButtonEvent (View view)
    {
        view.getControls()
        .getHeaderControls()
        .getBackButton()
        .setOnAction(e -> showMainView());

        return (this);
    }
    private AssessmentController wireUpSignOutButtonEvent (View view)
    {
        view.getControls()
        .getHeaderControls()
        .getSignOutButton()
        .setOnAction(e -> signOutAccount());

        return (this);
    }
    private AssessmentController wireUpButtonEvents (View view)
    {		
        return wireUpPrintButtonEvent(view)
                        .wireUpSpiButtonEvent(view)
                        .wireUpBackButtonEvent(view)
                        .wireUpSignOutButtonEvent(view);
    }
    private AssessmentController signOutAccount()
    {
        FrontController.Create().show();

        return (this);
    }
    private AssessmentController showMainView ()
    {
        MainController.Create(session).show();

        return (this);
    }
    private AssessmentController printAssessment()
    {        
        (new Thread((()->
        {
            final Button disabledButton =
                    disableButton((view.getControls()
                            .getHeaderControls()
                    .getPrintButton()));
            
            PrintSetting.Create()
                    .print((AssessmentPrintingTemplate.Create(session)));
            
            enableButton(disabledButton);
        }))).start();
                
        return (this);
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
