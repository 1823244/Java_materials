
package com.javarush.task.task08.task0827;

import java.util.Calendar;
import java.util.Date;

/* 
������ � �����
1. ����������� ����� isDateOdd(String date) ���, ����� �� ��������� true, ���� ���������� ���� � ������ ���� - �������� �����, ����� false
2. String date ���������� � ������� FEBRUARY 1 2013
�� �������� ������ ������ ���� ����.

� �����, ���� ������������ ����� Date, ��� ��������� ��� ���������
�������� - ������ ���� ������ ������ ����� � 0 ��. ������ ���������
��������� ���������� �� +40 �� +900 ��.
������������� ������ Calendar ������ ��� ��������

*/

public class Solution {
    public static void main(String[] args) {

        System.out.println(isDateOdd("JANUARY 2 1970"));

    }

    public static boolean isDateOdd(String date) {

        // �������� ������� ����
        Date dd = new Date(date); // ����� ������������ Calendar.getInstance()

        // ��������� ��������� �� ������ ������� ����
        Calendar currentCalendar = Calendar.getInstance();
        // ������������� �������� ��������� � ������������ � �����
        currentCalendar.setTime(dd);

        // ��������� ���������, ������� ����� ��� ���������� 1-� ������ �������� ����
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);

        // ��� ������������ ������ ����
        // ������ ����� (�����: 00:00:00.00), � ����� ��������� �� ��������� �����
        calendar.set(Calendar.YEAR, 1900+dd.getYear());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MONTH, 0);



        // ������� ������� ����� ����� ����������� � ������������
        long diff = currentCalendar.getTimeInMillis() - calendar.getTimeInMillis();

        // � ��������
        long seconds = diff / 1000;
        // � �������
        long minutes = seconds / 60;
        // � �����
        long hours = minutes / 60;
        // � ����
        long days = hours / 24;

        if ( (days + 1 ) % 2 == 0) {
            return false;
        } else {
            return true;
        }

    }
}
