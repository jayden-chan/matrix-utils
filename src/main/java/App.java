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
        Vector v = new Vector("v", 1, 2, 3);
        Vector q = new Vector("q", 3, 2, 1);
        System.out.println("Suh dudes.");
        System.out.println(v.toString());
        System.out.println(q.toString());

        Vector wq = v.add("v+q", v, q);

        System.out.println(wq.toString());
    }
}
