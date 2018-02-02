/**
 * @author Jayden Chan <jaydencn7@gmail.com>
 * @version 1
 *
 * An n-dimensional vector.
 */

import java.util.Random;

public class Vector {

    private double entries[];
    private String label;
    private Random rand = new Random();

    /**
     * Creates a new vector object and initializes the entires of the vector with the values provided.
     * @param entries The values for the entries of the vector. Variable length.
     * @throws IllegalArgumentException if no entries are provided.
     */
    public Vector(String label, double... entries) throws IllegalArgumentException {
        if(entries.length == 0) {
            throw new IllegalArgumentException("Size of vector cannot be 0!");
        }

        this.entries = new double[entries.length];
        this.label = label;

        for(int i = 0; i < entries.length; i++) {
            this.entries[i] = entries[i];
        }
    }

    /**
     * Initializes an empty vector of size {@code size}.
     * @param size The size of the vector to create.
     */
    public Vector(String label, int size) {
        this.entries = new double[size];
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(label);
        sb.append(": \n\n");

        for(int i = 0; i < entries.length; i++) {
            sb.append("    ");
            sb.append(entries[i]);
            sb.append("\n");
        }

        return sb.toString();
    }

/****************************************************************/
/*                      Getters / setters                       */
/****************************************************************/

    /**
     * Gets the size of the vector.
     * @return The size of the vector.
     */
    public int getSize() {
        return entries.length;
    }

    /**
     * Computes the magnitude of the vector.
     * @return The magnitude of the vector.
     */
    public double getMagnitude() {
        double sum = 0;

        for(double i : entries) {
            sum += i*i;
        }

        return Math.sqrt(sum);
    }

    /**
     * Gets the value of the vector's entry at index {@code index}.
     * @param index The index of the entry to return.
     * @return The value of the entry.
     * @throws IndexOutOfBoundsException if the index is greater than the size of the vector.
     */
    public double getEntry(int index) throws IndexOutOfBoundsException {
        if(index >= entries.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }

        return entries[index];
    }

    /**
     * Sets the value of the entry at index {@code index} to value {@code value}.
     * @param index The index for the entry to be changed.
     * @param value The new value for the entry.
     * @throws IndexOutOfBoundsException if the index is greater than the size of the vector.
     */
    public void setEntry(int index, double value) throws IndexOutOfBoundsException {
        if(index >= entries.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }

        entries[index] = value;
    }

    /**
     * Initializes each entry in the vector to a random whole number in the range provided.
     * @param lowerBound The lower bound of the random number range.
     * @param upperBound The upper bound of the random number range.
     */
    public void setRand(int lowerBound, int upperBound) {
        for(int i = 0; i < entries.length; i++) {
            entries[i] = (double) rand.nextInt(upperBound - lowerBound) + lowerBound;
        }
    }

    /**
     * Initializes each entry in the vector to a random whole number in the range [0, 10].
     */
    public void setRand() {
        setRand(0, 10);
    }

/****************************************************************/
/*                          Arithmetic                          */
/****************************************************************/

    /**
     * Adds two vectors of equal size.
     * @param label The label for the new vector.
     * @param a     The first vector.
     * @param b     The second vector.
     * @return The new vector which is the sum of {@code a} and {@code b}.
     * @throws IllegalArgumentException if the two vectors are not equal size.
     */
    public Vector add(String label, Vector a, Vector b) throws IllegalArgumentException {
        if(a.getSize() != b.getSize()) {
            throw new IllegalArgumentException("Cannot add vectors of unequal size!");
        }

        Vector toReturn = new Vector(label, a.getSize());

        for(int i = 0; i < a.getSize(); i++) {
            toReturn.setEntry(i, a.getEntry(i) + b.getEntry(i));
        }

        return toReturn;
    }

    /**
     * Adds two vectors of equal size.
     * @param a     The first vector.
     * @param b     The second vector.
     * @return The new vector which is the sum of {@code a} and {@code b}.
     * @throws IllegalArgumentException if the two vectors are not equal size.
     */
    public Vector add(Vector a, Vector b) throws IllegalArgumentException {
        return add("", a, b);
    }

    /**
     * Subtracts vector {@code b} from vector {@code a}.
     * @param a     Vector to subtract from.
     * @param b     Vector to subtract.
     * @param label The label for the new vector.
     * @return The new vector which is the difference of {@code a} and {@code b}.
     * @throws IllegalArgumentException if the vectors are now equal size.
     */
    public Vector sub(String label, Vector a, Vector b) throws IllegalArgumentException {
        if(a.getSize() != b.getSize()) {
            throw new IllegalArgumentException("Cannot subtract vectors of unequal size!");
        }
        Vector toReturn = new Vector(label, a.getSize());

        for(int i = 0; i < a.getSize(); i++) {
            toReturn.setEntry(i, a.getEntry(i) - b.getEntry(i));
        }

        return toReturn;
    }

    /**
     * Subtracts vector {@code b} from vector {@code a}.
     * @param a     Vector to subtract from.
     * @param b     Vector to subtract.
     * @return The new vector which is the difference of {@code a} and {@code b}.
     * @throws IllegalArgumentException if the vectors are now equal size.
     */
    public Vector sub(Vector a, Vector b) throws IllegalArgumentException {
        return sub("", a, b);
    }

    /**
     * Computes the cross product between vectors {@code a} and {@code b}.
     * @param a     The first vector.
     * @param b     The second vector.
     * @param label The label for the new vector.
     * @return The cross product A x B.
     * @throws IllegalArgumentException if either vector is not in R3.
     */
    public Vector cross(String label, Vector a, Vector b) throws IllegalArgumentException {
        if(a.getSize() != 3 || b.getSize() != 3) {
            throw new IllegalArgumentException("The cross product is only supported in R3.");
        }

        double i = a.getEntry(1) * b.getEntry(2) - a.getEntry(2) * b.getEntry(1);
        double j = a.getEntry(2) * b.getEntry(0) - a.getEntry(0) * b.getEntry(2);
        double k = a.getEntry(0) * b.getEntry(1) - a.getEntry(1) * b.getEntry(0);

        return new Vector(label, i, j, k);
    }

    /**
     * Computes the cross product between vectors {@code a} and {@code b}.
     * @param a The first vector.
     * @param b The second vector.
     * @return The cross product A x B.
     * @throws IllegalArgumentException if either vector is not in R3.
     */
    public Vector cross(Vector a, Vector b) throws IllegalArgumentException {
        return cross("", a, b);
    }

    /**
     * Computes the dot product between vectors {@code a} and {@code b}.
     * @param a The first vector.
     * @param b The second vector.
     * @return The dot product A * B.
     * @throws IllegalArgumentException if the vectors are not the same size.
     */
    public double dot(Vector a, Vector b) throws IllegalArgumentException {
        if(a.getSize() != b.getSize()) {
            throw new IllegalArgumentException("Cannot take the dot product of vectors with unequal size!");
        }

        double sum = 0;

        for(int i = 0; i < a.getSize(); i++) {
            sum += a.getEntry(i) * b.getEntry(i);
        }

        return sum;
    }
}
