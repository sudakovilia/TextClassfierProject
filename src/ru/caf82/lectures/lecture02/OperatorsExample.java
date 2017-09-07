package ru.caf82.lectures.lecture02;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by ilysko on 24.08.17.
 */
public class OperatorsExample {
    public static void main(String[] args) {
        System.out.println("Arithmetical operators:");
        int a = 10, b = 3, c = 2;
        float g = 5.14f, f = 2.05f;
        System.out.println("a + c - b = " + (a + c - b));
        System.out.println("b * c = " + b * c);
        System.out.println("a / b = " + a / b); // pay attention for this (integer division)
        System.out.println("g / f = " + g / f);

        System.out.println("\nUnary operators:");
        int d = 4;
        boolean e = true;
        System.out.println("d++ = " + d++);
        System.out.println("d = " + d);
        System.out.println("++d = " + ++d);
        System.out.println("(byte) d = " + Integer.toBinaryString(d));
        System.out.println("(byte) ~d = " + Integer.toBinaryString(~d));
        System.out.println("!e = " + !e);

        System.out.println("\nRelational operators:");
        double p = 5.5d, r = 2.7500000000000001d;
        System.out.println("p / 2 == r = " + (p / 2 == r));

        System.out.println("\nBitwise operators:");
        // TODO разобраться с битовыми представлениями!
        byte t = 0b1110, q = 0b0111;
        byte y = -13;
        System.out.println("t & q = " + Integer.toBinaryString(t & q));
        System.out.println("t | q = " + Integer.toBinaryString(t | q));
        System.out.println("t ^ q = " + Integer.toBinaryString(t ^ q));
        System.out.println("y = " + String.format("%8s", Integer.toBinaryString(y & 0xFF)).replace(' ', '0'));
        System.out.println("-y = " + String.format("%8s", Integer.toBinaryString(-y & 0xFF)).replace(' ', '0'));
        System.out.println("y << 2 = " + (y << 2));
        System.out.println("y >> 2 = " + (y >> 2));
        System.out.println("y >>> 2 = " + (y >>> 2));
        System.out.println("y << 2 = " + Integer.toBinaryString(y << 2));
        System.out.println("y >> 2 = " + Integer.toBinaryString(y >> 2));
        System.out.println("y >>> 2 = " + Integer.toBinaryString(y >>> 2));

        System.out.println("\nLogical operators:");
        boolean b1 = false, b2 = true;
        System.out.println("b1 || b2 = " + (b1 || b2));
        System.out.println("b1 && b2 = " + (b1 && b2));

        System.out.println("\nTernary operators:");
        boolean b3 = true;
        System.out.println("Rolling " + (b3 ? "Stones" : "Trees"));
        System.out.println("Paint It " + (!b3 ? "Red" : "Black"));
    }
}
