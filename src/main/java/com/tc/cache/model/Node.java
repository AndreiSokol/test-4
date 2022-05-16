package com.tc.cache.model;

public class Node<T> implements IniNode<T> {
    private final T value;
    private final DoList<T> list;
    private IniNode<T> next;
    private IniNode<T> prev;

    public Node(T value, IniNode<T> next, DoList<T> list) {
        this.value = value;
        this.next = next;
        this.setPrev(next.getPrev());
        this.prev.setNext(this);
        this.next.setPrev(this);
        this.list = list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public T getElement() {
        return value;
    }

    public void detach() {
        this.prev.setNext(this.getNext());
        this.next.setPrev(this.getPrev());
    }

    @Override
    public DoList<T> getListReference() {
        return this.list;
    }

    @Override
    public IniNode<T> setPrev(IniNode<T> prev) {
        this.prev = prev;
        return this;
    }

    @Override
    public IniNode<T> setNext(IniNode<T> next) {
        this.next = next;
        return this;
    }

    @Override
    public IniNode<T> getPrev() {
        return this.prev;
    }

    @Override
    public IniNode<T> getNext() {
        return this.next;
    }

}
