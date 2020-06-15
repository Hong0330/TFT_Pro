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

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.StringTokenizer;

public class bottomview_ctr extends AppCompatActivity{

    ArrayList<Match> matches = new ArrayList<Match>();
    int index = 1;  //참여자 닉네임 구분하는 인덱스

    boolean jsonCheck = true;

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
                    String tmp = dataInputStream.readUTF();
                    System.out.println(tmp);
                    //현재 문자열 안짤리고 들어온상태 이후에 객체 설정 후 문자열로 전달받은 json데이터 파싱 후 저장
                    //처음 받아올 때 매치아이디, 게임길이, 은하계설정, 이후닉네임들
                    Match match = new Match(); //매치를 저장할 인스턴스

                    StringTokenizer st = new StringTokenizer(tmp, "$"); //$기준으로 데이터자름
                    String match_id = st.nextToken();
                    float game_length = Float.parseFloat(st.nextToken());
                    String game_variation = st.nextToken();
                    String name_1 = st.nextToken(); //1번 닉네임
                    String name_2 = st.nextToken(); //2번 닉네임
                    String name_3 = st.nextToken(); //3번 닉네임
                    String name_4 = st.nextToken(); //4번 닉네임
                    String name_5 = st.nextToken(); //5번 닉네임
                    String name_6 = st.nextToken(); //6번 닉네임
                    String name_7 = st.nextToken(); //7번 닉네임
                    String name_8 = st.nextToken(); //8번 닉네임

                    match.setMatch_id(match_id);
                    match.setGame_length(game_length);
                    match.setGame_variation(game_variation);

                    ArrayList<Participant> participants = new ArrayList<Participant>(); //매치에 저장할 리스트
                    jsonCheck = true;
                    while(jsonCheck){
                        String json_tmp = dataInputStream.readUTF(); //String형으로 json데이터 받음
                        JSONObject jsonObject = new JSONObject(json_tmp);

                        Participant participant = new Participant(); //매치에 저장할 인스턴스

                        int gold_left = jsonObject.getInt("gold_left");
                        int last_round = jsonObject.getInt("last_round");
                        int level = jsonObject.getInt("level");
                        int players_eliminated = jsonObject.getInt("players_eliminated");

                        ArrayList<Trait> traits = new ArrayList<Trait>(); //시너지 정보 저장할 리스트

                        String traitString = jsonObject.getString("traits");
                        JSONArray traitArray = new JSONArray(traitString);

                        for(int i = 0 ; i < traitArray.length() ; i++) {
                            Trait trait = new Trait(); // 시너지 정보 저장할 인스턴스

                            JSONObject traitObject = traitArray.getJSONObject(i); //시너지 오브젝트
                            String trait_name = traitObject.getString("name");
                            int num_units = traitObject.getInt("num_units");
                            int tier_current = traitObject.getInt("tier_current");
                            //이후 리스트에 추가
                            trait.setTrait_name(trait_name);
                            trait.setNum_units(num_units);
                            trait.setTier_current(tier_current);
                            //모든 데이터들 저장 후 리스트 추가
                            traits.add(trait);
                        }

                        ArrayList<Unit> units = new ArrayList<Unit>();  //유닛 정보 저장할 리스트

                        String unitString = jsonObject.getString("units");
                        JSONArray unitArray = new JSONArray(unitString);

                        for(int j = 0 ; j < unitArray.length() ; j++ ) {
                            Unit unit = new Unit(); //유닛정보 저장할 인스턴스

                            JSONObject unitObject = unitArray.getJSONObject(j); //유닛 오브젝트
                            String character_id = unitObject.getString("character_id");
                            int tier = unitObject.getInt("tier");
                            //아이템은 존재하지 않을경우 0으로 세팅
                            int item_1 = 0;
                            int item_2 = 0;
                            int item_3 = 0;
                            String itemString = unitObject.getString("items"); // 아이템 파싱
                            //System.out.println(itemString);
                            JSONArray itemArray = new JSONArray(itemString);
                            switch(itemArray.length()) { //아이템 갯수로 아이템 코드 저장
                                case 0: //아이템이 없을 경우
                                    break;
                                case 1: //아이템이 한개 있을 경우
                                    item_1 = (int) itemArray.get(0);
                                    break;
                                case 2:
                                    item_1 = (int) itemArray.get(0);
                                    item_2 = (int) itemArray.get(1);
                                    break;
                                case 3:
                                    item_1 = (int) itemArray.get(0);
                                    item_2 = (int) itemArray.get(1);
                                    item_3 = (int) itemArray.get(2);
                                    break;
                            }
                            //이후 리스트에 추가
                            unit.setCharacter_id(character_id);
                            unit.setTier(tier);
                            unit.setItem_1(item_1);
                            unit.setItem_2(item_2);
                            unit.setItem_3(item_3);
                            //이후 리스트 추가
                            units.add(unit);
                        }
                        //참여자 데이터 정보 인스턴스에 삽입
                        switch(index){ //인덱스에 따른 참여자 닉네임 삽입
                            case 1:
                                participant.setUser_name(name_1);
                                break;
                            case 2:
                                participant.setUser_name(name_2);
                                break;
                            case 3:
                                participant.setUser_name(name_3);
                                break;
                            case 4:
                                participant.setUser_name(name_4);
                                break;
                            case 5:
                                participant.setUser_name(name_5);
                                break;
                            case 6:
                                participant.setUser_name(name_6);
                                break;
                            case 7:
                                participant.setUser_name(name_7);
                                break;
                            case 8:
                                participant.setUser_name(name_8);
                                break;
                        }
                        participant.setGold_left(gold_left);
                        participant.setLast_round(last_round);
                        participant.setLevel(level);
                        participant.setPlayers_eliminated(players_eliminated);
                        participant.setTraits(traits);
                        participant.setUnits(units);
                        //참여자 정보에 삽입
                        participants.add(participant);
                        index++; //인덱스 증가
                        if(index == 9){ //8번째 참가자까지 정보를 저장을 완료한경우
                            index = 1; //인덱스 초기화
                            jsonCheck = false; //데이터 파싱 부분 나가고 매치정보 받길 기다림
                        }
                    }
                    match.setParticipants(participants); //참여자 정보 삽입
                    matches.add(match); //매치 정보 삽입
                    //문자열로 보낼때
                    //
                    /*
                    String tmp = dataInputStream.readUTF();
                    System.out.println(tmp);
                    JSONObject jsonObject = new JSONObject(tmp);
                    jsonObjects.add(jsonObject);
                    System.out.println(jsonObjects.size());
                     */
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

