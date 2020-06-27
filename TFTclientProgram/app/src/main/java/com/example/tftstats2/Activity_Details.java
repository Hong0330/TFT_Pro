package com.example.tftstats2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Details extends AppCompatActivity {

    private DetailsAdapter adapter;

    private ArrayList<Participant> participants = new ArrayList<Participant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_detail);

        //상세정보 데이터 받음
        Intent intent = getIntent();
        participants = (ArrayList<Participant>) intent.getSerializableExtra("participants");
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
