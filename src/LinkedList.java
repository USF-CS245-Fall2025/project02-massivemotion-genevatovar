/**
 * Implementation of the List interface using a singly linked node structure containing nodes with next pointers
 * A LinkedList provides efficient insertion at head and has a dynamic size meaning there is no wasted memory from unused capacity
 * It can also grow as long as memory is available
 *
 * @param <T> Type of elements stored in the list
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * Node class for storing list elements
     * Each node contains data and a reference to the next node
     *
     * @param <T> the type of data store in this node
     */
    private class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructs a new node with the given data
         * Next reference initialized to null
         *
         * @param value the data to store in this node
         */
        public Node(T value) {
            data = value;
            next = null;
        }
    }

    /**
     * Constructs an empty linked list
     */
    public LinkedList() {
        size = 0;
        head = null;
    }

    /**
     * Adds an element at the given position in the list
     * Shifts the element at the given position (if it exists) and following elements to the right
     *
     * @param index the position where the element will be inserted
     * @param element the element to add
     * @throws Exception if index is out of bounds
     */
    @Override
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException("List index out of bounds"); }
        if (index == 0) {
            Node<T> newNode = new Node<>(element);
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        Node<T> prev = head;
        for (int i = 0; i < index - 1; i++) { prev = prev.next; }
        Node<T> newNode = new Node<>(element);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list
     * Traverses entire list to find the last node
     *
     * @param element the element to add
     * @return true
     */
    @Override
    public boolean add (T element) {
        if (size == 0) {
            head = new Node<>(element);
            size++;
            return true;
        } else {
            Node<T> node = head;
            while(node.next != null) { node = node.next; }
            Node <T> newLast = new Node<>(element);
            node.next = newLast;
            size++;
            return true;
        }
    }

    /**
     * Returns the element at the given position in the list
     * Traverses from the head to the given position
     *
     * @param index the position of the element to get
     * @return element that was retrieved from the list with the given index
     * @throws Exception if index is out of bounds
     */
    @Override
    public T get (int index) throws Exception {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException("List index out of bounds"); }
        Node<T> current = head;
        for (int i = 0; i < index; i++) { current = current.next;}
        return current.data;
    }

    /**
     * Removes and returns the element at the given position in the list
     * Shifts following elements to the left
     *
     * @param index the position of the element to remove
     * @return element that was removed from the list with the given index
     * @throws Exception if index is out of bounds
     */
    @Override
    public T remove(int index) throws Exception {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException("Index out of bounds"); }
        if (index == 0) {
            Node<T> node = head;
            head = head.next;
            size--;
            return node.data;
        }
        Node<T> prev = head;
        for (int i = 0; i < index - 1; i++) { prev = prev.next; }
        Node<T> current = prev.next;
        prev.next = current.next;
        size--;
        return current.data;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements in list
     */
    @Override
    public int size() {
        return size;
    }
}


// I used the LinkedList.java file in the course files to help me write this code: https://usfca.instructure.com/courses/1629342/files/74046396?module_item_id=18708737