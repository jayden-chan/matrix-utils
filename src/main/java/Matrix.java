/**
 * @author Jayden Chan <jaydencn7@gmail.com>
 * @version 1
 *
 * An m by n matrix.
 */

import java.util.Random;

public class Matrix {

    private Vector[] columnVectors;
    private String label;
    private Random rand = new Random();

    private int width, height;

    /**
     * Creates a new matrix object and initializes the column vectors with the ones provided.
     * @param label The label for the matrix.
     * @param columnVectors The column vectors of the matrix.
     * @throws IllegalArgumentException if there are not enough vectors provided.
     * @throws IllegalArgumentException if the vectors are not equal in size.
     */
    public Matrix(String label, Vector... columnVectors) throws IllegalArgumentException {
        if(columnVectors.length <= 1) {
            throw new IllegalArgumentException("No column vectors provided!");
        }

        this.columnVectors = new Vector[columnVectors.length];
        this.label = label;
        this.width = columnVectors.length;
        this.height = columnVectors[0].getSize();

        for(int i = 0; i < columnVectors.length; i++) {
            this.columnVectors[i] = columnVectors[i];

            if(columnVectors[i].getSize() != height) {
                throw new IllegalArgumentException("Column vectors are not equal size!");
            }
        }
    }

    /**
     * Constructs a new matrix object with the width and height provided.
     * @param label The label for the matrix.
     * @param width The width of the matrix.
     * @param height The height of the matrix.
     * @throws IllegalArgumentException if the size of the matrix is too small.
     */
    public Matrix(String label, int width, int height) throws IllegalArgumentException {
        if(width <= 1 || height <= 1) {
            throw new IllegalArgumentException("The minimum size for a matrix is 2x2.");
        }

        this.columnVectors = new Vector[width];
        this.label = label;

        for(int i = 0; i < width; i++) {
            this.columnVectors[i] = new Vector("", height);
        }
    }

    /**
     * Formats the entries of the matrix into a string prefixed by the matrix's label.
     * @return The formatted string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(label);
        sb.append(": \n\n");

        for(int i = 0; i < height; i++) {
            sb.append("    ");

            for(int j = 0; j < width; j++) {
                sb.append(columnVectors[i].getEntry(j));
                sb.append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

/****************************************************************/
/*                      Getters / setters                       */
/****************************************************************/

    /**
     * Gets the height of the matrix.
     * @return The width of the matrix.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the matrix.
     * @return The height of the matrix.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Initializes each entry in the matrix to a random whole number in the range provided.
     * @param lowerBound The lower bound of the random number range.
     * @param upperBound The upper bound of the random number range.
     */
    public void setRand(int lowerBound, int upperBound) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                columnVectors[j].setEntry(i, (double) rand.nextInt(upperBound - lowerBound) + lowerBound);
            }
        }
    }

    /**
     * Initializes each entry in the matrix to a random whole number in the range [0, 10].
     */
    public void setRand() {
        setRand(0, 10);
    }

    /**
     * Returns the vector's label.
     * @return The string containing the matrix's label.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Checks whether the matrix has equal width and height.
     * @return Whether or not the matrix is square.
     */
    public boolean isSquare() {
        return width == height;
    }

/****************************************************************/
/*                          Arithmetic                          */
/****************************************************************/

}
