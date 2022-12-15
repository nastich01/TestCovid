package com.example.testcovid;

public class Parameters {

    public static double temp_norm(double x){
        return AccessoryFunction.left(36.7,37.1,x);
    }

    public static double temp_high(double x){
        return AccessoryFunction.right(36.7,37.1,x);
    }

    public static double cough_yes(int x){
        return AccessoryFunction.right(0.1,0.9,x);
    }

    public static double cough_no(int x){
        return AccessoryFunction.left(0.1,0.9,x);
    }

    public static double age_young(int x){
        return AccessoryFunction.left(31,50,x);
    }

    public static double age_middle(int x){
        return AccessoryFunction.right(31,50,x);
    }

    public static double tasteSmell_yes(int x){
        return AccessoryFunction.right(0.1,0.9,x);
    }

    public static double tasteSmell_no(int x){
        return AccessoryFunction.left(0.1,0.9,x);
    }

    public static double tiredness_yes(int x){
        return AccessoryFunction.right(0.1,0.9,x);
    }

    public static double tiredness_no(int x){
        return AccessoryFunction.left(0.1,0.9,x);
    }



    public static double result_no(){
        return 0;
    }
    public static double result_maybe(){
        return 1;
    }
    public static double result_yes(){
        return 3;
    }



}

