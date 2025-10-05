package hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private MyHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertNull(map.get("three"));
    }

    @Test
    void testRemove() {
        map.put("a", 1);
        map.put("b", 2);

        assertEquals(1, map.remove("a"));
        assertNull(map.get("a"));
        assertEquals(2, map.get("b"));
        assertNull(map.remove("c")); // remove non-existing
    }
}