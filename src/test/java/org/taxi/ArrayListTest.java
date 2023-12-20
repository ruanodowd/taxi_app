package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.taxi.datastructure.ArrayList;
import org.taxi.taxi.Taxi;
import org.junit.jupiter.api.Test;


public class ArrayListTest {
    private ArrayList<Taxi> arrayList;
    private Taxi t1;
    private Taxi t2;

    @BeforeEach 
    public void setup() {
        arrayList = new ArrayList<>();
        t1 = new Taxi("Rawr");
        t2 = new Taxi("Bree");
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

}
