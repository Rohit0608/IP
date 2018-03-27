package com.example.gamincoder.ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    Log.e("start","Error"+e.getMessage());
                }

                Intent intent=new Intent(StartingActivity.this,MainActivity.class);
                StartingActivity.this.startActivity(intent);
                StartingActivity.this.finish();
            }
        }).start();
    }
}
