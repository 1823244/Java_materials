/**
 * пример из выступления 2014 года
 * https://www.youtube.com/watch?v=O8oN4KSZEXE
 *
 * Вывод сортированного списка групп людей
 * Метод printGroupsOriginalAlgo() - вариант без stream api
 * Метод printGroupsStreamApi() - демонстрация stream api
 * Метод printGroupsStreamApiPipeline() - пошаговое представление алгоритма stream api
 */

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class StreamApiExample01 {
    public static void main(String[] args) {
        ArrayList<People> people = new ArrayList<>();
        Group pens = new Group();
        pens.setName("pension");
        pens.setSize(0);
        Group work = new Group();
        work.setName("worker");
        work.setSize(0);
        Group stud = new Group();
        stud.setName("student");
        stud.setSize(0);

        People p1 = new People();
        p1.setAge(66);
        p1.setGroup(pens);
        people.add(p1);
        pens.setSize(pens.getSize()+1);
        People p5 = new People();
        p5.setAge(65);
        p5.setGroup(pens);
        people.add(p5);
        pens.setSize(pens.getSize()+1);
        People p6 = new People();
        p6.setAge(62);
        p6.setGroup(pens);
        people.add(p6);
        pens.setSize(pens.getSize()+1);

        People p2 = new People();
        p2.setAge(46);
        p2.setGroup(work);
        people.add(p2);
        work.setSize(work.getSize()+1);

        People p3 = new People();
        p3.setAge(21);
        p3.setGroup(stud);
        people.add(p3);
        stud.setSize(stud.getSize()+1);
        People p4 = new People();
        p4.setAge(20);
        p4.setGroup(stud);
        people.add(p4);
        stud.setSize(stud.getSize()+1);

        System.out.println("--- classic algo");

        printGroupsOriginalAlgo(people);

        System.out.println("--- stream api algo");

        printGroupsStreamApi(people);

        System.out.println("--- stream api via pipeline");

        printGroupsStreamApiPipeline(people);
    }

    public static void printGroupsOriginalAlgo(List<People> people) {
        Set<Group> groups = new HashSet<>();
        for (People p:people) {
            if (p.getAge() >= 18)
                groups.add(p.getGroup());
        }
        List<Group> sorted = new ArrayList<>(groups);
        Collections.sort(sorted, new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return Integer.compare(o1.getSize(), o2.getSize());
            }
        });
        for (Group g:sorted)
            System.out.println(g.getName());

    }

    public static void printGroupsStreamApi(List<People> people) {
        people.stream()
                .filter(p -> p.getAge() > 18)
                .map(p -> p.getGroup())
                .distinct()
                .sorted(comparing(g -> g.getSize()))
                .map(g -> g.getName())
                .forEach(n -> System.out.println(n));
    }

    public static void printGroupsStreamApiPipeline(List<People> people) {
        Stream<People> s1 = people.stream();
        Stream<People> s2 = s1.filter(p -> p.getAge() > 18);
        Stream<Group> s3 = s2.map(p -> p.getGroup());
        Stream<Group> s4 = s3.distinct();
        Stream<Group> s5 = s4.sorted(comparing(g -> g.getSize()));
        Stream<String> s6 = s5.map(g -> g.getName());
        s6.forEach(n -> System.out.println(n));
    }

    static class People {
        private int age;
        private Group group;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }
    }

    static class Group {
        private String name;
        private int size;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
