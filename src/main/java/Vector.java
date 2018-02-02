/**
 * @author Jayden Chan <jaydencn7@gmail.com>
 * @version 1
 *
 * An n-dimensional vector.
 */
public class Vector {

    private double vec[];

    /**
     * Creates a new vector object and initializes the entires of the vector with the values provided.
     * @param entries The values for the entries of the vector. Variable length.
     * @throws IllegalArgumentException if no entries are provided.
     */
    public Vector(double... entries) throws IllegalArgumentException {
        if(entries.length == 0) {
            throw new IllegalArgumentException("Size of vector cannot be 0!");
        }

        vec = new double[entries.length];

        for(int i = 0; i < entries.length; i++) {
            vec[i] = entries[i];
        }
    }

    /**
     * Initializes an empty vector of size {@code size}.
     * @param size The size of the vector to create.
     */
    public Vector(int size) {
        vec = new double[size];
    }

/****************************************************************/
/*                      Getters / setters                       */
/****************************************************************/

    /**
     * Gets the size of the vector.
     * @return The size of the vector.
     */
    public int getSize() {
        return vec.length;
    }

    /**
     * Computes the magnitude of the vector.
     * @return The magnitude of the vector.
     */
    public double getMagnitude() {
        double sum = 0;

        for(double i : vec) {
            sum += i*i;
        }

        return Math.sqrt(sum);
    }

    /**
     * Sets the value of the entry at index {@code index} to value {@code value}.
     * @param index The index for the entry to be changed.
     * @param value The new value for the entry.
     * @throws IllegalArgumentException if the index is out of bounds of the vector array.
     */
    public void setEntry(int index, double value) throws IllegalArgumentException {
        if(index >= vec.length) {
            throw new IllegalArgumentException("Index out of range!");
        }

        vec[index] = value;
    }
}
