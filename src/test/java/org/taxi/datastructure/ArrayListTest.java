package org.taxi.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    @Test
    void testAdd() {
        ArrayList<Integer> list = new ArrayList<>();
        assertTrue(list.add(1));
        assertEquals(1, list.size());
    }

    @Test
    void testGet() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    void testRemoveObject() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        assertTrue(list.remove(Integer.valueOf(1)));
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals(1, list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    void testContains() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        assertTrue(list.contains(1));
    }
}