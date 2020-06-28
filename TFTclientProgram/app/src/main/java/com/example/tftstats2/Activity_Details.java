package com.example.tftstats2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Details extends AppCompatActivity {

    private DetailsAdapter adapter;
    NetService netService;
    String TFT_name;

    private ArrayList<Participant> participants = new ArrayList<Participant>();
    String match_id;
    float game_length;
    String game_variation;

    boolean saved = false; //저장된 매치인지 판단하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_detail);

        netService = new NetService();
        netService.onCreate();

        //상세정보 데이터 받음
        Intent intent = getIntent();
        participants = (ArrayList<Participant>) intent.getSerializableExtra("participants");
        saved = intent.getExtras().getBoolean("save");
        match_id = intent.getExtras().getString("match_id");
        game_length = intent.getExtras().getFloat("game_length");
        game_variation = intent.getExtras().getString("game_variation");
        TFT_name = intent.getExtras().getString("name");
        System.out.println("접속한 닉네임 : " + TFT_name);
        System.out.println("매치 아이디 : " + match_id + " 길이 : " + game_length + " 은하계 : " + game_variation);
        System.out.println(participants.get(0).getUnits().size());
        System.out.println(participants.get(1).getUnits().size());
        System.out.println(participants.get(2).getUser_name());
        System.out.println(participants.get(3).getUser_name());



        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.match_detail);
        setSupportActionBar(myToolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new DetailsAdapter();
        recyclerView.setAdapter(adapter);
        init();
        getData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("상세 정보");
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


                dataOutputStream.writeUTF("SAVE$" + match_id + "$" + TFT_name); // 해당 매치를 저장하는 메시지 전송
                dataOutputStream.flush();

                while(true) {
                    String recv = dataInputStream.readUTF();
                    System.out.println(recv);
                    if (recv.equals("OVERLAP")) { //중복일 경우
                        //중복 토스트 출력
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "이미 저장된 매치입니다", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        break;
                    }
                }

                //데이터 전송
                System.out.println("전송 시작");
                //매치정보 전송
                String matchMsg = "MATCH$" + TFT_name + "$" + match_id + "$" + game_length + "$" + game_variation;
                dataOutputStream.writeUTF(matchMsg);
                dataOutputStream.flush();

                for(int i = 0 ; i < participants.size() ; i++) { //참여자 만큼 반복
                    //유저 정보 전송
                    String userMsg = "USER$" + match_id + "$" + participants.get(i).getUser_name() + "$" + participants.get(i).getGold_left() + "$" + participants.get(i).getLast_round() + "$" + participants.get(i).getLevel() + "$" + participants.get(i).getPlayers_eliminated();
                    dataOutputStream.writeUTF(userMsg);
                    dataOutputStream.flush();

                    for(int t = 0 ; t < participants.get(i).getTraits().size() ; t++) {
                        String traitMsg = "TRAIT$" +  match_id + "$" + participants.get(i).getUser_name() + "$" + t + "$" + participants.get(i).getTraits().get(t).getTrait_name() + "$" + participants.get(i).getTraits().get(t).getNum_units() + "$" + participants.get(i).getTraits().get(t).getTier_current();
                        dataOutputStream.writeUTF(traitMsg);
                        dataOutputStream.flush();
                    }

                    for(int u = 0 ; u < participants.get(i).getUnits().size() ; u++) {
                        String unitMsg = "UNIT$" + match_id + "$" + participants.get(i).getUser_name() + "$" + u + "$" + participants.get(i).getUnits().get(u).getCharacter_id() + "$" + participants.get(i).getUnits().get(u).getTier() + "$" + participants.get(i).getUnits().get(u).getItem_1() + "$" + participants.get(i).getUnits().get(u).getItem_2() + "$" + participants.get(i).getUnits().get(u).getItem_3();
                        dataOutputStream.writeUTF(unitMsg);
                        dataOutputStream.flush();
                    }
                }

                dataOutputStream.writeUTF("CLEAR");
                dataOutputStream.flush();


                while(true) {
                    String recv = dataInputStream.readUTF();
                    if(recv.equals("CLEAR")) {  //저장 성공
                        saved = true;
                        // 성공 토스트 출력
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "저장 성공", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(recv.equals("FAIL")){
                        //에러 토스트 출력
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "저장 실패", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "저장 실패", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
            case R.id.action_save:{
                if(saved){
                    //이미 저장된 정보이므로 토스트 출력
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "이미 저장된 매치입니다", Toast.LENGTH_LONG).show();
                        }
                    });
                    return true;
                }
                else { //저장 진행
                    Send send = new Send(netService.getSocket());
                    System.out.println("저장 진행");
                    send.start();

                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.match_detail);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new DetailsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        for(int i = 0 ; i < participants.size() ; i++){
            Data data = new Data();
            System.out.println(participants.get(i).getUser_name());

            data.setTitle(participants.get(i).getUser_name());
            data.setContent(String.valueOf(participants.get(i).getLevel()));
            data.setRound(participants.get(i).getLast_round());

            ArrayList<String> trait = new ArrayList<String>();
            for(int t = 0 ; t < participants.get(i).getTraits().size() ; t++) {
                String tmp = participants.get(i).getTraits().get(t).getTrait_name();
                tmp = tmp.replaceAll("Set3_" , "");
                trait.add(tmp);
            }

            ArrayList<String> unit = new ArrayList<String>();
            for(int u = 0 ; u < participants.get(i).getUnits().size() ; u++) {
                String tmp = participants.get(i).getUnits().get(u).getCharacter_id();
                System.out.println("유닛 이름 : " +tmp);
                unit.add(tmp);
            }
            data.setTrait(trait);
            data.setUnit(unit);

            // 각 값이 들어간 data를 adapter에 추가합니다.
            System.out.println("데이터 추가");
            adapter.addItem(data);
        }
        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();

        /*
        // 임의의 데이터
        List<String> listTitle = Arrays.asList("아리", "애니", "애쉬", "아우렐리온 솔", "소나", "소라카", "신드라", "샤코",
                "쉔", "다리우스", "피오라", "피즈", "갱플랭크", "가렌", "오공");
        List<String> listContent = Arrays.asList(
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터.",
                "데이터."
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.ahri,
                R.drawable.annie,
                R.drawable.ashe,
                R.drawable.aurelionsol,
                R.drawable.sona,
                R.drawable.soraka,
                R.drawable.syndra,
                R.drawable.shaco,
                R.drawable.shen,
                R.drawable.darius,
                R.drawable.fiora,
                R.drawable.fizz,
                R.drawable.gangplank,
                R.drawable.garen,
                R.drawable.wukong
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();

         */
    }

}
