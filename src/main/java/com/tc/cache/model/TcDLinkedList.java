package com.tc.cache.model;

import com.tc.cache.model.ATLNode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// class for Doubly LinkedList
public class TcDLinkedList<T> {
	
	// members
    private final TcDNode<T> dNode;
    private ATLNode<T> headNode;
    private ATLNode<T> tailNode;
    private AtomicInteger size;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    // constructor
    public TcDLinkedList() {
        this.dNode = new TcDNode<>(this);
    }
    
    // function that get size of this list
    public int size() {
        lock.readLock().lock();
        try {
            return size.get();
        } finally {
            lock.readLock().unlock();
        }
    }
}
