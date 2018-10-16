package com.study.data_structure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BTree<T extends Comparable<T>> implements Tree<T> {

    private final int keySize;
    private final int childSize;
    private Node root;

    public BTree(int degree) {
        keySize = degree * 2;
        childSize = degree * 2 + 1;
    }

    @Override
    public void add(T value) {
        if (root == null) {
            root = new Node();
        }
        root.add(value);
    }

    @Override
    public T find(T value) {
        if (root == null) {
            return null;
        }
        return root.find(value);
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public List<T> inorderTraverse() {
        return null;
    }

    @Override
    public List<T> breadthFirstTraverse() {
        return null;
    }


    private class Node implements Tree.Node<T, Node> {

        boolean isLeaf = true;
        Node parent;
        List<T> keys = new ArrayList<>(keySize);
        List<Node> children = new ArrayList<>(childSize);

        Node() {
        }

        Node(Node parent, List<T> keys) {
            this.keys.addAll(keys);
            this.parent = parent;
        }

        Node(List<T> keys, List<Node> children) {
            this.keys.addAll(keys);
            this.children.addAll(children);
        }

        @Override
        public void add(T value) {
            if (keys.contains(value)) {
                return;
            }
            if (isLeaf) {
                if (keys.size() < keySize) {
                    keys.add(value);
                    Collections.sort(keys);
                } else {
                    T middle = keys.get(keySize / 2);
                    split(middle);
                    root.add(value);
                }
            } else {
                int index = childIndex(value);
                children.get(index).add(value);
            }
        }

        private void split(T middle) {
            if (parent == null) {
                if (children.isEmpty()) {
                    isLeaf = false;
                    children.add(new Node(this, keys.subList(0, keySize / 2)));
                    children.add(new Node(this, keys.subList(keySize / 2 + 1, keySize)));

                    keys.clear();
                    keys.add(middle);
                } else {
                    Node left = new Node(this, keys.subList(0, keySize / 2));
                    left.isLeaf = false;
                    left.children.addAll(children.subList(0, childSize / 2 + 1));
                    for (Node child : left.children) {
                        child.parent = left;
                        child.isLeaf = child.children.isEmpty();
                    }

                    Node right = new Node(this, keys.subList(keySize / 2 + 1, keySize));
                    right.isLeaf = false;
                    right.children.addAll(children.subList(childSize / 2 + 1, childSize));
                    for (Node child : right.children) {
                        child.parent = right;
                        child.isLeaf = child.children.isEmpty();
                    }

                    keys.clear();
                    keys.add(middle);
                    children.clear();
                    children.add(left);
                    children.add(right);
                }
            } else {
                if (parent.keys.size() < keySize) {
                    int index = parent.addKey(middle);
                    parent.addChildren(index, keys.subList(0, keySize / 2));
                    parent.addChildren(index + 1, keys.subList(keySize / 2 + 1, keySize));
                    parent.children.remove(this);
                    parent.isLeaf = false;
                    if (children.size() > 0) {
                        parent.children.get(index).children.addAll(children.subList(0, childSize / 2 + 1));
                        parent.children.get(index + 1).children.addAll(children.subList(childSize / 2 + 1, childSize));
                    }
                } else {
                    parent.split(parent.keys.get(keySize / 2));
                    split(middle);
                }
            }
        }

        private int addKey(T value) {
            int index = childIndex(value);
            keys.add(index, value);
            return index;
        }

        private int childIndex(T add) {
            int index = 0;
            for (T val : keys) {
                if (add.compareTo(val) < 0) {
                    break;
                }
                index++;
            }
            return index;
        }

        private void addChildren(int index, List<T> keys) {
            this.children.add(index, new Node(this, keys));
        }

        @Override
        public boolean remove(T value, Node parent) {
            return false;
        }

        @Override
        public T find(T value) {
            if (keys.contains(value)) {
                return value;
            }
            if (children.size() == 0) {
                return null;
            }
            int index = childIndex(value);
            if (children.size() < index) {
                return null;
            }
            return children.get(index).find(value);
        }

        @Override
        public String toString() {
            return Arrays.toString(keys.toArray());
        }
    }

}
