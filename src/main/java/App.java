/**
 * @author Jayden Chan
 * @version 1
 *
 * Entry point for the application.
 *
 * Detailed description to be added at a later date.
 */
public class App {

    public static void main(String[] args) {
        Memory.store("A", 9.0);
        Memory.store("B", new Vector("", 1.0, 2.0, 3.0));
        Memory.store("C", new Matrix("", 5, 5));

        try {
            Memory.store("A", 234.0);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Good");
        }

        Memory.free("A");

        Memory.store("A", 90.0);
    }
}
