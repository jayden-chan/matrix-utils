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
        CONSTANT
    }

    public static void store(String label, Matrix data) throws IllegalArgumentException {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                throw new IllegalArgumentException("Identifier name taken!");
            }
        }

        identifiers.add(new MemoryEntry<EntryType>(label, EntryType.MATRIX));
        matrices.add(new MemoryEntry<Matrix>(label, data));
    }

    public static void store(String label, Vector data) throws IllegalArgumentException {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                throw new IllegalArgumentException("Identifier name taken!");
            }
        }

        identifiers.add(new MemoryEntry<EntryType>(label, EntryType.VECTOR));
        vectors.add(new MemoryEntry<Vector>(label, data));
    }

    public static void store(String label, Double data) throws IllegalArgumentException {
        for(MemoryEntry<EntryType> entry : identifiers) {
            if(label.equals(entry.getLabel())) {
                throw new IllegalArgumentException("Identifier name taken!");
            }
        }

        identifiers.add(new MemoryEntry<EntryType>(label, EntryType.CONSTANT));
        constants.add(new MemoryEntry<Double>(label, data));
    }
}
