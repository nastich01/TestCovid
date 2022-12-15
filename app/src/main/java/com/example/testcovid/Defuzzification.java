package com.example.testcovid;

import java.util.ArrayList;

public class Defuzzification {
    public static double defuzzier(ArrayList<Double> c, ArrayList<RuleBase> rules){
        int i = 0;
        double ch = 0;
        double znm = 0;

        while (i < 32)
        {
            ch += c.get(i) * rules.get(i).result_result();
            znm += c.get(i);
            i++;
        }
        System.out.println(ch+" "+znm);
        System.out.println((double) ch/znm);
        System.out.println("--");
        if (ch == 0)
            return 0;
        return ((double)ch / znm);
    }
}
