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
        root.add(value);
        rebalance();
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
            root = removeRoot(root);
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
        Node rotate = findDisbalance();
        if (rotate == null) {
            return;
        }
        int leftH = rotate.left == null ? 0 : rotate.left.height;
        int rightH = rotate.right == null ? 0 : rotate.right.height;
        if (leftH > rightH) {
            int leftLeftH = rotate.left.left == null ? 0 : rotate.left.left.height;
            int leftRightH = rotate.left.right == null ? 0 : rotate.left.right.height;
            if (leftLeftH > leftRightH) { //right rotate
                rightRotateNode(rotate);
            } else { //left right rotate
                rotate.left = leftRotate(rotate.left);
                rightRotateNode(rotate);
            }
        } else {
            int rightLeftH = rotate.right.left == null ? 0 : rotate.right.left.height;
            int rightRightH = rotate.right.right == null ? 0 : rotate.right.right.height;
            if (rightRightH > rightLeftH) {//left rotate
                leftRotateNode(rotate);
            } else { //right left rotate
                rotate.right = rightRotate(rotate.right);
                leftRotateNode(rotate);
            }
        }
        rotate.parent.updateChildrenHeight();
    }

    private void rightRotateNode(Node rotate) {
        if (rotate.parent == null) {
            root = rightRotate(rotate);
        } else {
            rotate.parent.left = rightRotate(rotate);
        }
    }

    private void leftRotateNode(Node rotate) {
        if (rotate.parent == null) {
            root = leftRotate(rotate);
        } else {
            rotate.parent.right = leftRotate(rotate);
        }
    }

    Node rightRotate(Node root) {
        if (root.left == null) {
            return root;
        }
        Node newRoot = root.left;
        root.left = newRoot.right;
        if (newRoot.right != null) {
            newRoot.right.parent = root;
        }
        newRoot.right = root;
        newRoot.parent = root.parent;
        root.parent = newRoot;

        return newRoot;
    }

    Node leftRotate(Node root) {
        if (root.right == null) {
            return root;
        }
        Node newRoot = root.right;
        root.right = newRoot.left;
        if (newRoot.left != null) {
            newRoot.left.parent = root;
        }
        newRoot.left = root;
        newRoot.parent = root.parent;
        root.parent = newRoot;

        return newRoot;
    }

    private Node removeRoot(Node node) {
        Node rightmostSubtree = findRightmostSubtree(node.left);
        Node rightmost = rightmostSubtree.right;
        Node leaf = rightmost.left;
        rightmost.left = node.left;
        rightmost.right = node.right;
        rightmost.parent = null;
        rightmostSubtree.right = leaf;
        return rightmost;
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
        Node<T> parent;
        T value;
        int height = 0;

        Node(T value) {
            this.value = value;
        }

        void add(T value) {
            int comp = value.compareTo(this.value);
            if (comp == 0) {
                return;
            }
            if (comp > 0) {
                addRight(value);
            } else {
                addLeft(value);
            }
        }

        private void addLeft(T value) {
            if (left == null) {
                left = new Node(value);
                left.parent = this;
                if (right == null) {
                    updateHeight();
                }
            } else {
                left.add(value);
            }
        }

        private void addRight(T value) {
            if (right == null) {
                right = new Node(value);
                right.parent = this;
                if (left == null) {
                    updateHeight();
                }
            } else {
                right.add(value);
            }
        }

        void updateHeight() {
            int leftH = left == null ? 0 : left.height;
            int rightH = right == null ? 0 : right.height;
            height = Math.max(leftH, rightH) + 1;
            if (parent != null) {
                parent.updateHeight();
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
            if (node.left == null && node.right == null) {
                node.height = 0;
            } else {
                node.updateHeight();
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
                    if (parent.left != null) {
                        parent.left.parent = parent;
                    }
                } else if (parent.right == this) {
                    parent.right = right != null ? right : left;
                    if (parent.right != null) {
                        parent.right.parent = parent;
                    }
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
