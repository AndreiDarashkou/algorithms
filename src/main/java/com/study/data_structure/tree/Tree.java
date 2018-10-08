package com.study.data_structure.tree;

import java.util.*;

public class Tree<T extends Comparable<T>> {

    private Node root;
    private List<T> inorderList = new ArrayList<>();

    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        root.add(value);
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
            return true;
        }
        return root.remove(value, null);
    }

    public List<T> inorderTraverse() {
        inorderList.clear();
        inorderTraverse(root);
        return inorderList;
    }

    private void inorderTraverse(Node node) {
        if (node.left != null) {
            inorderTraverse(node.left);
        }
        inorderList.add(node.value);
        if (node.right != null) {
            inorderTraverse(node.right);
        }
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
        List<T> nodes = breadthFirstTraverse(root);
        return Arrays.toString(nodes.toArray());
    }

    public List<T> breadthFirstTraverse(Node root) {
        Queue<Node> children = new ArrayDeque<>();
        children.add(root);
        List<T> nodes = new ArrayList<>();
        while (!children.isEmpty()) {
            Node next = children.poll();
            nodes.add(next.value);
            if (next.left != null)
                children.add(next.left);
            if (next.right != null)
                children.add(next.right);
        }
        return nodes;
    }

    private class Node {

        Node left;
        Node right;
        T value;

        Node(T value) {
            this.value = value;
        }

        void add(T value) {
            int comp = value.compareTo(this.value);
            if (comp == 0) {
                return;
            }
            if (comp > 0) {
                if (right == null) {
                    right = new Node(value);
                } else {
                    right.add(value);
                }
            } else {
                if (left == null) {
                    left = new Node(value);
                } else {
                    left.add(value);
                }
            }
        }

        boolean remove(T value, Node parent) {
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

        private T rightmost(Node node) {
            if (node.right != null) {
                return rightmost(node.right);
            }
            return node.value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}