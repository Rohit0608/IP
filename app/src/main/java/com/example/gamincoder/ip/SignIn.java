package com.example.gamincoder.ip;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
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

public class SignIn extends AppCompatActivity {
    EditText mail;
    EditText pass;
    Button signin;
    String TAG="abc";
    private FirebaseAuth mAuth;
    int NID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth=FirebaseAuth.getInstance();
        mail=(EditText)findViewById(R.id.MAIL);
        pass=(EditText)findViewById(R.id.PASS);
        signin=(Button)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usermail = mail.getText().toString();
                String userpass = pass.getText().toString();
                mAuth.signInWithEmailAndPassword(usermail, userpass)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    displayNotif();
                                    FirebaseUser use=FirebaseAuth.getInstance().getCurrentUser();
                                    final String name=use.getDisplayName();
                                    boolean emailVerified = use.isEmailVerified();
                                    Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(SignIn.this, MainActivity.class);
                                    i.putExtra("message",usermail);
                                    i.putExtra("name",name);
                                    SignIn.this.finish();
                                    startActivity(i);
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(SignIn.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
    }
    protected void displayNotif(){
        Intent i=new Intent(SignIn.this,Notification.class);
        i.putExtra("NID",NID);
        PendingIntent pendingIntent=PendingIntent.getActivity(SignIn.this,0,i,0);
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifBuilder;
        notifBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("IP")
                .setContentText("You have Successfully signed in to your account");
        notifBuilder.setVibrate(new long[] { 100, 100, 100, 100, 100 });
        nm.notify(NID,notifBuilder.build());
    }
}
