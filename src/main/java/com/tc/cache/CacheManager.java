package com.tc.cache;

import com.tc.cache.model.CacheE;
import com.tc.cache.model.DoList;
import com.tc.cache.model.IniNode;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheManager<K, V> {

    private final int size;
    private final Map<K, IniNode<CacheE<K, V>>> linkedListNodeMap;
    private final DoList<CacheE<K, V>> doublyLinkedList;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public CacheManager(int size) {
        this.size = size;
        this.linkedListNodeMap = new ConcurrentHashMap<>(size);
        this.doublyLinkedList = new DoList<>();
    }

    public boolean add(K key, V value) {
        lock.writeLock().lock();
        try {
            if (size == 0) {
                return false;
            }

			CacheE<K, V> item = new CacheE<K, V>(key, value);
            IniNode<CacheE<K, V>> newLinkedListNode;
            if (linkedListNodeMap.containsKey(key)) {
                IniNode<CacheE<K, V>> linkedListNode = linkedListNodeMap.get(key);
                newLinkedListNode = doublyLinkedList.updateAndMoveToFront(linkedListNode, item);
            } else {
                if (doublyLinkedList.size() >= size) {
                    evictElement();
                }
                newLinkedListNode = doublyLinkedList.add(item);
            }
            linkedListNodeMap.put(key, newLinkedListNode);
        } finally {
            lock.writeLock().unlock();
        }
        return true;
    }

    public Optional<V> get(K key) {
        lock.readLock().lock();
        try {
            IniNode<CacheE<K, V>> linkedListNode = linkedListNodeMap.get(key);
            if (linkedListNode != null) {
                linkedListNodeMap.put(key, doublyLinkedList.moveToFront(linkedListNode));
                return Optional.of(linkedListNode.getElement().getValue());
            }
        } finally {
            lock.readLock().unlock();
        }
        return Optional.empty();
    }

    private void evictElement() {
        lock.writeLock().lock();
        try {
            IniNode<CacheE<K, V>> linkedListNode = doublyLinkedList.removeTail();
            linkedListNodeMap.remove(linkedListNode.getElement().getKey());
        } finally {
            lock.writeLock().unlock();
        }
    }

}
