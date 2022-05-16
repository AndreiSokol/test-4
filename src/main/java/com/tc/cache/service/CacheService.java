package com.tc.cache.service;

import com.tc.cache.CacheManager;
import com.tc.cache.lru.api.CacheApiDelegate;
import com.tc.cache.lru.model.CacheData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
/*
 * Author Ming-Top
 */
@Service
public class CacheService implements CacheApiDelegate {

    @Autowired
    private CacheManager<Integer, Object> cacheManager;

    @Override
    public ResponseEntity<Object> cacheIdGet(final Integer id) {
        return new ResponseEntity<>(cacheManager.get(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> cachePut(final CacheData cacheData) {
    	cacheManager.add(cacheData.getId(), cacheData.getData());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
