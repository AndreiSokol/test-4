package com.tc.cache.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cache<K, V> {
	
    private  K key;
    private  V value;
    
    public Cache(K key, V value) {
    	this.key = key;
    	this.value = value;
    }
    public V getValue() {
    	return this.value;
    }
    
    public K getKey() {
    	return this.key;
    }
}