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
        List<T> inorderList = new ArrayList<>();
        inorderTraverse(root, inorderList);
        return inorderList;
    }

    @Override
    public List<T> breadthFirstTraverse() {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(inorderTraverse().toArray());
    }

    private void inorderTraverse(Node node, List<T> inorderList) {
        if (node.isLeaf) {
            inorderList.addAll(node.keys);
            return;
        }
        for (int i = 0; i < node.children.size(); i++) {
            inorderTraverse(node.children.get(i), inorderList);
            if (i < node.keys.size()) {
                inorderList.add(node.keys.get(i));
            }
        }
    }

    private class Node implements Tree.Node<T, Node> {

        boolean isLeaf = true;
        Node parent;
        List<T> keys = new ArrayList<>(keySize);
        List<Node> children = new ArrayList<>(childSize);

        Node() {
        }

        @Override
        public void add(T value) {
            if (keys.contains(value)) {
                return;
            }
            if (isLeaf) {
                keys.add(value);
                Collections.sort(keys);
                if (keys.size() > keySize) {
                    split(this);
                }
            } else {
                int index = childIndex(value);
                children.get(index).add(value);
            }
        }

        private void split(Node toSplit) {
            T middle = toSplit.keys.get(keySize / 2);
            if (toSplit.parent == null) {
                splitRoot(toSplit, middle);
            } else {
                splitInnerNode(toSplit, middle);
            }
        }

        private void splitInnerNode(Node toSplit, T middle) {
            Node leftN = createLeftNode(toSplit, toSplit.parent);
            Node rightN = createRightNode(toSplit, toSplit.parent);

            int index = toSplit.parent.childIndex(middle);
            toSplit.parent.keys.add(index, middle);
            toSplit.parent.children.add(index, leftN);
            toSplit.parent.children.add(index + 1, rightN);
            toSplit.parent.children.remove(toSplit);
            if (toSplit.parent.keys.size() > keySize) {
                split(toSplit.parent);
            }
        }

        private void splitRoot(Node toSplit, T middle) {
            Node newRoot = new Node();
            newRoot.isLeaf = false;
            newRoot.keys.add(middle);

            Node leftN = createLeftNode(toSplit, newRoot);
            Node rightN = createRightNode(toSplit, newRoot);

            newRoot.children.add(leftN);
            newRoot.children.add(rightN);

            root = newRoot;
        }

        private Node createLeftNode(Node toSplit, Node parent) {
            List<T> leftKeys = toSplit.keys.subList(0, keySize / 2);
            List<Node> leftChildren = toSplit.isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(0, toSplit.children.size() / 2);
            return createNode(leftKeys, leftChildren, parent);
        }

        private Node createRightNode(Node toSplit, Node parent) {
            List<T> rightKeys = toSplit.keys.subList(keySize / 2 + 1, keySize + 1);
            List<Node> rightChildren = toSplit.isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(toSplit.children.size() / 2, toSplit.children.size());
            return createNode(rightKeys, rightChildren, parent);
        }

        private Node createNode(List<T> keys, List<Node> children, Node parent) {
            Node newNode = new Node();
            newNode.isLeaf = children.isEmpty();
            newNode.keys.addAll(keys);
            newNode.children.addAll(children);
            newNode.parent = parent;
            for (Node node : newNode.children) {
                node.parent = newNode;
                for (Node child : node.children) {
                    child.parent = node;
                }
            }
            return newNode;
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
