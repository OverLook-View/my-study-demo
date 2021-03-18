package com.study.demo.tree;

/**
 * @description:
 * @author: OverlookView
 * @create: 2021-03-18 13:57
 **/
public class BinaryNode<AnyType> {
    AnyType element;
    BinaryNode<AnyType> left;
    BinaryNode<AnyType> right;

    public BinaryNode(AnyType theElement) {
        this(theElement,null,null);
    }

    public BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
        element=theElement;
        left=lt;
        right=rt;
    }
}
