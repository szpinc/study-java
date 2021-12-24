package me.szp.study.basic.map;

/**
 * @author sunzp
 * @since 2021/12/8 4:56 下午
 */
public interface Map<K, V> {

    V get(K key);

    void put(K key, V value);

    boolean remove(K key);
}
