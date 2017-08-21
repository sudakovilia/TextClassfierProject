package ru.caf82.lectures.lecture02;

public class BoxingExample {

    public static void main(String[] args) {
        Integer i = Integer.valueOf(50);
        Integer iTwo = Integer.valueOf(50);
        Integer iThree = 50;

        System.out.println(i == iThree);
        System.out.println(i == iTwo);
        System.out.println(i.equals(iThree));
        System.out.println(i.equals(iTwo));

        System.out.println("----------------------------------------");

        Integer iNew = Integer.valueOf(1000);
        Integer iNewTwo = Integer.valueOf(1000);
        Integer iNewThree = 1000;

        System.out.println(iNew == iNewThree);
        System.out.println(iNew == iNewTwo);
        System.out.println(iNew.equals(iNewThree));
        System.out.println(iNew.equals(iNewTwo));
    }
}
