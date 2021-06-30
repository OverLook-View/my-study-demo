package com.study.demo.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static void sort(List<Integer> list) {
        if (list.size() > 1) {
            ArrayList<Integer> smaller = new ArrayList<>();
            ArrayList<Integer> same = new ArrayList<>();
            ArrayList<Integer> larger = new ArrayList<>();

            Integer chosenItem = list.get(list.size() / 2);
            for (Integer i : list) {
                if (i < chosenItem)
                    smaller.add(i);
                else if (i > chosenItem)
                    larger.add(i);
                else
                    same.add(i);
            }

            sort(smaller);
            sort(larger);

            list.clear();
            list.addAll(smaller);
            list.addAll(same);
            list.addAll(larger);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static final int CUTOFF = 10;

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            int i = left, j = right - 1;
            for (; ; ) {
                while ((a[++i].compareTo(pivot) < 0)) {
                }
                while ((a[--j].compareTo(pivot) > 0)) {
                }
                if (i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }

                swapReferences(a, i, right - 1);

                quicksort(a, left, i - 1);
                quicksort(a, i + 1, right);
            }
        } else {
            InsertionSort.insertionSort(a);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a, int i, int i1) {
        AnyType temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }

    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        swapReferences(a, center, right - 1);
        return a[right - 1];
    }
}
