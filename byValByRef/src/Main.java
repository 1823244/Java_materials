package com.ens.byvalbyref;

public class Main {

    public static void main(String[] args) {
        byValue();
        byRef();
    }

    private static void byValue() {
        int param = 20;

        ParameterReceptor parameterReceptor = new ParameterReceptor();
        parameterReceptor.test01(param);
        System.out.println("byValue: result param = "+param);

    }

    private static void byRef() {

        Cat cat = new Cat();
        cat.setName("bars");
        System.out.println(cat.getName());
        changeCatName(cat);
        System.out.println(cat.getName());

    }

    private static void changeCatName(Cat cat) {
        cat.setName("divergent");
    }
}
