package com.tc.cache.model;

public interface IniNode<V> {

    boolean isEmpty();

    V getElement() throws NullPointerException;

    void detach();

    DoList<V> getListReference();

    IniNode<V> setPrev(IniNode<V> prev);

    IniNode<V> setNext(IniNode<V> next);

    IniNode<V> getPrev();

    IniNode<V> getNext();

}