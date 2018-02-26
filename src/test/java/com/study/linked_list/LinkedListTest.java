package com.study.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
