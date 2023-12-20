package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.map.GridMap;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    void testDynamicMap() {
        GridMap testMap = new GridMap(3, 3);
        assertEquals(9,testMap.countNodes());
        testMap = new GridMap(5,5);
        assertEquals(25, testMap.countNodes());
    }
    @Test
    void testNodeLinkage(){
        GridMap testMap = new GridMap(3,3);
        AtomicInteger count = new AtomicInteger();
        testMap.getLocationNodes().stream()
                .forEach(l -> count.addAndGet(l.getNeighbouringLocations().size()));
        assertEquals(24, count.get());
    }
}