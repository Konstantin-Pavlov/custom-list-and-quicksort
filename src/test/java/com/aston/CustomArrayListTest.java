package com.aston;

import com.aston.CustomArrayList.CustomArrayList;
import com.aston.entity.Person;
import com.aston.entity.PointGeneric;
import com.aston.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayListTest {
    @Test
    public void test1() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("C");
        list.add("A");
        list.add("B");
        assertEquals(String.format("CustomArrayList [size=3, data=C,A,B]%n"), list.toString());

        list.sort();
        assertEquals(String.format("CustomArrayList [size=3, data=A,B,C]%n"), list.toString());

    }

    @Test
    public void test2() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.get(1));
    }

    @Test
    public void test3() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void test4() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    public void test5() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(String.format("CustomArrayList [size=5, data=1,2,3,4,5]%n"), list.toString());
    }

    @Test
    public void test6() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(2);
        assertEquals(String.format("CustomArrayList [size=5, data=3,1,5,4,2]%n"), list.toString());
        list.sort();
        assertEquals(String.format("CustomArrayList [size=5, data=1,2,3,4,5]%n"), list.toString());
    }

    @Test
    public void test7() {
        CustomArrayList<Double> list = new CustomArrayList<>();
        list.add(3D);
        list.add(1.65);
        list.add(0.659);
        list.add(7.89);
        list.add(2.14);
        assertEquals(String.format("CustomArrayList [size=5, data=3.0,1.65,0.659,7.89,2.14]%n"), list.toString());
        list.sort();
        assertEquals(String.format("CustomArrayList [size=5, data=0.659,1.65,2.14,3.0,7.89]%n"), list.toString());
    }

    @Test
    public void test8() {
        CustomArrayList<Double> list = new CustomArrayList<>();
        double d;
        list.add(3D);
        list.add(1.65);
        list.add(0.659);
        assertEquals(String.format("CustomArrayList [size=3, data=3.0,1.65,0.659]%n"), list.toString());

        d = list.delete(2);
        assertEquals(0.659, d);
        assertEquals(String.format("CustomArrayList [size=2, data=3.0,1.65]%n"), list.toString());

        d = list.delete(0);
        assertEquals(String.format("CustomArrayList [size=1, data=1.65]%n"), list.toString());
        assertEquals(3.0, d);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

        d = list.delete(0);
        assertEquals(String.format("CustomArrayList [size=0, data=]%n"), list.toString());
        assertEquals(1.65, d);

        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(0));

        assertEquals(String.format("CustomArrayList [size=0, data=]%n"), list.toString());

        assertTrue(list.isEmpty());
    }

    @Test
    public void test9() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(3);
        list.add(1);
        list.add(5);
        assertEquals(String.format("CustomArrayList [size=3, data=3,1,5]%n"), list.toString());
        list.clean();
        assertEquals(String.format("CustomArrayList [size=0, data=]%n"), list.toString());
    }

    @Test
    public void test10() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Kiwi");
        list.add("Apple");
        list.add("Banana");
        assertEquals(String.format("CustomArrayList [size=3, data=Kiwi,Apple,Banana]%n"), list.toString());
        list.sort();
        assertEquals(String.format("CustomArrayList [size=3, data=Apple,Banana,Kiwi]%n"), list.toString());
    }

    @Test
    public void test11() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Banana");
        list.add("Apple");
        list.add("Cherry");

        // Sort in alphabetical order
        list.sort();
        assertEquals(String.format("CustomArrayList [size=3, data=Apple,Banana,Cherry]%n"), list.toString());

        // Sort in reverse alphabetical order
        list.sort(Comparator.reverseOrder());
        assertEquals(String.format("CustomArrayList [size=3, data=Cherry,Banana,Apple]%n"), list.toString());
    }

    @Test
    public void test12() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(9);

        // Sort in natural order
        list.sort(Comparator.naturalOrder());
        assertEquals(String.format("CustomArrayList [size=6, data=1,1,3,4,5,9]%n"), list.toString());

        // Sort in reverse order
        list.sort(Comparator.reverseOrder());
        assertEquals(String.format("CustomArrayList [size=6, data=9,5,4,3,1,1]%n"), list.toString());

