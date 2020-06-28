package com.example.tftstats2;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
<<<<<<< HEAD

import com.google.android.material.bottomnavigation.BottomNavigationView;
=======
>>>>>>> refs/remotes/origin/master

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Activity_Profile extends AppCompatActivity{

    BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    FragmentManager fm;
    FragmentTransaction ft;
    cham_fragment frag1;
    trait_fragment frag2;

    RecyclerView recyclerView;
    UnitAdapter unit_adapter;
    TraitAdapter trait_adapter;

    //시너지 26개
    int[] trait = new int[26];
    //유닛 67개
    int[] unit = new int[67];

    ArrayList<String> tmp_trait = new ArrayList<String>();
    ArrayList<String> tmp_unit = new ArrayList<String>();
    LeagueEntry leagueEntry = new LeagueEntry();

    BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    FragmentManager fm;
    FragmentTransaction ft;
    cham_fragment frag1;
    trait_fragment frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        /*
        //시너지 일경우
        recyclerView = findViewById(R.id.trait_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //유닛 일경우

        recyclerView = findViewById(R.id.cham_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        */

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("내 정보");

<<<<<<< HEAD
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.c_tread:
                        setFrag(0);
                        break;
                    case R.id.t_trend:
                        setFrag(1);
                        break;
                }
                return true;
            }
        });
        frag1=new cham_fragment();
        frag2=new trait_fragment();
