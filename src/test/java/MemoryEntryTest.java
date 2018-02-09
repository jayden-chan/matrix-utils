import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class MemoryEntryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetLabel() {
        MemoryEntry<String> mem = new MemoryEntry<>("Test 1", "Hello");

        assertEquals("Test 1", mem.getLabel());
    }

    @Test
    public void testGetData() {
        MemoryEntry<String> mem1 = new MemoryEntry<>("Test 1", "Hello");
        MemoryEntry<Double> mem2 = new MemoryEntry<>("Test 2", 3.1415);

        assertEquals("Hello", (String) mem1.getData());
        assertEquals(3.1415, (Double) mem2.getData(), 0);
    }

    @Test
    public void testSetData() {
        MemoryEntry<String> mem1 = new MemoryEntry<>("Test 1", "Hello");
        MemoryEntry<Double> mem2 = new MemoryEntry<>("Test 2", 3.1415);

        mem1.setData("Not Hello");
        mem2.setData(2.718);

        assertEquals("Not Hello", (String) mem1.getData());
        assertEquals(2.718, (Double) mem2.getData(), 0);
    }
}
