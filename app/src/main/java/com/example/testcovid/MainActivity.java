package com.example.testcovid;

import static com.example.testcovid.RuleBase.genRules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView temperature;
    TextView age;

    int cough;
    int tasteSmell;
    int tiredness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperature = findViewById(R.id.temperature);
        age = findViewById(R.id.age);
    }

    public void coughListen(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.haveCough:
                if (checked){
                    cough=1;
                }
                break;
            case R.id.noCough:
                if (checked){
                    cough=0;
                }
                break;
        }
    }

    public void tasteSmellListen(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.haveTS:
                if (checked){
                    tasteSmell=1;
                }
                break;
            case R.id.noTS:
                if (checked){
                    tasteSmell=0;
                }
                break;
        }
    }

    public void tirednessListen(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.haveU:
                if (checked){
                    tiredness=1;
                }
                break;
            case R.id.noU:
                if (checked){
                    tiredness=0;
                }
                break;
        }
    }

    public void getRes(View view){
        try {
            ArrayList<RuleBase> rules = genRules();
            ArrayList<Double> b = Fuzzification.fuzzifier(rules, Double.parseDouble(temperature.getText().toString()), cough, Integer.valueOf(age.getText().toString()), tasteSmell, tiredness);
            System.out.println(b);
            ArrayList<Double> c = Aggregation.aggregator(b);
            System.out.println("==================");
            System.out.println(c);
            double defRes = Defuzzification.defuzzier(c, rules);
            System.out.println("==================");
            System.out.println(defRes);

            ArrayList<Double> badRes = new ArrayList<>();
            badRes.add(Parameters.temp_high(Double.parseDouble(temperature.getText().toString())));
            badRes.add(Parameters.cough_yes(cough));
            badRes.add(Parameters.age_middle(Integer.valueOf(age.getText().toString())));
            badRes.add(Parameters.tasteSmell_no(tasteSmell));
            badRes.add(Parameters.tiredness_yes(tiredness));

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("result", defRes);
            intent.putExtra("points", badRes);
            startActivity(intent);
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Проверьте правильность введенных данных", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void goAdmin(View view){
        // переход к главной activity
        Intent intent = new Intent(this, ListHospActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}