package views;

import controls.adapter.StageAdapter;
import javafx.stage.Stage;

final class Data
{
    private Data () { }

    static Showable showable = (s) ->
    {
        s.show();
        return s;
    };
}
