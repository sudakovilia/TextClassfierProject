package ru.caf82.lectures.lecture02;

/**
 * Created by ilysko on 24.08.17.
 */
public class IfElseAndSwitchCaseExample {
    public static void main(String[] args) {
        int myMark = 5;

        if (myMark == 5) {
            System.out.println("Excellent");
        } else if (myMark <= 4 && myMark >=3) {
            System.out.println("Not bad");
        } else {
            System.out.println("Bad");
        }

        switch (myMark) {
            case 5:
                System.out.println("Excellent");
                break;
            case 4:
                System.out.println("Good");
                break;
            case 2:
                System.out.println("Bad");
            default:
                System.out.println("Not bad");
                break;
        }
    }
}
