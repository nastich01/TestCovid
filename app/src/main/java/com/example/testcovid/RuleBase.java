package com.example.testcovid;

import java.util.ArrayList;

public class RuleBase {

    String temperature_value;
    String cough_value;
    String age_value;
    String tasteSmell_value;
    String tiredness_value;
    double result_value;

    public RuleBase(String temperature_value, String cough_value, String age_value, String tasteSmell_value, String tiredness_value, double result_value) {
        this.temperature_value = temperature_value;
        this.cough_value = cough_value;
        this.age_value = age_value;
        this.tasteSmell_value = tasteSmell_value;
        this.tiredness_value = tiredness_value;
        this.result_value = result_value;
    }

    public double temperature_result(double x){
        double res;
        if (this.temperature_value.equals("temp_norm"))
            res= Parameters.temp_norm(x);
        else
            res= Parameters.temp_high(x);
        return res;
    }

    public double cough_result(int x){
        double res;
        if (this.cough_value.equals("cough_yes"))
            res= Parameters.cough_yes(x);
        else
            res= Parameters.cough_no(x);
        return res;
    }

    public double age_result(int x){
        double res;
        if (this.age_value.equals("age_young"))
            res= Parameters.age_young(x);
        else
            res= Parameters.age_middle(x);
        return res;
    }

    public double tasteSmell_result(int x){
        double res;
        if (this.tasteSmell_value.equals("tasteSmell_yes"))
            res= Parameters.tasteSmell_yes(x);
        else
            res= Parameters.tasteSmell_no(x);
        return res;
    }

    public double tiredness_result(int x){
        double res;
        if (this.tiredness_value.equals("tiredness_yes"))
            res= Parameters.tiredness_yes(x);
        else
            res= Parameters.tiredness_no(x);
        return res;
    }

    public double result_result(){
        return this.result_value;
    }

    public static ArrayList<RuleBase> genRules(){
        ArrayList<RuleBase> rules = new ArrayList<RuleBase>();

        rules.add(new RuleBase("temp_norm", "cough_no", "age_young", "tasteSmell_yes", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_no", "age_young", "tasteSmell_yes", "tiredness_yes", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_no", "age_young", "tasteSmell_no", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_no", "age_young", "tasteSmell_no", "tiredness_yes", Parameters.result_no()));

        rules.add(new RuleBase("temp_norm", "cough_no", "age_middle", "tasteSmell_yes", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_no", "age_middle", "tasteSmell_yes", "tiredness_yes", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_no", "age_middle", "tasteSmell_no", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_no", "age_middle", "tasteSmell_no", "tiredness_yes", Parameters.result_yes()));

        rules.add(new RuleBase("temp_norm", "cough_yes", "age_young", "tasteSmell_yes", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_yes", "age_young", "tasteSmell_yes", "tiredness_yes", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_yes", "age_young", "tasteSmell_no", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_yes", "age_young", "tasteSmell_no", "tiredness_yes", Parameters.result_yes()));

        rules.add(new RuleBase("temp_norm", "cough_yes", "age_middle", "tasteSmell_yes", "tiredness_no", Parameters.result_maybe()));
        rules.add(new RuleBase("temp_norm", "cough_yes", "age_middle", "tasteSmell_yes", "tiredness_yes", Parameters.result_no()));
        rules.add(new RuleBase("temp_norm", "cough_yes", "age_middle", "tasteSmell_no", "tiredness_no", Parameters.result_yes()));
        rules.add(new RuleBase("temp_norm", "cough_yes", "age_middle", "tasteSmell_no", "tiredness_yes", Parameters.result_yes()));

        rules.add(new RuleBase("temp_high", "cough_no", "age_young", "tasteSmell_yes", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_high", "cough_no", "age_young", "tasteSmell_yes", "tiredness_yes", Parameters.result_no()));
        rules.add(new RuleBase("temp_high", "cough_no", "age_young", "tasteSmell_no", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_high", "cough_no", "age_young", "tasteSmell_no", "tiredness_yes", Parameters.result_maybe()));

        rules.add(new RuleBase("temp_high", "cough_no", "age_middle", "tasteSmell_yes", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_high", "cough_no", "age_middle", "tasteSmell_yes", "tiredness_yes", Parameters.result_maybe()));
        rules.add(new RuleBase("temp_high", "cough_no", "age_middle", "tasteSmell_no", "tiredness_no", Parameters.result_yes()));
        rules.add(new RuleBase("temp_high", "cough_no", "age_middle", "tasteSmell_no", "tiredness_yes", Parameters.result_yes()));

        rules.add(new RuleBase("temp_high", "cough_yes", "age_young", "tasteSmell_yes", "tiredness_no", Parameters.result_no()));
        rules.add(new RuleBase("temp_high", "cough_yes", "age_young", "tasteSmell_yes", "tiredness_yes", Parameters.result_maybe()));
        rules.add(new RuleBase("temp_high", "cough_yes", "age_young", "tasteSmell_no", "tiredness_no", Parameters.result_yes()));
        rules.add(new RuleBase("temp_high", "cough_yes", "age_young", "tasteSmell_no", "tiredness_yes", Parameters.result_yes()));

        rules.add(new RuleBase("temp_high", "cough_yes", "age_middle", "tasteSmell_yes", "tiredness_no", Parameters.result_maybe()));
        rules.add(new RuleBase("temp_high", "cough_yes", "age_middle", "tasteSmell_yes", "tiredness_yes", Parameters.result_yes()));
        rules.add(new RuleBase("temp_high", "cough_yes", "age_middle", "tasteSmell_no", "tiredness_no", Parameters.result_yes()));
        rules.add(new RuleBase("temp_high", "cough_yes", "age_middle", "tasteSmell_no", "tiredness_yes", Parameters.result_yes()));

        return rules;
    }
}
