package com.study.data_structure.tree;

import java.util.List;

public interface Tree<T> {

    void add(T value);

    T find(T value);

    boolean remove(T value);

    List<T> inorderTraverse();

    List<T> breadthFirstTraverse();

    interface Node<T, N extends Node> {

        void add(T value);

        boolean remove(T value, N parent);

        T find(T value);
    }

}
