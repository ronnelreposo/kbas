
package controls.body;

import controls.adapter.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.abstractions.IFees;
import models.database.StudentAssessmentDatabase;

/**
 *
 * @author Arya
 */
public final class AssessmentBodyControls
{
    private final StudentAssessmentDatabase database;
    private AssessmentBodyControls(StudentAssessmentDatabase db)
    {
        database = db;
    }
    
    public final static AssessmentBodyControls Create(StudentAssessmentDatabase database)
    {
        return (new AssessmentBodyControls(database));
    }
    
    private TableViewAdapter createTableView (ObservableList<IFees> list)
    {
        final double DETAIL_PREF_WIDTH = 150;
	final double VALUE_PREF_WIDTH = 50;
        final CreateColumn col = (h, n, w) -> {
            return (TableColumnAdapter.Create((new TableColumn(h)))
                .setCellValueFactory((new PropertyValueFactory<>(n)))
                .setPrefWidth(w));
        };
        return (TableViewAdapter.Create((new TableView(list)))
                .addColumns((0),
                        (new TableColumn[] {
                            (col.create(("Detail"), ("detail"),
                                    (DETAIL_PREF_WIDTH)).get()),
                            (col.create(("Item"), ("item"),
                                    (VALUE_PREF_WIDTH)).get()),
                            (col.create(("Amount"), ("amount"),
                                    (VALUE_PREF_WIDTH)).get()),
                            (col.create(("Total"), ("total"),
                                    (VALUE_PREF_WIDTH)).get())
                        })));
    }
    private TitledPane createTitledPane (String heading, ObservableList<IFees> list)
    {
        return (new TitledPane((heading), (createTableView(list).get())));
    }
    
    public Node getParentControl()
    {
        final String studentId = "100540"; //session id.
        final TitledPane misc = (createTitledPane(("Miscellaneous"),
                (database.getFees((studentId),
                        "misc"))));
        
        return (AccordionAdapter.Create((new Accordion()))
                .setExpandedPane((misc))
                .addPanes((0), (new TitledPane[] {
                    (createTitledPane(("Registration"),
                            (database.getFees((studentId),
                                    ("reg"))))),
                    (createTitledPane(("Tuition"),
                            (database.getFees((studentId),
                                    ("tuition"))))),
                    (createTitledPane(("Internet"),
                        (database.getFees((studentId),
                                ("internet"))))),
                    (createTitledPane(("Test Booklet"),
                        (database.getFees((studentId),
                                ("tb"))))),
                    (createTitledPane(("ID Validation"),
                        (database.getFees((studentId),
                                ("idvalid"))))),
                    (misc),
                    (createTitledPane(("Other(s)"),
                        (database.getFees((studentId),
                                ("other")))))
                })).get());
    }
}