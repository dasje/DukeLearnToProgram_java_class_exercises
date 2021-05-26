package com.example;

public class dnaPlayground {
    public static void main (String[]args) {
        String s = "dukeprogramming";
        String x = s.substring(4,7);
        int y = s.length();
        int z = s.indexOf("program");
        int a = s.indexOf("g");
        Boolean b = s.startsWith("duke");
        Boolean c = s.endsWith("in");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
