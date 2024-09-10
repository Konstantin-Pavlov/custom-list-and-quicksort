# Custom ArrayList and QuickSort Implementation

## Project Description

This project provides a custom implementation of an `ArrayList` and a generic `QuickSort` algorithm in Java. The `CustomArrayList` class supports dynamic resizing and ensures that only elements that implement the `Comparable` interface can be added to the list. The list can be sorted using the natural ordering of its elements or a custom comparator. Additionally, the project includes various entity classes (`Person`, `Student`, `PointGeneric`) that implement the `Comparable` interface and can be used with the `CustomArrayList`.

## Features

- **CustomArrayList**: A custom generic implementation of an ArrayList with dynamic resizing.
- **CustomQuickSort**: A utility class that provides a generic implementation of the QuickSort algorithm.
- **Entity Classes**: `Person`, `Student`, and `PointGeneric` classes that implement the `Comparable` interface. They are created to test CustomQuickSort.
- **Unit Tests**: Comprehensive unit tests for the `CustomArrayList` and `CustomQuickSort` classes.

## Project Structure

<pre>
src
├── main
│   ├── java
│   │   ├── com
│   │   │   ├── aston
│   │   │   │   ├── CustomArrayList
│   │   │   │   │   └── CustomArrayList.java
│   │   │   │   ├── entity
│   │   │   │   │   ├── Person.java
│   │   │   │   │   ├── PointGeneric.java
│   │   │   │   │   └── Student.java
│   │   │   │   ├── sortings
│   │   │   │   │   └── CustomQuickSort.java
├── test
│   ├── java
│   │   ├── com
│   │   │   ├── aston
│   │   │   │   ├── CustomQuickSortTest.java
│   │   │   │   └── CustomQuickSortTest.java
.gitignore
pom.xml
README.md
</pre>

### Prerequisites

- Java 21.0.4 or later
- Maven (for dependency management and build)

### Setup

**Clone the repository**:
    ```sh
    git clone https://github.com/Konstantin-Pavlov/custom-list-and-quicksort.gite
    ```