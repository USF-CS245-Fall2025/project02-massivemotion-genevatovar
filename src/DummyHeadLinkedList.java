/**
 * Implementation of the List interface using a singly linked node structure with a dummy head node
 * A dummy head node simplifies algorithms by eliminating the need to check for an empty list
 * or special handling for the first element.
 *
 * @param <T> Type of elements stored in the list
 */
public class DummyHeadLinkedList<T> implements List<T> {
    private Node<T> dummyHead;
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
     * Constructs an empty linked list with a dummy head node
     * The dummy head holds null data
     */
    public DummyHeadLinkedList() {
        size = 0;
        dummyHead = new Node<>(null);
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
        Node<T> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> newNode = new Node<>(element);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element the element to add
     * @return true
     */
    @Override
    public boolean add (T element) {
        Node<T> current = dummyHead;
        while (current.next != null) {
            current = current.next;
        }
        Node<T> newNode = new Node<>(element);
        current.next = newNode;
        size++;
        return true;
    }

    /**
     * Returns the element at the given position in the list
     *
     * @param index the position of the element to get
     * @return element that was retrieved from the list with the given index
     * @throws Exception if index is out of bounds
     */
    @Override
    public T get (int index) throws Exception {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException("List index out of bounds"); }
        Node<T> current = dummyHead.next;
        for (int i = 0; i < index; i++) { current = current.next; }
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> remove = prev.next;
        prev.next = remove.next;
        size--;
        return remove.data;
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
// I also used YouTube videos for help: https://www.youtube.com/watch?v=-Cjgt5I0YvM&pp=ygUgd3JpdGluZyBhICBkdW1teSBoZWFkIGxpbmtlZGxpc3Q%3D and https://www.youtube.com/watch?v=aloIxnZ4EvY