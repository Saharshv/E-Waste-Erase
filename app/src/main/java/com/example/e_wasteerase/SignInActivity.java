package com.example.e_wasteerase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailText;
    private EditText passwordText;
    private String email = "";
    private String password = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        Button signInButton = findViewById(R.id.button5);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                if(checkPassword(password) && checkEmail(email))
                    signIn(email, password);
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Fill the form completely", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkPassword(String pass)
    {
        return !pass.equals("");
    }

    public boolean checkEmail(String email1)
    {
        return !email1.equals("");
    }

    public void signIn(final String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            db.collection("users")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                String type = "";
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    if(document.getData().get("email").toString().equals(email))
                                                    {
                                                       type = document.getData().get("type").toString();
                                                    }
                                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                                }
                                                if(type.equals("Donor"))
                                                {
                                                    Intent newIntent = new Intent(getApplicationContext(), CalculatorActivity.class);
                                                    startActivity(newIntent);
                                                }
                                                else
                                                {
                                                    Intent newIntent = new Intent(getApplicationContext(), RecyclersActivity.class);
                                                    startActivity(newIntent);
                                                }
                                            } else {
                                                Log.w("TAG", "Error getting documents.", task.getException());
                                            }
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
