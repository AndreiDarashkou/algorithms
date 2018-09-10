package com.study.data_structure.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for linked list")
public class LinkedListTest {

    @Test
    @DisplayName("Size equals 3 using addLast method")
    void testLinkedListSizeAddLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Size equals 3 using addFirst method")
    void testLinkedListSizeAddFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Doesn't contain 3")
    void testLinkedListDoesntContain3() {
        LinkedList<Integer> list = new LinkedList<>(4, 5, 6);
        assertFalse(list.contains(3));
    }

    @Test
    @DisplayName("Contains 10")
    void testLinkedListContains10() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14);
        assertTrue(list.contains(10));
    }

    @Test
    @DisplayName("Contains null")
    void testLinkedListContainsNull() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14, null);
        assertTrue(list.contains(null));
    }

    @Test
    @DisplayName("Remove 5")
    void testLinkedListRemove5() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14, 5);
        assertEquals(4, list.size());
        assertTrue(list.remove(5));
        assertEquals(3, list.size());
        assertFalse(list.contains(5));
        assertNotEquals(5, list.getLast());
        assertEquals(Integer.valueOf(14), list.getLast());
    }

    @Test
    @DisplayName("Check first element")
    void checkLinkedListFirstElement() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14, 5);
        assertEquals(Integer.valueOf(10), list.getFirst());
        assertNotEquals(14, list.getFirst());
    }

    @Test
    @DisplayName("Check last element")
    void checkLinkedListLastElement() {
        LinkedList<Integer> list = new LinkedList<>(10, 1, 14, 5);
        assertEquals(Integer.valueOf(5), list.getLast());
        assertNotEquals(14, list.getLast());
    }

    @Test
    @DisplayName("Remove null")
    void testLinkedListRemoveNull() {
        LinkedList<Integer> list = new LinkedList<>(10, null, 14, null);
        assertEquals(4, list.size());
        assertTrue(list.remove(null));
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Try to remove not existed value")
    void testLinkedListRemoveNotExisted() {
        LinkedList<Integer> list = new LinkedList<>(10, null, 14, null);
        assertEquals(4, list.size());
        assertFalse(list.remove(11));
    }

    @Test
    @DisplayName("Check copy list")
    void testLinkedListCopy() {
        LinkedList<Integer> sourceList = new LinkedList<>(10, 5, 14, null, 99);
        LinkedList<Integer> copyList = sourceList.copy();
        assertEquals(5, sourceList.size());
        assertEquals(5, copyList.size());
        assertTrue(sourceList.equals(copyList));
        assertTrue(sourceList != copyList);
    }

    @Test
    @DisplayName("Check empty list copy")
    void testEmptyLinkedListCopy() {
        LinkedList<Integer> sourceList = new LinkedList<>();
        LinkedList<Integer> copyList = sourceList.copy();
        assertEquals(0, sourceList.size());
        assertEquals(0, copyList.size());
        assertTrue(sourceList.equals(copyList));
    }

    @Test
    @DisplayName("Check equals list copy")
    void testLinkedListNotEqualCopy() {
        LinkedList<Integer> sourceList = new LinkedList<>(1, 2, 3, 4, null, 2);
        LinkedList<Integer> copyList = new LinkedList<>(1, 3, 4, null, 2);
        assertFalse(sourceList.equals(copyList));
    }

    @Test
    @DisplayName("Check not equals list copy")
    void testLinkedListNotEqual() {
        LinkedList<Integer> sourceList = new LinkedList<>(1, 8, 4, null, 2);
        LinkedList<Integer> copyList = new LinkedList<>(1, 3, 4, null, 2);
        assertFalse(sourceList.equals(copyList));
    }

    @Test
    @DisplayName("Check not equals to obj")
    void testLinkedListNotEqualToObj() {
        LinkedList<Integer> sourceList = new LinkedList<>(1, 8, 4, null, 2);
        assertFalse(sourceList.equals(null));
        assertFalse(sourceList.equals(new java.util.LinkedList()));
    }

    @Test
    @DisplayName("Check toString method")
    void testLinkedListToStringMethod() {
        LinkedList<Integer> list = new LinkedList<>(null, 8, 4, null, 2);
        assertEquals("[null,8,4,null,2]", list.toString());
    }

    @Test
    @DisplayName("Check insertion into list")
    void testLinkedListInsertAfterMethod() {
        LinkedList<Integer> list = new LinkedList<>(null, 8, 4, null, 2);
        assertTrue(list.insertAfter(8, 6));
        assertEquals(6, list.size());
        assertTrue(list.contains(6));
        assertEquals("[null,8,6,4,null,2]", list.toString());
    }

    @Test
    @DisplayName("Check fail insertion into list")
    void testLinkedListInsertAfterMethodFail() {
        LinkedList<Integer> list = new LinkedList<>(null, 8, 4, null, 2);
        assertFalse(list.insertAfter(10, 6));
        assertEquals(5, list.size());
        assertFalse(list.contains(6));
    }

    @Test
    @DisplayName("Check insertion into the end of a list")
    void testLinkedListInsertionIntoEnd() {
        LinkedList<Integer> list = new LinkedList<>(null, 8, 4, null, 2);
        assertTrue(list.insertAfter(2, 6));
        assertEquals(6, list.size());
        assertTrue(list.contains(6));
    }

    @Test
    @DisplayName("Check insertion before the first element")
    void testLinkedListInsertionBeforeFirst() {
        LinkedList<Integer> list = new LinkedList<>(null, 8, 4, null, 2);
        assertTrue(list.insertBefore(null, 6));
        assertEquals(6, list.size());
        assertTrue(list.contains(6));
    }

}
