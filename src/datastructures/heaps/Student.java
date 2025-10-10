package datastructures.heaps;

public class Student {

    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + " (" + grade + ")";
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }
}
