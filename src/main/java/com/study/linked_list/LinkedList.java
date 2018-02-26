package com.study.linked_list;

import lombok.Getter;
import lombok.Setter;

@Getter
public final class LinkedList<T> {
    @Setter
    private Element<T> first;
    @Setter
    private Element<T> last;
    private int size;

    public LinkedList() {
    }

    @SafeVarargs
    public LinkedList(T... values) {
        for (T value : values) {
            addLast(value);
        }
    }

    public void addFirst(T value) {
        Element<T> element = new Element<>(value);
        if (size == 0) {
            first = element;
            last = element;
        } else {
            first.previous = element;
            element.next = first;
            first = element;
        }
        size++;
    }

    public void addLast(T value) {
        Element<T> element = new Element<>(value);
        if (size == 0) {
            first = element;
            last = element;
        } else {
            last.next = element;
            element.previous = last;
            last = element;
        }
        size++;
    }

    public boolean contains(T value) {
        Element<T> pointer = first;
        while (pointer != null) {
            if (value == null) {
                if (pointer.value == null) {
                    return true;
                }
            } else if (value.equals(pointer.value)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public boolean remove(T value) {
        Element<T> pointer = first;
        while (pointer != null) {
            if (value == pointer.value || value.equals(pointer.value)) {
                pointer.previous = pointer.next;
                pointer.next = pointer.previous;
                size--;
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    private static class Element<T> {
        T value;
        Element<T> next;
        Element<T> previous;

        Element(T value) {
            this.value = value;
        }
    }
}