//        list.sort(Comparator.comparing(Integer::));
    }

    @Test
    public void test13() {
        CustomArrayList<Person> list = new CustomArrayList<>();
        list.add(new Person("Alice", 30));
        list.add(new Person("Bob", 25));
        list.add(new Person("Charlie", 35));

        // Sort by age
        list.sort(Comparator.comparingInt(Person::getAge));
        assertEquals(String.format("CustomArrayList [size=3, data=Person{name='Bob', age=25},Person{name='Alice', age=30},Person{name='Charlie', age=35}]%n"), list.toString());

        // Sort by name in reverse order
        list.sort(Comparator.comparing(Person::getName).reversed());
        assertEquals(String.format("CustomArrayList [size=3, data=Person{name='Charlie', age=35},Person{name='Bob', age=25},Person{name='Alice', age=30}]%n"), list.toString());
    }

    @Test
    public void test14() {
        CustomArrayList<Student> list = new CustomArrayList<>();
        list.add(new Student("Oleg", 4));
        list.add(new Student("Oleg", 5));
        list.add(new Student("Oleg", 3));
        list.add(new Student("Oleg", 2));
        list.add(new Student("Anya", 2));
        list.add(new Student("Lisa", 2));

        // Sort by grade in descending order, then by name in ascending order
        list.sort(Comparator.comparing(Student::getGrade).reversed().thenComparing(Student::getName));
        assertEquals(String.format("CustomArrayList [size=6, data=Oleg 5,Oleg 4,Oleg 3,Anya 2,Lisa 2,Oleg 2]%n"), list.toString());
    }

    @Test
    public void test15() {
        CustomArrayList<PointGeneric<Double>> list = new CustomArrayList<>();
        list.add(new PointGeneric<>(1.0, 2.0));
        list.add(new PointGeneric<>(2.0, 7.0));
        list.add(new PointGeneric<>(3.0, 1.0));
        list.add(new PointGeneric<>(0.0, 0.0));

        // Sort by distance from origin
        list.sort(Comparator.comparingDouble(p -> p.distanceTo(new PointGeneric<>(0.0, 0.0))));
        assertEquals(String.format("CustomArrayList [size=4, data=Point{x = 0,000, y = 0,000},Point{x = 1,000, y = 2,000},Point{x = 3,000, y = 1,000},Point{x = 2,000, y = 7,000}]%n"), list.toString());
    }

    @Test
    public void test16() {
        CustomArrayList<String> list = new CustomArrayList<>();

        // Test for empty list
        assertFalse(list.contains("A"));

        list.add("A");
        list.add("B");
        list.add("C");

        // Test for elements that are present
        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertTrue(list.contains("C"));

        // Test for elements that are not present
        assertFalse(list.contains("D"));
        assertFalse(list.contains("E"));

        // Test for null element
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void customIteratorTest1() {
        CustomArrayList<String> list = new CustomArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        list.add("A");
        list.add("B");
        list.add("C");

        list.forEach(stringBuilder::append);

        assertEquals("ABC", stringBuilder.toString());
    }

    @Test
    public void customIteratorTest2() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        AtomicInteger sum = new AtomicInteger();
        list.add(1);
        list.add(2);
        list.add(3);

        list.iterator().forEachRemaining(sum::addAndGet);

        assertEquals(6, sum.get());
    }

    @Test
    public void customIteratorSetTest1() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(1, 42);
        assertEquals(String.format("CustomArrayList [size=3, data=1,42,3]%n"), list.toString());

        list.set(0, 2);
        assertEquals(String.format("CustomArrayList [size=3, data=2,42,3]%n"), list.toString());

        list.set(2, 4);
        assertEquals(String.format("CustomArrayList [size=3, data=2,42,4]%n"), list.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, 333));
    }

    @Test
    public void customIteratorSetTest2() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("E1");
        list.add("E2");
        list.add("E3");

        list.set(1, "wow");
        assertEquals(String.format("CustomArrayList [size=3, data=E1,wow,E3]%n"), list.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "d1"));
    }

}
