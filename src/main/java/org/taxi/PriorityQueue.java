package org.taxi;

import java.util.Arrays;

public class PriorityQueue<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public PriorityQueue() {
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T element) {
        if (size >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        heap[size] = element;
        heapifyUp(size);
        size++;
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        T result = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        heapifyDown(0);
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapifyUp(int index) {
        T temp = heap[index];
        while (index > 0 && temp.compareTo(heap[parent(index)]) < 0) {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = temp;
    }

    private void heapifyDown(int index) {
        int child;
        T temp = heap[index];
        while (kthChild(index, 1) < size) {
            child = minChild(index);
            if (heap[child].compareTo(temp) < 0) {
                heap[index] = heap[child];
            } else {
                break;
            }
            index = child;
        }
        heap[index] = temp;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int kthChild(int i, int k) {
        return 2 * i + k;
    }

    private int minChild(int i) {
        int bestChild = kthChild(i, 1);
        int k = 2;
        int pos = kthChild(i, k);
        while ((k <= 2) && (pos < size)) {
            if (heap[pos].compareTo(heap[bestChild]) < 0) {
                bestChild = pos;
            }
            pos = kthChild(i, ++k);
        }    
        return bestChild;
    }
}
