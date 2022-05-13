package com.tc.cache.model;

import com.tc.cache.model.ATLNode;

public class TcDNode<T> implements ATLNode<T> {
	
    private final TcDLinkedList<T> list;
    public TcDNode(TcDLinkedList<T> list) {
        this.list = list;
    }

    @Override
    public boolean IsEmpty() {
        return this.list.size() == 0 ? true : false;
    }

    @Override
    public T GetValue() throws NullPointerException {
        throw new NullPointerException();
    }

    @Override
    public TcDLinkedList<T> GetTDLList() {
        return list;
    }

    @Override
    public ATLNode<T> SetPrevNode(ATLNode<T> next) {
        return next;
    }

    @Override
    public ATLNode<T> SetNextNode(ATLNode<T> prev) {
        return prev;
    }

    @Override
    public ATLNode<T> GetPrevNode() {
        return this;
    }

    @Override
    public ATLNode<T> GetNextNode() {
        return this;
    }

	@Override
	public void Detach() {
		// TODO Auto-generated method stub
		
	}

}
