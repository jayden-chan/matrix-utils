import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class VectorTest {

    private static final double MULT_THRESH = 0.000001;
    private static final double ADD_THRESH = 1e-12;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructor1() {
        thrown.expect(IllegalArgumentException.class);
        Vector v = new Vector("No values");
    }

    @Test
    public void testConstructor2() {
        thrown.expect(IllegalArgumentException.class);
        Vector v = new Vector("Testing", 0);
    }

    @Test
    public void testGetSize() {
        Vector v = new Vector("Test", 1.2, 5.5, 34, 124);
        assertEquals(4, v.getSize(), 0);
    }

    @Test
    public void testGetMag() {
        Vector v = new Vector("Test 1", 48.387, 437.2387, 724.398);
        double result = v.getMagnitude();

        assertEquals(847.5089645, result, MULT_THRESH);
    }

    @Test
    public void testGetEntry() {
        Vector v = new Vector("Test 1", 2.3, 5.44, 1.95);

        assertEquals(2.3, v.getEntry(0), 0);
        assertEquals(5.44, v.getEntry(1), 0);
        assertEquals(1.95, v.getEntry(2), 0);

        thrown.expect(IndexOutOfBoundsException.class);
        v.getEntry(10);
    }

    @Test
    public void testSetEntry() {
        thrown.expect(IndexOutOfBoundsException.class);
        Vector v = new Vector("Test 1", 2.3, 4.6, 9.9);

        v.setEntry(1, 564.23);
        assertEquals(564.23, v.getEntry(1), 0);

        thrown.expect(IndexOutOfBoundsException.class);
        v.setEntry(10, 90);
    }

    @Test
    public void testGetLabel() {
        Vector v = new Vector("This is the label", 1);

        assertEquals("This is the label", v.getLabel());
    }

    @Test
    public void testAdd() {
        Vector v = new Vector("Test 1", 2.342, 2.468, 31.492);
        Vector w = new Vector("Test 2", 23.489, 78.268, 12.654);

        Vector result = v.add(w);

        assertEquals(25.831, result.getEntry(0), ADD_THRESH);
        assertEquals(80.736, result.getEntry(1), ADD_THRESH);
        assertEquals(44.146, result.getEntry(2), ADD_THRESH);

        Vector q = new Vector("Test 3", 2, 6);
        thrown.expect(IllegalArgumentException.class);
        v.add(q);
    }

    @Test
    public void testSub() {
        Vector v = new Vector("Test 1", 3154.648, 168.684, 15.687);
        Vector w = new Vector("Test 2", 47.243, 7.6987, 1.867);

        Vector result = v.sub(w);

        assertEquals(3107.405, result.getEntry(0), ADD_THRESH);
        assertEquals(160.9853, result.getEntry(1), ADD_THRESH);
        assertEquals(13.82, result.getEntry(2), ADD_THRESH);

        Vector q = new Vector("Test 3", 2.56, 1);
        thrown.expect(IllegalArgumentException.class);
        v.sub(q);
    }

    @Test
    public void testCross() {
        Vector v = new Vector("Test 1", 4.66, 17.34, 90);
        Vector w = new Vector("Test 2", 2.44, 5.123, 9.334);

        Vector result = v.cross("", w);

        assertEquals(-299.21844, result.getEntry(0), MULT_THRESH);
        assertEquals(176.10356, result.getEntry(1), MULT_THRESH);
        assertEquals(-18.43642, result.getEntry(2), MULT_THRESH);

        Vector q = new Vector("Test 3", 23, 55, 90, 8);
        thrown.expect(IllegalArgumentException.class);
        v.cross(q);
    }

    @Test
    public void testDot() {
        Vector v = new Vector("Test Vector 1", 1.5, 2.99, 3.6);
        Vector w = new Vector("Test Vector 2", 3.1, 2.6, 1.08);

        double dot = v.dot(w);

        assertEquals(16.312, dot, MULT_THRESH);

        Vector q = new Vector("Test 3", 1, 6, 4, 9);
        thrown.expect(IllegalArgumentException.class);
        v.dot(q);
    }

    @Test
    public void testEquals() {
        Vector v = new Vector("TestEquals", 1, 2, 3);
        Vector w = new Vector("TestEquals", 1, 2, 3);
        Vector s = new Vector("TestNotEquals", 6, 21, 3);

        assertEquals(true, v.equals(w));
        assertEquals(true, w.equals(v));
        assertEquals(false, v.equals(s));
    }

    @Test
    public void testHashCode() {
        Vector v = new Vector("TestEquals", 1, 2, 3);
        Vector w = new Vector("TestEquals", 1, 2, 3);

        assertEquals(v.hashCode(), w.hashCode());
    }
}
