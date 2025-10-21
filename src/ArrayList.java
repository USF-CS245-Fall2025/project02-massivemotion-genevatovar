/**
 * Implementation of the List interface using a resizable array
 * An arraylist provides a dynamic array implementation (its size can grow or shrink as elements are added or removed)
 * which can be beneficial when the number of elements is unknown at compile time
 * It also maintains the insertion order of elements and allows for efficient random access to elements using their index
 *
 * @param <T> Type of elements stored in the list
 */
public class ArrayList<T> implements List<T> {
    T[] arr;
    int size;

    /**
     * Constructs an empty list with an intial capacity of 10
     * The capacity will grow as needed
     */
    public ArrayList() {
        arr = (T[]) new Object[10]; // initial capacity of 10
        size = 0;
    }

    /**
     * Doubles the capacity of the internal array when more space is needed/the array reaches capacity
     */
    protected void grow_array() {
        T[] new_arr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) { new_arr[i] = arr[i]; }
        arr = new_arr;
    }

    /**
     * Adds an element at the given position in the list
     * Shifts the element at the given position (if it exists) and following elements to the right
     * Grows the array if needed
     *
     * @param index the position where the element will be inserted
     * @param element the element to add
     * @throws Exception if index is out of bounds
     */
    @Override
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException("List index out of bounds"); }
        if (size == arr.length) { grow_array(); }
        for (int i = size; i > index; i--) {arr[i] = arr[i - 1];}
        arr[index] = element;
        size++;
    }

    /**
     * Adds an element to the end of the list
     * Grows the array if needed
     *
     * @param element the element to add
     * @return true
     */
    @Override
    public boolean add(T element) {
        if (size == arr.length) { grow_array(); }
        arr[size++] = element;
        return true;
    }

    /**
     * Returns the element at the given position in the list
     * Has constant-time
     *
     * @param index the position of the element to get
     * @return element that was retrieved from the list with the given index
     * @throws Exception if index is out of bounds
     */
    @Override
    public T get (int index) throws Exception {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException("List index out of bounds"); }
        return arr[index];
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
    public T remove (int index) throws Exception {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException("List index out of bounds"); }
        T copy = arr[index];
        for (int i = index; i < size - 1; i ++) { arr[i] = arr[i + 1]; }
        size--;
        return copy;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements in list
     */
    @Override
    public int size () {
        return size;
    }
}

// I used the ArrayList.java file in the course files to help me write this code: https://usfca.instructure.com/courses/1629342/files/74046395?module_item_id=18708732