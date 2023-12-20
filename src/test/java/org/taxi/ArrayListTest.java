package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.taxi.datastructure.ArrayList;
import org.taxi.taxi.Taxi;
import org.junit.jupiter.api.Test;


public class ArrayListTest {
    private ArrayList<Taxi> arrayList;
    private ArrayList<Taxi> helperArray;
    private Taxi t1;
    private Taxi t2;
    private Taxi t3;

    @BeforeEach 
    public void setup() {
        arrayList = new ArrayList<>();
        t1 = new Taxi("Rawr");
        t2 = new Taxi("Bree");
        t3 = new Taxi("Rree");
        helperArray = new ArrayList<>();
        helperArray.add(t1);
        helperArray.add(t2);
        // helperArray.add(t3);
    }
    
    //constructor tests
    @Test 
    public void constructor() {
        int size = arrayList.size();
        assertEquals(0, size, "When initialising array list with no input list size should equal 0");
    }

    // addition tests
    @Test 
    public void addition() {
        arrayList.add(t1);
        arrayList.add(t2);

        assertEquals(2., arrayList.size(), "Addition is meant to increase the size of the array list");
    }

    @Test
    public void additionWithIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(1, t1));
        
        // valid input 
        arrayList.add(0,t1);
        Taxi taxi = arrayList.get(0);
        assertEquals(taxi, t1);
    }

    // removal tests
    @Test
    public void removeObject() {
        // non existing elements
        assertEquals(false, arrayList.remove(t1), "Returns false when removing an element that doesn't exist");

        // existing elements 
        arrayList.add(t1);
        assertEquals(true, arrayList.remove(t1), "Returns true when removing an existing element");
        assertEquals(0, arrayList.size());
    }

    @Test
    public void removeIndex() {
        // non existing indexes
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(0));

        // existing indexes
        arrayList.add(t1);
        assertEquals(t1, arrayList.remove(0));
    }

    // Access and search tests
    @Test
    public void getObject() {
        // valid index
        arrayList.add(t1);
        assertEquals(t1, arrayList.get(0));

        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(1));
    }

    @Test
    public void getIndexof() {
        // existing elements
        arrayList.add(t1);
        assertEquals(0, arrayList.indexOf(t1));

        // non existing elements 
        assertEquals(-1, arrayList.indexOf(t2));
    }

    // test clear 
    @Test
    public void clear() {
        arrayList.add(t1);
        assertEquals(1, arrayList.size());

        arrayList.clear();
        assertEquals(0, arrayList.size());
        assertEquals(true, arrayList.isEmpty());
    }

    // test set 
    @Test 
    public void set(){
        arrayList.add(t1);
        Taxi oldTaxi = arrayList.set(0, t2);

        assertEquals(t1, oldTaxi);
        assertEquals(t2, arrayList.get(0));
    }

    // test collections
    @Test
    public void containsAll(){
        arrayList.add(t1);
        arrayList.add(t2);
        arrayList.add(t3);
        assertTrue(arrayList.containsAll(helperArray));

        assertFalse(helperArray.containsAll(arrayList));
    }

    // test add all 
    @Test
    public void addAll() {
        // collection of objects 
        arrayList.addAll(helperArray);
        assertEquals(2, arrayList.size());

        // collection at index
        assertTrue(arrayList.addAll(0, helperArray));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.addAll(5, helperArray));
    }

    // test unsupported methods 
    @Test
    public void testUnsuported() {
        assertThrows(I, null)
    }





}
