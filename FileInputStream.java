package com.javarush.task.task13.task1318;

import java.io.*;

/* 
Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.


Требования:
1. Программа должна считывать c консоли имя файла.
2. Программа должна выводить на экран содержимое файла.
3. Поток чтения из файла (FileInputStream) должен быть закрыт.
4. BufferedReader также должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws IOException, Exception {
        // напишите тут ваш код

        BufferedReader reader = BFR();
        String fileName = reader.readLine();
        reader.close();

        FileInputStream fileInputStream = new FileInputStream(fileName);

        try {
            int b = fileInputStream.read();

            while (b != -1) {
                System.out.print((char) b);
                b = fileInputStream.read();
            }
        }
        catch (IOException e) {
            System.out.println("error. "+e.getCause());
            return ;
        }
        fileInputStream.close();
    }

    // Рефакторинг. Создание ридера выделено в отдельный метод
    static BufferedReader BFR() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}




