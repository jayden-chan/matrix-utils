/**
 * @author Jayden Chan
 * @version 1
 *
 * An m by n matrix.
 */

import java.util.Random;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Matrix {

    private Vector[] rowVectors;
    private String label;
    private Random rand = new Random();

    private int width, height;

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static final double COMPARE_PRECISION = 1e-2;

    /**
     * Creates a new matrix object and initializes the column vectors with the ones provided.
     * @param label The label for the matrix.
     * @param rowVectors The column vectors of the matrix.
     * @throws IllegalArgumentException if there are not enough vectors provided.
     * @throws IllegalArgumentException if the vectors are not equal in size.
     */
    public Matrix(String label, Vector... rowVectors) throws IllegalArgumentException {
        if(rowVectors.length <= 1) {
            throw new IllegalArgumentException("No column vectors provided!");
        }

        this.rowVectors = new Vector[rowVectors.length];
        this.label = label;
        this.height = rowVectors.length;
        this.width = rowVectors[0].getSize();

        for(int i = 0; i < rowVectors.length; i++) {
            this.rowVectors[i] = rowVectors[i];

            if(rowVectors[i].getSize() != width) {
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

        this.rowVectors = new Vector[height];
        this.label = label;
        this.width = width;
        this.height = height;

        for(int i = 0; i < height; i++) {
            this.rowVectors[i] = new Vector("", width);
        }
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
     * Returns the value of the entry in the position provided.
     * @param x The x index.
     * @param y The y index.
     * @return The value at the specified index.
     * @throws IndexOutOfBoundsException if the x or y indices provided are greater
     * than the width or height of the matrix respectively.
     */
    public double getEntry(int x, int y) throws IndexOutOfBoundsException {
        if(x >= width || x < 0 || y >= height || y < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }

        return rowVectors[y].getEntry(x);
    }

    /**
     * Sets the value of the entry in the position provided to the value provided.
     * @param x The x index.
     * @param y The y index.
     * @param value The value to set the entry to.
     * @throws IndexOutOfBoundsException if the x or y indices provided are greater
     * than the width or height of the matrix respectively.
     */
    public void setEntry(int x, int y, double value) throws IndexOutOfBoundsException {
        if(x >= width || x < 0 || y >= height || y < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }

        rowVectors[y].setEntry(x, value);
    }

    /**
     * Returns the entries in a specific row as a {@code Vector}.
     * @param index The index of the row to return.
     * @return The row vector at the index specified.
     */
    public Vector getRowVector(int index) throws IndexOutOfBoundsException {
        if(index >= this.height || index < 0) {
            throw new IndexOutOfBoundsException("Index greater than matrix height!");
        }

        return rowVectors[index];
    }

    /**
     * Returns the entries in a specific column as a {@code Vector}.
     * @param index The index of the column to return.
     * @return The column vector at the index specified.
     */
    public Vector getColumnVector(int index) throws IndexOutOfBoundsException {
        if(index >= this.width || index < 0) {
            throw new IndexOutOfBoundsException("Index greater than matrix width!");
        }

        Vector toReturn = new Vector(this.label, this.height);

        for(int i = 0; i < height; i++) {
            toReturn.setEntry(i, this.getEntry(index, i));
        }

        return toReturn;
    }

    /**
     * Initializes each entry in the matrix to a random whole number in the range provided.
     * @param lowerBound The lower bound of the random number range.
     * @param upperBound The upper bound of the random number range.
     */
    public void setRand(int lowerBound, int upperBound) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                rowVectors[j].setEntry(i, (double) rand.nextInt(upperBound - lowerBound) + lowerBound);
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
     * Returns the matrix's label.
     * @return The string containing the matrix's label.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the matrix's label.
     * @param label The new label.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Checks whether the matrix has equal width and height.
     * @return Whether or not the matrix is square.
     */
    public boolean isSquare() {
        return this.width == this.height;
    }

/****************************************************************/
/*                          Arithmetic                          */
/****************************************************************/

    /**
     * Adds this matrix and the matrix provided.
     * @param label The label for the new matrix.
     * @param m The matrix to add.
     * @return The new matrix.
     * @throws IllegalArgumentException if the matrix passed is not the
     * same size as this one.
     */
    public Matrix add(String label, Matrix m) throws IllegalArgumentException {
        if(m.getWidth() != width || m.getHeight() != height) {
            throw new IllegalArgumentException("Cannot add matrices of unequal size!");
        }

        Matrix toReturn = new Matrix(label, width, height);

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                toReturn.setEntry(i, j, this.getEntry(i, j) + m.getEntry(i, j));
            }
        }

        return toReturn;
    }
    /**
     * Adds this matrix and the matrix provided.
     * @param m The matrix to add.
     * @return The new matrix.
     * @throws IllegalArgumentException if the matrix passed is not the
     * same size as this one.
     */
    public Matrix add(Matrix m) throws IllegalArgumentException {
        return add(this.label, m);
    }

    /**
     * Subtracts a provided matrix from this matrix.
     * @param label The label for the new matrix.
     * @param m The matrix to subtract.
     * @return The new matrix.
     * @throws IllegalArgumentException if the matrix passed is not the
     * same size as this one.
     */
    public Matrix sub(String label, Matrix m) throws IllegalArgumentException {
        if(m.getWidth() != width || m.getHeight() != height) {
            throw new IllegalArgumentException("Cannot subtract matrices of unequal size!");
        }

        Matrix toReturn = new Matrix(label, width, height);

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                toReturn.setEntry(i, j, this.getEntry(i, j) - m.getEntry(i, j));
            }
        }

        return toReturn;
    }

    /**
     * Subtracts a provided matrix from this matrix.
     * @param m The matrix to subtract.
     * @return The new matrix.
     * @throws IllegalArgumentException if the matrix passed is not the
     * same size as this one.
     */
    public Matrix sub(Matrix m) throws IllegalArgumentException {
        return sub(this.label, m);
    }

    /**
     * Performs matrix multiplication between this matrix and the matrix provided.
     * @param label The label for the new matrix.
     * @param m The matrix to multiply by.
     * @return The new matrix.
     * @throws IllegalArgumentException if the height of the matrix provided is
     * not equal to the width of this matrix.
     */
    public Matrix mult(String label, Matrix m) throws IllegalArgumentException {
        if(m.getHeight() != width) {
            throw new IllegalArgumentException("Cannot multiply these matrices!");
        }

        Matrix toReturn = new Matrix(label, m.getWidth(), height);

        for(int i = 0; i < m.getWidth(); i++) {
            for(int j = 0; j < height; j++) {
                double sum = 0;
                for(int k = 0; k < width; k++) {
                    sum += this.getEntry(k, j) * m.getEntry(i, k);
                }

                toReturn.setEntry(i, j, sum);
            }
        }

        return toReturn;
    }

    /**
     * Performs matrix multiplication between this matrix and the matrix provided.
     * @param m The matrix to multiply by.
     * @return The new matrix.
     * @throws IllegalArgumentException if the height of the matrix provided is
     * not equal to the width of this matrix.
     */
    public Matrix mult(Matrix m) throws IllegalArgumentException {
        return mult(this.label, m);
    }

    /**
     * Multiplies each entry in this matrix by a constant.
     * @param c The constant by which to multiply.
     */
    public void mult(double c) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                this.setEntry(i, j, this.getEntry(i, j) * c);
            }
        }
    }

    /**
     * Returns the identity matrix whose dimensions match those of this
     * matrix. An identity matrix is one whose values are all 0 except
     * those on the diagonal. Example:
     * <pre>
     *       1 0 0 0
     *       0 1 0 0
     *       0 0 1 0
     *       0 0 0 1
     * </pre>
     * @return The identity matrix.
     * @throws UnsupportedOperationException if this matrix is not square.
     */
    public Matrix identity() throws UnsupportedOperationException {
        if(!isSquare()) {
            throw new UnsupportedOperationException("Cannot get the identity of a non-square matrix!");
        }

        Matrix toReturn = new Matrix(this.label, this.width, this.height);

        for(int i = 0; i < width; i++) {
            toReturn.setEntry(i, i, 1);
        }

        return toReturn;
    }

    /**
     * Returns the transpose of this matrix. The transpose of a matrix
     * is the same matrix but with the row vectors as column vectors, and
     * vice versa.
     * @return The transpose of this matrix.
     */
    public Matrix transpose() {
        Matrix toReturn = new Matrix(label, height, width);

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                toReturn.setEntry(j, i, this.getEntry(i, j));
            }
        }

        return toReturn;
    }

    /**
     * Returns the trace of this matrix. The trace of a square matrix
     * is the sum of its diagonal entries.
     * @return The trace of this matrix.
     * @throws UnsupportedOperationException if the matrix is not square.
     */
    public double trace() throws UnsupportedOperationException {
        if(!isSquare()) {
            throw new UnsupportedOperationException("Cannot take the trace of a non-square matrix!");
        }

        double sum = 0;
        for(int i = 0; i < width; i++) {
            sum += this.getEntry(i, i);
        }

        return sum;
    }

    /**
     * Computes the Row Echelon Form of the matrix.
     * @return The Row Echelon Form.
     */
    public Matrix rowEchelon() {
        Matrix toReturn = this.cloneMatrix();

        int currRow = 0;

        columnLoop:
        for(int currCol = 0; currCol < height; currCol++) {
            currRow = currCol;

            // Find the next pivot
            while(toReturn.getEntry(currCol, currRow) == 0) {
                currRow++;
                if(currRow == height){
                    continue columnLoop;
                }
            }

            // Divide the row by the pivot to make it equal 1
            toReturn.multRow(currRow, 1.0 / toReturn.getEntry(currCol, currRow));

            // Place the row with the pivot in the highest row that doesn't contain
            // a previously used pivot
            toReturn.swapRows(currCol, currRow);
            currRow = 1+currCol;

            // Use the pivot row to make all entries in the column below the pivot 0
            while(currRow < height) {
                double currEntry = toReturn.getEntry(currCol, currRow);
                if(currEntry != 0) {
                    toReturn.multRow(currRow, 1.0 / currEntry);
                    toReturn.subRow(currCol, currRow);
                }

                currRow++;
            }
        }

        return toReturn;
    }

    /**
     * Computes the Reduced Row Echelon Form of the matrix.
     * @return The Reduced Row Echelon Form.
     */
    public Matrix reducedRowEchelon() {
        Matrix toReturn = this.rowEchelon();

        int currCol = 0;
        int currRow = toReturn.getHeight()-1;

        while(currRow >= 0) {
            while(Math.abs(toReturn.getEntry(currCol, currRow) - 1) > 1e-9) {
                currCol++;
                if(currCol == toReturn.getWidth()) {
                    currRow--;
                    currCol = 0;
                }
            }

            for(int i = currRow; i > 0; i--) {
                double above = toReturn.getEntry(currCol, i-1);
                if(above != 0) {
                    toReturn.multRow(currRow, above);
                    toReturn.subRow(currRow, i-1);
                    toReturn.multRow(currRow, 1.0 / above);
                }
            }
            currCol = 0;
            currRow--;
        }

        return toReturn;
    }

