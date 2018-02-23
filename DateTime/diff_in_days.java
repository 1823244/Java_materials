
package com.javarush.task.task08.task0827;

import java.util.Calendar;
import java.util.Date;

/* 
–абота с датой
1. –еализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передаетс€ в формате FEBRUARY 1 2013
Ќе забудьте учесть первый день года.

¬ общем, если использовать класс Date, при сравнении дат возникает
проблема - начало года нельз€ задать точно в 0 мс. ¬сегда возникает
небольшое отклонение от +40 до +900 мс.
»спользование класса Calendar решает эту проблему

*/

public class Solution {
    public static void main(String[] args) {

        System.out.println(isDateOdd("JANUARY 2 1970"));

    }

    public static boolean isDateOdd(String date) {

        // ѕолучаем текущую дату
        Date dd = new Date(date); // Ћучше использовать Calendar.getInstance()

        // ‘ормируем календарь на основе текущей даты
        Calendar currentCalendar = Calendar.getInstance();
        // ”станавливаем значение календар€ в соответствии с датой
        currentCalendar.setTime(dd);

        // ‘ормируем календарь, который будет нам показывать 1-е €нвар€ текущего года
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);

        // ƒл€ формировани€ начала года
        // первое число (врем€: 00:00:00.00), а потом переходим на следующий мес€ц
        calendar.set(Calendar.YEAR, 1900+dd.getYear());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MONTH, 0);



        // Ќаходим разницу между двум€ календар€ми в милисекундах
        long diff = currentCalendar.getTimeInMillis() - calendar.getTimeInMillis();

        // в секундах
        long seconds = diff / 1000;
        // в минутах
        long minutes = seconds / 60;
        // в часах
        long hours = minutes / 60;
        // в дн€х
        long days = hours / 24;

        if ( (days + 1 ) % 2 == 0) {
            return false;
        } else {
            return true;
        }

    }
}
