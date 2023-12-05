package org.taxi;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

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
        Map testMap = new Map();
        assertEquals(4, testMap.countNodes());
    }
    @Test
    void testDynamicMap() {
        Map testMap = new Map(3, 3);
        assertEquals(9,testMap.countNodes());
        testMap = new Map(5,5);
        assertEquals(25, testMap.countNodes());
    }
    @Test
    void testNodeLinkage(){
        Map testMap = new Map(3,3);
        AtomicInteger count = new AtomicInteger();
        testMap.getLocationNodes().stream()
                .forEach(l -> count.addAndGet(l.getNeighbouringLocations().size()));
        assertEquals(24, count.get());
    }
}