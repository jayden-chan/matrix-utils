/**
 * @author Jayden Chan <jaydencn7@gmail.com>
 * @version 1
 *
 * An n-dimensional vector.
 */

import java.util.Random;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Vector {

    private double entries[];
    private String label;

    /**
     * Creates a new vector object and initializes the entires of the vector with the values provided.
     * @param label The label for the vector.
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
     * Initializes an empty vector of the size provided.
     * @param label The label for the vector.
     * @param size The size of the vector to create.
     * @throws IllegalArgumentException if the size provided is 0.
     */
    public Vector(String label, int size) throws IllegalArgumentException {
        if(size < 1) {
            throw new IllegalArgumentException("Size of vector cannot be 0!");
        }

        this.entries = new double[size];
        this.label = label;
    }

/****************************************************************/
/*                      Getters / setters                       */
/****************************************************************/

    /**
     * Gets the number of entries in the vector.
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
     * Gets the value of the vector's entry at the index provided.
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
     * Sets the value of the entry at the index provided to value provided.
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
        Random rand = new Random();

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

    /**
     * Returns the vector's label.
     * @return The string containing the vector's label.
     */
    public String getLabel() {
        return this.label;
    }

/****************************************************************/
/*                          Arithmetic                          */
/****************************************************************/

    /**
     * Adds a vector of equal size to this vector.
     * @param label The label for the new vector.
     * @param a     The vector to add.
     * @return The new vector.
     * @throws IllegalArgumentException if the two vectors are not equal size.
     */
    public Vector add(String label, Vector a) throws IllegalArgumentException {
        if(a.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Cannot add vectors of unequal size!");
        }

        Vector toReturn = new Vector(label, this.getSize());

        for(int i = 0; i < a.getSize(); i++) {
            toReturn.setEntry(i, a.getEntry(i) + this.getEntry(i));
        }

        return toReturn;
    }

    /**
     * Adds a vector of equal size to this vector.
     * @param a     The vector to add.
     * @return The new vector.
     * @throws IllegalArgumentException if the two vectors are not equal size.
     */
    public Vector add(Vector a) throws IllegalArgumentException {
        return add("", a);
    }

    /**
     * Subtracts a vector from this vector.
     * @param label The label for the new vector.
     * @param a     Vector to subtract.
     * @return The new vector.
     * @throws IllegalArgumentException if the vectors are now equal size.
     */
    public Vector sub(String label, Vector a) throws IllegalArgumentException {
        if(a.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Cannot subtract vectors of unequal size!");
        }
        Vector toReturn = new Vector(label, this.getSize());

        for(int i = 0; i < a.getSize(); i++) {
            toReturn.setEntry(i, this.getEntry(i) - a.getEntry(i));
        }

        return toReturn;
    }

    /**
     * Subtracts a vector from this vector.
     * @param a     Vector to subtract.
     * @return The new vector.
     * @throws IllegalArgumentException if the vectors are now equal size.
     */
    public Vector sub(Vector a) throws IllegalArgumentException {
        return sub("", a);
    }

    /**
     * Computes the cross product between this vector and another vector.
     * @param label The label for the new vector.
     * @param a     The vector to cross with.
     * @return The cross product.
     * @throws IllegalArgumentException if either vector is not in R3.
     */
    public Vector cross(String label, Vector a) throws IllegalArgumentException {
        if(a.getSize() != 3 || this.getSize() != 3) {
            throw new IllegalArgumentException("The cross product is only supported in R3.");
        }

        double i = this.getEntry(1) * a.getEntry(2) - this.getEntry(2) * a.getEntry(1);
        double j = this.getEntry(2) * a.getEntry(0) - this.getEntry(0) * a.getEntry(2);
        double k = this.getEntry(0) * a.getEntry(1) - this.getEntry(1) * a.getEntry(0);

        return new Vector(label, i, j, k);
    }

    /**
     * Computes the cross product between this vector and another vector.
     * @param a     The vector to cross with.
     * @return The cross product.
     * @throws IllegalArgumentException if either vector is not in R3.
     */
    public Vector cross(Vector a) throws IllegalArgumentException {
        return cross("", a);
    }

    /**
     * Computes the dot product between this vector and another vector.
     * @param a The vector to dot with.
     * @return The dot product.
     * @throws IllegalArgumentException if the vectors are not the same size.
     */
    public double dot(Vector a) throws IllegalArgumentException {
        if(a.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Cannot take the dot product of vectors with unequal size!");
        }

        double sum = 0;

        for(int i = 0; i < a.getSize(); i++) {
            sum += a.getEntry(i) * this.getEntry(i);
        }

        return sum;
    }

/****************************************************************/
/*                           Override                           */
/****************************************************************/

    /**
     * Formats the entries of the vector into a string prefixed by the vector's label.
     * @return The formatted string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(label);
        sb.append(": \n\n");

        for(int i = 0; i < entries.length; i++) {
            sb.append("    ");
            sb.append(entries[i]);
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Test for equality between this vector and another vector.
     * Will fail if the vectors are not the same size, if they do not
     * have the same label, or if their entries are not all identical.
     * Will also fail if the object passed is null or not a Vector.
     * @param obj The object to compare to.
     * @return Whether or not the object passed is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector)) {
            return false;
        }

        if(obj == this) return true;

        Vector v = (Vector) obj;

        if(v.getSize() != this.getSize()) return false;
        if(v.getLabel() != label) return false;

        for(int i = 0; i < this.getSize(); i++) {
            if(v.getEntry(i) != entries[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the hash code for this object. Overridden to
     * ensure that the contract for <code>hashCode()<\code> is not
     * broken as a result of overriding <code>equals()<\code>.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder(17, 31);

        for(int i = 0; i < this.getSize(); i++) {
            hb.append(this.getEntry(i));
        }

        hb.append(label);
        return hb.toHashCode();
    }
}
