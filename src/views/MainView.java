package views;

import controls.MainViewControls;
import javafx.stage.Stage;

public final class MainView
{
	private final MainViewControls controls;
	
	private MainView (Stage stage)
	{
		controls = MainViewControls.Create(stage);
	}
	
	public final static MainView Create (Stage stage)
	{
		return new MainView(stage);
	}
	
	public final MainViewControls getControls() { return controls; }
	
	public final Stage showStage()
	{
		return Data.showable.show(controls.getStage());
	}
}
