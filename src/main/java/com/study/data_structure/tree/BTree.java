package com.study.data_structure.tree;

import java.util.*;

public class BTree<T extends Comparable<T>> implements Tree<T> {

    private final int degree;
    private final int keySize;
    private final int childSize;
    private Node root;

    public BTree(int degree) {
        this.degree = degree;
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
        if (root == null) {
            return false;
        }
        return root.remove(value, null);
    }

    @Override
    public List<T> inorderTraverse() {
        List<T> inorderList = new ArrayList<>();
        inorderTraverse(root, inorderList);
        return inorderList;
    }

    @Override
    public List<T> breadthFirstTraverse() {
        Queue<Node> children = new ArrayDeque<>();
        children.add(root);
        List<T> values = new ArrayList<>();
        while (!children.isEmpty()) {
            Node next = children.poll();
            values.addAll(next.keys);
            children.addAll(next.children);
        }
        return values;
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
        List<T> keys = new ArrayList<>(keySize + 1);
        List<Node> children = new ArrayList<>(childSize + 1);

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
            T middle = toSplit.keys.get(degree);
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
            List<T> leftKeys = toSplit.keys.subList(0, degree);
            List<Node> leftChildren = toSplit.isLeaf ? Collections.EMPTY_LIST : toSplit.children.subList(0, toSplit.children.size() / 2);
            return createNode(leftKeys, leftChildren, parent);
        }

        private Node createRightNode(Node toSplit, Node parent) {
            List<T> rightKeys = toSplit.keys.subList(degree + 1, keySize + 1);
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

        @Override
        public boolean remove(T value, Node parent) {
            if (this.keys.contains(value)) {
                keys.remove(value);
                if (!isLeaf || (isLeaf && keys.size() < degree && parent != null)) {
                    this.rebalance(value);
                }
                return true;
            }
            if (this.isLeaf) {
                return false;
            }
            int index = childIndex(value);
            return children.get(index).remove(value, this);
        }

        private void rebalance(T deleted) {
            if (!isLeaf) {
                int index = childIndex(deleted);
                Node right = children.get(index + 1);
                Node leaf = right.children.get(0);
                while (!leaf.isLeaf) {
                    leaf = leaf.children.get(0);
                }

                T extracted = leaf.keys.get(0);
                keys.add(index, extracted);
                leaf.keys.remove(0);
                leaf.rebalanceNode();
            } else {
                rebalanceNode();
            }
        }

        private void rebalanceNode() {
            if (keys.size() < degree) {
                Node sibling = findSibling();
                boolean isLeft = keys.get(0).compareTo(sibling.keys.get(0)) > 0;
                if (sibling.keys.size() > degree) {
                    rotate(sibling, isLeft);
                } else {
                    merge(sibling, isLeft);
                }
            }
        }

        private Node findSibling() {
            int index = parent.children.indexOf(this);
            if (index == 0) {
                return parent.children.get(index + 1);
            }
            if (index == parent.children.size() - 1) {
                return parent.children.get(index - 1);
            }
            if (parent.children.get(index - 1).keys.size() > parent.children.get(index + 1).keys.size()) {
                return parent.children.get(index - 1);
            }
            return parent.children.get(index + 1);
        }

        private void rotate(Node sibling, boolean isLeftSibling) {
            if (isLeftSibling) { //right rotate
                if (!sibling.isLeaf) {
                    Node lastChild = sibling.lastChild();
                    sibling.children.remove(lastChild);
                    children.add(0, lastChild);
                    lastChild.parent = this;
                }
                T rightKey = sibling.lastKey();
                int index = parent.childIndex(rightKey);
                T parentKey = parent.keys.get(index);
                parent.keys.remove(index);
                parent.keys.add(index, rightKey);
                keys.add(0, parentKey);
                sibling.keys.remove(rightKey);
            } else { //left rotate
                if (!sibling.isLeaf) {
                    Node firstChild = sibling.firstChild();
                    sibling.children.remove(firstChild);
                    children.add(firstChild);
                    firstChild.parent = this;
                }
                T leftKey = sibling.firstKey();
                int index = parent.childIndex(leftKey);
                T parentKey = parent.keys.get(index);
                parent.keys.remove(index);
                parent.keys.add(index, leftKey);
                keys.add(parentKey);
                sibling.keys.remove(leftKey);
            }
        }

        private void merge(Node sibling, boolean isSiblingLeft) {
            int parentKey = parent.children.indexOf(this);
            if (isSiblingLeft) {
                sibling.keys.add(parent.keys.get(parentKey));
                sibling.keys.addAll(keys);
                sibling.children.addAll(children);
            } else {
                keys.add(parent.keys.get(parentKey));
                keys.addAll(sibling.keys);
                children.addAll(sibling.children);
            }
            parent.keys.remove(parentKey);
            parent.children.remove(parentKey + 1);
            for (Node child : children) {
                child.parent = this;
            }
            if (parent.keys.size() < degree && parent.parent != null) { //is not root
                parent.rebalanceNode();
            }
        }

        private T lastKey() {
            return keys.get(keys.size() - 1);
        }

        private T firstKey() {
            return keys.get(0);
        }

        private Node lastChild() {
            return children.get(children.size() - 1);
        }

        private Node firstChild() {
            return children.get(0);
        }

        @Override
        public T find(T value) {
            if (keys.contains(value)) {
                return value;
            }
            if (isLeaf) {
                return null;
            }
            int index = childIndex(value);
            return children.get(index).find(value);
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
        public String toString() {
            return Arrays.toString(keys.toArray());
        }
    }

}
