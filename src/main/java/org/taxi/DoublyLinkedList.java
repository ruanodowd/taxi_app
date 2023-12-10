package org.taxi;

public class DoublyLinkedList<T> {
    //implements Collection, Iterator
    private Node head;
    private Node current;

    private class Node {
        T data;
        Node next;
        Node prev;
    
        public Node (T data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    public DoublyLinkedList() {
        head = null;
        current = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        if (!isEmpty() && current.next != null) {
            current = current.next;
        }
    }

    public boolean isLast() {
        return !isEmpty() && current.next != null;
    }

    public T retrieve() {
        if (isEmpty()) {
            System.out.println("List is empty, null will be returned");
            return null;
        } else {
            return current.data;
        }
    }

    public void update(T e) {
        if (!isEmpty())
            current.data = e;
        else
            System.out.println("List is empty, nothing to update");
    }

    public void add(T e) {
        Node newNode = new Node(e);
       
        if(isEmpty()) {
            head = current = newNode;
        } else {
            newNode.next = current.next;
            newNode.prev = current;
            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;
            current = newNode;
        }
    }

    public void remove(T e) {
        if (!isEmpty()) {

            boolean isFound = false;
            // set pointer at first location
            findFirst();

            // if current is not set to the last location
            while (!isLast()) {
                // if current contains element, continue with current set to that. 
                if (current.data.equals(e)) {
                    isFound = true;
                    break;
                }

                else {
                    findNext();
                }
            }

            if (!isFound) {
                System.out.println("Element doesn't exist");
            }

            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }
           
            if (current.next != null) {
                current.next.prev = current.prev;
            }
           
            current = current.prev;
        } else {
            System.out.println("List is empty, nothing to remove");
        }
    }

    public boolean contains (T e) {
        if (!isEmpty()) {
            //set to start 
            findFirst();

            // check if contains element 
            if (current.data.equals(e)) {
                return true;
            } else {
                findNext();
            }

        }
        return false;
    }
}