=======
        //인텐트에서 정보 불러오기
        Intent intent = getIntent();
        leagueEntry = (LeagueEntry) intent.getSerializableExtra("league");
        tmp_trait = intent.getStringArrayListExtra("trait");
        tmp_unit = intent.getStringArrayListExtra("unit");


        for(int i = 0 ; i < tmp_trait.size() ; i++) { //시너지 개수만큼 반복
            switch(tmp_trait.get(i)) {
                case "Astro":
                    trait[0]++;
                    break;
                case "Battlecast":
                    trait[1]++;
                    break;
                case "Blademaster":
                    trait[2]++;
                    break;
                case "Blaster":
                    trait[3]++;
                    break;
                case "Brawler":
                    trait[4]++;
                    break;
                case "Celestial":
                    trait[5]++;
                    break;
                case "Chrono":
                    trait[6]++;
                    break;
                case "Cybernetic":
                    trait[7]++;
                    break;
                case "Darkster":
                    trait[8]++;
                    break;
                case "Demolitionist":
                    trait[9]++;
                    break;
                case "Infiltrator":
                    trait[10]++;
                    break;
                case "Manareaver":
                    trait[11]++;
                    break;
                case "Mechpilot":
                    trait[12]++;
                    break;
                case "Mercenary":
                    trait[13]++;
                    break;
                case "Mystic":
                    trait[14]++;
                    break;
                case "Paragon":
                    trait[15]++;
                    break;
                case "Protector":
                    trait[16]++;
                    break;
                case "Rebel":
                    trait[17]++;
                    break;
                case "Void":
                    trait[18]++;
                    break;
                case "Sniper":
                    trait[19]++;
                    break;
                case "Sorcerer":
                    trait[20]++;
                    break;
                case "Spacepirate":
                    trait[21]++;
                    break;
                case "Starguardian":
                    trait[22]++;
                    break;
                case "Starship":
                    trait[23]++;
                    break;
                case "Valkyrie":
                    trait[24]++;
                    break;
                case "Vanguard":
                    trait[25]++;
                    break;
            }

        }
        //시너지 정보 테스트
        for(int i = 0 ; i < trait.length ; i++) {
            System.out.println( i +" 번째 시너지 : " + trait[i]);
        }

        for(int i = 0 ; i < tmp_unit.size() ; i++) { //유닛 갯수만큼 반복
            switch (tmp_unit.get(i)) {
                case "TFT3_Ahri":
                    unit[0]++;
                    break;
                case "TFT3_Annie":
                    unit[1]++;
                    break;
                case "TFT3_Ashe":
                    unit[2]++;
                    break;
                case "TFT3_AureLionSol":
                    unit[3]++;
                    break;
                case "TFT3_Bard":
                    unit[4]++;
                    break;
                case "TFT3_Blitzcrank":
                    unit[5]++;
                    break;
                case "TFT3_Caitlyn":
                    unit[6]++;
                    break;
                case "TFT3_Cassiopeia":
                    unit[7]++;
                    break;
                case "TFT3_Chogath":
                    unit[8]++;
                    break;
                case "TFT3_Darius":
                    unit[9]++;
                    break;
                case "TFT3_Ekko":
                    unit[10]++;
                    break;
                case "TFT3_Ezreal":
                    unit[11]++;
                    break;
                case "TFT3_Fiora":
                    unit[12]++;
                    break;
                case "TFT3_Fizz":
                    unit[13]++;
                    break;
                case "TFT3_Gangplank":
                    unit[14]++;
                    break;
                case "TFT3_Garen":
                    unit[15]++;
                    break;
                case "TFT3_Gnar":
                    unit[16]++;
                    break;
                case "TFT3_Graves":
                    unit[17]++;
                    break;
                case "TFT3_Illaoi":
                    unit[18]++;
                    break;
                case "TFT3_Irelia":
                    unit[19]++;
                    break;
                case "TFT3_Janna":
                    unit[20]++;
                    break;
                case "TFT3_JarvanIV":
                    unit[21]++;
                    break;
                case "TFT3_Jayce":
                    unit[22]++;
                    break;
                case "TFT3_Jhin":
                    unit[23]++;
                    break;
                case "TFT3_Jinx":
                    unit[24]++;
                    break;
                case "TFT3_Kaisa":
                    unit[25]++;
                    break;
                case "TFT3_Karma":
                    unit[26]++;
                    break;
                case "TFT3_Kassadin":
                    unit[27]++;
                    break;
                case "TFT3_Kayle":
                    unit[28]++;
                    break;
                case "TFT3_Khazix":
                    unit[29]++;
                    break;
                case "TFT3_KogMaw":
                    unit[30]++;
                    break;
                case "TFT3_Leona":
                    unit[31]++;
                    break;
                case "TFT3_Lucian":
                    unit[32]++;
                    break;
                case "TFT3_Lulu":
                    unit[33]++;
                    break;
                case "TFT3_Lux":
                    unit[34]++;
                    break;
                case "TFT3_Malphite":
                    unit[35]++;
                    break;
                case "TFT3_MasterYi":
                    unit[36]++;
                    break;
                case "TFT3_MissFortune":
                    unit[37]++;
                    break;
                case "TFT3_Modekaiser":
                    unit[38]++;
                    break;
                case "TFT3_Nautilus":
                    unit[39]++;
                    break;
                case "TFT3_Neeko":
                    unit[40]++;
                    break;
                case "TFT3_Nocturne":
                    unit[41]++;
                    break;
                case "TFT3_Poppy":
                    unit[42]++;
                    break;
                case "TFT3_Rakan":
                    unit[43]++;
                    break;
                case "TFT3_Riven":
                    unit[44]++;
                    break;
                case "TFT3_Rumble":
                    unit[45]++;
                    break;
                case "TFT3_Shaco":
                    unit[46]++;
                    break;
                case "TFT3_Shen":
                    unit[47]++;
                    break;
                case "TFT3_Sona":
                    unit[48]++;
                    break;
                case "TFT3_Soraka":
                    unit[49]++;
                    break;
                case "TFT3_Syndra":
                    unit[50]++;
                    break;
                case "TFT3_Teemo":
                    unit[51]++;
                    break;
                case "TFT3_Thresh":
                    unit[52]++;
                    break;
                case "TFT3_TwistedFate":
                    unit[53]++;
                    break;
                case "TFT3_Urgot":
                    unit[54]++;
                    break;
                case "TFT3_Vayne":
                    unit[55]++;
                    break;
                case "TFT3_VelKoz":
                    unit[56]++;
                    break;
                case "TFT3_Vi":
                    unit[57]++;
                    break;
                case "TFT3_Viktor":
                    unit[58]++;
                    break;
                case "TFT3_WuKong":
                    unit[59]++;
                    break;
                case "TFT3_Xayah":
                    unit[60]++;
                    break;
                case "TFT3_Xerath":
                    unit[61]++;
                    break;
                case "TFT3_XinZhao":
                    unit[62]++;
                    break;
                case "TFT3_Yasuo":
                    unit[63]++;
                    break;
                case "TFT3_Zed":
                    unit[64]++;
                    break;
                case "TFT3_Ziggs":
                    unit[65]++;
                    break;
                case "TFT3_Zoe":
                    unit[66]++;
                    break;
            }
        }

        //유닛 정보 테스트
        for(int i = 0 ; i < unit.length ; i++) {
            System.out.println( i +" 번째 유닛 : " + unit[i]);
        }


        //리그 정보 업데이트
        System.out.println(leagueEntry.getName());

        if(!(leagueEntry.getName().equals("!!!"))) {

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //닉네임 업데이트
                    TextView nameView = findViewById(R.id.textView2);
                    nameView.setText(leagueEntry.getName());
                    //티어 업데이트
                    TextView tierView = findViewById(R.id.textView3);
                    ImageView tierImage = findViewById(R.id.imageView3);
                    switch (leagueEntry.getTier()) {
                        case "IRON":
                            tierImage.setImageResource(R.drawable.iron);
                            tierView.setText("아이언");
                            break;
                        case "BRONZE":
                            tierImage.setImageResource(R.drawable.bronze);
                            tierView.setText("브론즈");
                            break;
                        case "SILVER":
                            tierImage.setImageResource(R.drawable.silver);
                            tierView.setText("실버");
                            break;
                        case "GOLD":
                            tierImage.setImageResource(R.drawable.gold);
                            tierView.setText("골드");
                            break;
                        case "PLATINUM":
                            tierImage.setImageResource(R.drawable.platinum);
                            tierView.setText("플래티넘");
                            break;
                        case "DIAMOND":
                            tierImage.setImageResource(R.drawable.diamond);
                            tierView.setText("다이아몬드");
                            break;
                        case "MASTER":
                            tierImage.setImageResource(R.drawable.master);
                            tierView.setText("마스터");
                            break;
                        case "GRANDMASTER":
                            tierImage.setImageResource(R.drawable.grandmaster);
                            tierView.setText("그랜드마스터");
                            break;
                        case "CHALLENGER":
                            tierImage.setImageResource(R.drawable.challenger);
                            tierView.setText("챌린저");
                            break;
                        default:
                            break;
                    }
                    //랭크 업데이트
                    TextView rankView = findViewById(R.id.textView10);
                    rankView.setText("티어 " + leagueEntry.getRank());
                    //리그포인트 업데이트
                    TextView lpView = findViewById(R.id.textView11);
                    lpView.setText(String.valueOf(leagueEntry.getLeaguePoints()) + " LP");
                    //승리 업데이트
                    TextView winsView = findViewById(R.id.textView12);
                    winsView.setText(String.valueOf("승      리 : " + leagueEntry.getWins()));
                    //패배 업데이트
                    TextView lossesView = findViewById(R.id.textView13);
                    lossesView.setText(String.valueOf("패      배 : " + leagueEntry.getLosses()));
                    //승률 업데이트
                    TextView aView = findViewById(R.id.textView14);
                    DecimalFormat form = new DecimalFormat("#.##");
                    double rate = ((double)leagueEntry.getWins()/(double)(leagueEntry.getWins() + leagueEntry.getLosses()))*100;
                    aView.setText(String.valueOf("승      률 : "  + form.format(rate) + "%"));

                }
            });
        }

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.c_tread: //챔피언 트렌드 선택
                        setFrag(0);
                        break;
                    case R.id.t_trend: //시너지 트렌드 선택
                        setFrag(1);
                        break;
                }
                return true;
            }
        });


        frag1=new cham_fragment();
        Bundle unitbundle = new Bundle();
        unitbundle.putIntArray("unit" , unit);
        frag1.setArguments(unitbundle);

        frag2=new trait_fragment();
        Bundle traitbundle = new Bundle();
        traitbundle.putIntArray("trait" , trait);
        frag2.setArguments(traitbundle);
