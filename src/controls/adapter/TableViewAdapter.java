package controls.adapter;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Arya
 */
public final class TableViewAdapter
{
    private final TableView control;
    private TableViewAdapter (TableView tableView)
    {
        control = tableView;
    }
    public final static TableViewAdapter Create (TableView tableView)
    {
        return (new TableViewAdapter(tableView));
    }
    public TableViewAdapter addColumn (TableColumn tableColumn)
    {
        control.getColumns().add(tableColumn);
        
        return (TableViewAdapter.Create((control)));
    }
    public TableViewAdapter addColumns (int i, TableColumn[] tableColumns)
    {
        return ( ((i) > ((tableColumns.length) - (1))) ) ? (this)
                : addColumn(tableColumns[(i)])
                        .addColumns(((i) + (1)), (tableColumns));
    }
    public TableView get() { return (control); }
}
