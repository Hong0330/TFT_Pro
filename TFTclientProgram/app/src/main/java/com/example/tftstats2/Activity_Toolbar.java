package com.example.tftstats2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Activity_Toolbar extends AppCompatActivity {

    NetService netService;
    String name;
    String msg;
    boolean jsonCheck = true;
    int index = 1;
    ArrayList<Match> matches = new ArrayList<Match>();
    LeagueEntry leagueEntry = new LeagueEntry();    //티어정보 저장
    String TFT_name; //접속한 닉네임


    boolean update = false;  //데이터가 업데이트 되었는지 확인

    private DrawerLayout mDrawerLayout;
    private Context context = this;
    private RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //소켓 연결
        netService = new NetService();
        netService.onCreate();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        //닉네임 저장
        Intent intent = getIntent();
        TFT_name = intent.getExtras().getString("name");
        System.out.println("접속한 닉네임 : " + TFT_name);

        // 추가된 소스, Toolbar를 생성한다.
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.matchlist);
        setSupportActionBar(myToolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        //init();
        //getData();
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                System.out.println("세부 정보 클릭");
                Intent intent = new Intent(
                        getApplicationContext(),
                        Activity_Details.class);
                startActivity(intent);
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.saved){
                    System.out.println("저장된 전적");
                    Intent intent = new Intent(
                            getApplicationContext(),
                            Activity_Saved.class);
                    intent.putExtra("name", TFT_name);
                    startActivity(intent);
                }
                else if(id == R.id.profile){
                    Intent intent = new Intent(
                            getApplicationContext(),
                            Activity_Toolbar.class);
                    startActivity(intent);
                }
                else if(id == R.id.logout){
                    Toast.makeText(context, title + ": 로그아웃 시도중", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        // 검색 버튼 클릭했을 때 searchview 길이 꽉차게 늘려주기
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("소환사 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 입력받은 문자열 처리
                Send send = new Send(netService.getSocket());
                name = s;
                msg = "SEARCH$" + s;
                send.start();
                while(true) {
                    if(update) {
                        getData();
                        //adapter.notifyDataSetChanged();
                        System.out.println("업데이트");
                        update = false;
                        break;
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 입력란의 문자열이 바뀔 때 처리
                return false;
            }
        });

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            //검색했을 때 쿼리 구현

            System.out.println("검색 완료");
            return true;
        }

        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                System.out.println("상단 메뉴");
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.matchlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void getData() {
        for(int i = 0 ; i < matches.size() ; i++) {
            Data data  = new Data();
            data.setTitle(matches.get(i).getMatch_id());    //매치 아이디
            data.setContent(matches.get(i).getGame_variation());    //은하계 설정
            for(int j = 0 ; j < matches.get(i).getParticipants().size() ; j ++) { //검색한 닉네임을 찾을때 까지 반복
                if(name.equals(matches.get(i).getParticipants().get(j).getUser_name())) {   //이름이 같으면
                    System.out.println("이름 : " + name + " 비교하는 이름 : " + matches.get(i).getParticipants().get(j).getUser_name());
                    //해당하는 유저의 데이터 저장
                    ArrayList<String> trait = new ArrayList<String>();
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

                String msg = dataInputStream.readUTF();
                StringTokenizer strt = new StringTokenizer(msg , "$");
                String league_tier = strt.nextToken();
                String league_rank = strt.nextToken();
                String league_summonerName = strt.nextToken();
                int league_leaguePoints = Integer.parseInt(strt.nextToken());
                int league_wins = Integer.parseInt(strt.nextToken());
                int league_losses = Integer.parseInt(strt.nextToken());
                leagueEntry.setTier(league_tier);
                leagueEntry.setRank(league_rank);
                leagueEntry.setName(league_summonerName);
                leagueEntry.setLeaguePoints(league_leaguePoints);
                leagueEntry.setWins(league_wins);
                leagueEntry.setLosses(league_losses);

                while(true) {
                    String tmp = dataInputStream.readUTF();
                    System.out.println(tmp);
                    //현재 문자열 안짤리고 들어온상태 이후에 객체 설정 후 문자열로 전달받은 json데이터 파싱 후 저장
                    //처음 받아올 때 매치아이디, 게임길이, 은하계설정, 이후닉네임들
                    if(tmp.equals("CLEAR")) { //완료 메시지 일경우
                        break;
                    }
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
                        System.out.println(json_tmp);
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

                //모든 정보를 받은 상태

                //테트스출력
                System.out.println(matches.get(0).getParticipants().get(0).getUser_name());
                System.out.println(matches.get(1).getParticipants().get(0).getUser_name());
                System.out.println(matches.get(2).getParticipants().get(0).getUser_name());
                System.out.println(matches.get(3).getParticipants().get(0).getUser_name());

                //이 쓰레드에서 아이템을 추가하면 반영이 되는지 테스트
                //Data data = new Data();
                //data.setContent("test");
                //data.setResId(R.drawable.ashe);
                //data.setTitle("테스트");
                //adapter.addItem(data);
                // 테스트 결과 아이템 추가 성공 해당 쓰레드에서 동작을 완수해야함
                // 위 동작들 getData에서 진행

                update = true;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
