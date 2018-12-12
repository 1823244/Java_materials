package com.javarush.task.task08.task0817;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Иванов1", "Иван");
        map.put("Иванов2", "Fktr");
        map.put("Иванов3", "asdf");
        map.put("Иванов4", "asdfh");
        map.put("Иванов5", "89hiu");
        map.put("Иванов6", "jbnjsfd");
        map.put("Иванов7", "-98y7yguhj");
        map.put("Иванов8", "Иван");
        map.put("Иванов9", "saudhfi7");
        map.put("Иванов0", "8lhszfi");

        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map) {
        // чтобы избежать Exception in thread "main" java.util.ConcurrentModificationException
        // скопируем исходный мэп и будем в цикле обходить именно его
        HashMap<String, String> copy = new HashMap<String, String>(map);

        Iterator<Map.Entry<String, String>> iterator = copy.entrySet().iterator();
        while (iterator.hasNext())
        {
            //получение «пары» элементов
            Map.Entry<String, String> pair = iterator.next();
            String key = pair.getKey();            //ключ
            String value = pair.getValue();        //значение
            // удалим пару
            iterator.remove();
            // проверим, что такое значение есть в другой паре
            if (copy.containsValue(value)) {
                // если да - удаляем все вхождения из оригинального мэпа
                removeItemFromMapByValue(map, value);
            } else {
                // действий не требуется
            }
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value) {
        // создаем копию мэпа на базе переданного через параметр
        HashMap<String, String> copy = new HashMap<String, String>(map);
        // обходим копию
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            // если в копии элемент равен переданному через параметр value - удаляем из оригинального
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

        // test
        /*
        HashMap<String, String> map = createMap();
        System.out.println(map);
        removeTheFirstNameDuplicates(map);
        System.out.println(map);
        */

    }
}
