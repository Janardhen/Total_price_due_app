package com.zybooks.totalDueCost;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {
    private TextView outputText;
    private Spinner serviceSpinner;
    private Spinner discountSpinner;
    private HashMap<String,Integer> priceMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceSpinner = (Spinner) findViewById(R.id.attendEditText);
        discountSpinner = (Spinner) findViewById(R.id.attendEditText1);

        outputText = findViewById(R.id.answerTextView);

        addAdapterToSpinner(serviceSpinner,R.array.services);
        addAdapterToSpinner(discountSpinner,R.array.discount);

        createPriceMap();
    }

    private void addAdapterToSpinner(Spinner s,int Array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(Array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    private void createPriceMap() {
        priceMap = new HashMap<String,Integer>();
        priceMap.put("Makeover", 125);
        priceMap.put("Hair Styling", 60);
        priceMap.put("Permanent Makeup", 200);
        priceMap.put("ManiCure", 35);
    }

    public void calculateClick(View view) {
        String service = serviceSpinner.getSelectedItem().toString();
        String discount = discountSpinner.getSelectedItem().toString();

        outputText.setText("Your Total due cost : " + calculateCost());
    }

    private double calculateCost() {
        String serviceString = serviceSpinner.getSelectedItem().toString();
        String discountString = discountSpinner.getSelectedItem().toString();

        int discount = Integer.parseInt(discountString);

        double price = priceMap.get(serviceString);
        price *= 1 - (discount/100.0);


        return price; // TODO
    }
}
