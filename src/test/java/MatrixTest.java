import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class MatrixTest {
    private static final double MULT_THRESH = 0.000001;
    private static final double ADD_THRESH = 1e-12;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructor1() {
        Matrix g = new Matrix("Test 1", new Vector("", 3, 2, 1)
                                      , new Vector("", 4, 2, 5));

        thrown.expect(IllegalArgumentException.class);
        Matrix u = new Matrix("Test 2", new Vector("", 3, 4, 2)
                                      , new Vector("", 1, 5, 3, 6));

        thrown.expect(IllegalArgumentException.class);
        Matrix q = new Matrix("Test 4", new Vector("", 1, 2, 3));
    }

    @Test
    public void testConstructor2() {
        Matrix m = new Matrix("Test 1", 4, 4);

        thrown.expect(IllegalArgumentException.class);
        Matrix q = new Matrix("Test 3", 10, 0);
    }

    @Test
    public void testGetWidth() {
        Matrix m = new Matrix("Test 1", 4, 10);
        Matrix n = new Matrix("Test 2", new Vector("", 5, 7, 2)
                                      , new Vector("", 5, 9, 1));

        int result = m.getWidth();
        int result2 = n.getWidth();

        assertEquals(4, result, 0);
        assertEquals(3, result2, 0);
    }

    @Test
    public void testGetHeight() {
        Matrix m = new Matrix("Test 1", 4, 10);
        Matrix n = new Matrix("Test 2", new Vector("", 5, 7, 2)
                                      , new Vector("", 5, 9, 1));

        assertEquals(10, m.getHeight(), 0);
        assertEquals(2, n.getHeight(), 0);
    }

    @Test
    public void testGetEntry() {
        Matrix m = new Matrix("Test 1", new Vector("", 1, 2, 3),
                                        new Vector("", 7, 2, 0),
                                        new Vector("", 0, 2, 5));

        assertEquals(0, m.getEntry(2, 1), ADD_THRESH);
        assertEquals(5, m.getEntry(2, 2), ADD_THRESH);
        assertEquals(1, m.getEntry(0, 0), ADD_THRESH);

        thrown.expect(IndexOutOfBoundsException.class);
        m.getEntry(10, 10);
    }

    @Test
    public void testSetEntry() {
        Matrix m = new Matrix("Test 1", new Vector("", 2, 8, 3),
                                        new Vector("", 9, 2, 6),
                                        new Vector("", 0, 2, 7));

        assertEquals(6, m.getEntry(2, 1), ADD_THRESH);
        m.setEntry(2, 1, 90);
        assertEquals(90, m.getEntry(2, 1), ADD_THRESH);

        thrown.expect(IndexOutOfBoundsException.class);
        m.setEntry(10, 10, 1);
    }

    @Test
    public void testGetLabel() {
        Matrix m = new Matrix("This is the label", 5, 5);

        assertEquals("This is the label", m.getLabel());
    }

    @Test
    public void testIsSquare() {
        Matrix m = new Matrix("Test 1", 5, 5);
        Matrix q = new Matrix("Test 2", 2, 7);

        assertEquals(true, m.isSquare());
        assertEquals(false, q.isSquare());
    }

    @Test
    public void testEquals() {
        Matrix m = new Matrix("TestEquals", new Vector("", 2, 8, 3),
                                            new Vector("", 9, 2, 6),
                                            new Vector("", 0, 2, 7));

        Matrix p = new Matrix("TestEquals", new Vector("", 2, 8, 3),
                                            new Vector("", 9, 2, 6),
                                            new Vector("", 0, 2, 7));

        Matrix q = new Matrix("TestNotEquals", new Vector("", 2, 8, 3),
                                               new Vector("", 9, 2, 6),
                                               new Vector("", 0, 2, 7));

        assertEquals(true, m.equals(p));
        assertEquals(true, p.equals(m));
        assertEquals(false, q.equals(m));
        assertEquals(false, m.equals(q));
    }

    @Test
    public void testHashCode() {
        Matrix m = new Matrix("TestEquals", new Vector("", 2, 8, 3),
                                            new Vector("", 9, 2, 6),
                                            new Vector("", 0, 2, 7));

        Matrix p = new Matrix("TestEquals", new Vector("", 2, 8, 3),
                                            new Vector("", 9, 2, 6),
                                            new Vector("", 0, 2, 7));

        Matrix q = new Matrix("TestNotEquals", new Vector("", 2, 8, 3),
                                               new Vector("", 9, 2, 6),
                                               new Vector("", 0, 2, 7));

        assertEquals(m.hashCode(), p.hashCode());
        assertNotEquals(m.hashCode(), q.hashCode());
    }
}
