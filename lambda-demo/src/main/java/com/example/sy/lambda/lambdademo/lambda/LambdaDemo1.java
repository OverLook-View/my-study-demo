package com.example.sy.lambda.lambdademo.lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaDemo1 {

    public static void main(String[] args) {
        // 匿名类
        //before
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8 ");
            }
        }).start();

        //now
        new Thread(() -> System.out.println("In Java8")).start();

        //事件处理
        JButton show = new JButton("show");
        show.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("without lambda expression is boring");
            }
        });

        show.addActionListener((e -> {
            System.out.println("Action !! Lambda expressions Rocks");
        }));

        //遍历list
        List<String> features = Arrays.asList("lambdas", "default method", "stream api", "date and time api");
        for (String feature : features) {
            System.out.println(feature);
        }

        features.forEach(n -> {
            System.out.println(n);
        });

        //Predicate 函数接口
        filter(features, (str) -> str.startsWith("d"));

        // Map和Reduce
        List<Integer> integers = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : integers) {
            double price = cost + .12 * cost;
            System.out.println(price);
            total += price;
        }
        System.out.println("Total: " + total);

        integers.stream().map((cost) -> cost + .12 * cost).forEach(d -> System.out.println(d));
        Double aDouble = integers.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + aDouble);

        //filtering
        String string[] = {"12", "456"};
        List<String> strings = Arrays.asList(string);
        List<String> filtered = strings.stream().filter(s -> s.length() > 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strings, filtered);

        //集合
        List<String> g7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K,", "Canada");
        String g7Countries = g7.stream().map(s -> s.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(g7Countries);

        //复制不同值创建子列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s, Square Without duplicates : %s %n", numbers, distinct);

        //计算最大值、最小值、总和、平均值
        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Highest prime number in List : "+stats.getMax());
        System.out.println("Lowest prime number in List : "+stats.getMin());
        System.out.println("Sum of all prime number : "+stats.getSum());
        System.out.println("Average of all prime number : "+stats.getAverage());
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
        names.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        });
    }
}
