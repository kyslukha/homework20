package ua.com.alevel.task2;

public class TestClass {
    @Value ()
    private String name;

    @Value(value = "id for testClass")
    private String id;

    @Override
    public String toString() {
        return "TestClass{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
