
package controllers.printing;
import java.awt.print.Printable;

/**
 *
 * @author Arya
 */
public interface IPrinting<T> { T print(Printable p); }
