/**
 * @author Jayden Chan
 * @version 1
 * @since 1.0
 * Date created: Feb 8 2018
 *
 * Stores and keeps track of the users variables.
*/

import java.util.ArrayList;

public class Memory {

    private static ArrayList<MemoryEntry<EntryType>> identifiers = new ArrayList<>();

    private static ArrayList<MemoryEntry<Matrix>> matrices = new ArrayList<>();
    private static ArrayList<MemoryEntry<Vector>> vectors = new ArrayList<>();
    private static ArrayList<MemoryEntry<Double>> constants = new ArrayList<>();

    /*
     * The different types of entries allowed. Used for variable retrieval.
     */
    enum EntryType {
        VECTOR,
        MATRIX,
        CONSTANT,
        NULL
    }

    /**
     * Stores the provided Matrix in memory with the provided identifier.
     * @param label The identifier for the Matrix.
     * @param data  The Matrix.
     * @throws IllegalArgumentException if the identifier provided is already taken.
     */
    public static void store(String label, Matrix data) throws IllegalArgumentException {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                throw new IllegalArgumentException("Identifier name taken!");
            }
        }

        identifiers.add(new MemoryEntry<EntryType>(label, EntryType.MATRIX));
        matrices.add(new MemoryEntry<Matrix>(label, data));
    }

     /**
     * Stores the provided Vector in memory with the provided identifier.
     * @param label The identifier for the Vector.
     * @param data  The Vector.
     * @throws IllegalArgumentException if the identifier provided is already taken.
     */
   public static void store(String label, Vector data) throws IllegalArgumentException {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                throw new IllegalArgumentException("Identifier name taken!");
            }
        }

        identifiers.add(new MemoryEntry<EntryType>(label, EntryType.VECTOR));
        vectors.add(new MemoryEntry<Vector>(label, data));
    }

    /**
     * Stores the provided constant in memory with the provided identifier.
     * @param label The identifier for the constant.
     * @param data  The constant.
     * @throws IllegalArgumentException if the identifier provided is already taken.
     */
    public static void store(String label, Double data) throws IllegalArgumentException {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                throw new IllegalArgumentException("Identifier name taken!");
            }
        }

        identifiers.add(new MemoryEntry<EntryType>(label, EntryType.CONSTANT));
        constants.add(new MemoryEntry<Double>(label, data));
    }

    /**
     * Gets the type of data associated with the provided label.
     * @param label The identifier of the variable to search for.
     * @return The entry type of the variable. NULL if the variable does not exist.
     */
    public static EntryType retrieveType(String label) {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(entry.getLabel() == label) {
                return entry.getData();
            }
        }
        return EntryType.NULL;
    }

    public static Matrix retrieveMatrix(String label) {
        for(MemoryEntry<Matrix> entry : matrices) {
            if(entry.getLabel() == label) {
                return entry.getData();
            }
        }

        return null;
    }

    public static Vector retrieveVector(String label) {
        for(MemoryEntry<Vector> entry : vectors) {
            if(entry.getLabel() == label) {
                return entry.getData();
            }
        }

        return null;
    }

    public static double retrieveConstant(String label) {
        for(MemoryEntry<Double> entry : constants) {
            if(entry.getLabel() == label) {
                return entry.getData();
            }
        }

        return 0;
    }

    public static void free(String label) {
        MemoryEntry<EntryType> toRemove = null;

        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                switch(entry.getData()) {
                    case MATRIX:
                        freeMatrix(label);
                        toRemove = entry;
                        break;
                    case VECTOR:
                        freeVector(label);
                        toRemove = entry;
                        break;
                    case CONSTANT:
                        freeConstant(label);
                        toRemove = entry;
                        break;
                }
            }
        }

        if(toRemove != null) {
            System.out.println("Identifier removed: " + toRemove.getLabel());
            identifiers.remove(toRemove);
        }
    }

/****************************************************************/
/*                        Helper methods                        */
/****************************************************************/

    private static void freeMatrix(String label) {
        MemoryEntry<Matrix> toRemove = null;
        for(MemoryEntry<Matrix> mat : matrices) {
            if(mat.getLabel() == label) {
                toRemove = mat;
                break;
            }
        }

        matrices.remove(toRemove);
    }

    private static void freeVector(String label) {
        MemoryEntry<Vector> toRemove = null;
        for(MemoryEntry<Vector> vec : vectors) {
            if(vec.getLabel() == label) {
                toRemove = vec;
                break;
            }
        }

        vectors.remove(toRemove);
    }

    private static void freeConstant(String label) {
        MemoryEntry<Double> toRemove = null;
        for(MemoryEntry<Double> con : constants) {
            if(con.getLabel() == label) {
                toRemove = con;
                break;
            }
        }

        constants.remove(toRemove);
    }
}
