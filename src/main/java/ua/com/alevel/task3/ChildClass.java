package ua.com.alevel.task3;


import ua.com.alevel.task2.Value;

public class ChildClass extends FatherClass {
    @Value(value = "value")
    String name;
    @Value(value = "id")
    String id;

    @Override
    public String toString() {
        return "ChildClass{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    static void printHello(){
        System.out.println("Hello!");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
