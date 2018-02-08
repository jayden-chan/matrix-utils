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
        Matrix w = new Matrix("", new Vector("", 7, 7, 0, 9),
                                  new Vector("", 3, 2, 2, 5),
                                  new Vector("", 9, 6, 7, 2),
                                  new Vector("", 0, 1, 3, 2));

        Matrix r = new Matrix("", new Vector("", 1, 0, 0, 0),
                                  new Vector("", 0, 1, 0, 0),
                                  new Vector("", 0, 0, 1, 0),
                                  new Vector("", 0, 0, 0, 1));

        System.out.println(w.reducedRowEchelon().toString());
        System.out.println(r.toString());
        System.out.println(w.reducedRowEchelon().equals(r));
    }
}
