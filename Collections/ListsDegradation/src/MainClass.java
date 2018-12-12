/**
 * Проверка деградации производительности при работе с коллекциями.
 * Здесь реализованы тесты для ArrayList и LinkedList.
 */

import java.util.*;
public class MainClass {

    public static void main(String[] args) throws Exception {
        TestList();
    }

    public static void TestList() throws Exception {

        ArrayList<String> arr;
        LinkedList<String> list;

        int limit;
        int middleStart;
        int middleEnd;

        long start;
        long end;

        long timeOfOperArr;
        long timeOfOperLinked;

        int qtyOfNewValues; //how many new values we will add to list

        qtyOfNewValues = 500000;

        // Start tests

        //+------------------------------------------------

        for (int k = 100000; k <= 1000000; k = k *10) {
            System.out.println("-------------------- Arrayist -----------------------------------------");
            System.out.println("List size = " + k);

            limit = k;
            middleStart = limit /4;
            System.out.println("Middle of list begins from index " + middleStart);
            middleEnd = limit /4 * 3;
            System.out.println("Middle of list ends with index " + middleEnd);


            arr = new ArrayList<String >();
            list = new LinkedList<String >();

            for (int i = 0 ; i < limit; i++){

                arr.add(Integer.toString(i));
                list.add(Integer.toString(i));
            }

            // GET
            System.out.println("Current operation: get(i). Gets elements from index " + middleStart + " to " + middleEnd);
            start = letsStart();
            ArrayListGet(  arr, middleStart, middleEnd);
            end = letsFinish();
            timeOfOperArr = getTimeOfOperation(start, end);

            start = letsStart();
            ArrayListGet(  list, middleStart, middleEnd);
            end = letsFinish();
            timeOfOperLinked = getTimeOfOperation(start, end);

            showDistance("List get()", timeOfOperArr, timeOfOperLinked);

            // SET
            System.out.println("Current operation: set(i). Sets new value to elements from index " + middleStart + " to " + middleEnd);
            start = letsStart();
            ArrayListSet(  arr, middleStart, middleEnd);
            end = letsFinish();
            timeOfOperArr = getTimeOfOperation(start, end);

            start = letsStart();
            ArrayListSet(  list, middleStart, middleEnd);
            end = letsFinish();
            timeOfOperLinked = getTimeOfOperation(start, end);

            showDistance("List set()", timeOfOperArr, timeOfOperLinked);

            // ADD
            System.out.println("Current operation: add(value). Adds new values into the end of the list. Quantity of values is " + qtyOfNewValues);
            start = letsStart();
            ArrayListAdd(  arr, qtyOfNewValues);
            end = letsFinish();
            timeOfOperArr = getTimeOfOperation(start, end);

            start = letsStart();
            ArrayListAdd(  list, qtyOfNewValues);
            end = letsFinish();
            timeOfOperLinked = getTimeOfOperation(start, end);

            showDistance("List add() at the end of list", timeOfOperArr, timeOfOperLinked);

            // ADD in the middle
            System.out.println("Current operation: add(i, value). Adds new values into the middle of the list from index " + middleStart + " to " + middleEnd + ". Quantity of values is " + qtyOfNewValues);
            start = letsStart();
            ArrayListAddInMiddle(  arr, middleStart, qtyOfNewValues);
            end = letsFinish();
            timeOfOperArr = getTimeOfOperation(start, end);

            start = letsStart();
            ArrayListAddInMiddle(  list, middleStart, qtyOfNewValues);
            end = letsFinish();
            timeOfOperLinked = getTimeOfOperation(start, end);

            showDistance("List add() in the middle", timeOfOperArr, timeOfOperLinked);

            // REMOVE from start
            System.out.println("Current operation: remove(i). Removes values from the start of the list.");
            start = letsStart();
            ArrayListRemoveFromStart(  arr, limit);
            end = letsFinish();
            timeOfOperArr = getTimeOfOperation(start, end);

            start = letsStart();
            ArrayListRemoveFromStart(  list, limit);
            end = letsFinish();
            timeOfOperLinked = getTimeOfOperation(start, end);

            showDistance("List remove() from start", timeOfOperArr, timeOfOperLinked);

            // REMOVE from end
            System.out.println("Current operation: remove(i). Removes values from the end of the list.");
            start = letsStart();
            ArrayListRemoveFromEnd(  arr, limit);
            end = letsFinish();
            timeOfOperArr = getTimeOfOperation(start, end);

            start = letsStart();
            ArrayListRemoveFromEnd(  list, limit);
            end = letsFinish();
            timeOfOperLinked = getTimeOfOperation(start, end);

            showDistance("List remove() from end", timeOfOperArr, timeOfOperLinked);
        }
    }

    public static long letsStart(){
        Date tmTime = new Date();
        long tm = tmTime.getTime();
        return tm;

    }

    public static long letsFinish(){
        Date tmTime = new Date();
        long tm = tmTime.getTime();
        return tm;

    }

    public static long getTimeOfOperation(long start, long end){
        return end - start;
    }

    // Shows duration of the selected operation
    public static void showDistance(String operation, long timeOfOperArr, long timeOfOperLinked){
        System.out.println("Operation is: "+operation+". ArrayList " + timeOfOperArr + " ms, LinkedList " +  timeOfOperLinked + " ms");
    }

    // ArrayList. get()
    public static void ArrayListGet( List<String> arr, int start, int end){
        String a;
        int i = start;
        while   ( i <= end ){
            a = arr.get(i);
            i++;
        }
    }

    // ArrayList. set()
    public static void ArrayListSet( List<String> arr, int start, int end){

        int i = start;
        while   ( i <= end ){
            arr.set(i, " a ");
            i++;
        }
    }

    // ArrayList. add()
    public static void ArrayListAdd( List<String> arr, int qty){

        for (int i = 0 ; i < qty; i++){
            arr.add(" a ");
        }
    }

    // ArrayList. add(i, value)
    public static void ArrayListAddInMiddle( List<String> arr, int start, int qty){

        int i = start;
        while   ( qty >= 0 ){
            arr.set(i, " a ");
            //i++;
            qty--;
        }

    }

    // ArrayList. remove from start()
    public static void ArrayListRemoveFromStart( List<String> arr, int qty) {

        for (int i = 0; i < qty; i++) {
            arr.remove(0);
        }
    }


    // ArrayList. remove from end
    public static void ArrayListRemoveFromEnd( List<String> arr, int qty) {

        for (int i = arr.size() - 1; i >= 0; i--) {
            arr.remove(i);
        }
    }


}












