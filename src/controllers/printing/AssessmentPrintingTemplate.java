package controllers.printing;

import functional.core.Tuple;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.ObservableList;
import models.abstractions.IFees;
import models.database.StudentAssessmentDatabase;

/**
 *  Serves as the template for printing Assessment.
 * @author Arya
 */
public final class AssessmentPrintingTemplate implements Printable
{
    private final Tuple<Integer, String> session;
    private final int X_INIT;
    private final int X_INIT_TAB;
    private final int Y_INIT;
    private final int Y_PLUS;
    private final int Y_INTERVAL;
    private final int X_CENTER_TAB;
    private final int NEXT_TAB;
    private final StringDrawer<Graphics2D> drawer;
    private final IndexPropertyStreamer<String, Integer, ObservableList<IFees>> feeDetailOf;
    private final IndexPropertyStreamer<String, Integer, ObservableList<IFees>> feeTotalPropertyOf;
    
    private AssessmentPrintingTemplate (Tuple<Integer, String> session)
    {
        this.session = session;
        X_INIT = 20;
        X_INIT_TAB = 100;
        Y_INIT = 20;
        Y_PLUS = 12;
        Y_INTERVAL = 15;
        X_CENTER_TAB = 200;
        NEXT_TAB = X_INIT_TAB * 3;
        
        feeDetailOf = (i, l) ->
        {
            return ((l) == (null)) ?
                    ("Detail is not available.") :
                    (l.get((i)).getDetail());
        };
        feeTotalPropertyOf = (i, l) ->
        {
            return ((l) == (null)) ? ("0") :
                    (String.valueOf(l.get((i)).totalProperty().get()));
        };
        drawer = (Drawer.Create());
        
        session_data = session.second();
        //MUST BE PASSED IN THE CONTROLLER.
        database = (StudentAssessmentDatabase.Create());
    }

    public final static Printable Create (Tuple<Integer, String> session)
    {
        return new AssessmentPrintingTemplate(session);
    }
    
    private Graphics2D g2d;
    
    private final String session_data;
    private final StudentAssessmentDatabase database;
    
    @Override public final int print(Graphics g, PageFormat pageFormat, int page)
                    throws PrinterException
    {	
        if (page > 0) { return (NO_SUCH_PAGE); }

        g2d = (PrintingData.TranslatedGraphics((g), (pageFormat)));

        final Tuple ssu =
                (Tuple.Create(("Samar State University"),
                        (Point.Create((X_CENTER_TAB), (Y_INIT)))));

        final Tuple heading =
                (Tuple.Create(("Students Assessment"),
                        (PrintingData.NextRowPoint((X_CENTER_TAB),
                                (Y_INTERVAL), (ssu)))));

        final Tuple current_date =
                (Tuple.Create(("Date : ") +
                        (new SimpleDateFormat("MMMM-dd-yyyy"))
                                .format((new Date())),
                        (PrintingData.NextRowPoint((X_INIT),
                                (Y_INTERVAL), (heading)))));

        final Tuple student_id
                = (Tuple.Create(("Student ID : ") + (session_data),
                        (PrintingData.NextRowPoint((X_INIT),
                                (Y_INTERVAL), (current_date)))));

        draw((0), (g2d), (new Tuple[] {
            (ssu), (heading),
            (current_date), (student_id)
        }));

        final Tuple printed_student_id = (drawAssessmentData((student_id)));

        final Tuple total_label = (Tuple.Create(("Total : "),
                (PrintingData.NextRowPoint((X_INIT_TAB),
                        (Y_INTERVAL), (printed_student_id)))));

        final Tuple total_ps = (Tuple.Create(
                (String.valueOf(database.getTotal((session_data)))),
                (PrintingData.NextRowPoint((NEXT_TAB),
                        (Y_INTERVAL), (printed_student_id)))));

        draw((0), (g2d),
                (new Tuple[] {
                    (total_label),
                    (total_ps)
                }));

        return (PAGE_EXISTS);
    }

    private Graphics2D draw (int i, Graphics2D g2d, Tuple<String, IPoint>[] vector)
    {
        return (((i) > ((vector.length) - (1))) ? (g2d) :
                (draw(((i) + (1)),
                        (drawer.draw(Tuple.Create((g2d), (vector[(i)])))),
                        (vector))));
    }

    private Graphics2D drawValue (IPrintingDat<IFees> dat, String value)
    {
        return (drawer.draw(Tuple.Create((dat.getGraphics2D()),
                        (Tuple.Create((value), (dat.getPoint()))))));
    }

