import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class MemoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testStore() {
        Memory.store("A", 9.0);
        Memory.store("B", new Vector("", 1.0, 2.0, 3.0));
        Memory.store("C", new Matrix("", 5, 5));
    }

    @Test
    public void testRetrieveType() {
        Memory.store("D", 9.0);
        Memory.store("E", new Vector("", 1.0, 2.0, 3.0));
        Memory.store("F", new Matrix("", 5, 5));

        assertEquals("CONSTANT", Memory.retrieveType("D").toString());
        assertEquals("VECTOR", Memory.retrieveType("E").toString());
        assertEquals("MATRIX", Memory.retrieveType("F").toString());
        assertEquals("NULL", Memory.retrieveType("K").toString());

        Memory.free("D");
        Memory.free("E");
        Memory.free("F");
    }

    @Test
    public void testFree() {
        Memory.free("A");
        Memory.free("B");
        Memory.free("C");

        Memory.store("A", 9.0);
        Memory.store("B", new Vector("", 1.0, 2.0, 3.0));
        Memory.store("C", new Matrix("", 5, 5));
    }

    @Test
    public void testRetrieve() {
        double a = Memory.retrieveConstant("A");
        Vector b = Memory.retrieveVector("B");
        Matrix c = Memory.retrieveMatrix("C");

        assertEquals(9.0, a, 1e-4);
        assertEquals(new Vector("", 1.0, 2.0, 3.0), b);
        assertEquals(new Matrix("", 5, 5), c);
    }
}
