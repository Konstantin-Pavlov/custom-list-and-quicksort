package com.aston;

import com.aston.CustomArrayList.CustomArrayList;
import com.aston.entity.Person;
import com.aston.entity.PointGeneric;
import com.aston.entity.Student;
import com.aston.sortings.CustomQuickSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomQuickSortTest {

    private CustomArrayList<Integer> customArrayList;

    @BeforeEach
    public void setUp() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void test1() {
        CustomArrayList<Integer> emptyList = new CustomArrayList<>();

        CustomQuickSort.sort(emptyList);

        assertEquals(String.format("CustomArrayList [size=0, data=]%n"), emptyList.toString());
    }

    @Test
    public void test2() {
        customArrayList.add(5);

        CustomQuickSort.sort(customArrayList);

        assertEquals(String.format("CustomArrayList [size=1, data=5]%n"), customArrayList.toString());
    }

    @Test
    public void test3() {
        customArrayList.add(5);
        customArrayList.add(3);
        customArrayList.add(1);
        customArrayList.add(4);
        customArrayList.add(2);

        CustomQuickSort.sort(customArrayList);

        assertEquals(String.format("CustomArrayList [size=5, data=1,2,3,4,5]%n"), customArrayList.toString());
    }

    @Test
    public void test4() {
        customArrayList.add(5);
        customArrayList.add(3);
        customArrayList.add(1);
        customArrayList.add(4);
        customArrayList.add(2);
        customArrayList.add(2);

        CustomQuickSort.sort(customArrayList);

        assertEquals(String.format("CustomArrayList [size=6, data=1,2,2,3,4,5]%n"), customArrayList.toString());
    }

    @Test
    public void test5() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Kiwi");
        list.add("Apple");
        list.add("Banana");

        assertEquals(String.format("CustomArrayList [size=3, data=Kiwi,Apple,Banana]%n"), list.toString());
        CustomQuickSort.sort(list);
        assertEquals(String.format("CustomArrayList [size=3, data=Apple,Banana,Kiwi]%n"), list.toString());
    }

    @Test
    public void test6() {
        CustomArrayList<Person> people = new CustomArrayList<>();
        people.add(new Person("Bob", 25));
        people.add(new Person("Alice", 30));
        people.add(new Person("Charlie", 35));

        assertEquals(String.format("CustomArrayList [size=3, data=Person{name='Bob', age=25},Person{name='Alice', age=30},Person{name='Charlie', age=35}]%n"), people.toString());

        // Person class implements sorting by name
        CustomQuickSort.sort(people);

        assertEquals(String.format("CustomArrayList [size=3, data=Person{name='Alice', age=30},Person{name='Bob', age=25},Person{name='Charlie', age=35}]%n"), people.toString());
    }

    @Test
    public void test7() {
        CustomArrayList<Student> students = new CustomArrayList<>();
        students.add(new Student("Oleg", 4));
        students.add(new Student("Oleg", 5));
        students.add(new Student("Oleg", 3));
        students.add(new Student("Oleg", 2));
        students.add(new Student("Anya", 2));
        students.add(new Student("Lisa", 2));

        assertEquals(String.format("CustomArrayList [size=6, data=Oleg 4,Oleg 5,Oleg 3,Oleg 2,Anya 2,Lisa 2]%n"), students.toString());

        // Student class implements sorting by grade in descending order than by name in ascending order
        CustomQuickSort.sort(students);
        assertEquals(String.format("CustomArrayList [size=6, data=Oleg 5,Oleg 4,Oleg 3,Anya 2,Lisa 2,Oleg 2]%n"), students.toString());
    }

    @Test
    public void test8() {
        CustomArrayList<PointGeneric<Double>> points = new CustomArrayList<>();
        points.add(new PointGeneric<>(1.0, 2.0));
        points.add(new PointGeneric<>(2.0, 7.0));
        points.add(new PointGeneric<>(3.0, 1.0));
        points.add(new PointGeneric<>(0.0, 0.0));

        assertEquals(String.format("CustomArrayList [size=4, data=Point{x = 1,000, y = 2,000},Point{x = 2,000, y = 7,000},Point{x = 3,000, y = 1,000},Point{x = 0,000, y = 0,000}]%n"), points.toString());

        // PointGeneric class implements sorting by distance to origin in increasing order
        CustomQuickSort.sort(points);
        assertEquals(String.format("CustomArrayList [size=4, data=Point{x = 0,000, y = 0,000},Point{x = 1,000, y = 2,000},Point{x = 3,000, y = 1,000},Point{x = 2,000, y = 7,000}]%n"), points.toString());
    }
}