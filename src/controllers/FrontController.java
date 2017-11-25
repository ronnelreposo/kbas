package controllers;

import controls.*;
import abstractions.*;
import adapters.TextInputControlAdapter;
import controls.body.*;
import controls.header.IFrontHeaderControls;
import functional.core.Func1;
import functional.core.Tuple;
import java.util.logging.Level;
import java.util.logging.Logger;
import keypad.KeyPad;
import views.FrontView;
import javafx.event.Event;
import javafx.scene.control.*;
import keypad.IKeyPad;
import models.abstractions.Account;
import models.abstractions.AccountDatabase;
import models.data.SignInAccount;
import models.database.StudentAccountDatabase;

/**
 * Front Application Controller.
 * @author Arya
 *
 */
public final class FrontController implements Cloneable
{
    private final FrontView view;
    private final IFrontViewControls viewControls;

    private final int MAX_INPUT_LEN;
    
    private FrontController ()
    {
        MAX_INPUT_LEN = 6;
        
        view = (FrontView.Create());

        viewControls = (view.getViewControls());
        
        final IFrontUpdatePassControls updateControls
                = (viewControls.getUpdatePassControls());
        
        final IFrontHeaderControls headerControls
                = (viewControls.getHeaderControls());
        
        final AccountDatabase database = (StudentAccountDatabase.Instance());
        
        final IKeyPad keypad = (KeyPad.Create());
        
        wireUpKeyPadEvents(Tuple.Create((keypad), (viewControls.getNumericPadControls())))
                .wireUpUpdatePassword(Tuple.Create((updateControls), (database)))
                .wireUpUpdateEvent(Tuple.Create((updateControls), (database)))
                .wireUpSignInEvent(Tuple.Create((headerControls), (database)));
        TextControlEvents.Create((keypad),
                (new TextInputControl [] {
                    (updateControls.getStudIdTextField()),
                    (updateControls.getCurrentPassPasswordField()),
                    (updateControls.getNewPassPasswordField()),
                    (updateControls.getConfirmPassPasswordField()),
                    (headerControls.getSignInIdTextField()),
                    (headerControls.getSignInPassPasswordField())}));
    }

    public final static FrontController Create ()
    {
        return (new FrontController());
    }

    public final FrontController show()
    {
        viewControls.getStage().show();
        
        return (FrontController.Create());
    }

    private FrontController wireUpSignInEvent (Tuple<IFrontHeaderControls, AccountDatabase> t)
    {
        t.first().getSignInButton().setOnAction(e ->
                signIn((t.first()), (t.second())));
        
        return (this);
    }
    private FrontController wireUpUpdatePassword (Tuple<IFrontUpdatePassControls, AccountDatabase> t)
    {
        t.first()
        .getUpdateButton()
        .setOnAction((e) -> updatePassword(Tuple.Create((t.first()), (t.second()))));

        return (this);
    }
    private FrontController attachButtonEvents (int i, Tuple<IKeyPad, Button[]> t)
    {
        if ((i) > ((t.second().length) - (1))) { return (this); }

        final SourceStreamer<String, Event> eventSource = (b, e) -> 
        {
            return ((Button) e.getSource()).getText();
        };

        (t.second()[(i)]).setOnAction((e) ->
                t.first().pressKey((eventSource.stream("", e))));

        return (attachButtonEvents(((i) + (1)), (t)));
    }
    private FrontController attachClrButtonEvent (Tuple<Button, IKeyPad> t)
    {
        t.first().setOnAction(e -> t.second().clearKeys());

        return (this);
    }
    private FrontController attachBkspButtonEvent (Tuple<Button, IKeyPad> t)
    {
        t.first().setOnAction(e -> t.second().removeEndKey());

        return (this);
    }
    private FrontController wireUpKeyPadEvents (Tuple<IKeyPad, IFrontNumericPadControls> t)
    {
        return (attachButtonEvents((0), (Tuple.Create((t.first()),
                (new Button[] {
                    (t.second().getButton0()),
                    (t.second().getButton1()),
                    (t.second().getButton2()),
                    (t.second().getButton3()),
                    (t.second().getButton4()),
                    (t.second().getButton5()),
                    (t.second().getButton6()),
                    (t.second().getButton7()),
                    (t.second().getButton8()),
                    (t.second().getButton9()) })))))
                .attachClrButtonEvent(Tuple.Create((t.second().getClearButton()), (t.first())))
                .attachBkspButtonEvent(Tuple.Create((t.second().getBkspButton()), (t.first())));
    }
    private FrontController wireUpUpdateEvent (Tuple<IFrontUpdatePassControls, AccountDatabase> t)
    {
        t.first().getUpdateButton().setOnAction(e ->
                        updatePassword(Tuple.Create((t.first()), (t.second()))));

        return (this);
    }
    
