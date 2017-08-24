package ru.caf82.lectures.lecture02;

/**
 * Created by ilysko on 24.08.17.
 */
public class BreakAndContinueExample {
    public static void main(String[] args) {
        // Break example
        for (int i = 0; i < 100; i++) {
            if (i == 4) {
                break;
            }
            System.out.print(i + " ");
        }

        System.out.println();

        // Continue example
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }

        System.out.println("\n");

        // Continue and Break with "tags"
        outer:
        for (int i = 0; i < 10; i++) {
            inner:
            for (int j = 0; j < 10; j++) {
                if (j > i) {
                    System.out.println();
                    continue outer;
                }
                if (i == 8) {
                    break outer;
                }
                System.out.print(" " + (i * j));
            }
        }
    }
}
