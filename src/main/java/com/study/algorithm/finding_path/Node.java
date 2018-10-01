package com.study.algorithm.finding_path;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node<T> {
    private final T value;
    int lowestCost;
    boolean isVisited = false;
    Node<T> parent;
    Map<Node<T>, Integer> connections = new HashMap<>();

    Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}