
package controls.body;

import controls.adapter.TableColumnAdapter;

/**
 * Functional Interface for creating column.
 * @author Arya
 */
interface CreateColumn
{
    TableColumnAdapter create (String heading, String name, double width);
}
