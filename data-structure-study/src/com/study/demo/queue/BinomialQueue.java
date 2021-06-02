package com.study.demo.queue;

public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {

    private static class Node<AnyType> {
        AnyType element;
        Node<AnyType> leftChild;
        Node<AnyType> nextSibling;

        public Node(AnyType element) {
            this(element, null, null);
        }

        public Node(AnyType element, Node<AnyType> leftChild, Node<AnyType> nextSibling) {
            this.element = element;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }
    }

    private static final int DEFAULT_TREES = 1;
    private int currentSize;
    private Node<AnyType>[] theTrees;

    public BinomialQueue() {
    }

    public BinomialQueue(AnyType item) {
        theTrees = new Node[DEFAULT_TREES];
        theTrees[0] = new Node<AnyType>(item);
    }

    public void merge(BinomialQueue<AnyType> rhs) {
        if (this == rhs)
            return;
        currentSize += rhs.currentSize;
        if (currentSize > capacity()) {
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }
        Node<AnyType> carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            Node<AnyType> t1 = theTrees[i];
            Node<AnyType> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0://No trees
                case 1://Only this
                    break;
                case 2://Only rhs
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4://Only carry
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3://this and rhs
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5://this and carry
                    carry = combineTrees(t1, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 6://rhs and carry
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7://All three
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }
        for (int k = 0; k < rhs.theTrees.length; k++) {
            rhs.theTrees[k] = null;
        }
        rhs.currentSize = 0;
    }

    public void insert(AnyType x) {
        merge(new BinomialQueue<>(x));
    }

    public AnyType findMin() {
        return theTrees[0].element;
    }

    public AnyType deleteMin() throws Exception {
        if (isEmpty())
            throw new Exception("Underflow Exception");
        int minIndex = findMinIndex();
        AnyType minItem = theTrees[minIndex].element;

        Node<AnyType> deletedTree = theTrees[minIndex].leftChild;

        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTrees(minIndex + 1);

        deletedQueue.currentSize = (1 << minIndex) - 1;
        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);

        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        theTrees = null;
        currentSize = 0;
    }

    private void expandTheTrees(int newNumTrees) {

    }

    private Node<AnyType> combineTrees(Node<AnyType> t1, Node<AnyType> t2) {
        if (t1.element.compareTo(t2.element) > 0) {
            return combineTrees(t2, t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private int capacity() {
        return (1 << theTrees.length - 1);
    }

    private int findMinIndex() {
        return 0;
    }
}
