package ru.caf82.lectures.lecture02;

/**
 * Created by ilysko on 24.08.17.
 */
public class WhileAndDoWhileExample {
    public static void main(String[] args) {
        int i = 3;

        while (i > 0) {
            System.out.println(i--);
        }

        System.out.println("\n" + i + "\n");

        do {
            System.out.println(i--);
        } while (i > 0);
    }
}
