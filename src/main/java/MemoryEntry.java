/**
 * @author Jayden Chan
 * @version 1
 * Date created: Feb 7 2018
 *
 * A container for the user's runtime variables.
*/

public class MemoryEntry <DataType> {

    private final String label;
    private DataType data;

    /**
     * Initializes the memory entry with the label and data provided.
     * @param label The label for the memory entry.
     * @param data  The data for the memory entry.
     */
    public MemoryEntry(String label, DataType data) {
        this.label = label;
        this.data = data;
    }

    /**
     * Returns the entry's label.
     * @return The label.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the entry's data.
     * @return The data.
     */
    public DataType getData() {
        return this.data;
    }

    /**
     * Sets the entry's data.
     * @param data The new data.
     */
    public void setData(DataType data) {
        this.data = data;
    }
}
