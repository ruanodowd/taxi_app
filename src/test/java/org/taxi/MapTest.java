package org.taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void getLocationNodes() {
    }

    @Test
    void getLocation() {
    }

    @Test
    void countNodes() {
    }
    @Test
    void testDynamicMap() {
        Map testMap = new Map(3, 3);
        assertEquals(9,testMap.countNodes());
        testMap = new Map(5,5);
        assertEquals(25, testMap.countNodes());
    }
}