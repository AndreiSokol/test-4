package com.tc.cache.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DNodeTest<T> {

    @InjectMocks
    private DNode<Cache<Integer, Object>> dummyNode;

    @Test
    void whenGetElement_thenThrowNullPointException() {
        assertThrows(NullPointerException.class, () -> {
            dummyNode.getElement();
        });
    }

    @Test
    void whenDetach_thenDoNothing() {
        dummyNode.detach();
    }

    @Test
    void whenGetListReference_thenReturnList() {
        DoList<Cache<Integer, Object>> list = dummyNode.getListReference();
        assertNull(list);
    }

    @Test
    void whenGetNext_thenReturnNext() {
        IniNode<Cache<Integer, Object>> node = dummyNode.getNext();
        assertTrue(node.isEmpty());
    }

}
