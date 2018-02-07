package com.tulu.java.reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestingReflection {
    public static void main(String[] args){
        Class reflectClass = UFOEnemyShip.class;

        System.out.println("===== Definition =====");
        String className = reflectClass.getName();
        System.out.println(className);

        int classModifier = reflectClass.getModifiers();
        System.out.println(Modifier.isPublic(classModifier));

        Class[] interfaces = reflectClass.getInterfaces();
        if (interfaces.length > 0) {
            System.out.println("Interfaces:");
            for (Class interface_ : interfaces) {
                System.out.println("Interface: " + interface_.getName());
            }
        }

        Class classSuper = reflectClass.getSuperclass();
        System.out.println("Super class: " + classSuper.getName());

        System.out.println("===== Method =====");
        Method[] classMethods = reflectClass.getMethods();
        for (Method method: classMethods) {
            System.out.println("Method name: " + method.getName());

            if (method.getName().startsWith("get")){
                System.out.println("* getter method");
            } else if (method.getName().startsWith("get")){
                System.out.println("* setter method");
            }
            System.out.println("\tReturn type: " + method.getReturnType());

            Class[] parameterTypes = method.getParameterTypes();
            System.out.println("\tParameters:");
            for (Class parameter : parameterTypes) {
                System.out.println("\t\t" + parameter.getName());
            }

            System.out.println();
        }

        System.out.println("===== Manipulate class =====");
        Constructor constructor = null;
        Object constructor2 = null;
        try {
            constructor = reflectClass.getConstructor(new Class[]{EnemyShipFactory.class});
            constructor2 = reflectClass.getConstructor(int.class, String.class).newInstance(12, "rand args");
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UFOEnemyShip newEnemyShip = null;
        EnemyShipFactory shipFactory = null;
        try {
            newEnemyShip = (UFOEnemyShip) constructor.newInstance(shipFactory);
            newEnemyShip.setName("XT-1000");
            System.out.println("Enemy ship value: " + newEnemyShip.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UFOEnemyShip ufoEnemyShipPrivate = new UFOEnemyShip(shipFactory);
        try {
            String idCodeString = "idCode";
            Field privateStringName = UFOEnemyShip.class.getDeclaredField(idCodeString);
            privateStringName.setAccessible(true);
            String valueOfName = (String) privateStringName.get(ufoEnemyShipPrivate);
            System.out.println("Private field name: " + valueOfName);

            String methodName = "getPrivate";
            Method privateMethod = UFOEnemyShip.class.getDeclaredMethod(methodName);
            privateMethod.setAccessible(true);
            String privateReturnValue = (String) privateMethod.invoke(ufoEnemyShipPrivate);
            System.out.println("Private method return: " + privateReturnValue);

            methodName = "getOtherPrivate";
            Class[] classParameters = new Class[]{Integer.TYPE, String.class};
            Object[] params = new Object[]{new Integer(10), new String("random")};
            privateMethod = UFOEnemyShip.class.getDeclaredMethod(methodName, classParameters);
            privateMethod.setAccessible(true);
            privateReturnValue = (String) privateMethod.invoke(ufoEnemyShipPrivate, params);
            System.out.println("Other private method return: " + privateReturnValue);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
