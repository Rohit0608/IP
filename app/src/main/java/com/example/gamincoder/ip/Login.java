package com.example.gamincoder.ip;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.gamincoder.ip.User;
import java.util.ArrayList;
import java.util.List;


import static android.widget.Toast.LENGTH_LONG;

public class Login extends AppCompatActivity {
    int NID=1;
    List<String> Emails = new ArrayList<>();
    List<String> Passwords = new ArrayList<>();
    private DatabaseReference mDatabase;
    Button already;
    EditText mail;
    EditText pass;
    Button register;
    String TAG = "abc";
    Spinner num;
    Spinner spinner;
    String hnum;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        String spinnerArray[]={"Choose Your Hostel","Hostel 1(Parijat)","Hostel 2(Chaitanya)","Hostel 3(Satpura)","Hostel 4(Lohit)","Hostel 5(Brihaspathi)","Hostel 6(Kabir)","Hostel 7(Drona)","Hostel 8(Varun)","Aurobindo Hostel"};

        final Spinner spinner=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(Login.this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
        mail = (EditText) findViewById(R.id.Mail);
        pass = (EditText) findViewById(R.id.Password);
        register = (Button) findViewById(R.id.button);
        num=(Spinner) findViewById(R.id.spinner);
        already= (Button)findViewById(R.id.already);
        SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("hnum", spinner.getSelectedItemPosition());
        editor.commit();
        spinner.setSelection(settings.getInt("hnum", 0));
        hnum=spinner.getSelectedItem().toString();
        /*if(hnum!="Choose Your Hostel"){
            register.setEnabled(true);
        }
        else{
            register.setEnabled(false);
        }*/
        /*public class SpinnerActivity extends Login implements AdapterView.OnItemSelectedListener{
            public void onItemSelected(AdapterView<?> parent,View view ,int pos,long id){
                if(hnum!="Choose Your Hostel"){
                    register.setEnabled(true);
                }
                else{
                    register.setEnabled(false);
                }
            }
        }*/

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String usermail = mail.getText().toString();
                String userpass = pass.getText().toString();
                //hnum=num.getText().toString();



                mAuth.createUserWithEmailAndPassword(usermail, userpass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    writeNewUser(user.getDisplayName(),user.getEmail(),hnum);
                                    displayNotif();
                                    final String name = user.getDisplayName();
                                    Toast.makeText(Login.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Login.this, "PLEASE SIGN IN", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Login.this, SignIn.class);
                                    Login.this.finish();
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
        already.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Login.this, SignIn.class);
                startActivity(i);
            }
        });

        }
        protected void displayNotif(){
        Intent i=new Intent(Login.this,Notification.class);
        i.putExtra("NID",NID);
            PendingIntent pendingIntent=PendingIntent.getActivity(Login.this,0,i,0);
            NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder notifBuilder;
            notifBuilder=new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Created Account")
                    .setContentText("Your Account has been created Successfully");
            notifBuilder.setVibrate(new long[] { 100, 100, 100, 100, 100 });

            nm.notify(NID,notifBuilder.build());
        }
    private void writeNewUser( String name, String email, String hnum) {
        User user = new User();
        user.setUsermail(email);
        user.setUsername(name);
        //user.setHostel(hnum);
        mDatabase.child(""+hnum).push().setValue(user);
    }  @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}

