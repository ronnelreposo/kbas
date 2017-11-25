package controllers.printing;

interface IPoint
{
    int x ();
    int y ();
    IPoint nextAbscissa (int abcissa);
    IPoint nextOrdinate (int ordinate);
}