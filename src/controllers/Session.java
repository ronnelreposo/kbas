package controllers;

/**
 *  Serves as the session for the entire application.
 * @author Arya
 */
public final class Session
{
    private final int id;
    private final String data;
    private static Session instance;
    
    private Session (int id, String data)
    {
        this.id = id;
        this.data = data;
    }
    /**
     * Creates a session.
     * @param id session id.
     * @param data session data.
     * @return new session, if already created returns the last session.
     */
    public static Session Create (int id, String data)
    {
        return (instance = (((instance) == null) ?
                (new Session((id), (data))) : (instance)));
    }
    /**
     * Returns the the current instance of Session.
     * @return 
     */
    public static Session getInstance () { return (instance); }
    public int getId () { return (id); }
    public String getData () { return (data); }
}
