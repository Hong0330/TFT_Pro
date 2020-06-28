package com.example.tftstats2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TraitAdapter extends RecyclerView.Adapter<TraitAdapter.ItemViewHolder>  {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public TraitAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_trait, parent, false);
        return new TraitAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraitAdapter.ItemViewHolder holder, int position) {
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

        //시너지 필드
        private ImageView traitView; //시너지 이미지
        private TextView traitText1;
        private TextView traitText2;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            traitView = itemView.findViewById(R.id.imageView12);
            traitText1 = itemView.findViewById(R.id.textView7);
            traitText2 = itemView.findViewById(R.id.textView19);
        }

        void onBind(Data data) {
            if(data.getCount() > 0) {
                switch(data.getNumber()) {
                    case 0:
                        traitView.setImageResource(R.drawable.astro);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("우주비행사");
                        break;
                    case 1:
                        traitView.setImageResource(R.drawable.battlecast);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("전투 기계");
                        break;
                    case 2:
                        traitView.setImageResource(R.drawable.blademaster);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("검사");
                        break;
                    case 3:
                        traitView.setImageResource(R.drawable.blaster);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("총잡이");
                        break;
                    case 4:
                        traitView.setImageResource(R.drawable.brawler);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("싸움꾼");
                        break;
                    case 5:
                        traitView.setImageResource(R.drawable.celestial);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("천상");
                        break;
                    case 6:
                        traitView.setImageResource(R.drawable.chrono);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("시공간");
                        break;
                    case 7:
                        traitView.setImageResource(R.drawable.cybernetic);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("사이버네틱");
                        break;
                    case 8:
                        traitView.setImageResource(R.drawable.darkstar);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("암흑의 별");
                        break;
                    case 9:
                        traitView.setImageResource(R.drawable.demolitionist);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("폭파광");
                        break;
                    case 10:
                        traitView.setImageResource(R.drawable.infiltrator);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("잠입자");
                        break;
                    case 11:
                        traitView.setImageResource(R.drawable.manareaver);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("마나 약탈자");
                        break;
                    case 12:
                        traitView.setImageResource(R.drawable.mechpilot);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("메카 파일럿");
                        break;
                    case 13:
                        traitView.setImageResource(R.drawable.mercenary);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("용병");
                        break;
                    case 14:
                        traitView.setImageResource(R.drawable.mystic);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("신비술사");
                        break;
                    case 15:
                        traitView.setImageResource(R.drawable.paragon);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("인도자");
                        break;
                    case 16:
                        traitView.setImageResource(R.drawable.protector);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("수호자");
                        break;
                    case 17:
                        traitView.setImageResource(R.drawable.rebel);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("반군");
                        break;
                    case 18:
                        traitView.setImageResource(R.drawable.set3_void);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("공허");
                        break;
                    case 19:
                        traitView.setImageResource(R.drawable.sniper);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("저격수");
                        break;
                    case 20:
                        traitView.setImageResource(R.drawable.sorcerer);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("마법사");
                        break;
                    case 21:
                        traitView.setImageResource(R.drawable.spacepirate);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("우주 해적");
                        break;
                    case 22:
                        traitView.setImageResource(R.drawable.starguardian);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("별 수호자");
                        break;
                    case 23:
                        traitView.setImageResource(R.drawable.starship);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("우주선");
                        break;
                    case 24:
                        traitView.setImageResource(R.drawable.valkyrie);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("발키리");
                        break;
                    case 25:
                        traitView.setImageResource(R.drawable.vanguard);
                        traitText2.setText(String.valueOf(data.getCount()) + " 회");
                        traitText1.setText("선봉대");
                        break;
                }
            }
        }
    }

}
