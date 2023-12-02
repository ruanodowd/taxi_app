package org.taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.taxi.Map.create2x2Map;


class MainTest {

    @Test
    void create2x2MapTest() {
        Map testMap = create2x2Map();
        assertEquals(4, testMap.countNodes());
    }

    @Test
    void createDynamicMap() {
        Map testMap = new Map(3, 3);
        assertEquals(9,testMap.countNodes());
        testMap = new Map(5,5);
        assertEquals(25, testMap.countNodes());

    }
}