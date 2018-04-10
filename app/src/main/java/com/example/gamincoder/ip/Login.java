package com.example.gamincoder.ip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class Login extends AppCompatActivity {
    List<String> Emails = new ArrayList<>();
    List<String> Passwords = new ArrayList<>();

    EditText mail;
    EditText pass;
    Button register;
    String TAG = "abc";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mail = (EditText) findViewById(R.id.Mail);
        pass = (EditText) findViewById(R.id.Password);
        register = (Button) findViewById(R.id.button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermail = mail.getText().toString();
                String userpass = pass.getText().toString();
                mAuth.createUserWithEmailAndPassword(usermail, userpass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    startActivity(i);
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });

            }

    /*    Emails.add("abc@gmail.com");
        Emails.add("xyz@gmail.com");
        Emails.add("iiitkota@gmail.com");
        Passwords.add("123456");
        Passwords.add("123456");
        Passwords.add("123456");
        instmail = this.findViewById(R.id.editText);
        pass = this.findViewById(R.id.editText2);
        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail2 = instmail.getText().toString();
                String pass2 = pass.getText().toString();
                if (Emails.contains(mail2) && Passwords.contains(pass2)) {
                    int x = Emails.indexOf(mail2);
                    int y = Passwords.indexOf(pass2);
                    if (x == y) {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(Login.this, "Invalid Credentials Specified!!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Login.this, "Email or Password Not Found!!!", Toast.LENGTH_LONG).show();
                }
            }
        });*/
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}