    private int lastIndexOf (ObservableList<IFees> list)
    {
        return ((list) == (null)) ? (0) : ((list.size()) - (1));
    }
    
    /**
     * Draws Row of amount.
     * @param i index.
     * @param dat data.
     * @return last Printable String.
     */
    private Tuple<String, IPoint> drawRowAmount (int i, IPrintingDat<IFees> dat)
    {
        return (( (i) > (lastIndexOf(dat.getList())) ) ?
                (Tuple.Create((feeTotalPropertyOf.stream(((i) - (1)),
                                (dat.getList()))),
                        (dat.getPoint()))) :
                (drawRowAmount(((i) + (1)),
                        (new PrintingDat<>(
                                (drawValue((dat),
                                        (feeTotalPropertyOf.stream((i),
                                                (dat.getList()))))),
                                (dat.getPoint().nextAbscissa((Y_PLUS))),
                                (dat.getList()))))));
    }
    
    /**
     * Draws Row of detail.
     * @param i index.
     * @param dat data.
     * @return last Printable String.
     */
    private Tuple<String, IPoint> drawRowDetail (int i, IPrintingDat<IFees> dat)
    {
        return (( (i) > (lastIndexOf(dat.getList())) ) ?
                (Tuple.Create(
                        (feeDetailOf.stream(((i) - (1)),
                                (dat.getList()))),
                        (dat.getPoint()))) :
                (drawRowDetail(((i) + (1)),
                        (new PrintingDat<>(
                                (drawValue((dat),
                                        (feeDetailOf.stream((i),
                                                (dat.getList()))))),
                                (dat.getPoint().nextAbscissa((Y_PLUS))),
                                (dat.getList())
                        )))));
    }

    /**
     * Helper method for drawing the Fees, with corresponding heading the detail(s) and amount(s).
     * @param heading The heading to be drawn.
     * @param fees The List of fees to be drawn.
     * @return The last drawn IPrintableString.
     */
    private Tuple<String, IPoint> drawFees (Tuple<String, IPoint> t, ObservableList<IFees> fees)
    {	
        // draw heading.
        g2d = (drawer.draw(Tuple.Create((g2d), (t))));
        drawRowDetail((0),
                (new PrintingDat<>((g2d),
                        (PrintingData.NextRowPoint((X_INIT_TAB),
                                (Y_INTERVAL), (t))), (fees))));
        return (drawRowAmount((0),
                (new PrintingDat<>((g2d),
                        (PrintingData.NextRowPoint((NEXT_TAB),
                                (Y_INTERVAL), (t))), (fees)))));
    }
    
    private boolean isArrayLengthEquals(String[] a1, String[] a2)
    {
    	if (a1 == null && a2 = null) { return true; }
	if (a1 == null && a2 != null) { return false; }
	if (a1 != null && a2 == null) { return false; }
        return a1.length == a2.length;
    }
    
    private Tuple<String, IPoint> drawFeesAndDetails (int i, String[] headings, String[] types,
            Tuple<String, IPoint> t)
    {
        return (( (isArrayEquals((headings), (types)))
                && ((i) > ((headings.length) - (1))) ) ? (t) :
                (drawFeesAndDetails(((i) + (1)), (headings), (types),
                        (drawFees((Tuple.Create((headings[(i)]),
                (PrintingData.NextRowPoint((X_INIT),
                        (Y_INTERVAL),
                        (t))))),
                                (database.getFees((session_data),
                                        (types[(i)]))))))));
    }
    
    private Tuple<String, IPoint> drawAssessmentData (Tuple t)
    {
        final String[] headings = { ("Tuition"), ("Miscellaneous"),
            ("Internet"), ("Test Booklet"),
            ("ID Validation"), ("Other(s)") };
        final String[] types = { ("tuition"), ("misc"),
            ("internet"), ("tb"),
            ("idvalid"), ("other") };
        
        return (drawFeesAndDetails ((0), (headings), (types), (t)));
    }
}

/**
 * Helper class for printing data.
 * @author Arya
 */
final class PrintingData
{
    private PrintingData () { }
	
    public static final Graphics2D TranslatedGraphics (Graphics g, PageFormat pageFormat)
    {
    	final Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        return g2d;
    }
	
    public static final IPoint NextRowPoint (int domain, int interval, Tuple<String, IPoint> t)
    {
        return t.second() == null ? Point.Origin() :
                Point.Create(domain, t.second().nextAbscissa(interval).y());
    }
}
