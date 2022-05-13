package com.tc.cache.model;

import java.util.Optional;

// TcCache Class implemented TCache Interface
public class TcCache<K, V> implements TCache<K, V> {
	
	// members
    private  K key;
    private  V value;
    
    // constructor
    public TcCache(K key, V value) {
    	this.key = key;
    	this.value = value;
    }
    // getter for value
    public V getValue() {
    	return this.value;
    }
    // getter for key
    public K getKey() {
    	return this.key;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public Optional<V> GetValue(K key) {
		return (Optional<V>) this.value;
	}
	@Override
	public boolean AddContent(K key, V value) {
		this.key = key;
		this.value = value;
		return true;
	}
}