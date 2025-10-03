package hw1;

import java.util.Arrays;
import java.util.Objects;


public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    private Node<K, V>[] array = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];

    private int size = 0;

    public void put(K key, V value) {
        if (checkResize()) {
            resize();
        }

        Key<K> keyClass = new Key<>(key);
        int hashCode = keyClass.hashCode();
        int index = (array.length - 1) & hashCode;

        if (index >= array.length) {
            throw new IllegalStateException("Array index out of bounds");
        }

        array[index] = new Node<>(key, value, null);
    }

    public V remove(K key) {
        Key<K> keyClass = new Key<>(key);
        int hashCode = keyClass.hashCode();
        int index = (array.length - 1) & hashCode;

        if (index >= array.length || array[index] == null) {
            return null;
        }

        Node<K, V> current = array[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    array[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }

    public V get(K key) {
        if (checkResize()) {
            resize();
        }

        Key<K> keyClass = new Key<>(key);
        int hashCode = keyClass.hashCode();
        int index = (array.length - 1) & hashCode;

        if (index >= array.length) {
            return null;
        }

        return array[index].getValue();

    }

    private boolean checkResize() {
        long nonNullCount = Arrays.stream(array)
                .filter(Objects::nonNull)
                .count();
        return (float) nonNullCount / array.length >= DEFAULT_LOAD_FACTOR;
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        Node<K, V>[] newArray = (Node<K, V>[]) new Node[array.length * 2];

        System.arraycopy(array, 0, newArray, 0, array.length);

        array = newArray;
    }
}
