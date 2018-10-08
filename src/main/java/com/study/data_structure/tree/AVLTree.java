package com.study.data_structure.tree;

import java.util.*;

public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;
    private List<T> inorderList = new ArrayList<>();
    private boolean needRebalance;

    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        if (root.add(value)) {
            rebalance();
        }
    }

    public T find(T value) {
        if (root == null || value == null) {
            return null;
        }
        return root.find(value);
    }

    public boolean remove(T value) {
        if (root == null || value == null) {
            return false;
        }
        if (value.equals(root.value)) {
            root = removeRoot();
            root.updateChildrenHeight();
            return true;
        }
        boolean isRemoved = root.remove(value, null);
        if (isRemoved) {
            rebalance();
        }
        return isRemoved;
    }

    public List<T> inorderTraverse() {
        inorderList.clear();
        if (root != null) {
            inorderTraverse(root);
        }
        return inorderList;
    }

    private void inorderTraverse(Node<T> node) {
        if (node.left != null) {
            inorderTraverse(node.left);
        }
        inorderList.add(node.value);
        if (node.right != null) {
            inorderTraverse(node.right);
        }
    }

    private void rebalance() {
        root.updateChildrenHeight();
        Node rotate = findDisbalance();
        if (rotate == null) {
            return;
        }
        Node parent = findParent(rotate);
        int leftH = rotate.left == null ? 0 : rotate.left.height;
        int rightH = rotate.right == null ? 0 : rotate.right.height;
        if (leftH > rightH) {
            int leftLeftH = rotate.left.left == null ? 0 : rotate.left.left.height;
            int leftRightH = rotate.left.right == null ? 0 : rotate.left.right.height;
            if (leftLeftH > leftRightH) { //right rotate
                rightRotateNode(rotate, parent);
            } else { //left right rotate
                rotate.left = leftRotate(rotate.left);
                rightRotateNode(rotate, parent);
            }
        } else {
            int rightLeftH = rotate.right.left == null ? 0 : rotate.right.left.height;
            int rightRightH = rotate.right.right == null ? 0 : rotate.right.right.height;
            if (rightRightH > rightLeftH) {//left rotate
                leftRotateNode(rotate, parent);
            } else { //right left rotate
                rotate.right = rightRotate(rotate.right);
                leftRotateNode(rotate, parent);
            }
        }
        root.updateChildrenHeight();
    }

    private Node findParent(Node node) {
        Queue<Node> children = new ArrayDeque<>();
        children.add(root);
        while (!children.isEmpty()) {
            Node next = children.poll();
            if (next.left == node || next.right == node) {
                return next;
            }
            if (next.left != null)
                children.add(next.left);
            if (next.right != null)
                children.add(next.right);
        }
        return null;
    }

    private void rightRotateNode(Node rotate, Node parent) {
        if (parent == null) {
            root = rightRotate(rotate);
        } else {
            parent.left = rightRotate(rotate);
        }
    }

    private void leftRotateNode(Node rotate, Node parent) {
        if (parent == null) {
            root = leftRotate(rotate);
        } else {
            parent.right = leftRotate(rotate);
        }
    }

    Node rightRotate(Node root) {
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        return newRoot;
    }

    Node leftRotate(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        return newRoot;
    }

    private Node removeRoot() {
        if (root.left == null && root.right == null) {
            return null;
        } else if (root.left != null) {
            Node rightmostSubtree = findRightmostSubtree(root.left);
            Node rightmost = rightmostSubtree.right;
            Node leaf = rightmost.left;
            rightmost.left = root.left;
            rightmost.right = root.right;
            rightmostSubtree.right = leaf;
            return rightmost;
        }
        return root.right;
    }

    private Node findRightmostSubtree(Node node) {
        if (node.right != null && node.right.right != null) {
            return findRightmostSubtree(node.right);
        }
        return node;
    }

    @Override
    public String toString() {
        List<Node> nodes = breadthFirstTraverse(root);
        return Arrays.toString(nodes.toArray());
    }

    private Node findDisbalance() {
        Queue<Node> children = new ArrayDeque<>();
        children.add(root);
        Node node = null;
        while (!children.isEmpty()) {
            Node next = children.poll();
            int leftH = next.left == null ? 0 : next.left.height;
            int rightH = next.right == null ? 0 : next.right.height;
            if (Math.abs(leftH - rightH) > 1) {
                node = next;
            }
            if (next.left != null)
                children.add(next.left);
            if (next.right != null)
                children.add(next.right);
        }
        return node;
    }

    private List<Node> breadthFirstTraverse(Node root) {
        Queue<Node> children = new ArrayDeque<>();
        children.add(root);
        List<Node> nodes = new ArrayList<>();
        while (!children.isEmpty()) {
            Node next = children.poll();
            nodes.add(next);
            if (next.left != null)
                children.add(next.left);
            if (next.right != null)
                children.add(next.right);
        }
        return nodes;
    }

    private static class Node<T extends Comparable<T>> {

        Node<T> left;
        Node<T> right;
        T value;
        int height = 0;

        Node(T value) {
            this.value = value;
        }

        boolean add(T value) {
            int comp = value.compareTo(this.value);
            if (comp == 0) {
                return false;
            }
            return comp > 0 ? addRight(value) : addLeft(value);
        }

        private boolean addLeft(T value) {
            if (left == null) {
                left = new Node(value);
                return right == null;
            } else {
                return left.add(value);
            }
        }

        private boolean addRight(T value) {
            if (right == null) {
                right = new Node(value);
                return left == null;
            } else {
                return right.add(value);
            }
        }

        void updateChildrenHeight() {
            postorderTraverse(this);
        }

        private void postorderTraverse(Node<T> node) {
            if (node.left != null) {
                postorderTraverse(node.left);
            }
            if (node.right != null) {
                postorderTraverse(node.right);
            }
            node.updateHeight();
        }

        private void updateHeight() {
            if (left == null && right == null) {
                height = 0;
            } else {
                int leftH = left == null ? 0 : left.height;
                int rightH = right == null ? 0 : right.height;
                height = Math.max(leftH, rightH) + 1;
            }
        }

        boolean remove(T value, Node<T> parent) {
            int comp = value.compareTo(this.value);
            if (comp > 0) {
                return right != null && right.remove(value, this);
            } else if (comp < 0) {
                return left != null && left.remove(value, this);
            } else {
                if (left != null && right != null) {
                    this.value = rightmost(left);
                    left.remove(this.value, this);
                } else if (parent.left == this) {
                    parent.left = left != null ? left : right;
                } else if (parent.right == this) {
                    parent.right = right != null ? right : left;
                }
                return true;
            }
        }

        private T rightmost(Node<T> node) {
            if (node.right != null) {
                return rightmost(node.right);
            }
            return node.value;
        }

        T find(T value) {
            int comp = value.compareTo(this.value);
            if (comp == 0) {
                return value;
            }
            if (comp > 0) {
                return right == null ? null : right.find(value);
            } else {
                return left == null ? null : left.find(value);
            }
        }

        @Override
        public String toString() {
            return "v:" + value.toString() + " h:" + height;
        }
    }
}
