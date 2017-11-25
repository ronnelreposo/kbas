package controllers.printing;

import java.awt.Graphics2D;
import javafx.collections.ObservableList;

final class PrintingDat<T> implements IPrintingDat<T>
{
    private final Graphics2D graphics;
    private final IPoint point;
    private final ObservableList<T> list;

    public PrintingDat (Graphics2D g2d, IPoint point, ObservableList<T> list)
    {
        this.graphics = g2d;
        this.point = point;
        this.list = list;
    }

    @Override public Graphics2D getGraphics2D () { return (graphics); }
    @Override public IPoint getPoint () { return (point); }
    @Override public ObservableList<T> getList () { return (list); }
}