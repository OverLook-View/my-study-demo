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
}
