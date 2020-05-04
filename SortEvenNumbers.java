/* 
Сортировка четных чисел из файла

1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.

Пример ввода:
5
8
-2
11
3
-5
2
10

Пример вывода:
-2
2
8
10


Требования:
1. Программа должна считывать данные с консоли.
2. Программа должна создавать FileInputStream для введенной с консоли строки.
3. Программа должна выводить данные на экран.
4. Программа должна вывести на экран все четные числа считанные из файла отсортированные по возрастанию.
5. Программа должна закрывать поток чтения из файла(FileInputStream).
*/


package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = BFR();
        String fileName = reader.readLine();
        reader.close();
        reader = null;

        ArrayList<Integer> arr = new ArrayList<Integer>();

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader r = new BufferedReader(fr);
        String line;
        while ((line = r.readLine()) != null ){

            arr.add(Integer.parseInt(line));
        }
        r.close();
        fr.close();
        file = null;

        Collections.sort(arr);

        for (int i:arr   ) {
            if (i%2==0){
                System.out.println(i);
            }
        }

    }





    // Рефакторинг. Создание ридера выделено в отдельный метод
    static BufferedReader BFR() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}


////////////////////////////
второй вариант - через FileInputStream, но тоже построчно
////////////////////////////


package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = BFR();
        String fileName = reader.readLine();
        reader.close();
        reader = null;

        ArrayList<Integer> arr = new ArrayList<Integer>();

        FileInputStream fs = new FileInputStream(fileName);
        InputStreamReader is = new InputStreamReader(fs);
        BufferedReader r = new BufferedReader(is);

        String line;
        while ((line = r.readLine()) != null ){
            arr.add(Integer.parseInt(line));
        }
        r.close();
        is.close();
        fs.close();

        Collections.sort(arr);

        for (int i:arr   ) {
            if (i%2==0){
                System.out.println(i);
            }
        }

    }





    // Рефакторинг. Создание ридера выделено в отдельный метод
    static BufferedReader BFR() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
