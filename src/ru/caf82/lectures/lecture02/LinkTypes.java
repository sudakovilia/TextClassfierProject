package ru.caf82.lectures.lecture02;

public class LinkTypes {

    public static void main(String[] args) {
        String a = "Hello", b = "World";

        System.out.println(a + " " + b);

        String c = 2 + 2 + "";
        String d = "" + 2 + 2;
        String g = "" + (2 + 2);

        System.out.println(c);
        System.out.println(d);
        System.out.println(g);

        String foo = "a string";
        String bar = "a string";
        String baz = new String("a string");
        
        System.out.println("foo == bar ? " + (foo == bar));
        System.out.println("foo равен bar ? " + (foo.equals(bar)));
        System.out.println("foo == baz ? " + (foo == baz));
        System.out.println("foo равен baz ? " + (foo.equals(baz)));



    }


}
