package com.study.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        assertFalse(list.contains(3));
    }

    @Test
    @DisplayName("Linked list contains 10")
    void testLinkedListContains10() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(10);
        list.addFirst(1);
        list.addFirst(14);
        assertTrue(list.contains(10));
    }

    @Test
    @DisplayName("Linked list contains null")
    void testLinkedListContainsNull() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(10);
        list.addFirst(1);
        list.addFirst(14);
        list.addFirst(null);
        assertTrue(list.contains(null));
    }
}
