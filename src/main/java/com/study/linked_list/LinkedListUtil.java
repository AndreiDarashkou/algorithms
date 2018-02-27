package com.study.linked_list;

public final class LinkedListUtil {

    private LinkedListUtil() {
    }

    public static <T> LinkedList<T> insertionSort(LinkedList<T> list) {
        if (!(list.getFirst() instanceof Comparable)) {
            throw new UnsupportedOperationException("Values should be comparable");
        }
        LinkedList<T> sorted = new LinkedList<>();
        for (T val : list) {
            if (sorted.size() == 0) {
                sorted.addLast(val);
                continue;
            }
            T insertBeforeValue = null;
            for (T sortedVal : sorted) {
                int comp = ((Comparable) sortedVal).compareTo(val);
                if (comp >= 0) {
                    insertBeforeValue = sortedVal;
                    break;
                }
            }
            if (insertBeforeValue == null) {
                sorted.addLast(val);
            } else {
                sorted.insertBefore(insertBeforeValue, val);
            }
        }
        return sorted;
    }

    public <T> LinkedList<T> selectionSort(LinkedList<T> list) {
        return list;
    }

}
