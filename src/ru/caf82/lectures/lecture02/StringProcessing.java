package ru.caf82.lectures.lecture02;

public class StringProcessing {

    public static void main(String[] args) {

        //String Buffer examples
        StringBuffer sb = new StringBuffer("Котэ");

        System.out.println("Длина: " + sb.length());
        System.out.println("Объем: " + sb.capacity());

        sb.setCharAt(1, 'o');

        System.out.println("Было Котэ, стало: " + sb.toString());

        String str1 = "У кота ";
        String str2 = " лапы";
        int paws = 4;
        StringBuffer sbNew = new StringBuffer(20);
        sbNew.append(str1).append(paws).append(str2);

        System.out.println(sbNew.toString());

        sb.insert(0, "Люблю ");
        System.out.println(sb.toString());

        sb.reverse();
        System.out.println(sb.toString());

        //TODO delete, deleteCharAt, replace, substring write your own code



        //TODO StringBuilder the same methods implementation

    }

}
