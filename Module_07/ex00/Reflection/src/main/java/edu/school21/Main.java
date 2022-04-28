package edu.school21;

import java.lang.reflect.*;
import java.util.Scanner;

public class Main {
    private static final String SEPARATOR = "---------------------\n";
    private static final String PACKAGE = "edu.school21.";
    private static final String CLASS_JL = "java.lang.";
    private static Class user = User.class;
    private static Class car = Car.class;
    private static Object object;
    private static Class selectedClass = Main.class;

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.print("Classes:\n" +
                "User\nCar\n" + SEPARATOR +
                "Enter class name:\n");
        while (selectedClass.getName().equals(PACKAGE.concat("Main")))
            selectedClass = selectClass();
        aboutClass();
        classConstructor();
        changeField();
        callMethod();
    }

    private static void callMethod() {

        System.out.print(SEPARATOR);
        System.out.println("Enter name of the method for call:");
        Method [] methods = selectedClass.getDeclaredMethods();
        Method selectedMethod = null;
        while (selectedMethod == null) {
            Scanner scanner = new Scanner(System.in);
            String methodName = scanner.nextLine();
            for (Method method : methods) {
                    StringBuilder stringTypes = new StringBuilder();
                    Type [] types = method.getParameterTypes();
                    for (Type type:types) {
                        if (type.getTypeName().startsWith("java.lang."))
                            stringTypes.append(type.getTypeName().substring(10));
                        else
                            stringTypes.append(type.getTypeName());
                        stringTypes.append(" ");
                    }
                    if (stringTypes.length() > 0)
                        stringTypes.setCharAt(stringTypes.length() - 1, ')');
                    else
                        stringTypes.append(")");
                    String tmp = method.getName() + "(" + stringTypes;
                    if (tmp.equals(methodName))
                        selectedMethod = method;
            }
            if (selectedMethod == null) {
                System.err.println("Field not found, try again!");
            }

            Parameter[] parametrs = selectedMethod.getParameters();
            Object[] arguments = new Object[parametrs.length];
            for (int i = 0; i < parametrs.length; i++) {
                String typeName = parametrs[i].getType().getSimpleName();
                while (arguments[i] == null) {
                    System.out.println("Enter " + typeName + " value");
                    arguments[i] = convertValue(typeName, scanner.nextLine());
                }
            }

            Object result = null;
            try {
                selectedMethod.setAccessible(true);
                result = selectedMethod.invoke(object, arguments);
            } catch (IllegalArgumentException e) {
                System.out.println("The method was not called due to incorrect arguments!");
                return;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                return;
            }
            if (!(selectedMethod.getReturnType().getSimpleName().equals("void"))) {
                System.out.println("Method returned:");
                System.out.println(result);
            }
        }
    }

    private static void changeField() throws IllegalAccessException {
        System.out.print(SEPARATOR);
        System.out.println("Enter name of the field for changing:");
        Field [] fields = selectedClass.getDeclaredFields();
        Field selectedField = null;
        while (selectedField == null) {
            Scanner scanner = new Scanner(System.in);
            String fieldName = scanner.nextLine();
            for (Field field : fields) {
                if (field.getName().equals(fieldName))
                    selectedField = field;
            }
            if (selectedField == null) {
                System.err.println("Field not found, try again!");
            }
        }
        System.out.println("Enter " + selectedField.getType().getSimpleName() + " value");
        Scanner scanner = new Scanner(System.in);
        Object value = convertValue(selectedField.getType().getSimpleName(), scanner.nextLine());
        selectedField.setAccessible(true);
        selectedField.set(object, value);
        System.out.println("Object updated:" + object);
    }

    private static void classConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.print(SEPARATOR);
        System.out.println("Let's create an object.");
        Scanner scanner = new Scanner(System.in);
        Constructor constructor = selectedClass.getConstructor();
        object = constructor.newInstance();
        for (Field field : object.getClass().getDeclaredFields()) {
            Object value = null;
            while (value == null) {
                System.out.println(field.getName());
                value = convertValue(field.getType().getSimpleName(), scanner.nextLine());
                field.setAccessible(true);
                try {
                    field.set(object, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            };
        }
        System.out.println("Object created: " + object);
    }

    private static Object convertValue(String typeName, String value) {
        try {
            switch (typeName) {
                case "Integer":
                    return (Integer.parseInt(value));
                case "Long":
                    return (Long.parseLong(value));
                case "Boolean":
                    return (Boolean.parseBoolean(value));
                case "float":
                    return (Float.parseFloat(value));
                case "Double":
                    return (Double.parseDouble(value));
                case "Short":
                    return (Short.parseShort(value));
                case "String":
                    return (value);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error cast " + value + " to " + typeName);
            return null;
        }
    }

    private static void aboutClass() {
        System.out.println("fields:");
        Field [] fields = selectedClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("    " + field.getType().getSimpleName() + " " + field.getName());
        }
        System.out.println("methods:");
        Method [] methods = selectedClass.getDeclaredMethods();
        for (Method method : methods) {
            StringBuilder stringTypes = new StringBuilder();
            Type [] types = method.getParameterTypes();
            for (Type type:types) {
                if (type.getTypeName().startsWith("java.lang."))
                    stringTypes.append(type.getTypeName().substring(10));
                else
                    stringTypes.append(type.getTypeName());
                stringTypes.append(" ");
            }
            if (stringTypes.length() > 0)
                stringTypes.setCharAt(stringTypes.length() - 1, ')');
            else
                stringTypes.append(")");
            System.out.println("    " + method.getReturnType().getSimpleName() +
                    " " + method.getName() + "(" + stringTypes);
        }
    }

    private static Class selectClass() {
        Class selectedClass = Main.class;
        Scanner scanner = new Scanner(System.in);
        String className = PACKAGE.concat(scanner.nextLine());
        if (className.equals(user.getName()))
            selectedClass = user;
        else if (className.equals(car.getName()))
            selectedClass = car;
        else {
            System.err.println("Class not fount. Please try again!");
        }
        return selectedClass;
    }
}