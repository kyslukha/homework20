package ua.com.alevel.task2;

public class AnnotationTestClass {

    @Value(value = "Bill")
    private String name;

    @Value(value = "456")
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
