/*пузырьковая сортировка для строк*/
package com.javarush.task.task08.task0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] array = new String[20];
        //for (int i = 0; i < array.length; i++) { array[i] = reader.readLine(); }
        array[0]= "asdg";
        array[1]= "dfh";
        array[2]= "bsdf";
        array[3]= "bawe";
        array[4]= "fghk";
        array[5]= "wtu";
        array[6]= "ngbgdf";
        array[7]= "aweh";
        array[8]= "uxc";
        array[9]= "poijuhg";
        array[10]= "zvh";
        array[11]= "esgn";
        array[12]= "wergb";
        array[13]= "mjjg";
        array[14]= "asd";
        array[15]= "poik";
        array[16]= "uyrst";
        array[17]= "mnbv";
        array[18]= "estj";
        array[19]= "jhg";

        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {


        String t = "";
        for(int a = 1; a < array.length; a++)
            for(int b = array.length - 1; b >= a; b--) {
                if (isGreaterThan( array[b - 1] , array[b] )) { // Если требуемый порядок следования не соблюдается
                    // элементы меняются местами
                    t = array[b - 1];
                    array[b - 1] = array[b];
                    array[b] = t;
                }
            }


    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }
}
