package controllers;

import keypad.IKeyPad;
import abstractions.ControlPropertySetter;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;

public final class TextControlEvents
{
    private TextControlEvents (IKeyPad keypad,
            TextInputControl[] textInputControl)
    {
        attachMouseEvents (0, (keypad), textInputControl);
    }

    public final static TextControlEvents Create (IKeyPad keypad,
            TextInputControl[] textInputControl)
    {
        return (new TextControlEvents(keypad, textInputControl));
    }

    private TextControlEvents attachMouseEvents (int i, IKeyPad keypad,
            TextInputControl[] controls)
    {
        if ((keypad) == (null)) { return (this); }
        if ((controls) == (null)) { return (this); }

        if ((i) > ((controls.length) - (1))) { return (this); }

        final ControlPropertySetter<TextInputControl, EventHandler<MouseEvent>>
                mouseEventSetter;
        mouseEventSetter = (s, t) ->
        {
            s.setOnMouseClicked(t);

            return (s);
        };
        
        final TextInputControl source = (controls[(i)]);
        mouseEventSetter.set(source,
                ((e) -> keypad.setTargetTextInputControl(source)));

        return (attachMouseEvents(((i) + (1)), (keypad), (controls)));
    }
}