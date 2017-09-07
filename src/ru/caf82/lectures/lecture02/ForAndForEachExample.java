package ru.caf82.lectures.lecture02;

/**
 * Created by ilysko on 24.08.17.
 */
public class ForAndForEachExample {
    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            System.out.println("i = " + i);
        }
        System.out.println();
        int[] intList = {1,2,3,4};
        for (int i : intList) {
            System.out.println("i = " + i);
        }
    }
}
