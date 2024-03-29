import java.util.Objects;

public class Pair<K, V> {
    private K key;

    public K getKey() {
        return key;
    }

    private V value;

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Pair(K key, V value) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (key != null ? key.hashCode() : 0);
        hash = 31 * hash + (value != null ? value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (!Objects.equals(key, pair.key)) return false;
            if (!Objects.equals(value, pair.value)) return false;
            return true;
        }
        return false;
    }
}
