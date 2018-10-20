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
            boolean isLeaf = toSplit.children.isEmpty();

            List<T> leftK = toSplit.keys.subList(0, keySize / 2);
            List<Node> leftC = isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(0, toSplit.children.size() / 2);
            Node leftN = new Node();
            leftN.isLeaf = leftC.isEmpty();
            leftN.keys.addAll(leftK);
            leftN.children.addAll(leftC);
            for (Node node : leftN.children) {
                node.parent = leftN;
                for (Node child : node.children) {
                    child.parent = node;
                }
            }

            List<T> rightK = toSplit.keys.subList(keySize / 2 + 1, keySize + 1);
            List<Node> rightC = isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(toSplit.children.size() / 2, toSplit.children.size());
            Node rightN = new Node();
            rightN.isLeaf = rightC.isEmpty();
            rightN.keys.addAll(rightK);
            rightN.children.addAll(rightC);
            for (Node node : rightN.children) {
                node.parent = rightN;
                for (Node child : node.children) {
                    child.parent = node;
                }
            }

            int index = toSplit.parent.childIndex(middle);
            toSplit.parent.keys.add(index, middle);
            toSplit.parent.children.add(index, leftN);
            toSplit.parent.children.add(index + 1, rightN);
            toSplit.parent.children.remove(toSplit);
            leftN.parent = toSplit.parent;
            rightN.parent = toSplit.parent;
            if (toSplit.parent.keys.size() > keySize) {
                split(toSplit.parent);
            }
        }

        private void splitRoot(Node toSplit, T middle) {
            boolean isLeaf = toSplit.children.isEmpty();

            List<T> leftK = toSplit.keys.subList(0, keySize / 2);
            List<Node> leftC = isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(0, childSize / 2 + 1);
            Node leftN = new Node();
            leftN.isLeaf = leftC.isEmpty();
            leftN.keys.addAll(leftK);
            leftN.children.addAll(leftC);
            for (Node node : leftN.children) {
                node.parent = leftN;
                for (Node child : node.children) {
                    child.parent = node;
                }
            }

            List<T> rightK = toSplit.keys.subList(keySize / 2 + 1, keySize + 1);
            List<Node> rightC = isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(childSize / 2 + 1, childSize + 1);
            Node rightN = new Node();
            rightN.isLeaf = rightC.isEmpty();
            rightN.keys.addAll(rightK);
            rightN.children.addAll(rightC);
            for (Node node : rightN.children) {
                node.parent = rightN;
                for (Node child : node.children) {
                    child.parent = node;
                }
            }

            Node newRoot = new Node();
            newRoot.isLeaf = false;
            newRoot.keys.add(middle);
            newRoot.children.add(leftN);
            newRoot.children.add(rightN);
            leftN.parent = newRoot;
            rightN.parent = newRoot;

            root = newRoot;
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
