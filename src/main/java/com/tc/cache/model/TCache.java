package com.tc.cache.model;

import java.util.Optional;

// Cache Interface
public interface TCache<K, V> {
    
    // function that get Value by key in cache
    Optional<V> GetValue(K key);
    
	// function that add content to cache
    boolean AddContent(K key, V value);

}