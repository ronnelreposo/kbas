package controllers.printing;

final class Point implements IPoint
{
    private final int x;
    private final int y;
    private Point (int x, int y) { this.x = x; this.y = y; }
    public final static IPoint Create (int x, int y) { return (new Point((x), (y))); }

    @Override public final int x () { return (x); }

    @Override public final int y () { return (y); }
    
    /**
     * Point of Origin.
     * @return a new Point with coordinates (x = 0, y = 0).
     */
    public final static IPoint Origin() { return (Point.Create((0), (0))); }
    /**
     * Creates a new point with new abscissa.
     * @param abcissa the additional abscissa.
     * @return Point(x, y+abscissa).
     */
    @Override public final IPoint nextAbscissa (int abcissa)
    {
        return (Point.Create((x()), ((y()) + (abcissa))));
    }
    /**
     * Creates a new point with new ordinate.
     * @param ordinate the additional ordinate.
     * @return Point(x+ordinate, y).
     */
    @Override public final IPoint nextOrdinate(int ordinate)
    {
        return (Point.Create((x()) + (ordinate), (y())));
    }
}