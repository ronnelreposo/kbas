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
import models.abstractions.IStudentGrade;
import models.database.StudentGradeDatabase;

/**
 *
 * @author Arya
 */
public final class GradesPrintingTemplate implements Printable
{
    private final Tuple<Integer, String> session;
    
    private final int X_INIT;
    private final int Y_INIT;
    private final int Y_PLUS;
    private final int Y_INTERVAL;
    private final int X_CENTER_TAB;
    
    private final IndexPropertyStreamer
    <String, Integer, ObservableList<IStudentGrade>> subjectCode;
    private final IndexPropertyStreamer
    <String, Integer, ObservableList<IStudentGrade>> midTerm;
    private final IndexPropertyStreamer
    <String, Integer, ObservableList<IStudentGrade>> finalTerm;
    private final IndexPropertyStreamer
    <String, Integer, ObservableList<IStudentGrade>> remark;
    private final IndexPropertyStreamer
    <String, Integer, ObservableList<IStudentGrade>> teacher;
    
    private final StringDrawer<Graphics2D> drawer;
    
    private GradesPrintingTemplate (Tuple<Integer, String> session)
    {
        this.session = session;
        
        this.X_INIT = 20;
        this.Y_INIT = 20;
        this.Y_PLUS = 12;
        this.Y_INTERVAL = 15;
        this.X_CENTER_TAB = 200;
        this.teacher = (i, l) ->
        {
            return l == null ? "" : l.get(i).getEmployee().getID();
        };
        this.remark = (i, l) ->
        {
            return l == null ? "" : l.get(i).getRemark();
        };
        this.finalTerm = (i, l) ->
        {
            return l == null ? "" : l.get(i).getFinalTerm();
        };
        this.midTerm = (i, l) ->
        {
            return l == null ? "" : l.get(i).getMidTerm();
        };
        this.subjectCode = (i, l) ->
        {
            return l == null ? "" : l.get(i).getSubject().getCode();
        };
        drawer = Drawer.Create();
    }

    public final static Printable Create (Tuple<Integer, String> session)
    {
        return new GradesPrintingTemplate(session);
    }
	
    @Override public int print (Graphics g, PageFormat pageFormat, int page) throws PrinterException
    {	
        if (page > 0) { return NO_SUCH_PAGE; }

        final Graphics2D g2d = (PrintingData.TranslatedGraphics(g, pageFormat));

        final Tuple ssu =
                (Tuple.Create(("Samar State University"),
                                (Point.Create((X_CENTER_TAB), (Y_INIT)))));

        final Tuple heading =
                (Tuple.Create(("Student Grades"),
                                (PrintingData.NextRowPoint((X_CENTER_TAB + X_INIT),
                                                (Y_INTERVAL), (ssu)))));

        final Tuple current_date =
                (Tuple.Create("Date : " +
                        (new SimpleDateFormat("MMMM-dd-yyyy"))
                                .format((new Date())),
                        (PrintingData.NextRowPoint((X_INIT),
                                (Y_INTERVAL), (heading)))));

        final String session_data = session.second();

        final Tuple student_id =
                (Tuple.Create(("Student ID : " + session_data),
                        (PrintingData.NextRowPoint((X_INIT),
                                (Y_INTERVAL), (current_date)))));

        drawGrades((session_data),
                draw((0), (g2d),
                        (new Tuple[] {
                            (ssu), (heading), (current_date), (student_id)
                        })),
                (student_id));

        return (PAGE_EXISTS);
    }

    private Graphics2D draw (int i, Graphics2D g2d, Tuple<String, IPoint>[] vector)
    {
        return ((i) > ((vector.length) - (1))) ? (g2d) :
                (draw(((i) + (1)),
                        (drawer.draw(Tuple.Create((g2d),
                                (vector[i])))), (vector)));
    }
    private int lastIndexOf (ObservableList<IStudentGrade> list)
    {
        return (list == null) ? 0 : (list.size() - 1);
    }
    
    private Graphics2D drawValue (Tuple<IPrintingDat<IStudentGrade>, String> t)
    {
        return (drawer.draw((Tuple.Create((t.first().getGraphics2D()),
                (Tuple.Create((t.second()), (t.first().getPoint())))))));
    }
        
