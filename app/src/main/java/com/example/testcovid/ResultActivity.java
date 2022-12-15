package com.example.testcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    String data = "0 - низкая вероятность COVID\n" +
            "1 - средняя вероятность COVID\n" +
            "2 - высокая вероятность COVID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle arguments = getIntent().getExtras();

        TextView hello = findViewById(R.id.yourres);
        double value = (double) arguments.get("result");
        double scale = Math.pow(10, 3);
        double result = Math.ceil(value * scale) / scale;
        hello.setText(String.valueOf(result));

        TextView textRes = findViewById(R.id.price);
        Double doRes = Double.parseDouble(arguments.get("result").toString());
        if (doRes<0.75)
            textRes.setText("Не COVID");
        else if (doRes<2.75)
            textRes.setText("Возможно COVID");
        else
            textRes.setText("Это COVID");

        ArrayList b = (ArrayList) arguments.get("points");
        GraphView graph = (GraphView) findViewById(R.id.resGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        series.appendData(new DataPoint(1,Math.ceil((double)b.get(0) * Math.pow(10,2)) / Math.pow(10,2)),true,500);
        series.appendData(new DataPoint(2,Math.ceil((double)b.get(1) * Math.pow(10,2)) / Math.pow(10,2)),true,500);
        series.appendData(new DataPoint(3,Math.ceil((double)b.get(2) * Math.pow(10,2)) / Math.pow(10,2)),true,500);
        series.appendData(new DataPoint(4,Math.ceil((double)b.get(3) * Math.pow(10,2)) / Math.pow(10,2)),true,500);
        series.appendData(new DataPoint(5,Math.ceil((double)b.get(4) * Math.pow(10,2)) / Math.pow(10,2)),true,500);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Температура", "     Кашель", "Возраст", "Вкус/запах", "     Усталость"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.addSeries(series);
    }

    public void getAllTable(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                data, Toast.LENGTH_LONG);
        toast.show();
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goViewHospitals(View view){
        Intent intent = new Intent(this, ListBeforeMap.class);
        startActivity(intent);
    }
}