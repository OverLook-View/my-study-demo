package com.study.demo.test;

import com.study.demo.tree.AvlTree;
import com.study.demo.tree.BinarySearchTree;

import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        Random random = new Random();
        AvlTree<Integer> avlTree = new AvlTree<>();
        for (int i = 0; i < 100; i++) {
            avlTree.insert(random.nextInt(1000));
        }
        avlTree.printTree();
        System.out.println(avlTree.height());
    }
}
