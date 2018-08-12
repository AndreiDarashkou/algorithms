package com.study.linked_list;

public final class LinkedListUtil {

    private LinkedListUtil() {
    }

    public static <T extends Comparable<T>> LinkedList<T> insertionSort(LinkedList<T> list) {
        LinkedList<T> sorted = new LinkedList<>();
        for (T val : list) {
            if (sorted.size() == 0 || val == null) {
                sorted.addLast(val);
                continue;
            }
            T insertBeforeValue = null;
            for (T sortedVal : sorted) {
                if (sortedVal == null || ((Comparable) sortedVal).compareTo(val) >= 0) {
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

    public static <T extends Comparable<T>> LinkedList<T> selectionSort(LinkedList<T> list) {
        int element = 0;
        for (T value : list) {
            T min = value;
            int curIndex = 0;
            T lastSorted = null;
            for (T val : list) {
                if (curIndex < element) {
                    curIndex++;
                    lastSorted = val;
                    continue;
                }
                if (val.compareTo(min) <= 0) {
                    min = val;
                }
            }
            list.remove(min);
            if (lastSorted == null) {
                list.addFirst(min);
            } else {
                list.insertAfter(lastSorted, min);
            }
            element++;
        }
        return list;
    }

}
