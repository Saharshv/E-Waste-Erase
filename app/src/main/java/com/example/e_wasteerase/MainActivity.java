package com.example.e_wasteerase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signUpButton = findViewById(R.id.button2);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(newIntent);
            }
        });

        Button signInButton = findViewById(R.id.button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(newIntent);
            }
        });

        Button faqButton = findViewById(R.id.button4);

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), FaqActivity.class);
                startActivity(newIntent);
            }
        });

        Button contactusButton = findViewById(R.id.button3);

        contactusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), ContactusActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
