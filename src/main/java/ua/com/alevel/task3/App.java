package ua.com.alevel.task3;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        ChildClass childClass = new ChildClass();
        FatherClass fatherClass = new FatherClass();
        ClassA classWithoutAnnotation = new ClassA();

        List<String> listOfMarkedClasses = new ArrayList<>();

        inspectForService(childClass,listOfMarkedClasses);
        inspectForService(classWithoutAnnotation,listOfMarkedClasses);
        inspectForService(fatherClass,listOfMarkedClasses);

        System.out.println(listOfMarkedClasses);
    }

    private static <T> void inspectForService(T classToCheck, List<String> listOfMarkedClasses) {

        if(classToCheck.getClass().isAnnotationPresent(Service.class)){
            System.out.println("Annotation is present.");
            listOfMarkedClasses.add(classToCheck.getClass().getSimpleName());
        }else if(!classToCheck.getClass().isAnnotationPresent(Service.class)){
            System.out.println("There is no Service annotation under the class.");
        }
    }

}
