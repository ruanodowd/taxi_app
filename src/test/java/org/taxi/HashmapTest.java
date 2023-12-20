package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.datastructure.Hashmap;

import static org.junit.jupiter.api.Assertions.*;

public class HashmapTest {

    @Test
    public void testPutAndGet() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        assertEquals(1, map.get("key1"));
        assertEquals(2, map.get("key2"));
        assertEquals(3, map.get("key3"));
        assertNull(map.get("key4")); // key4 does not exist
    }

    @Test
    public void testUpdateValue() {
        Hashmap<String, String> map = new Hashmap<>();
        map.put("name", "Alice");
        assertEquals("Alice", map.get("name"));

        // Update the value for key "name"
        map.put("name", "Bob");
        assertEquals("Bob", map.get("name"));
    }

    @Test
    public void testCollision() {
        // Assuming the hash code for these keys results in a collision
        Hashmap<MyKey, Integer> map = new Hashmap<>();
        MyKey key1 = new MyKey("key", 1);
        MyKey key2 = new MyKey("key", 2);

        map.put(key1, 1);
        map.put(key2, 2);

        assertEquals(1, map.get(key1));
        assertEquals(2, map.get(key2));
    }

    // Helper class to simulate hash collisions
    static class MyKey {
        String key;
        int id;

        public MyKey(String key, int id) {
            this.key = key;
            this.id = id;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MyKey myKey = (MyKey) obj;
            return id == myKey.id && key.equals(myKey.key);
        }
    }

    @Test
    public void testResizing() {
        Hashmap<String, Integer> map = new Hashmap<>();
        // Fill the map to trigger resising
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }

        // Check if all values are correctly retrieved after resising
        for (int i = 0; i < 20; i++) {
            assertEquals(i, map.get("key" + i));
        }
    }

    @Test
    public void testRemoveExistingKey() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        // Remove an existing key
        map.remove("key2");
        assertNull(map.get("key2"), "Removing 'key2' should result in it not being found");
        assertNotNull(map.get("key1"), "'key1' should still be present in the map");
        assertNotNull(map.get("key3"), "'key3' should still be present in the map");
    }

    @Test
    public void testRemoveNonExistingKey() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("key1", 1);

        // Try to remove a key that does not exist
        map.remove("key2");
        assertNotNull(map.get("key1"), "Removing a non-existing key should not affect other entries");
    }

    @Test
    public void testRemoveFromEmptyMap() {
        Hashmap<String, Integer> map = new Hashmap<>();

        // Attempt to remove a key from an empty map
        map.remove("key1");
        assertNull(map.get("key1"), "Removing a key from an empty map should not cause errors");
    }

    @Test
    public void testRemoveCollision() {
        Hashmap<MyKey, Integer> map = new Hashmap<>();
        MyKey key1 = new MyKey("key", 1);
        MyKey key2 = new MyKey("key", 2);

        map.put(key1, 1);
        map.put(key2, 2);

        // Remove one of the colliding keys
        map.remove(key1);
        assertNull(map.get(key1), "key1 should be removed from the map");
        assertEquals(2, map.get(key2), "key2 should still exist in the map");
    }

}