package ua.com.alevel.task3;

import ua.com.alevel.task2.Value;

@Service()
public class FatherClass {
    @Value(value = "name for farther")
    String name;

    @Value()
    String id;

    @Init
    static void printHello(Object obj){
        System.out.println(obj.toString());
    }
    @Override
    public String toString() {
        return "FatherClass{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
