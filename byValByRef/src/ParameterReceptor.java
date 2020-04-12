package com.ens.byvalbyref;

public class ParameterReceptor {

    public void test01(int param) {

        System.out.println("income parameter = "+param);
        param += 10;
        System.out.println("processed parameter = "+param);
    }

}
