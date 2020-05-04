package com.javarush.task.task13.task1326;

/* 
	Прочитать файл построчно и вывести в консоль
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = BFR();
        String fileName = reader.readLine();
        reader.close();
        reader = null;

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader r = new BufferedReader(fr);
        String line;
        while ((line = r.readLine()) != null ){
            System.out.println(line);
        }
        r.close();
        fr.close();
        file = null;


    }





    // Рефакторинг. Создание ридера выделено в отдельный метод
    static BufferedReader BFR() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
