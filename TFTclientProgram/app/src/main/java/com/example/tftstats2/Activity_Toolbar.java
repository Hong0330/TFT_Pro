package com.example.tftstats2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.Arrays;
import java.util.List;

public class Activity_Toolbar extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Context context = this;
    private RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        // 추가된 소스, Toolbar를 생성한다.
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.matchlist);
        setSupportActionBar(myToolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        init();
        getData();

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
                    Intent intent = new Intent(
                            getApplicationContext(),
                            Activity_Toolbar.class);
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
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
        //검색했을 때 쿼리 구현
            return true;
        }

        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
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
    }
}
