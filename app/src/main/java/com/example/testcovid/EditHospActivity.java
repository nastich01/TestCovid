package com.example.testcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditHospActivity extends AppCompatActivity {

    private EditText nameBox;
    private EditText addressBox;
    private EditText priceBox;
    private EditText phoneBox;
    private EditText latBox;
    private EditText longBox;
    private Button delButton;

    private DataBaseAdapter adapter;
    private long storeId =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hosp);

        nameBox = findViewById(R.id.name);
        addressBox = findViewById(R.id.address);
        priceBox = findViewById(R.id.price);
        phoneBox = findViewById(R.id.phone);
        latBox = findViewById(R.id.latitude);
        longBox = findViewById(R.id.longitude);

        delButton = findViewById(R.id.deleteButton);
        adapter = new DataBaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            storeId = extras.getLong("id");
        }
        // если 0, то добавление
        if (storeId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            Hospital store = adapter.getStore(storeId);
            nameBox.setText(store.getName());
            addressBox.setText(store.getAddress());
            priceBox.setText(store.getPrice());
            phoneBox.setText(store.getPhone());
            latBox.setText(String.valueOf(store.getLatitude()));
            longBox.setText(String.valueOf(store.getLongitude()));
            adapter.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }

    }

    public void save(View view){

        String name = nameBox.getText().toString();
        String address = addressBox.getText().toString();
        String price = priceBox.getText().toString();
        String phone = phoneBox.getText().toString();
        double latitude = Double.parseDouble(latBox.getText().toString());
        double longitude = Double.parseDouble(longBox.getText().toString());

        Hospital store = new Hospital(storeId, name, address, price, phone, latitude, longitude);

        adapter.open();
        if (storeId > 0) {
            adapter.update(store);
        } else {
            adapter.insert(store);
        }
        adapter.close();
        goHome();
    }
    public void delete(View view){

        adapter.open();
        adapter.delete(storeId);
        adapter.close();
        goHome();
    }
    private void goHome(){
        // переход к главной activity
        Intent intent = new Intent(this, ListHospActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}