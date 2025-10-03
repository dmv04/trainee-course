package hw1;

public class Key<K> {
    private final K keyValue;

    public Key(K keyValue) {
        this.keyValue = keyValue;
    }

    public K getKeyValue() {
        return keyValue;
    }

    @Override
    public int hashCode() {
        return keyValue != null ? keyValue.hashCode() : 0;
    }
}