    boolean printCondition (String inp, Func1<String, Boolean> cond, String message)
    {
        if ((cond.op(inp))) { System.out.println(message); return true; }
        return false;
    }
    private boolean inputPassed (String field, String data)
    {       
        return (
                printCondition(data,
                        (inp) -> inp.equals(""), field + " is required.")
                ||
                printCondition(data,
                        (inp) -> inp.length() < MAX_INPUT_LEN,
                        field + " must at least " + MAX_INPUT_LEN + " characters in length.")
                ||
                printCondition(data,
                        (inp) -> inp.length() > MAX_INPUT_LEN,
                        field + " must not exceed " + MAX_INPUT_LEN + " characters in length.")
                );
    }
    private boolean inputPassed (String field, TextInputControlAdapter control)
    {
        final String data = (control.get().getText());
        
        return (
                printCondition(data,
                        (inp) -> inp.equals(""), field + " is required.")
                ||
                printCondition(data,
                        (inp) -> inp.length() < MAX_INPUT_LEN,
                        field + " must at least " + MAX_INPUT_LEN + " characters in length.")
                ||
                printCondition(data,
                        (inp) -> inp.length() > MAX_INPUT_LEN,
                        field + " must not exceed " + MAX_INPUT_LEN + " characters in length.")
                );
    }
    private boolean isAccountRegistered (Tuple<Account, AccountDatabase> t)
    {
        final boolean isAccountExisting = (t.second().isExisting((Account) (t.first())));
        if (!isAccountExisting) System.out.println("Your Account is unregistered.");
        return (isAccountExisting);
    }
    private boolean isAccountPasswordCorrect (Tuple<String, Account> t)
    {
        final boolean isPasswordMatches = (t.first().equals(t.second().getPlainPassword()));
        if (!isPasswordMatches) System.out.println("Your Account Password is incorrect.");
        return (isPasswordMatches);
    }
    
    private FrontController clearTextInputControl (TextInputControl control)
    {
        control.clear();
        
        return (this);
    }
    private FrontController clearTextInputControls (int i, TextInputControl[] controls)
    {
        return ((i) > ((controls.length) - (1))) ? (this) :
                (clearTextInputControl((controls[i]))
                        .clearTextInputControls(((i) + (1)), (controls)));
    }
    private FrontController signIn (IFrontHeaderControls headerControls,
            AccountDatabase database)
    {
        final String username = headerControls.getSignInIdTextField().getText();
        final String password = headerControls.getSignInPassPasswordField().getText();
        
        if (inputPassed("Username", username) ||
                inputPassed("Password", password))
        { return this; }
        
        final Account account =
                (database.get(SignInAccount.Create(username, password)));

        if (! (isAccountRegistered(Tuple.Create((account), (database)))
                && isAccountPasswordCorrect(Tuple.Create(password, account))) )
        {
            return clearTextInputControl(headerControls.getSignInPassPasswordField());
        }
        
        MainController.Create(Tuple.Create((1), (username))).show();
        
        return (this);
    }
    
    private boolean isUpdateStudIdPassed (IFrontUpdatePassControls updateControls)
    {
        return (inputPassed(("Student ID"),
                (TextInputControlAdapter.Create(
                        (updateControls.getStudIdTextField())))));
    }
    private boolean isUpdateNewPassPassed (IFrontUpdatePassControls controls)
    {
        return (inputPassed(("New Password"),
                (TextInputControlAdapter.Create(
                        (controls.getNewPassPasswordField())))));
    }
    private boolean isUpdateCurrentPassPassed (IFrontUpdatePassControls controls)
    {
        return (inputPassed(("Current Password"),
                (TextInputControlAdapter.Create(
                        (controls.getCurrentPassPasswordField())))));
    }
    private boolean isUpdateConfirmPassPassed (IFrontUpdatePassControls controls)
    {
        return (inputPassed(("Confirm Password"),
                (TextInputControlAdapter.Create(
                        (controls.getConfirmPassPasswordField())))));
    }
    private FrontController updatePassword (Tuple<IFrontUpdatePassControls, AccountDatabase> t)
    {        
        if (!( (isUpdateStudIdPassed(t.first())) &&
                (isUpdateCurrentPassPassed(t.first())) &&
                (isUpdateNewPassPassed(t.first())) &&
                (isUpdateConfirmPassPassed(t.first())) ))
        { return (this); }
        
        final TextInputControl inputNewPasswordField
                = (t.first().getNewPassPasswordField());
        
        final TextInputControl inputPasswordConfirmField
                = (t.first().getConfirmPassPasswordField());
        
        if (! (inputNewPasswordField.getText()
                .equals(inputPasswordConfirmField.getText())) )
        {
            System.out.println("Your Password Confirmation did not match.");
            
            return (clearTextInputControls((0),
                    (new TextInputControl[] {
                        (inputNewPasswordField),
                        (inputPasswordConfirmField)
                    })));
        }
        
        final TextInputControl inputStudIdField
                = (t.first().getStudIdTextField());
        
        final TextInputControl inputCurrentPasswordField
                = (t.first().getCurrentPassPasswordField());
        
        final String inputCurrentPassword
                = (inputCurrentPasswordField.getText());
        
        final Account account = t.second().get(
                SignInAccount.Create(
                        (inputStudIdField.getText()),
                        (inputCurrentPassword)));
        
        if (! (isAccountRegistered(Tuple.Create((account), (t.second())))) ) { return (this); }
        
        if (! (isAccountPasswordCorrect(Tuple.Create((inputCurrentPassword), (account)))) )
        {
            return (clearTextInputControl(inputCurrentPasswordField));
        }
        
        System.out.println("Updating Password...");
        
        final String newPassword = (inputNewPasswordField.getText());

        final boolean updated =
                (t.second().update(SignInAccount.Create(
                        (t.first().getStudIdTextField().getText()),
                        (newPassword))));
        
        System.out.println(((updated) ? ("Password Updated.")
                : ("Something went bad, please try again.")));
        
        return (clearTextInputControls((0),
                    (new TextInputControl[] {
                        (inputStudIdField),
                        (inputCurrentPasswordField),
                        (inputNewPasswordField),
                        (inputPasswordConfirmField)})));
    }
    @Override public FrontController clone()
    {
        try {
            return ((FrontController) (super.clone()));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }
}