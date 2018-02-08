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
        Matrix p = new Matrix("p", new Vector("", 2, 8, 3, 9),
                                   new Vector("", 9, 2, 6, 2),
                                   new Vector("", 0, 2, 7, 8));

        System.out.println(p.toString());

        Matrix w = p.rref();

        System.out.println(w.toString());
    }
}
