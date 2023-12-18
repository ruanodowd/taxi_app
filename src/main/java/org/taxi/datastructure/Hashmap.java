package org.taxi.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Hashmap<K, V> implements Iterable<Hashmap.Entry<K, V>>{

    // creates each entry node
    public static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // create the array element
    private final int SIZE = 5;
    private Entry<K,V> table[];

    // create constructor 
    @SuppressWarnings("unchecked")
    public Hashmap() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        // doesn't check for null for now
        int hash = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> e = table[hash];

        // if nothing at position set postion equal to new value
        // else 
            // check for existing key and update, then return
            // if no entry found then add new value 

        if (e == null) {
            table[hash] = new Entry<K,V>(key, value);
        } else {
            // check for everything other than last
            while (!(e.next == null)) {
                if (e.key.equals(key)) {
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }

            // check for last
            if (e.key.equals(key)) {
                e.setValue(value);
                return;
            }

            // add new entry
            Entry<K,V> newValue = new Entry<K,V>(key, value);
            e.next = newValue;
        }
    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode()) % SIZE;
        Entry<K,V> e = table[hash];

        // check if values are empty 
        if (e == null) {
            return null;
        }

        // everything
        while (!(e == null)) {
            if (e.getKey().equals(key)) {
                return e.getValue();
            }
            e = e.next;
        }

        // not found now
        return null;
    }

    public void remove(K key) {
        int hash = Math.abs(key.hashCode()) % SIZE;
        Entry<K,V> e = table[hash];

        // if list is empty 
        if (e == null) {
            return;
        }

        // if node to be removed is at the top
        if (e.key.equals(key)) {
            table[hash] = e.next;
        }

        Entry<K,V> prev = e;
        e = e.next;

        while (e != null) {
            if (e.key.equals(key)) {
                prev.next = e.next;
                return;
            }
            prev = e;
            e = e.next;
        }

        return;
    }

    // Implementing an iterator
    @Override
    public Iterator<Entry<K,V>> iterator() {
        return new HashmapIterator();
    }

    private class HashmapIterator implements Iterator<Entry<K,V>> {
        private int currentIndex = 0;
        private Entry<K,V> currentEntry = null;
        
        public HashmapIterator() {
            advanceToNextEntry();
        }

        @Override
        public boolean hasNext() {
            return currentEntry != null;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Entry<K, V> entryToReturn = currentEntry;
            advanceToNextEntry();
            return entryToReturn;
        }

        private void advanceToNextEntry() {
            // Continue from current entry if possible
            if (currentEntry != null && currentEntry.next != null) {
                currentEntry = currentEntry.next;
                return;
            }

            // Move to the next bucket and find a non-null entry
            while (currentIndex < SIZE) {
                currentEntry = table[currentIndex++];
                if (currentEntry != null) {
                    return;
                }
            }

            // No more entries
            currentEntry = null;
        }
        
    }

    // added the stream features
    public Stream<V> stream() {
        return StreamSupport.stream(this.spliterator(), false)
                            .map(Entry::getValue);
    }

}