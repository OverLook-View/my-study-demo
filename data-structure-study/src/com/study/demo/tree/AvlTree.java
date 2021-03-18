package com.study.demo.tree;

/**
 * @description:
 * @author: OverlookView
 * @create: 2021-03-18 16:46
 **/
public class AvlTree<AnyType extends Comparable<? super AnyType>> extends BinarySearchTree<AnyType> {
    private static class AvlNode<AnyType> {
        AnyType element;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;

        public AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        public AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
            this.height = 0;
        }
    }
}