/****************************************************************/
/*                           Override                           */
/****************************************************************/

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
                if(this.getEntry(j, i) != 0) {
                    sb.append(String.format(ANSI_BLUE + "%10.3f" + ANSI_RESET, rowVectors[i].getEntry(j)));
                }
                else {
                    sb.append(String.format("%10.3f", rowVectors[i].getEntry(j)));
                }
                sb.append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Tests whether this matrix is equal to the object provided.
     * Will fail if the matrices are not the same size, if their labels
     * are not the same, or if any of their entries differ. Will also
     * fail if the object passed is null or not a Matrix.
     * @param obj The object to compare to.
     * @return Whether or not the object passed is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Matrix)) {
            return false;
        }

        if(obj == this) return true;

        Matrix m = (Matrix) obj;

        if(m.getWidth() != this.width || m.getHeight() != this.height || m.getLabel() != this.label) {
            return false;
        }

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(Math.abs(m.getEntry(i, j) - this.getEntry(i, j)) > COMPARE_PRECISION) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns the hash code for this object. Overridden to
     * ensure that the contract for <code>hashCode()</code> is not
     * broken as a result of overriding <code>equals()</code>.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder(17, 31);

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                hb.append(this.getEntry(i, j));
            }
        }

        hb.append(label);
        hb.append(width);
        hb.append(height);
        return hb.toHashCode();
    }

