package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Гласные и согласные
Мама мыла раму.
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        // Обычное создание ридера
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String sss = reader.readLine();

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (char ch : sss.toCharArray()) {
            //System.out.println(ch);
            if (isVowel(ch)) {
                sb1.append(ch);
                sb1.append(" ");
            } else if (Character.isWhitespace(ch))  {
                continue;
            } else {
                sb2.append(ch);
                sb2.append(" ");
            }


        }
        //sb2.append(".");

        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char character) {
        character = Character.toLowerCase(character);  // приводим символ в нижний регистр - от заглавных к строчным буквам
        for (char vowel : vowels) {  // ищем среди массива гласных
            if (character == vowel) {
                return true;
            }
        }
        return false;
    }
}