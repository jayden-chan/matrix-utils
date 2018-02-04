/**
 * @author Jayden Chan <jaydencn7@gmail.com>
 * @version 1
 *
 * Entry point for the application.
 *
 * Detailed description to be added at a later date.
 */
public class App {

    public static void main(String[] args) {
        Matrix m = new Matrix("m", new Vector("", 1, 2, 3),
                                   new Vector("", 5, 8, 2),
                                   new Vector("", 2, 9, 4));

        System.out.println(m.toString());
    }
}
