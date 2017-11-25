package controllers.printing;

import functional.core.Tuple;
import java.awt.Graphics2D;

final class Drawer implements StringDrawer<Graphics2D>
{
    private Drawer() { }
    public static StringDrawer<Graphics2D> Create() { return new Drawer(); }
    
    @Override
    public Graphics2D draw(Tuple<Graphics2D, Tuple<String, IPoint>> t)
    {
        t.first().drawString((t.second().first()), (t.second().second().x()), (t.second().second().y()));
        return (t.first());
    }
}