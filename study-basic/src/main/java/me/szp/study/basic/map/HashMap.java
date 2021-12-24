package me.szp.study.basic.map;

/**
 * @author sunzp
 * @since 2021/12/8 4:55 下午
 */
public class HashMap<K, V> implements Map<K, V> {

    private Object[] entries;

    public HashMap() {
        this(10);
    }

    public HashMap(int initSize) {
        entries = new Entry[initSize];
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() % entries.length;
        if (index > entries.length - 1 || index < 0) {
            return null;
        }
        return getValue((Entry<K, V>) entries[index], key);
    }


    private V getValue(Entry<K, V> entry, K key) {
        if (entry == null) {
            return null;
        }
        Entry<K, V> temp = entry;
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int index = key.hashCode() % entries.length;
        if (index > entries.length - 1) {
            resize();
        }
        Entry<K, V> entry = (Entry<K, V>) entries[index];

        if (entry == null) {
            entry = new Entry<>();
            entry.setKey(key);
            entry.setValue(value);
            entries[index] = entry;
            return;
        }

        while (entry.next != null) {
            entry = entry.next;
        }

        Entry<K, V> newEntry = new Entry<>();
        newEntry.setKey(key);
        newEntry.setValue(value);
        entry.setNext(newEntry);
    }

    @Override
    public boolean remove(K key) {
        return false;
    }


    private void resize() {
        int newSize = entries.length * 2;
        Object[] newEntries = new Object[newSize];
        System.arraycopy(entries, 0, newEntries, 0, entries.length);
        entries = newEntries;
    }


    public static class Entry<K, V> {

        private K key;

        private V value;

        private Entry<K, V> next;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
