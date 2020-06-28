package com.example.tftstats2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public UnitAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_chams, parent, false);
        return new UnitAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitAdapter.ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        //유닛 필드
        private ImageView unitView;     //유닛 이미지
        private TextView unitText1;
        private TextView unitText2;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            unitView = itemView.findViewById(R.id.imageView12);
            unitText1 = itemView.findViewById(R.id.textView7);
            unitText2 = itemView.findViewById(R.id.textView19);
        }

        void onBind(Data data) {
            if(data.getCount() > 0) {
                switch(data.getNumber()) {
                    case 0:
                        unitView.setImageResource(R.drawable.ahri);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("아리");
                        break;
                    case 1:
                        unitView.setImageResource(R.drawable.annie);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("애니");
                        break;
                    case 2:
                        unitView.setImageResource(R.drawable.ashe);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("애쉬");
                        break;
                    case 3:
                        unitView.setImageResource(R.drawable.aurelionsol);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("아우렐리온 솔");
                        break;
                    case 4:
                        unitView.setImageResource(R.drawable.bard);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("바드");
                        break;
                    case 5:
                        unitView.setImageResource(R.drawable.blitzcrank);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("블리츠크랭크");
                        break;
                    case 6:
                        unitView.setImageResource(R.drawable.caitlyn);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("케이틀린");
                        break;
                    case 7:
                        unitView.setImageResource(R.drawable.cassiopeia);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("카시오페아");
                        break;
                    case 8:
                        unitView.setImageResource(R.drawable.chogath);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("초가스");
                        break;
                    case 9:
                        unitView.setImageResource(R.drawable.darius);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("다리우스");
                        break;
                    case 10:
                        unitView.setImageResource(R.drawable.ekko);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("에코");
                        break;
                    case 11:
                        unitView.setImageResource(R.drawable.ezreal);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("이즈리얼");
                        break;
                    case 12:
                        unitView.setImageResource(R.drawable.fiora);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("피오라");
                        break;
                    case 13:
                        unitView.setImageResource(R.drawable.fizz);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("피즈");
                        break;
                    case 14:
                        unitView.setImageResource(R.drawable.gangplank);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("갱플랭크");
                        break;
                    case 15:
                        unitView.setImageResource(R.drawable.garen);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("가렌");
                        break;
                    case 16:
                        unitView.setImageResource(R.drawable.gnar);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("나르");
                        break;
                    case 17:
                        unitView.setImageResource(R.drawable.graves);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("그레이브즈");
                        break;
                    case 18:
                        unitView.setImageResource(R.drawable.illaoi);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("일라오이");
                        break;
                    case 19:
                        unitView.setImageResource(R.drawable.irelia);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("이렐리아");
                        break;
                    case 20:
                        unitView.setImageResource(R.drawable.janna);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("잔나");
                        break;
                    case 21:
                        unitView.setImageResource(R.drawable.jarvaniv);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("자르반 4세");
                        break;
                    case 22:
                        unitView.setImageResource(R.drawable.jayce);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("제이스");
                        break;
                    case 23:
                        unitView.setImageResource(R.drawable.jhin);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("진");
                        break;
                    case 24:
                        unitView.setImageResource(R.drawable.jinx);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("징크스");
                        break;
                    case 25:
                        unitView.setImageResource(R.drawable.kaisa);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("카이사");
                        break;
                    case 26:
                        unitView.setImageResource(R.drawable.karma);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("카르마");
                        break;
                    case 27:
                        unitView.setImageResource(R.drawable.kassadin);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("카사딘");
                        break;
                    case 28:
                        unitView.setImageResource(R.drawable.kayle);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("케일");
                        break;
                    case 29:
                        unitView.setImageResource(R.drawable.khazix);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText2.setText("카직스");
                        break;
                    case 30:
                        unitView.setImageResource(R.drawable.kogmaw);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("코그모");
                        break;
                    case 31:
                        unitView.setImageResource(R.drawable.leona);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("레오나");
                        break;
                    case 32:
                        unitView.setImageResource(R.drawable.lucian);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("루시안");
                        break;
                    case 33:
                        unitView.setImageResource(R.drawable.lulu);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("룰루");
                        break;
                    case 34:
                        unitView.setImageResource(R.drawable.lux);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("럭스");
                        break;
                    case 35:
                        unitView.setImageResource(R.drawable.malphite);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("말파이트");
                        break;
                    case 36:
                        unitView.setImageResource(R.drawable.masteryi);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("마스터 이");
                        break;
                    case 37:
                        unitView.setImageResource(R.drawable.missfortune);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("미스 포츈");
                        break;
                    case 38:
                        unitView.setImageResource(R.drawable.mordekaiser);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("모데카이저");
                        break;
                    case 39:
                        unitView.setImageResource(R.drawable.nautilus);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("노틸러스");
                        break;
                    case 40:
                        unitView.setImageResource(R.drawable.neeko);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("니코");
                        break;
                    case 41:
                        unitView.setImageResource(R.drawable.nocturne);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("녹턴");
                        break;
                    case 42:
                        unitView.setImageResource(R.drawable.poppy);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("뽀삐");
                        break;
                    case 43:
                        unitView.setImageResource(R.drawable.rakan);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("라칸");
                        break;
                    case 44:
                        unitView.setImageResource(R.drawable.riven);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("리븐");
                        break;
                    case 45:
                        unitView.setImageResource(R.drawable.rumble);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("럼블");
                        break;
                    case 46:
                        unitView.setImageResource(R.drawable.shaco);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("샤코");
                        break;
                    case 47:
                        unitView.setImageResource(R.drawable.shen);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("쉔");
                        break;
                    case 48:
                        unitView.setImageResource(R.drawable.sona);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("소나");
                        break;
                    case 49:
                        unitView.setImageResource(R.drawable.soraka);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("소라카");
                        break;
                    case 50:
                        unitView.setImageResource(R.drawable.syndra);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("신드라");
                        break;
                    case 51:
                        unitView.setImageResource(R.drawable.teemo);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("티모");
                        break;
                    case 52:
                        unitView.setImageResource(R.drawable.thresh);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("쓰레쉬");
                        break;
                    case 53:
                        unitView.setImageResource(R.drawable.twistedfate);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("트위스티드 페이트");
                        break;
                    case 54:
                        unitView.setImageResource(R.drawable.urgot);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("우르곳");
                        break;
                    case 55:
                        unitView.setImageResource(R.drawable.vayne);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("베인");
                        break;
                    case 56:
                        unitView.setImageResource(R.drawable.velkoz);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("벨코즈");
                        break;
                    case 57:
                        unitView.setImageResource(R.drawable.vi);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("바이");
                        break;
                    case 58:
                        unitView.setImageResource(R.drawable.viktor);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("빅토르");
                        break;
                    case 59:
                        unitView.setImageResource(R.drawable.wukong);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("오공");
                        break;
                    case 60:
                        unitView.setImageResource(R.drawable.xayah);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("자야");
                        break;
                    case 61:
                        unitView.setImageResource(R.drawable.xerath);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("제라스");
                        break;
                    case 62:
                        unitView.setImageResource(R.drawable.xinzhao);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("신 짜오");
                        break;
                    case 63:
                        unitView.setImageResource(R.drawable.yasuo);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("야스오");
                        break;
                    case 64:
                        unitView.setImageResource(R.drawable.zed);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("제드");
                        break;
                    case 65:
                        unitView.setImageResource(R.drawable.ziggs);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("직스");
                        break;
                    case 66:
                        unitView.setImageResource(R.drawable.zoe);
                        unitText2.setText(String.valueOf(data.getCount()) + " 회");
                        unitText1.setText("조이");
                        break;
                }
            }

        }

    }
}
