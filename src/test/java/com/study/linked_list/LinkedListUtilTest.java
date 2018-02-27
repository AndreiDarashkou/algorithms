package com.study.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test cases for linked list util")
public class LinkedListUtilTest {

    @Test
    @DisplayName("Linked List should be sorted using insertion method")
    void testLinkedListInsertionSort() {
        LinkedList<Integer> list = new LinkedList<>(1, 7, 5, 2, 3);
        LinkedList<Integer> sorted = LinkedListUtil.insertionSort(list);
        assertEquals(5, sorted.size());
        assertEquals(Integer.valueOf(1), sorted.getFirst());
        assertEquals(Integer.valueOf(7), sorted.getLast());
        assertEquals("[\"1\",\"2\",\"3\",\"5\",\"7\"]", sorted.toString());
    }

    @Test
    @DisplayName("Linked List should be sorted using insertion with null values")
    void testLinkedListInsertionSortWithNullValues() {
        LinkedList<Integer> list = new LinkedList<>(88, 311, null, 1, 18, 7, 5, 2, 3, null, 99);
        LinkedList<Integer> sorted = LinkedListUtil.insertionSort(list);
        assertEquals(11, sorted.size());
        assertEquals(Integer.valueOf(1), sorted.getFirst());
        assertEquals(null, sorted.getLast());
        assertEquals("[\"1\",\"2\",\"3\",\"5\",\"7\",\"18\",\"88\",\"99\",\"311\",\"null\",\"null\"]", sorted.toString());
    }


    @Test
    @DisplayName("Should throw an exception if values are not comparable")
    void shouldThrowExceptionIfElementsAreNotComparable() {
        LinkedList<?> list = new LinkedList<>(new Object(), new Object());
        assertThrows(UnsupportedOperationException.class, () -> LinkedListUtil.insertionSort(list));
    }

}
