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
        Matrix p = new Matrix("p", new Vector("", 2, 8, 3),
                                            new Vector("", 9, 2, 6),
                                            new Vector("", 0, 2, 7));

        Matrix q = new Matrix("q", new Vector("", 4, 16, 6),
                                            new Vector("", 18, 4, 12),
                                            new Vector("", 0, 4, 14));

        Matrix k = new Matrix("k", new Vector("", 152, 76, 150),
                                            new Vector("", 72, 176, 162),
                                            new Vector("", 36, 36, 122));


        Matrix w = p.mult("w", q);

        System.out.println(p.toString());
        System.out.println(q.toString());
        System.out.println(k.toString());
        System.out.println(w.toString());

    }
}
