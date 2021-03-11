package ua.com.alevel.task4;

import ua.com.alevel.task2.Value;
import ua.com.alevel.task3.Init;
import ua.com.alevel.task3.Service;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Scanner {
    public static void main(String[] args) throws ClassNotFoundException {

        List<File> classList = new ArrayList<>();
        List<String> initFileList = new ArrayList<>();
        File path = new File("src//main//java//ua//com//alevel");
        findJava(path, classList);
        List<String> finalPathStringList = getNameToInit(classList, initFileList);
        valueInitialization(annotationCheck(finalPathStringList));
    }


    private static <T> void initialize(T testClass) {
        Field[] fields = testClass.getClass().getFields();
        for (Field field : fields) {
            Value annotationValue = field.getAnnotation(Value.class);
            if (field.isAnnotationPresent(Value.class)) {
                String value = annotationValue.value();
                try {
                    field.set(testClass, value);
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }

            }

        }
    }


    private static <T> T createInstance(Class<T> classToCreate) {
        try {
            Constructor constructor = classToCreate.getConstructor();
            T entity = (T) constructor.newInstance();
            return entity;
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Map<String, Object> annotationCheck(List<String> finalPathStringList) {
        Map<String, Object> resultMap = new TreeMap<>();
        try {
            for (String str : finalPathStringList) {
                Class<?> instanceClass = Class.forName(str);
                if (instanceClass.isAnnotationPresent(Service.class)) {
                    resultMap.put(str, createInstance(instanceClass));
                }

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("something wrong with class");
        }
        return resultMap;
    }

    private static void invokeMethod(Method method, Map<String, Object> objects) {
        for (Map.Entry<String, Object> entry : objects.entrySet()) {
            try {
                method.invoke(method.getClass().getConstructor().newInstance(), entry.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }


    private static Method initAnnotationCheck(List<String> finalPathStringList) throws ClassNotFoundException {

        for (String str : finalPathStringList) {
            Class<?> instanceClass = Class.forName(str);
            Method[] methods = instanceClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    return method;
                }

            }
        }
        return null;
    }

    private static void valueInitialization(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            initialize(entry.getKey().getClass());
        }
    }

    public static List<File> findJava(File currentDir, List<File> classFiles) {
        File[] fileList = currentDir.listFiles();
        for (File file : fileList) {
            if (file.isFile()) {
                if (file.getName().contains(".java")) {
                    classFiles.add(file);
                }
            } else {
                findJava(file, classFiles);
            }
        }
        return classFiles;
    }

    public static List<String> getNameToInit(List<File> fileList, List<String> initFileList) {
        String path = "C:\\Users\\kyslu\\IdeaProjects\\HomeWork20\\src\\main\\java";
        for (File file : fileList) {
            String fileName = file.getAbsolutePath()
                    .replace(path, "")
                    .replace(".java", "")
                    .replace("\\", ".");

            String finalName = fileName.substring(fileName.indexOf(".") + 1);
            initFileList.add(finalName);
        }
        getNecessaryPaths(initFileList);
        return initFileList;
    }

    private static void getNecessaryPaths(List<String> initFileList) {
        for (int i = 0; i < initFileList.size(); i++) {
            if (initFileList.get(i).contains("App")
                    || initFileList.get(i).contains("Service")
                    || initFileList.get(i).contains("Value")) {
                initFileList.remove(i);
            }
        }
    }

}
