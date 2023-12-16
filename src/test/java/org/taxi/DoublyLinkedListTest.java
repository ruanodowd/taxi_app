package org.taxi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testIsEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testAddAndSize() {
        assertTrue(list.add(1));
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        assertTrue(list.add(2));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveFromEmptyList() {
        assertFalse(list.remove(1)); // The remove method now returns a boolean
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        
        assertTrue(list.remove(2));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));

        assertTrue(list.remove(1));
        assertEquals(1, list.size());
        assertFalse(list.contains(1));

        assertTrue(list.remove(3));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveNonExistingElement() {
        list.add(1);
        assertFalse(list.remove(2)); // The remove method now returns a boolean
    }

    @Test
    public void testContains() {
        list.add(1);
        list.add(2);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(3));
    }

    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testToString() {
        list.add(1);
        list.add(2);
        list.add(3);

        String expected = "1 2 3";
        assertEquals(expected, list.toString());
    }
}