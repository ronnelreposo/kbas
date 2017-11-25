package controllers.printing;

import java.awt.Graphics2D;
import javafx.collections.ObservableList;

interface IPrintingDat<T>
{
    Graphics2D getGraphics2D ();
    IPoint getPoint ();
    ObservableList<T> getList ();
}