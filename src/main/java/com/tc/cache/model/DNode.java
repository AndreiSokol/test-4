package com.tc.cache.model;

public class DNode<T> implements IniNode<T> {
    private final DoList<T> list;

    public DNode(DoList<T> list) {
        this.list = list;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public T getElement() throws NullPointerException {
        throw new NullPointerException();
    }

    @Override
    public void detach() {
    }

    @Override
    public DoList<T> getListReference() {
        return list;
    }

    @Override
    public IniNode<T> setPrev(IniNode<T> next) {
        return next;
    }

    @Override
    public IniNode<T> setNext(IniNode<T> prev) {
        return prev;
    }

    @Override
    public IniNode<T> getPrev() {
        return this;
    }

    @Override
    public IniNode<T> getNext() {
        return this;
    }

}
