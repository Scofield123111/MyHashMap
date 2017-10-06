package main;

import java.util.*;

public class MyHashMap<K, V> extends AbstractMap<K, V> {
    private static final int SIZE = 131;//bucket size

    @SuppressWarnings("unchecked")
    LinkedList<Node<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        for (Node<K, V> pair : buckets[index]
                ) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;

        if (buckets[index] == null) {
            buckets[index] = new LinkedList<Node<K, V>>();
        }
        LinkedList<Node<K, V>> ll = buckets[index];
        ListIterator<Node<K, V>> it = ll.listIterator();
        boolean found = false;

        while (it.hasNext()) {
            Node<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = value;
                iPair.setValue(value);
                found = true;
                break;
            }
        }

        if (!found) {
            buckets[index].add(new Node<>(key, value));
        }

        return oldValue;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Node<K, V>> ll : buckets
                ) {
            if (ll == null) {
                continue;
            }
            set.addAll(ll);
        }
        return set;
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (!key.equals(node.key)) return false;
            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
