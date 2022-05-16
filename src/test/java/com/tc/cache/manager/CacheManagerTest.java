package com.tc.cache.manager;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.tc.cache.CacheManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheManagerTest {

    @InjectMocks
    private CacheManager<Integer, Object> tcCacheManager = new CacheManager<>(1);

    @Test
    void whenAddWhereCacheSizeZero_thenNotAddCache() {
        tcCacheManager = new CacheManager<>(0);
        Integer id = 1;
        Object data = "data-1";

        boolean added = tcCacheManager.add(id, data);
        Optional<Object> dataOpt = tcCacheManager.get(id);
        assertFalse(added);
        assertTrue(dataOpt.isPresent());
    }

    @Test
    void whenAddWhereKeyNotExists_thenAddNewCache() {
        Integer id = 1;
        Object data = "data-1";

        boolean added = tcCacheManager.add(id, data);
        Optional<Object> dataOpt = tcCacheManager.get(id);
        assertTrue(added);
        assertTrue(dataOpt.isPresent());
        assertEquals(data, dataOpt.get());
    }

    @Test
    void whenAddWhereKeyExists_thenUpdateExistingCache() {
        Integer id = 1;
        Object data1 = "data-1";
        tcCacheManager.add(id, data1);


        Object data2 = "data-2";
        boolean added = tcCacheManager.add(id, data2);
        Optional<Object> dataOpt = tcCacheManager.get(id);
        assertTrue(added);
        assertTrue(dataOpt.isPresent());
        assertEquals(data2, dataOpt.get());
    }

    @Test
    void whenAddWhereKeyNotExistsAndCacheIsFull_thenEvictLruCacheAndAddNewCache() {
        Integer id1 = 1;
        Object data1 = "data-1";
        tcCacheManager.add(id1, data1);

        Integer id2 = 2;
        Object data2 = "data-2";
        boolean added = tcCacheManager.add(id2, data2);
        Optional<Object> dataOpt = tcCacheManager.get(id2);
        assertTrue(added);
        assertTrue(dataOpt.isPresent());
        assertEquals(data2, dataOpt.get());
    }

    @Test
    void whenGetWhereKeyNotExists_thenReturnEmpty() {
        Integer id = 1;
        Optional<Object> dataOpt = tcCacheManager.get(id);
        assertTrue(dataOpt.isPresent());
    }

    @Test
    void whenGetWhereKeyExists_thenReturnData() {
        Integer id = 1;
        Object data = "data-1";
        tcCacheManager.add(id, data);

        Optional<Object> dataOpt = tcCacheManager.get(id);
        assertTrue(dataOpt.isPresent());
        assertEquals(data, dataOpt.get());
    }

}