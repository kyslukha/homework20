package ua.com.alevel.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<A> aClass = A.class;

        try {
            Constructor<?> constructor = aClass.getDeclaredConstructor();
            setAccessibleConstructor(constructor);
            A newA =  (A) constructor.newInstance();
            Field field_id = newA.getClass().getDeclaredField("id");
            Field field_name = newA.getClass().getDeclaredField("name");
            System.out.println("Before changing:");
            System.out.println(newA);
            setAccessibleTrue(field_id);
            setAccessibleTrue(field_name);
            field_id.set(newA,"321");
            field_name.set(newA, "B");
            setAccessibleFalse(field_id);
            setAccessibleFalse(field_name);
            System.out.println("After changing:");
            System.out.println(newA);
        } catch (NoSuchMethodException | NoSuchFieldException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAccessibleConstructor(Constructor<?> constructor) {
        constructor.setAccessible(true);
    }

    private static void setAccessibleFalse(Field field) {
        field.setAccessible(false);
    }

    private static void setAccessibleTrue(Field field) {
        field.setAccessible(true);
    }
}
