package com.example.tftstats2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText id;
    EditText pw;
    ImageView btn;
    NetService netService;
    String msg = null;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        netService = new NetService();
        netService.onCreate();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        id = (EditText) findViewById(R.id.editTextTextPersonName);
        pw = (EditText) findViewById(R.id.editTextTextPassword);
        btn = (ImageView) findViewById(R.id.loginButton);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String id = this.id.getText().toString();
        String pw = this.pw.getText().toString();
        msg = "LOGIN$" + id + "$" + pw;
        System.out.println(msg);
        Send send = new Send (netService.getSocket());
        send.start();
    }

    class Send extends Thread {
        Socket socket;
        Send(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            OutputStream outputStream  = null;
            InputStream intputStream = null;
            try {
                outputStream = socket.getOutputStream();
                intputStream = socket.getInputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(intputStream);
                dataOutputStream.writeUTF(msg);
                System.out.println(msg);
                dataOutputStream.flush();

                while(true) {
                    String recv = dataInputStream.readUTF();
                    if(recv.equals("CLEAR")) {  //로그인 성공
                        check = true;
                        Intent intent = new Intent(
                                getApplicationContext(), // 현재 화면의 제어권자
                                Activity_Toolbar.class); // 다음 넘어갈 클래스 지정
                        startActivity(intent); // 다음 화면으로 넘어간다
                    }
                    else if(recv.equals("FAIL")){
                        //에러 토스트 출력
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

