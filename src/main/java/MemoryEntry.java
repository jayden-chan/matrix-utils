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

    public MemoryEntry(String label, DataType data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return this.label;
    }

    public DataType getData() {
        return this.data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
