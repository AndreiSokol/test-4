package com.tc.cache.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoListTest<T> {

    @InjectMocks
    private DoList<CacheE<Integer, Object>> doublyLinkedList;

    @Test
    void whenClear_thenDoClear() {
        doublyLinkedList.clear();
        assertEquals(0, doublyLinkedList.size());
    }

    @Test
    void whenSize_thenReturnSize() {
        int size = doublyLinkedList.size();
        assertEquals(0, size);
    }

    @Test
    void whenAddElement_thenAdd() {
        CacheE<Integer, Object> element = new CacheE<>(1, "data-1");

        IniNode<CacheE<Integer, Object>> added = doublyLinkedList.add(element);
        assertEquals(1, doublyLinkedList.size());
    }

    @Test
    void whenRemoveTailLastElement_thenRemove() {
        CacheE<Integer, Object> element = new CacheE<>(1, "data-1");
        doublyLinkedList.add(element);

        doublyLinkedList.removeTail();
        assertEquals(0, doublyLinkedList.size());
    }

    @Test
    void whenRemoveTailElement_thenRemove() {
        CacheE<Integer, Object> element1 = new CacheE<>(1, "data-1");
        doublyLinkedList.add(element1);

        CacheE<Integer, Object> element2 = new CacheE<>(2, "data-2");
        doublyLinkedList.add(element2);

        doublyLinkedList.removeTail();
        assertEquals(1, doublyLinkedList.size());
    }

    @Test
    void whenMoveToFrontWithDummyNode_thenDontMove() {
        IniNode<CacheE<Integer, Object>> node = new DNode<>(new DoList<>());

        IniNode<CacheE<Integer, Object>> movedNode = doublyLinkedList.moveToFront(node);
        assertTrue(movedNode.isEmpty());
    }

    @Test
    void whenUpdateAndMoveToFrontWithDummyNode_thenDontMove() {
        IniNode<CacheE<Integer, Object>> node = new DNode<>(new DoList<>());
        CacheE<Integer, Object> element = new CacheE<>(1, "data-1");

        IniNode<CacheE<Integer, Object>> movedNode = doublyLinkedList.updateAndMoveToFront(node, element);
        assertTrue(movedNode.isEmpty());
    }

    @Test
    void whenUpdateAndMoveToFrontWithSingleNode_thenDoMove() {
        CacheE<Integer, Object> element = new CacheE<>(1, "data-1");
        IniNode<CacheE<Integer, Object>> node = doublyLinkedList.add(element);

        IniNode<CacheE<Integer, Object>> movedNode = doublyLinkedList.updateAndMoveToFront(node, element);
        assertFalse(movedNode.isEmpty());
        assertEquals(1, doublyLinkedList.size());
    }

    @Test
    void whenUpdateAndMoveToFrontWithMultiNode_thenDoMove() {
        CacheE<Integer, Object> element1 = new CacheE<>(1, "data-1");
        doublyLinkedList.add(element1);

        CacheE<Integer, Object> element2 = new CacheE<>(2, "data-2");
        IniNode<CacheE<Integer, Object>> node = doublyLinkedList.add(element2);

        CacheE<Integer, Object> element3 = new CacheE<>(3, "data-3");

        IniNode<CacheE<Integer, Object>> movedNode = doublyLinkedList.updateAndMoveToFront(node, element3);
        assertFalse(movedNode.isEmpty());
        assertEquals(2, doublyLinkedList.size());
    }

}
