package ua.com.alevel.task1;

public class A {
    private String id = "123";
    private String name = "A";

    private A() {
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}