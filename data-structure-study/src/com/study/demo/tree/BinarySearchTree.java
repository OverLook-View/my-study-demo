package com.study.demo.tree;

import java.util.Comparator;
import java.util.EmptyStackException;

/**
 * @description:
 * @author: OverlookView
 * @create: 2021-03-18 14:27
 **/
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    private BinaryNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super AnyType> c) {
        root = null;
        cmp = c;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else
            return true; //Match
    }

    private int myCompare(AnyType lhs, AnyType rhs) {
        if (cmp != null)
            return cmp.compare(lhs, rhs);
        else
            return lhs.compareTo(rhs);
    }

    public AnyType findMax() {
        if (isEmpty())
            throw new EmptyStackException();
        return findMax(root).element;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;
        return t;
    }

    public AnyType findMin() {
        if (isEmpty())
            throw new EmptyStackException();
        return findMin(root).element;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ; //Duplicate;do nothing
        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) { //two children
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    public void printTree() {
        //todo
        printTree(root);
    }

    public void printTree(BinaryNode<AnyType> t) {
        //todo
    }

}
