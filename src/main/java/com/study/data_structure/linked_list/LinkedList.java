package com.study.data_structure.linked_list;

import java.util.Iterator;

public final class LinkedList<T> implements Iterable<T> {
    private Element<T> first;
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

    public boolean insertAfter(T after, T value) {
        Element<T> element = new Element<>(value);
        Element<T> pointer = first;
        while (pointer != null) {
            T val = pointer.value;
            if (val == after || (val != null && val.equals(after))) {
                element.previous = pointer;
                if (pointer.next != null) {
                    element.next = pointer.next;
                    pointer.next.previous = element;
                } else {
                    last = element;
                }
                pointer.next = element;
                size++;
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public boolean insertBefore(T before, T value) {
        Element<T> element = new Element<>(value);
        Element<T> pointer = first;
        while (pointer != null) {
            T val = pointer.value;
            if (val == before || (val != null && val.equals(before))) {
                element.next = pointer;
                if (pointer.previous != null) {
                    element.previous = pointer.previous;
                    pointer.previous.next = element;
                } else {
                    first = element;
                }
                pointer.previous = element;
                size++;
                return true;
            }
            pointer = pointer.next;
        }
        return false;
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
            if ((value == null && pointer.value == null) || (value != null && value.equals(pointer.value))) {
                if (pointer.previous != null) {
                    pointer.previous.next = pointer.next;
                } else {
                    first = pointer.next;
                }
                if (pointer.next != null) {
                    pointer.next.previous = pointer.previous;
                } else {
                    last = pointer.previous;
                }
                size--;
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public LinkedList<T> copy() {
        LinkedList<T> copy = new LinkedList<>();
        for (T element : this) {
            copy.addLast(element);
        }

        return copy;
    }

    public T getFirst() {
        if (first == null) {
            return null;
        }
        return first.value;
    }

    public T getLast() {
        if (last == null) {
            return null;
        }
        return last.value;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LinkedList)) {
            return false;
        }
        LinkedList list = (LinkedList) obj;
        if (list.size == 0 && size == 0) {
            return true;
        }
        if (list.size != size) {
            return false;
        }
        Element<T> pointer = first;
        Element pointer2 = list.first;
        while (pointer != null && pointer2 != null) {
            if ((pointer.value == null && pointer2.value != null)
                    || (pointer.value != null && pointer2.value == null)
                    || (pointer.value != null && !pointer.value.equals(pointer2.value))) {
                return false;
            }
            pointer = pointer.next;
            pointer2 = pointer2.next;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        Element<T> pointer = first;
        while (pointer != null) {
            string.append(pointer.value).append(",");
            pointer = pointer.next;
        }
        string.setLength(string.length() - 1);
        string.append("]");

        return string.toString();
    }

    private class LinkedIterator implements Iterator<T> {

        private Element<T> pointer;

        LinkedIterator() {
            pointer = first;
        }

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public T next() {
            T value = pointer.value;
            pointer = pointer.next;
            return value;
        }
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
