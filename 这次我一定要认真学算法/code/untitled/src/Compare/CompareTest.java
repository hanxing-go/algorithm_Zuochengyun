package Compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest {
    public static void main(String[] args) {
        List<People> people = new ArrayList<>();
        People p1 = new People("zhangxu",20,3);
        People p2 = new People("zhangyang", 18, 1);
        People p3 = new People("zhangyidong", 15,2);
        People p4 = new People("wangping", 20, 1);
        Collections.addAll(people,p1,p2,p3,p4);

        sort(people);
        print(people);

        System.out.println("==========================");

        List<People> people2 = new ArrayList<>();
        Collections.addAll(people2,p1,p3,p2);
        Collections.sort(people2);
        print(people2);


    }

    //升序排列
    public static void sort(List<People> people) {
        Collections.sort(people, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.age < o2.age ? -1 : o1.age == o2.age ? 0 : 1;
            }
        }.thenComparing(new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                char A = o1.name.charAt(0);
                char B = o2.name.charAt(0);
                return A < B ? -1 : A == B ? 0 : 1;
            }
        }.thenComparing(new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.id < o2.id ? -1 : o1.id == o2.id ? 0 : 1;
            }
        })));

    }

    public static void print(List<People> people) {
        people.forEach(  p -> System.out.println(p));
    }
}

class People implements Comparable<People>{
    String name;
    int age;
    int id;

    public People(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(People o) {
        return -(this.age - o.age);
    }
}

