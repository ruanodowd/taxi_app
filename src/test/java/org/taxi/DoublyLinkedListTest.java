package org.taxi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taxi.datastructure.DoublyLinkedList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        assertFalse(list.remove(2)); 
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
    @Test
    void testAdd() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertTrue(list.add(1));
        assertEquals(1, list.size());
    }


    @Test
    void testContainsOne() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        assertTrue(list.contains(1));
    }

    @Test
    void testClear() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testReverse() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.reverse();
        assertEquals(2, list.iterator().next());
    }

    @Test
    public void testContainsAll() {
    list.add(1);
    list.add(2);
    list.add(3);
    
    List<Integer> checkList = new ArrayList<>(Arrays.asList(1, 2));
    assertTrue(list.containsAll(checkList), "List should contain all elements from checkList");
    
    checkList = new ArrayList<>(Arrays.asList(1, 4));
    assertFalse(list.containsAll(checkList), "List should not contain all elements from checkList");
    }

    @Test
    public void testAddAll() {
        List<Integer> toAdd = new ArrayList<>(Arrays.asList(1, 2, 3));
        list.addAll(toAdd);
        assertEquals(3, list.size(), "All elements should be added to the list");
        assertTrue(list.containsAll(toAdd), "List should contain all added elements");
    }

    @Test
    public void testRemoveAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        
        List<Integer> toRemove = new ArrayList<>(Arrays.asList(1, 2));
        assertTrue(list.removeAll(toRemove), "List should change after removing elements");
        assertEquals(1, list.size(), "List should have one element after removing two");
        assertFalse(list.contains(1), "List should not contain removed element 1");
        assertFalse(list.contains(2), "List should not contain removed element 2");
    }

    @Test
    public void testRetainAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        
        List<Integer> toRetain = new ArrayList<>(Arrays.asList(1, 3));
        assertTrue(list.retainAll(toRetain), "List should change after retaining elements");
        assertEquals(2, list.size(), "List should have two elements after retaining");
        assertTrue(list.contains(1), "List should contain retained element 1");
        assertFalse(list.contains(2), "List should not contain non-retained element 2");
    }

    @Test
    public void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        
        Object[] array = list.toArray();
        assertArrayEquals(new Object[]{1, 2, 3}, array, "Array should contain all elements of the list");
    }

    @Test
    public void testToArrayWithType() {
        list.add(1);
        list.add(2);
        list.add(3);
        
        Integer[] array = new Integer[list.size()];
        array = list.toArray(array);
        assertArrayEquals(new Integer[]{1, 2, 3}, array, "Typed array should contain all elements of the list");
    }
}