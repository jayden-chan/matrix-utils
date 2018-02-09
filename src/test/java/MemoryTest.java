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

        thrown.expect(IllegalArgumentException.class);
        Memory.store("A", 90.0);
        thrown.expect(IllegalArgumentException.class);
        Memory.store("B", new Vector("", 6));
        thrown.expect(IllegalArgumentException.class);
        Memory.store("C", new Matrix("", 8, 8));
    }
}
