package com.study.data_structure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryNode root;

    @Override
    public void add(T value) {
        if (root == null) {
            root = new BinaryNode(value);
            return;
        }
        root.add(value);
    }

    @Override
    public T find(T value) {
        if (root == null || value == null) {
            return null;
        }
        return root.find(value);
    }

    @Override
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

    @Override
    public List<T> inorderTraverse() {
        List<T> inorderList = new ArrayList<>();
        inorderTraverse(root, inorderList);
        return inorderList;
    }

    @Override
    public List<T> breadthFirstTraverse() {
        Queue<BinaryNode> children = new ArrayDeque<>();
        children.add(root);
        List<T> values = new ArrayList<>();
        while (!children.isEmpty()) {
            BinaryNode next = children.poll();
            values.add(next.value);
            if (next.left != null)
                children.add(next.left);
            if (next.right != null)
                children.add(next.right);
        }
        return values;
    }

    private void inorderTraverse(BinaryNode node, List<T> inorderList) {
        if (node.left != null) {
            inorderTraverse(node.left, inorderList);
        }
        inorderList.add(node.value);
        if (node.right != null) {
            inorderTraverse(node.right, inorderList);
        }
    }

    private BinaryNode removeRoot() {
        if (root.left == null && root.right == null) {
            return null;
        } else if (root.left != null) {
            BinaryNode rightmostSubtree = findRightmostSubtree(root.left);
            BinaryNode rightmost = rightmostSubtree.right;
            BinaryNode leaf = rightmost.left;
            rightmost.left = root.left;
            rightmost.right = root.right;
            rightmostSubtree.right = leaf;
            return rightmost;
        }
        return root.right;
    }

    private BinaryNode findRightmostSubtree(BinaryNode node) {
        if (node.right != null && node.right.right != null) {
            return findRightmostSubtree(node.right);
        }
        return node;
    }

    class BinaryNode implements Tree.Node<T, BinaryNode> {

        T value;
        BinaryNode left;
        BinaryNode right;

        BinaryNode(T value) {
            this.value = value;
        }

        @Override
        public void add(T value) {
            int comp = value.compareTo(this.value);
            if (comp == 0) {
                return;
            }
            if (comp > 0) {
                if (right == null) {
                    right = new BinaryNode(value);
                } else {
                    right.add(value);
                }
            } else {
                if (left == null) {
                    left = new BinaryNode(value);
                } else {
                    left.add(value);
                }
            }
        }

        @Override
        public boolean remove(T value, BinaryNode parent) {
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

        private T rightmost(BinaryNode node) {
            if (node.right != null) {
                return rightmost(node.right);
            }
            return node.value;
        }

        @Override
        public T find(T value) {
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
    }

}