/****************************************************************/
/*                        Helper methods                        */
/****************************************************************/

    /*
     * Swaps rows.
     */
    private void swapRows(int index1, int index2) {
        Vector temp = rowVectors[index1];
        rowVectors[index1] = rowVectors[index2];
        rowVectors[index2] = temp;
    }

    /*
     * Multiplies each entry in the row by the constant.
     */
    private void multRow(int index, double c) {
        for(int i = 0; i < width; i++) {
            this.setEntry(i, index, this.getEntry(i, index) * c);
        }
    }

    /*
     * Adds row 1 to row 2.
     */
    private void addRow(int index1, int index2) {
        for(int i = 0; i < width; i++) {
            this.setEntry(i, index2, this.getEntry(i, index2) + this.getEntry(i ,index1));
        }
    }

    /*
     * Subtracts row 1 from row 2.
     */
    private void subRow(int index1, int index2) {
        for(int i = 0; i < width; i++) {
            this.setEntry(i, index2, this.getEntry(i, index2) - this.getEntry(i, index1));
        }
    }

    /*
     * Provides a clone of this matrix.
     */
    private Matrix cloneMatrix() {
        Matrix toReturn = new Matrix(this.label, this.width, this.height);

        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                toReturn.setEntry(i, j, this.getEntry(i, j));
            }
        }

        return toReturn;
    }
}
