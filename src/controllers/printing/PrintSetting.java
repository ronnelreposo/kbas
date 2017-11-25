package controllers.printing;

import java.awt.print.Printable;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrintQuality;

/**
 * 
 * @author Arya
 */
public class PrintSetting
{
    private PrintSetting() { }
    public final static PrintSetting Create () { return (new PrintSetting()); }
    public final PrintSetting print (Printable printable)
    {
        final IPrinting<PrintSetting> printing; 
        printing = (Printable p) -> 
        {
            final DocAttributeSet das;
            das = (new HashDocAttributeSet());

            das.add((PrintQuality.HIGH));
            das.add ((MediaSizeName.NA_LETTER));
            
            try
            {
                PrintServiceLookup.lookupDefaultPrintService()
                        .createPrintJob()
                        .print((new SimpleDoc((p),
                                (DocFlavor.SERVICE_FORMATTED.PRINTABLE),
                                (das))), (null));
            } catch (PrintException ex) {
                System.out.println((ex.getMessage()));
            }
            return (this);
        };

        return (printing.print((printable)));
    }
}
