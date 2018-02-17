/**
	�������� �� ���������
*/
package com.javarush.task.task08.task0814;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* 
������ 10? �� ��� �� ���������
*/

public class Solution {
    public static HashSet<Integer> createSet() {
        //�������� ��� ��� ���
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i =  1; i<21; i++) set.add(i);
        return set;

    }

    public static HashSet<Integer> removeAllNumbersMoreThan10(HashSet<Integer> set) {
        // ��� ������! ����� exception Exception in thread "main" java.util.ConcurrentModificationException
        //for (int s : set) {if (s > 10) {set.remove(s);}}
        // ���� ������������ ����� remove() � ���������
        Iterator<Integer> it = set.iterator();
        while ( it.hasNext() ) {
            Integer i = it.next();
            if ( i > 10 ) it.remove();
        }
        return set;
    }

    public static void main(String[] args) {
        HashSet<Integer> s = createSet();
        HashSet<Integer> ss= removeAllNumbersMoreThan10( s);
        System.out.println(ss.size());
        for ( int a : ss) System.out.println(a);
    }
}
