package exercise;
import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SafetyList() {
        elements = new int[INITIAL_CAPACITY];
        size = 0;
    }

    public synchronized void add(int element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    public int getSize() {
        return size;
    }

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }
    // END
}
