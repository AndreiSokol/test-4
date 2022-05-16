package com.tc.cache.service;

import com.tc.cache.CacheManager;
import com.tc.cache.lru.model.CacheData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CacheServiceTest {

    @InjectMocks
    private CacheService cacheService;

    @Mock
    private CacheManager<Integer, Object> tcCacheManager;

    @Test
    void whenCacheIdGetWhereIdExists_thenReturnsObject() {
        Object data = "data-1";

        doReturn(Optional.of(data))
                .when(tcCacheManager)
                .get(anyInt());

        ResponseEntity<Object> result = cacheService.cacheIdGet(4);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(data, result.getBody());

        verify(tcCacheManager, times(1)).get(anyInt());
    }

    @Test
    void whenCacheIdGetWhereIdNotExists_thenReturnsNothing() {
        doReturn(Optional.empty())
                .when(tcCacheManager)
                .get(anyInt());

        ResponseEntity<Object> result = cacheService.cacheIdGet(4);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNull(result.getBody());

        verify(tcCacheManager, times(1)).get(anyInt());
    }

    @Test
    void whenCachePut_thenReturnOk() {
        CacheData cacheData = new CacheData().id(1).data("data-1");

        doReturn(true)
                .when(tcCacheManager)
                .add(anyInt(), any(Object.class));

        ResponseEntity<Void> result = cacheService.cachePut(cacheData);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(tcCacheManager, times(1)).add(anyInt(), any(Object.class));
    }

}