>>>>>>> refs/remotes/origin/master
        setFrag(0); // 첫 프래그먼트 화면 지정
    }

    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0:
<<<<<<< HEAD
                ft.replace(R.id.setFrame,frag1);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.setFrame,frag2);
                ft.commit();
                break;

        }
    }

=======
                //유닛 일경우
                ft.replace(R.id.setFrame,frag1);
                ft.commit();
                break;

            case 1:
                //시너지 일경우
                ft.replace(R.id.setFrame,frag2);
                ft.commit();
                break;

        }
    }

    /*
    private void getData(String type) {
        switch (type) {
            case "trait": //이곳에서 어댑터에 데이터 추가
                for(int i = 0 ; i < trait.length ; i++) {
                    Data data = new Data();
                    if(trait[i] > 0) {
                        data.setNumber(i);
                        data.setCount(trait[i]);
                        trait_adapter.addItem(data);
                    }
                }
                trait_adapter.notifyDataSetChanged();
                break;
            case "unit":
                for(int i = 0 ; i < unit.length ; i++) {
                    Data data = new Data();
                    if(unit[i] > 0) {
                        data.setNumber(i);
                        data.setCount(unit[i]);
                        unit_adapter.addItem(data);
                    }
                }
                unit_adapter.notifyDataSetChanged();
                break;
        }
    }
     */
>>>>>>> refs/remotes/origin/master

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}