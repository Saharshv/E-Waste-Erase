package com.example.e_wasteerase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity {

    private int numberOfEditTexts = 0;
    private EditText amountText;
    private int totalPrice;
    private Spinner firstSpinner;
    private HashMap<String, Integer> appliances;
    private EditText firstQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        appliances = new HashMap<>();
        fillAppliances();
        amountText = findViewById(R.id.amountText);

        firstSpinner = findViewById(R.id.spinner);
        firstQuantity = findViewById(R.id.amountText);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.appliances_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        firstSpinner.setAdapter(adapter);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstQuantity.getText().toString().equals("") && isNumeric(firstQuantity.getText().toString())) {
                    totalPrice = appliances.get(firstSpinner.getSelectedItem().toString()) * Integer.parseInt(firstQuantity.getText().toString());
                    boolean allNumbers = true;
                    for (int i = 0; i < numberOfEditTexts && allNumbers; i += 2) {
                        EditText newQuantityText = findViewById(i + 2);
                        if(!isNumeric(newQuantityText.getText().toString()) || newQuantityText.getText().toString().equals("")) {
                            allNumbers = false;
                        }
                    }
                    if(allNumbers) {

                        for (int i = 0; i < numberOfEditTexts; i += 2) {
                            Spinner newSpinner = findViewById(i + 1);
                            EditText newQuantity = findViewById(i + 2);
                            totalPrice += appliances.get(newSpinner.getSelectedItem().toString()) * Integer.parseInt(newQuantity.getText().toString());
                        }

                        Intent newIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                        newIntent.putExtra("totalPrice", totalPrice);
                        startActivity(newIntent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Please make sure all quantities are numbers.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Please make sure all quantities are numbers.", Toast.LENGTH_SHORT).show();
            }
        });

        final Button add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Line();
            }
        });
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public void fillAppliances() {
        appliances.put("Air conditioner",2500);
        appliances.put("Blender",600);
        appliances.put("Camcorder",1000);
        appliances.put("Ceiling fan",200);
        appliances.put("Clock",50);
        appliances.put("Graphing calculator",100);
        appliances.put("Clothes dryer",1500);
        appliances.put("Clothes iron",700);
        appliances.put("Juicer",700);
        appliances.put("Washing machine",2000);
        appliances.put("Combo washer dryer",2500);
        appliances.put("Computer",1100);
        appliances.put("Modem",500);
        appliances.put("Mouse",100);
        appliances.put("Printer",400);
        appliances.put("Keyboard",100);
        appliances.put("Charging Wires",100);
        appliances.put("Convection oven",1900);
        appliances.put("Deep fryer",1000);
        appliances.put("Dehumidifier",1000);
        appliances.put("Digital camera(NON Dslr)",500);
        appliances.put("Digital camera (DSLR)",1100);
        appliances.put("DVD player",400);
        appliances.put("Electric cooker",300);
        appliances.put("Electric razor",100);
        appliances.put("Electric toothbrush",100);
        appliances.put("Electric water boiler,", 500);
        appliances.put("Desk fan Food processor",1000);
        appliances.put("Freezer",1000);
        appliances.put("Garbage disposal unit",400);
        appliances.put("Gaming devices",400);
        appliances.put("Iron",500);
        appliances.put("Microphone",200);
        appliances.put("Microwave oven",1000);
        appliances.put("Mixer",500);
        appliances.put("Oil heater",700);
        appliances.put("Oven",1200);
        appliances.put("Phone(mobile)",1000);
        appliances.put("Pressure-cooker",400);
        appliances.put("Radiator (heating)",1000);
        appliances.put("Refrigerator",5000);
        appliances.put("Stereo",300);
        appliances.put("Telephone(landline)",1000);
        appliances.put("Television >32 inches",700);
        appliances.put("Television <32 inches",1100);
        appliances.put("Receiver",100);
        appliances.put("Remote",50);
        appliances.put("Speaker",400);
        appliances.put("Toaster",400);
        appliances.put("Vacuum cleaner",1000);
        appliances.put("Waffle iron",300);
        appliances.put("cooker",300);
        appliances.put("Water purifier",500);
    }

    public void Add_Line() {
        LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayoutVertical);

        LinearLayout parent = new LinearLayout(this);

        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.HORIZONTAL);

        //children of parent linearlayout

        ll.addView(parent);

        float pixels =  80 * this.getResources().getDisplayMetrics().density;
        float pixels2 =  65 * this.getResources().getDisplayMetrics().density;
        float pixels3 = 250 * this.getResources().getDisplayMetrics().density;

        // add spinner
        Spinner spinner = new Spinner(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int)pixels3, (int)pixels2);
        spinner.setLayoutParams(p);
        spinner.setId(numberOfEditTexts + 1);
        parent.addView(spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.appliances_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        numberOfEditTexts++;

        // add edittext
        EditText et = new EditText(this);

        p = new LinearLayout.LayoutParams((int)pixels, (int)pixels2);
        et.setLayoutParams(p);
        et.setHint("Quantity");
        et.setId(numberOfEditTexts + 1);
        et.setGravity(Gravity.CENTER);
        parent.addView(et);
        numberOfEditTexts++;
    }
}
