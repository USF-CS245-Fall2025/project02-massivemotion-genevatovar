/**
 * Implementation of the List interface using a doubly linked node structure containing nodes with next and prev pointers
 * A DoublyLinkedList can traverse backward and has more flexible algorithms allowing for implementation of more sophisticated operations
 * It also has easier element removal if there is a reference to a node
 *
 * @param <T> Type of elements stored in the list
 */
public class DoublyLinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * Node class for storing list elements
     * Each node contains data and a reference to the next and previous nodes
     *
     * @param <T> the type of data store in this node
     */
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Constructs a new node with the given data
         * Next and prev reference initialized to null
         *
         * @param value the data to store in this node
         */
        public Node(T value) {
            data = value;
            next = null;
            prev = null;
        }
    }

    /**
     * Constructs an empty doubly linked list
     */
    public DoublyLinkedList() {
        size = 0;
        head = null;
    }

    /**
     * Adds an element at the given position in the list
     * Updates forward and backward references
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
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            size++;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list
     * Maintains forward and backward references
     *
     * @param element the element to add
     * @return true
     */
    @Override
    public boolean add(T element) {
        if (size == 0) {
            head = new Node<>(element);
            size++;
            return true;
        } else {
            Node<T> node = head;
            while (node.next != null) {
                node = node.next;
            }
            Node<T> newLast = new Node<>(element);
            newLast.prev = node;
            node.next = newLast;
            size++;
            return true;
        }
    }

    /**
     * Returns the element at the given position in the list
     * Traverses forward from the head to the given position
     *
     * @param index the position of the element to get
     * @return element that was retrieved from the list with the given index
     * @throws Exception if index is out of bounds
     */
    @Override
    public T get(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Removes and returns the element at the given position in the list
     * Updates forward and backward references
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
        if (index == 0) {
            Node<T> node = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
            return node.data;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }
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
// I also used a YouTube video for help: https://www.youtube.com/watch?v=cYAZZt8GyUs