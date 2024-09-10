package com.aston.entity;

/*
https://stepik.org/lesson/90696/step/6?auth=login&unit=66924
Sort this list of students in descending order of grades.
In case of equal scores, students must be sorted by ascending name.

Sample Input:
6
Oleg 4
Oleg 5
Oleg 3
Oleg 2
Anya 2
Lisa 2
Sample Output:
Oleg 5
Oleg 4
Oleg 3
Anya 2
Lisa 2
Oleg 2
 */

public class Student implements Comparable<Student> {
    private final String name;
    private final int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student other) {
        if (this.grade != other.grade) {
            // Sort by grade in descending order
            return Integer.compare(other.grade, this.grade);
        } else {
            // Sort by name in ascending order
            return this.name.compareTo(other.name);
        }
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.grade);
    }
}
