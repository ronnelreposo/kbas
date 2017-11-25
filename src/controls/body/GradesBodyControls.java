
package controls.body;

import controls.adapter.TableColumnAdapter;
import controls.adapter.TableViewAdapter;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.abstractions.IGradesModel;

/**
 *
 * @author Arya
 */
public final class GradesBodyControls
{
    private final ObservableList<IGradesModel> list;
    private GradesBodyControls (ObservableList<IGradesModel> list)
    {
        this.list = list;
    }
    public static GradesBodyControls Create (ObservableList<IGradesModel> list)
    {
        return (new GradesBodyControls(list));
    }
    private TableViewAdapter createTableView (ObservableList<IGradesModel> list)
    {
        final CreateColumn col;
        col = (String h, String n, double w) ->
                (TableColumnAdapter.Create((new TableColumn(h)))
                        .setCellValueFactory((new PropertyValueFactory<>(n)))
                        .setPrefWidth(w));
        final double VALUE_PREF_WIDTH;
        VALUE_PREF_WIDTH = 50;
        return (TableViewAdapter.Create((new TableView(list))))
                .addColumns((0), (new TableColumn[] {
                    (col.create(("Subject Code"), ("subjectCode"),
                            (VALUE_PREF_WIDTH)).get()),
                    (col.create(("Mid Term"), ("midTerm"),
                            (VALUE_PREF_WIDTH)).get()),
                    (col.create(("Final Term"), ("finalTerm"),
                            (VALUE_PREF_WIDTH)).get()),
                    (col.create(("Remark"), ("remark"),
                            (VALUE_PREF_WIDTH)).get()),
                    (col.create(("Teacher"), ("employeeId"),
                            (VALUE_PREF_WIDTH)).get())
                }));
    }
    public Node getParentContainer()
    {
        return (createTableView(list).get());
    }
}
