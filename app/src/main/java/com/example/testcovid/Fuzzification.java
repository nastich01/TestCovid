package com.example.testcovid;

import java.util.ArrayList;

public class Fuzzification {

    public static ArrayList<Double> fuzzifier(ArrayList<RuleBase> rules, double temp_x, int cough_x, int age_x, int taste_x, int tired_x) {
        ArrayList<Double> b = new ArrayList();
        int j = 0;
        while (j < 32) {
            b.add(rules.get(j).temperature_result(temp_x));
            b.add(rules.get(j).cough_result(cough_x));
            b.add(rules.get(j).age_result(age_x));
            b.add(rules.get(j).tasteSmell_result(taste_x));
            b.add(rules.get(j).tiredness_result(tired_x));
            j++;
        }

        return b;
    }
}

