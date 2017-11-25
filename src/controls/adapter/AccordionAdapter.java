
package controls.adapter;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

/**
 * Serves as the adapter for Accordion.
 * @author Arya
 */
public class AccordionAdapter
{
    private final Accordion control;
    
    private AccordionAdapter (Accordion accordion) { control = accordion; }
    
    public static AccordionAdapter Create (Accordion accordion)
    {
        return (new AccordionAdapter(accordion));
    }
    
    public AccordionAdapter addPane (TitledPane titledPane)
    {
        control.getPanes().add(titledPane);
        
        return (AccordionAdapter.Create(control));
    }
    
    public AccordionAdapter addPanes (int i, TitledPane[] titledPanes)
    {
        return (((i) > ((titledPanes.length) - (1))) ?
                (AccordionAdapter.Create(control)) :
                (addPane(titledPanes[(i)])
                        .addPanes(((i) + (1)), (titledPanes))));
    }
    
    public Accordion get() { return (control); }

    public AccordionAdapter setExpandedPane (TitledPane titledPane)
    {
        control.setExpandedPane(titledPane);
        
        return (AccordionAdapter.Create(control));
    }
}