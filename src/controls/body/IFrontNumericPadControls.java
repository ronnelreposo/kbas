package controls.body;

import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Front Numeric Pad Controls Abstraction.
 * @author Arya
 *
 */
public interface IFrontNumericPadControls
{	
    Button getClearButton();
    Button getBkspButton();
    Button getButton1();
    Button getButton2();
    Button getButton3();
    Button getButton4();
    Button getButton5();
    Button getButton6();
    Button getButton7();
    Button getButton8();
    Button getButton9();
    Button getButton0();
    Node getParentControls();
}