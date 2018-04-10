package com.example.gamincoder.ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class Login extends AppCompatActivity {
    List<String> Emails = new ArrayList<>();
    List<String> Passwords = new ArrayList<>();

    EditText instmail;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Emails.add("abc@gmail.com");
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
        });
    }
}
