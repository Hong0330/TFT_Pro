package com.example.tftstats2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Signup extends AppCompatActivity{
    EditText id;
    EditText pw;
    EditText nick;
    NetService netService;
    String msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        netService = new NetService();
        netService.onCreate();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        id = (EditText) findViewById(R.id.editTextTextPersonName);
        pw = (EditText) findViewById(R.id.editTextTextPassword);
        nick = (EditText) findViewById(R.id.editTextTextPersonName2);

        nick.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String ID = id.getText().toString();
                String PW = pw.getText().toString();
                String Nick = nick.getText().toString();

                switch (keyCode){
                    case KeyEvent.KEYCODE_ENTER:
                        msg = "SINGUP$" + ID + "$" + PW + "$" + Nick;
                        System.out.println(msg);
                        Signup.Send send = new Signup.Send(netService.getSocket());
                        send.start();
                        return true;
                }
                return false;
            }
        });
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
                dataOutputStream.flush();
                while(true) {
                    String recv = dataInputStream.readUTF();
                    if(recv.equals("CLEAR")) {  //회원가입 성공
                        Intent intent = new Intent(
                                getApplicationContext(), // 현재 화면의 제어권자
                                MainActivity.class); // 다음 넘어갈 클래스 지정
                        startActivity(intent); // 다음 화면으로 넘어간다
                    }
                    else if(recv.equals("FAIL")){
                        //에러 토스트 출력
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
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