    private Tuple drawSubjectCodeCol (int i, IPrintingDat<IStudentGrade> dat)
    {
        return ( i > lastIndexOf(dat.getList()) ) ?
                (Tuple.Create((subjectCode.stream((i - 1),
                        (dat.getList()))),
                        (dat.getPoint())))
                : (drawSubjectCodeCol((i + 1),
                        (new PrintingDat(
                                (drawValue(Tuple.Create((dat),
                                        (subjectCode.stream((i),
                                                (dat.getList())))))),
                                (dat.getPoint().nextAbscissa(Y_PLUS)),
                                (dat.getList())))));
    }
    private Tuple drawMidTermCol (int i, IPrintingDat<IStudentGrade> dat)
    {
        return ( (i) > (lastIndexOf(dat.getList())) ) ?
                (Tuple.Create(midTerm.stream((i - 1),
                        (dat.getList())),
                        (dat.getPoint())))
                :   (drawMidTermCol((i + 1),
                        (new PrintingDat(
                                (drawValue(Tuple.Create((dat),
                                        (midTerm.stream((i), (dat.getList())))))),
                                (dat.getPoint().nextAbscissa(Y_PLUS)),
                                (dat.getList())))));
    }
    private Tuple drawFinalTermCol (int i, IPrintingDat<IStudentGrade> dat)
    {
        return ( (i) > (lastIndexOf(dat.getList())) ) ?
                (Tuple.Create(
                        (finalTerm.stream(((i) - (1)),
                                (dat.getList()))),
                        (dat.getPoint())))
                : (drawFinalTermCol((i + 1),
                        (new PrintingDat(
                                (drawValue(Tuple.Create((dat),
                                        (finalTerm.stream((i),
                                                (dat.getList())))))),
                                (dat.getPoint().nextAbscissa(Y_PLUS)),
                                (dat.getList())))));
    }
    private Tuple drawRemarkCol (int i, IPrintingDat<IStudentGrade> dat)
    {
        return (( (i) > (lastIndexOf(dat.getList())) ) ?
                (Tuple.Create((remark.stream(((i) - (1)),
                        (dat.getList()))),
                        (dat.getPoint())))
                : (drawRemarkCol((i + 1),
                        (new PrintingDat((drawValue(Tuple.Create((dat),
                                (remark.stream((i),
                                        (dat.getList())))))),
                                (dat.getPoint().nextAbscissa(Y_PLUS)),
                                (dat.getList()))))));
    }
    private Tuple drawTeacherCol (int i, IPrintingDat<IStudentGrade> dat)
    {
        return ( i > lastIndexOf(dat.getList()) ) ?
                (Tuple.Create((teacher.stream((i - 1),
                        (dat.getList()))),
                        (dat.getPoint())))
                : (drawTeacherCol((i + 1),
                        (new PrintingDat(
                                (drawValue(Tuple.Create((dat),
                                        (teacher.stream((i),
                                                (dat.getList())))))),
                                (dat.getPoint().nextAbscissa(Y_PLUS)),
                                (dat.getList())))));
    }

    private void drawGrades (String studentId, Graphics2D g2d, Tuple t)
    {
        final ObservableList<IStudentGrade> allGrades =
                        StudentGradeDatabase.Instance().GetAll(studentId);
        
        drawSubjectCodeCol((0),
                (new PrintingDat((g2d),
                        (PrintingData.NextRowPoint((X_INIT), (Y_INTERVAL), (t))),
                        (allGrades))));

        final int MID_TERM_TAB = 5;
        drawMidTermCol((0),
                (new PrintingDat((g2d),
                        (PrintingData.NextRowPoint(((X_INIT) * (MID_TERM_TAB)),
                                (Y_INTERVAL), (t))), (allGrades))));

        final int FINAL_TERM_TAB = nextColumnTab(MID_TERM_TAB);
        drawFinalTermCol((0),
                (new PrintingDat((g2d),
                        (PrintingData.NextRowPoint((X_INIT) * (FINAL_TERM_TAB),
                                (Y_INTERVAL), (t))), (allGrades))));

        final int REMARK_TAB = nextColumnTab(FINAL_TERM_TAB);
        drawRemarkCol((0),
                (new PrintingDat((g2d),
                        (PrintingData.NextRowPoint(((X_INIT) * (REMARK_TAB)),
                                (Y_INTERVAL), (t))), (allGrades))));

        final int TEACHER_TAB = nextColumnTab(REMARK_TAB);
        drawTeacherCol(0,
                (new PrintingDat((g2d),
                        (PrintingData.NextRowPoint(((X_INIT) * (TEACHER_TAB)),
                                (Y_INTERVAL), (t))), (allGrades))));
    }
    private int nextColumnTab (int currentTab)
    {
        return ((currentTab) + (5));
    }
}