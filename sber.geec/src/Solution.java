
package com.geecko;

import org.w3c.dom.ls.LSOutput;

import java.sql.Array;
import java.util.ArrayList;

class Solution {

    public static void main(String[] args) {

        String names = "Tror Gvigris Deriana Nori";
        int res = fourLetters(names);
        System.out.println(res);
        String s1 = "sdf";
        String s2 = "sdf";
        System.out.println(s1==s2);
        String s3 = new String("sdf");
        String s4 = new String("sdf");
        System.out.println(s3==s4);
        System.out.println(s1);
        C c =new C();
        c.test();


    }



    /**
     * Реализуйте метод fourLetters
     */
    public static int fourLetters(String names) {
        // Напишите ваш код здесь...
        String arr[] = names.split(" ");
        ArrayList<String> men = new ArrayList<String>();
        // цикл по словам
        for (int i = 0; i<arr.length; i++) {
            if (arr[i].length() == 4){
                men.add(arr[i]);
            }
        }
        return men.size();
    }

    public static void runCode() {
        // Данный метод будет вызван из метода main
        // Вы можете писать здесь произвольный код для дебага вашего кода
    }
}

  interface A {
    public default void test(){
        System.out.println("A");
    }
}
  interface B {
    public default void test(){
        System.out.println("B");
    }
}
  class C implements A,B {
    public void test(){
        System.out.println("C");
    }
}