package hw1;

import java.util.Objects;


public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    private Node<K, V>[] array = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];

    private int size = 0;

    class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }


    public void put(K key, V value) {
        if (checkResize()) {
            resize();
        }

        int index = getIndex(key);

        Node<K, V> current = array[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        array[index] = new Node<>(key, value, array[index]);
        size++;
    }

    public V remove(K key) {
        int index = getIndex(key);

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
        int index = getIndex(key);

        Node<K, V> current = array[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    private boolean checkResize() {
        return (float) size / array.length >= DEFAULT_LOAD_FACTOR;
    }

    private void resize() {
        Node<K, V>[] oldArray = array;
        int newCapacity = oldArray.length * 2;
        //noinspection unchecked
        array = (Node<K, V>[]) new Node[newCapacity];
        size = 0;

        for (Node<K, V> node : oldArray) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int getIndex(K key) {
        int hash = key == null ? 0 : key.hashCode();
        return (array.length - 1) & hash;
    }
}
