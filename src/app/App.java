package app;

import controllers.*;
import javafx.application.Application;
import javafx.stage.Stage;
import settings.ConnectionParameters;
import settings.ConnectionSettings;
/**
 * Main Application.
 * @author Arya
 */
public final class App extends Application
{
    public static Stage AppStage;
    
    public static void main(String[] args) { launch(args); }
    
    @Override public void start (Stage stage) throws Exception
    {
        App.AppStage = (stage);
        
        //must be global.
        ConnectionSettings
        .connectionParameters(
                (ConnectionParameters.Create("127.0.0.1", "kbas", "root", "")));
        ConnectionSettings.databaseConnectionIns();

        FrontController.Create().show();
    }
}