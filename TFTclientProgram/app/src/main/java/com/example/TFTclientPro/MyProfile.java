package com.example.TFTclientPro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.*;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
    }

    @Override
    public void onBackPressed() { //뒤로 가기 버튼
        super.onBackPressed(); // 이전 액티비티로 돌아가기

    }
}
