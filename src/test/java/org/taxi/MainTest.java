package org.taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {

    @Test
    void createDynamicMap() {
        Map testMap = new Map(3, 3);
        assertEquals(9,testMap.countNodes());
        testMap = new Map(5,5);
        assertEquals(25, testMap.countNodes());
    }

    @Test
    void compareDynamicCreatorToSet2x2MapCreator(){
        Map test2x2Map = new Map();
        Map testDynamic2x2Map = new Map(2,2);
        assertEquals(true, test2x2Map.equals(testDynamic2x2Map));

    }
    @Test
    void testNodeLinkage(){

    }
}