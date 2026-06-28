public class DynamicArray {
    private int[] arr;       // Internal array to store elements
    private int size;        // Number of elements currently in the array
    private int capacity;    // Total capacity of the array

    // Constructor: O(1)
    public DynamicArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.size = 0;
    }

    // Get element at index i: O(1)
    public int get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return arr[i];
    }

    // Set element at index i: O(1)
    public void set(int i, int n) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        arr[i] = n;
    }

    // Add element to the end: Amortized O(1), Worst-case O(n) when resizing
    public void pushback(int n) {
        if (size == capacity) {
            resize(); // Double the capacity if array is full
        }
        arr[size] = n;
        size++;
    }

    // Remove and return last element: O(1)
    public int popback() {
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        size--;
        return arr[size];
    }

    // Double the capacity: O(n)
    private void resize() {
        capacity *= 2;
        int[] newArr = new int[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // Return current number of elements: O(1)
    public int getSize() {
        return size;
    }

    // Return current capacity: O(1)
    public int getCapacity() {
        return capacity;
    }
}
