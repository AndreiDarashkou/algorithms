package com.study.linked_list;

public final class LinkedListUtil {

    private LinkedListUtil() {
    }

    //TODO
    public <T> LinkedList<T> insertionSort(LinkedList<T> list) {
        if (!(list.getFirst() instanceof Comparable)) {
            throw new UnsupportedOperationException("Values should be comparable");
        }
        LinkedList<T> sorted = new LinkedList<>();
        for (T val : list) {
            for (T sVal : sorted) {
                if (((Comparable)sVal).compareTo(val) > 1) {
                    sorted.insertAfter(sVal, val);
                    break;
                }
            }
        }
        return sorted;
    }

    public <T> LinkedList<T> selectionSort(LinkedList<T> list) {
        return list;
    }

}
