https://javarush.ru/groups/posts/cikly-java

while (expression) {
     statement(s)
}

public class WhileExample {
    public static void main(String[] args) {
        int countDown = 10;

        while (countDown >= 0) {
            System.out.println("До старта: " + countDown);
            countDown --;
        }
		
------------------------------

do {
     statement(s)
} while (expression);

public class DoWhileExample {
    public static void main(String[] args) {
        int count = 1;
        do {
            System.out.println("count = " + count);
            count ++;
        } while (count < 11);
    }
}
---------------------------------
for (initialization; termination; increment) {
    statement(s)
}

// бесконечный цикл
for ( ; ; ) {
    // код тела цикла
}

public class ForExample {

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            System.out.println("Строка №" + i);
        }
    }
}

-----------------------------------

Цикл for each

for (Type var : vars) {
    statement(s)
}


Здесь: 

    vars — переменная, существующий список или массив
    Type var — определение новой переменной того же типа (Type), что и коллекция vars.


public class ForExample {

    public static void main(String[] args) {
        String[] daysOfWeek =
                { "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье" };


        for (String dayOfWeek : daysOfWeek) {
            System.out.println(dayOfWeek);
        }
    }
}

---------------------------------

https://metanit.com/java/tutorial/2.6.php

Операторы continue и break
Оператор break позволяет выйти из цикла в любой его момент, даже если цикл не закончил свою работу:
	
for (int i = 0; i < 10; i++){
    if (i == 5)
        break;
    System.out.println(i);
}

Когда счетчик станет равным 5, сработает оператор break, и цикл завершится.

Теперь сделаем так, чтобы если число равно 5, цикл не завершался, а просто переходил к следующей итерации. Для этого используем оператор continue:
	
for (int i = 0; i < 10; i++){
    if (i == 5)
        continue;
    System.out.println(i);
}

В этом случае, когда выполнение цикла дойдет до числа 5, программа просто пропустит это число и перейдет к следующему.