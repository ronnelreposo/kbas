package controls.adapter;

/**
 *
 * @author Arya
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

public final class StageAdapter extends Stage implements Cloneable
{
    private final Stage stage;
    private StageAdapter (Stage stage)
    {
        this.stage = stage;
    }
    public static StageAdapter Create (Stage stage)
    {
        return (new StageAdapter(stage));
    }
    @Override public Object clone()
    {
        try {
            return (super.clone());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(StageAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }
    
    public Stage clonedStage()
    {
        return ((Stage) (clone()));
    }
}
