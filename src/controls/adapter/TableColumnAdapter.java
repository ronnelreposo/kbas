
package controls.adapter;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Serves as the adapter for Table Column.
 * @author Arya
 */
public final class TableColumnAdapter
{
    private final TableColumn control;
    private TableColumnAdapter(TableColumn tableColumn)
    {
        this.control = tableColumn;
    }
    public final static TableColumnAdapter Create (TableColumn tableColumn)
    {
        return (new TableColumnAdapter(tableColumn));
    }
    public TableColumnAdapter setPrefWidth (double value)
    {
        control.setPrefWidth(value);
        
        return (TableColumnAdapter.Create(control));
    }
    public TableColumnAdapter setCellValueFactory (PropertyValueFactory value)
    {
        control.setCellValueFactory(value);
        
        return (TableColumnAdapter.Create(control));
    }
    public TableColumn get() { return (control); }
}
