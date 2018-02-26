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

    public void addFirst(T value) {
        Element<T> element = new Element<>(value);
        element.next = first;
        first = element;
        size++;
    }

    public void addLast(T value) {
        Element<T> element = new Element<>(value);
        element.previous = last;
        last = element;
        size++;
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
