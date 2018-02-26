package com.study.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for linked list")
public class LinkedListTest {

    @Test
    @DisplayName("Linked list size equals 3")
    void testLinkedListSizeAddLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        assertEquals(3, list.getSize());
    }

    @Test
    @DisplayName("Linked list size equals 3 add first")
    void testLinkedListSizeAddFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        assertEquals(3, list.getSize());
    }

    @Test
    @DisplayName("Linked list doesn't contain 3")
    void testLinkedListDoesntContain3() {
        LinkedList<Integer> list = new LinkedList<>(4, 5, 6);
        assertFalse(list.contains(3));
    }

    @Test
    @DisplayName("Linked list contains 10")
    void testLinkedListContains10() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14);
        assertTrue(list.contains(10));
    }

    @Test
    @DisplayName("Linked list contains null")
    void testLinkedListContainsNull() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14, null);
        assertTrue(list.contains(null));
    }

    @Test
    @DisplayName("Linked list remove 5")
    void testLinkedListRemove5() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14, 5);
        assertEquals(4, list.getSize());
        assertTrue(list.remove(5));
        assertEquals(3, list.getSize());
    }

    @Test
    @DisplayName("Linked list remove null")
    void testLinkedListRemoveNull() {
        LinkedList<Integer> list = new LinkedList<>(10, null, 14, null);
        assertEquals(4, list.getSize());
        assertTrue(list.remove(null));
        assertEquals(3, list.getSize());
    }

}
