package views;

import controls.GradesViewControls;
import controls.ViewControls;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import models.abstractions.IGradesModel;

public final class GradesView implements View
{
	private final ViewControls viewControls;
	
	private GradesView (ObservableList<IGradesModel> list)
	{
            viewControls = GradesViewControls.Create(list);
	}
	
	public final static GradesView Create (ObservableList<IGradesModel> list)
        {
            return (new GradesView(list));
        }

	@Override public final ViewControls getControls() { return viewControls; }
	
	@Override public Stage showStage()
	{
            return (Data.showable.show(viewControls.getStage()));
	}
}
