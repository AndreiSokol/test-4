package com.tc.cache.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DoList<T> {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final DNode<T> dummyNode;
    private IniNode<T> head;
    private IniNode<T> tail;
    private AtomicInteger size;

    public DoList() {
        this.dummyNode = new DNode<>(this);
        clear();
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            head = dummyNode;
            tail = dummyNode;
            size = new AtomicInteger(0);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return size.get();
        } finally {
            lock.readLock().unlock();
        }
    }

    public IniNode<T> add(T value) {
        lock.writeLock().lock();
        try {
            head = new Node<>(value, head, this);
            if (tail.isEmpty()) {
                tail = head;
            }
            size.incrementAndGet();
        } finally {
            lock.writeLock().unlock();
        }
        return head;
    }

    public IniNode<T> removeTail() {
        lock.writeLock().lock();
        IniNode<T> oldTail = tail;
        try {
            if (oldTail == head) {
                tail = head = dummyNode;
            } else {
                tail = tail.getPrev();
                oldTail.detach();
            }
            if (!oldTail.isEmpty()) {
                size.decrementAndGet();
            }
        } finally {
            lock.writeLock().unlock();
        }
        return oldTail;
    }

    public IniNode<T> moveToFront(IniNode<T> node) {
        return node.isEmpty() ? dummyNode : updateAndMoveToFront(node, node.getElement());
    }

    public IniNode<T> updateAndMoveToFront(IniNode<T> node, T newValue) {
        lock.writeLock().lock();
        try {
            if (node.isEmpty() || (this != (node.getListReference()))) {
                return dummyNode;
            }
            detach(node);
            add(newValue);
        } finally {
            lock.writeLock().unlock();
        }
        return head;
    }

    private void detach(IniNode<T> node) {
        if (node != tail) {
            node.detach();
            if (node == head) {
                head = head.getNext();
            }
            size.decrementAndGet();
        } else {
            removeTail();
        }
    }

}
