package ru.caf82.lectures.lecture02;

public class PrimitiveTypes {


        public static void main(String[] args) {

            //byte b = 216;
            short s = 1123;
            int i = 64536;
            long l = 2147483648L;
            int j = 10;

            System.out.println(i);
            //System.out.println(b);
            System.out.println(s);
            System.out.println(l);

            System.out.println(i + 2147483647);
            System.out.println(l + j);
            System.out.println(j + l);

            char a = 'a', c = 'c';
            int b;
            b = ((a + c) / 2);
            System.out.println(b);
            System.out.println((char) b);

            System.out.println((char) b + i);
            System.out.println(b+i);

            double doubleOne, doubleTwo = 4.12;
            doubleOne = 22.1 + doubleTwo;
            float pi = 3.14f;
            //float anotherPi = 3.14;
            double doubleThree = 27;
            double d = pi * doubleThree;

            System.out.println(d);
            System.out.println(doubleOne);
            System.out.println(pi);
        }
}
