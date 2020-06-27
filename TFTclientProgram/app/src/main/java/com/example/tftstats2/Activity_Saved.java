package com.example.tftstats2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import java.util.StringTokenizer;

public class Activity_Saved extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Context context = this;
    private RecyclerAdapter adapter;

    String TFT_name;
    NetService netService;
    ArrayList<Match> matches = new ArrayList<Match>();
    Match match;
    ArrayList<Participant> participants = new ArrayList<Participant>();
    Participant participant = new Participant();
    ArrayList<Trait> traits = new ArrayList<Trait>();
    ArrayList<Unit> units = new ArrayList<Unit>();
    boolean update = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        netService = new NetService();
        netService.onCreate();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_data);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.saved_match_list);

        //닉네임 저장
        Intent intent = getIntent();
        TFT_name = intent.getExtras().getString("name");
        System.out.println("접속한 닉네임 : " + TFT_name);

        setSupportActionBar(myToolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        //init();
        //getData();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("저장한 매치");

        Send send = new Send(netService.getSocket());
        send.start();

        while(true) {
            if(update) {
                update = false;
                getData();
                System.out.println("불러오기 성공");
                break;
            }
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
                String msg = "CALL$" + TFT_name;
                dataOutputStream.writeUTF(msg);
                System.out.println(msg);
                dataOutputStream.flush();

                while(true) {
                    String recv = dataInputStream.readUTF();
                    System.out.println(recv);

                    if(recv.equals("FAIL")) { //실패시

                    }
                    if(recv.equals("MATCHCLEAR")) {
                        match.setParticipants(participants);
                        matches.add(match);
                        // 인스턴스 초기화
                        participants = new ArrayList<Participant>();
                    }
                    if(recv.equals("USERCLEAR")) { //한명의 유저정보 완성
                        participants.add(participant);
                    }
                    if(recv.equals("TRAITCLEAR")) {
                        participant.setTraits(traits);
                    }
                    if(recv.equals("UNITCLEAR")) {
                        participant.setUnits(units);
                    }
                    if(recv.equals("CLEAR")) {
                        update = true;
                    }

                    StringTokenizer st = new StringTokenizer(recv , "$");
                    String message = st.nextToken();
                    switch(message) {
                        case "MATCH":
                            match = new Match();
                            String match_id = st.nextToken();
                            float game_length = Float.parseFloat(st.nextToken());
                            String game_variation = st.nextToken();
                            match.setMatch_id(match_id);
                            match.setGame_length(game_length);
                            match.setGame_variation(game_variation);
                            break;
                        case "USER":
                            participant = new Participant();
                            traits = new ArrayList<Trait>();
                            units = new ArrayList<Unit>();
                            String user_match_id = st.nextToken();
                            String user_user_name = st.nextToken();
                            int user_gold_left = Integer.parseInt(st.nextToken());
                            int user_last_round = Integer.parseInt(st.nextToken());
                            int user_level = Integer.parseInt(st.nextToken());
                            int user_player_eliminated = Integer.parseInt(st.nextToken());
                            participant.setUser_name(user_user_name);
                            participant.setGold_left(user_gold_left);
                            participant.setLast_round(user_last_round);
                            participant.setLevel(user_level);
                            participant.setPlayers_eliminated(user_player_eliminated);
                            break;
                        case "TRAIT":
                            Trait trait = new Trait();
                            String trait_match_id = st.nextToken();
                            String trait_user_name = st.nextToken();
                            String trait_trait_name = st.nextToken();
                            trait.setTrait_name(trait_trait_name);
                            trait.setTier_current(1);
                            traits.add(trait);
                            break;
                        case "UNIT":
                            Unit unit = new Unit();
                            String unit_match_id = st.nextToken();
                            String unit_user_name = st.nextToken();
                            String unit_character_id = st.nextToken();
                            int unit_tier = Integer.parseInt(st.nextToken());
                            int item_1 = Integer.parseInt(st.nextToken());
                            int item_2 = Integer.parseInt(st.nextToken());
                            int item_3 = Integer.parseInt(st.nextToken());
                            unit.setCharacter_id(unit_character_id);
                            unit.setTier(unit_tier);
                            unit.setItem_1(item_1);
                            unit.setItem_2(item_2);
                            unit.setItem_3(item_3);
                            units.add(unit);
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.saved_match_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        System.out.println("매치 : " + matches.size());
        for(int i = 0 ; i < matches.size() ; i++) {
            Data data  = new Data();
            data.setTitle(matches.get(i).getMatch_id());    //매치 아이디
            data.setContent(matches.get(i).getGame_variation());    //은하계 설정
            System.out.println("참여자 : " + matches.get(i).getParticipants().size());
            for(int j = 0 ; j < matches.get(i).getParticipants().size() ; j ++) { //검색한 닉네임을 찾을때 까지 반복
                if(TFT_name.equals(matches.get(i).getParticipants().get(j).getUser_name())) {   //이름이 같으면
                    System.out.println("이름 : " + TFT_name + " 비교하는 이름 : " + matches.get(i).getParticipants().get(j).getUser_name());
                    //해당하는 유저의 데이터 저장
                    ArrayList<String> trait = new ArrayList<String>();
                    System.out.println("시너지 개수 : " + matches.get(i).getParticipants().get(j).getTraits().size());
                    for(int t = 0 ; t < matches.get(i).getParticipants().get(j).getTraits().size() ; t++) { //시너지 갯수만큼 반복
                        if(matches.get(i).getParticipants().get(j).getTraits().get(t).getTier_current() > 0){ //시너지가 적용되는경우
                            String tmp = matches.get(i).getParticipants().get(j).getTraits().get(t).getTrait_name();
                            tmp = tmp.replaceAll("Set3_" , "");
                            trait.add(tmp);
                        }
                    }
                    ArrayList<String> unit = new ArrayList<String>();
                    for(int u = 0 ; u < matches.get(i).getParticipants().get(j).getUnits().size() ; u++) { //유닛 갯수만큼 반복
                        String tmp = matches.get(i).getParticipants().get(j).getUnits().get(u).getCharacter_id();
                        unit.add(tmp);
                    }
                    data.setTrait(trait);
                    data.setUnit(unit);
                    // 각 값이 들어간 data를 adapter에 추가합니다.
                    System.out.println("데이터 추가");
                    adapter.addItem(data);
                }
            }
        }
        // adapter의 값이 변경되었다는 것을 알려줍니다.
        System.out.println("어댑터 값 변경");
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