package com.tc.cache.model;

import com.tc.cache.model.TcDLinkedList;

// Interface for LinkedList Node
public interface ATLNode<V> {

    void Detach();
    TcDLinkedList<V> GetTDLList();
    ATLNode<V> SetPrevNode(ATLNode<V> prev);
    ATLNode<V> SetNextNode(ATLNode<V> next);
    ATLNode<V> GetPrevNode();
    ATLNode<V> GetNextNode();
    boolean IsEmpty();
    V GetValue() throws NullPointerException;

}