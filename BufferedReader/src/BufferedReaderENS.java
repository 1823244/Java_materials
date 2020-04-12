/**
 Шаблон кода для ввода строки с клавиатуры с применением массива и ArrayList
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BufferedReaderENS {
    static int dim;
    static String[] stringArray;
    static int[] intArray;
    static ArrayList<String> stringList;

    public static void main(String[] args) throws Exception {

        System.out.println("input array dimension");

        // Обычное создание ридера
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        dim = Integer.parseInt(reader.readLine());

        stringArray = new String[dim];
        intArray = new int[dim];
        stringList = new ArrayList<String>();

        testReader0();

        //testReader1();

        // Вывод содержимого коллекций
        showResult();
    }

    // Рефакторинг. Создание ридера выделено в отдельный метод
    static BufferedReader BFR() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    // "Классическое" создание и использование ридера (с переменной)
    public static void testReader0() throws Exception {

        // Если в коде часто создаем объект ридера, то можно отрефакторить
        BufferedReader reader;
        reader = BFR();

        System.out.println("input "+dim+" integer numbers");

        for (int i = 0; i < dim; i++) {
            stringArray[i] = reader.readLine();
            intArray[i] = Integer.parseInt(stringArray[i]);
            stringList.add(i,stringArray[i]);
        }
    }

    /* Использование ридера без выделения переменной, см. вызов BFR()
       Так можно делать, если в коде создается мало таких объектов,
       иначе накладные расходы на создание будут замедлять код.*/
    public static void testReader1() throws Exception {

        System.out.println("input "+dim+" integer numbers");

        for (int i = 0; i < dim; i++) {
            stringArray[i] = BFR().readLine();
            intArray[i] = Integer.parseInt(stringArray[i]);
            stringList.add(i,stringArray[i]);
        }
    }

    // Для проверки выведем на экран содержимое коллекций
    public static void showResult() {
        // проверка ввода
        System.out.println("Содержимое массива типа String");
        for (int i = 0; i < dim; i++) {
            System.out.println(stringArray[i]);
        }
        System.out.println("Содержимое массива типа int");
        for (int i = 0; i < dim; i++) {
            System.out.println(intArray[i]);
        }
        System.out.println("Содержимое ArrayList типа String");
        for (int i = 0; i < dim; i++) {
            System.out.println(stringList.get(i));
        }

    }

}



