package com.example.tftstats2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView loginB;
    ImageView signupB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginB = (ImageView) findViewById(R.id.loginbtn);
        signupB = (ImageView) findViewById(R.id.signupbtn);
        loginB.setOnClickListener(this);
        signupB.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.loginbtn:
                intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Login.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
                break;
            case R.id.signupbtn:
                intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Signup.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
                break;
        }

    }

   /* public view clickMethod(view view)
        Imageview b = (Imageview) findViewById(R.id.loginbtn);
        b.setOnClickListener(new b.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        bottomview_ctr.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }*/
}
