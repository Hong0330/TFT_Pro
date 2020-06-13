package com.example.tftstats2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class bottomview_ctr extends AppCompatActivity{
    NetService netService;
    boolean isService = false; // 서비스 중인 확인용

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment_Search fragment_search = new Fragment_Search();
    private Fragment_Save fragment_save = new Fragment_Save();
    private Fragment_Profile fragment_profile = new Fragment_Profile();

    EditText search;
    String msg;
    ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();

    /*
    ServiceConnection conn = new ServiceConnection() {
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            // 서비스와 연결되었을 때 호출되는 메서드
            // 서비스 객체를 전역변수로 저장
            NetService.MyBinder mb = (NetService.MyBinder) service;
            netService = mb.getService();
            System.out.println("서비스 시작");
            // 서비스가 제공하는 메소드 호출하여
            // 서비스쪽 객체를 전달받을수 있슴
            isService = true;
        }
        public void onServiceDisconnected(ComponentName name) {
            // 서비스와 연결이 끊겼을 때 호출되는 메서드
            isService = false;
        }
    };
     */



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment_search).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        /*
        Intent intent = new Intent(
                bottomview_ctr.this,
                NetService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        System.out.println("바인드 서비스 시작");
         */
        netService = new NetService();
        netService.onCreate();

        search = (EditText) findViewById(R.id.editTextTextPersonName3);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //엔터키를 눌렀을때
                    String name = search.getText().toString();
                    System.out.println(name);
                    msg = "SEARCH$" + name;
                    Send send = new Send(netService.getSocket());
                    send.start();
                    return true;
                }
                return false;
            }
        });

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.searchItem:
                    transaction.replace(R.id.frameLayout, fragment_search).commitAllowingStateLoss();
                    break;
                case R.id.cameraItem:
                    transaction.replace(R.id.frameLayout, fragment_save).commitAllowingStateLoss();
                    break;
                case R.id.callItem:
                    transaction.replace(R.id.frameLayout, fragment_profile).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
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
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(intputStream);
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
                while(true) {
                    String msg = dataInputStream.readUTF();
                    if(msg.equals("CLEAR")) { //검색성공
                        System.out.println("검색성공");
                        break;
                    }
                }
                while(true) {
                    JSONObject tmp = (JSONObject) objectInputStream.readObject();
                    jsonObjects.add(tmp);
                    System.out.println(jsonObjects.size());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

