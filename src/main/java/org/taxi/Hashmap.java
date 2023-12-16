package org.taxi;

public class Hashmap<K, V> {
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] table;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public Hashmap() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int index = key.hashCode() % capacity;
        Entry<K, V> newEntry = new Entry<>(key, value, null);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = newEntry;
            }
        }
        size++;
    }

    public V get(K key) {
        int index = key.hashCode() % capacity;
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> headNode : table) {
            while (headNode != null) {
                Entry<K, V> next = headNode.next;
                int newIndex = headNode.key.hashCode() % newCapacity;
                headNode.next = newTable[newIndex];
                newTable[newIndex] = headNode;
                headNode = next;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }
}
