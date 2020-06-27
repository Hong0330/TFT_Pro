package com.example.tftstats2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class NetService extends Service {


    Socket socket;


    OutputStream outputStream;
    DataOutputStream dataOutputStream;
    //ObjectOutputStream objectOutputStream;

    InputStream inputStream;
    DataInputStream dataInputStream;
    //ObjectInputStream objectInputStream;

    IBinder mBinder = new MyBinder(); // 외부의 액티비티에서 데이터에 접근하기 위한 바인더

    class MyBinder extends Binder {
        NetService getService() { // 서비스 객체를 리턴
            return NetService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 액티비티에서 bindService() 를 실행하면 호출됨
        // 리턴한 IBinder 객체는 서비스와 클라이언트 사이의 인터페이스 정의한다
        return mBinder; // 서비스 객체를 리턴
    }


    @Override
    public void onCreate() {    //초기화 시점
        super.onCreate();

        ClientThread clientThread = new ClientThread();
        clientThread.start();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    /*
    public void send(String str) throws IOException {
        dataOutputStream.writeUTF(str);
        dataOutputStream.flush();
    }
    */

    class ClientThread extends Thread {

        public void run() {
            try {
                System.out.println("test");
                socket = new Socket("192.168.1.2", 9999);
                System.out.println("성공");
                outputStream  = socket.getOutputStream();
                dataOutputStream = new DataOutputStream(outputStream);
                //objectOutputStream = new ObjectOutputStream(outputStream);

                inputStream = socket.getInputStream();
                dataInputStream = new DataInputStream(inputStream);
                //objectInputStream = new ObjectInputStream(inputStream);



            } catch (IOException e) {
                System.out.println("예외발생");
                e.printStackTrace();
            }
        }
    }
}
