package com.test.designpattern.structural;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterPattern {
    /**
     * 过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）是一种设计模式，这种模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来。这种类型的设计模式属于结构型模式，它结合多个标准来获得单一标准。
     */

    static class Person {
        private String name;
        private String gender;
        private String maritalStatus;

        public Person(String name, String gender, String maritalStatus) {
            this.name = name;
            this.gender = gender;
            this.maritalStatus = maritalStatus;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender='" + gender + '\'' +
                    ", maritalStatus='" + maritalStatus + '\'' +
                    '}';
        }
    }

    interface Criteria {
        List<Person> meetCriteria(List<Person> list);
    }

    static class CriteriaMale implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> list) {
            for (Iterator<Person> iterator = list.iterator(); iterator.hasNext(); ) {
                if (!iterator.next().getGender().equalsIgnoreCase("male")) {
                    iterator.remove();
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        CriteriaMale criteriaMale = new CriteriaMale();
        System.out.println(criteriaMale.meetCriteria(persons));
    }
}
