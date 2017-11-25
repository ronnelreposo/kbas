package controls.adapter;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Serves as the Adapter for PropertyValueFactory.
 * @author Arya
 */
public final class PropertyValueFactoryAdapter
{
    private final PropertyValueFactory control;
    private PropertyValueFactoryAdapter (PropertyValueFactory propertyValueFactory)
    {
        control = propertyValueFactory;
    }
    public final static PropertyValueFactoryAdapter
        Create (PropertyValueFactory propertyValueFactory)
    {
        return (new PropertyValueFactoryAdapter(propertyValueFactory));
    }
    public PropertyValueFactory get() { return (control); }
}
