package views;
import controls.*;

public final class FrontView
{
    private final IFrontViewControls viewControls;

    private FrontView () { viewControls = (FrontViewControls.Create()); }
    public final static FrontView Create () { return (new FrontView()); }
    public IFrontViewControls getViewControls () { return (viewControls); }
}