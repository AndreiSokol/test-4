package com.tc.cache.model;

import com.tc.cache.model.ATLNode;

// TcNode Class
public class TcNode<T> implements ATLNode<T> {
	
	// members
    private final T value;
    private final TcDLinkedList<T> list;
    private ATLNode<T> next;
    private ATLNode<T> prev;
    
    // constructor
    public TcNode(T value, ATLNode<T> next, TcDLinkedList<T> list) {
        this.value = value;
        this.next = next;
        this.SetPrevNode(next.GetNextNode());
        this.prev.SetNextNode(this);
        this.next.SetNextNode(this);
        this.list = list;
    }
    
    // function that check if IsEmtpy
    @Override
    public boolean IsEmpty() {
        return this.list.size() == 0 ? true : false;
    }

    public T getElement() {
        return value;
    }
    
    // function that detach 
	@Override
	public void Detach() {
        this.prev.SetNextNode(this.GetNextNode());
        this.next.SetPrevNode(this.GetPrevNode());	
	}
	
	// function that get all Doubly LinkedList
	@Override
	public TcDLinkedList<T> GetTDLList() {
		return this.list;
	}
	
	// function that set previous node
	@Override
	public ATLNode<T> SetPrevNode(ATLNode<T> prev) {
        this.prev = prev;
        return this;
	}
	
	// function that set next node
	@Override
	public ATLNode<T> SetNextNode(ATLNode<T> next) {
        this.next = next;
        return this;
	}
	
	// function that get previous node
	@Override
	public ATLNode<T> GetPrevNode() {
		return this.prev;
	}
	
	// function that get next node
	@Override
	public ATLNode<T> GetNextNode() {
		return this.next;
	}
	
	// function that get value
	@Override
	public T GetValue() throws NullPointerException {		
		return this.value;
	}
}
