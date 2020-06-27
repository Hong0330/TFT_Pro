package com.example.tftstats2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
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

        private TextView textView7;
        private TextView textView8;
        private TextView textView9;

        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private ImageView imageView4;

        private ImageView imageView5;
        private ImageView imageView6;
        private ImageView imageView7;
        private ImageView imageView8;

        private ImageView imageView9;
        private ImageView imageView10;
        private ImageView imageView11;
        private ImageView imageView12;

        private ImageView imageView13;
        private ImageView imageView14;
        private ImageView imageView15;
        private ImageView imageView16;

        private ImageView imageView17;
        private ImageView imageView18;
        private ImageView imageView19;
        private ImageView imageView20;

        private ImageView imageView21;
        private ImageView imageView22;
        private ImageView imageView23;
        private ImageView imageView24;



        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView7 = itemView.findViewById(R.id.textView4);
            textView8 = itemView.findViewById(R.id.textView5);
            textView9 = itemView.findViewById(R.id.textView6);

            //시너지
            imageView1 = itemView.findViewById(R.id.imageView43);
            imageView2 = itemView.findViewById(R.id.imageView45);
            imageView3 = itemView.findViewById(R.id.imageView44);
            imageView4 = itemView.findViewById(R.id.imageView46);

            imageView5 = itemView.findViewById(R.id.imageView47);
            imageView6= itemView.findViewById(R.id.imageView48);
            imageView7 = itemView.findViewById(R.id.imageView50);
            imageView8 = itemView.findViewById(R.id.imageView51);


            imageView9 = itemView.findViewById(R.id.imageView5);
            imageView10 = itemView.findViewById(R.id.imageView4);
            imageView11 = itemView.findViewById(R.id.imageView6);
            imageView12 = itemView.findViewById(R.id.imageView7);

            //유닛
            imageView13 = itemView.findViewById(R.id.imageView52);
            imageView14 = itemView.findViewById(R.id.imageView53);
            imageView15 = itemView.findViewById(R.id.imageView54);
            imageView16 = itemView.findViewById(R.id.imageView55);

            imageView17 = itemView.findViewById(R.id.imageView56);
            imageView18 = itemView.findViewById(R.id.imageView57);
            imageView19 = itemView.findViewById(R.id.imageView58);
            imageView20 = itemView.findViewById(R.id.imageView59);

            imageView21 = itemView.findViewById(R.id.imageView8);
            imageView22 = itemView.findViewById(R.id.imageView9);
            imageView23 = itemView.findViewById(R.id.imageView10);
            imageView24 = itemView.findViewById(R.id.imageView11);
        }

        public void onBind(Data data) {
            ArrayList<Integer> trait = new ArrayList<Integer>();
            ArrayList<Integer> unit = new ArrayList<Integer>();

            //textView7.setText(data.getTitle());
            //textView9.setText("테스트용");
            textView7.setText(data.getTitle());
            textView8.setText("레벨 : " + data.getContent());
            textView9.setText("화면");

            for(int i = 0 ; i < data.getTrait().size() ; i++) {
                int tmp = 0;
                switch (data.getTrait().get(i)) {
                    case "Astro":
                        tmp = R.drawable.astro;
                        trait.add(tmp);
                        break;
                    case "Battlecast":
                        tmp = R.drawable.battlecast;
                        trait.add(tmp);
                        break;
                    case "Blademaster":
                        tmp = R.drawable.blademaster;
                        trait.add(tmp);
                        break;
                    case "Blaster":
                        tmp = R.drawable.blaster;
                        trait.add(tmp);
                        break;
                    case "Brawler":
                        tmp = R.drawable.brawler;
                        trait.add(tmp);
                        break;
                    case "Celestial":
                        tmp = R.drawable.celestial;
                        trait.add(tmp);
                        break;
                    case "Chrono":
                        tmp = R.drawable.chrono;
                        trait.add(tmp);
                        break;
                    case "Cybernetic":
                        tmp = R.drawable.cybernetic;
                        trait.add(tmp);
                        break;
                    case "Darkster":
                        tmp = R.drawable.darkstar;
                        trait.add(tmp);
                        break;
                    case "Demolitionist":
                        tmp = R.drawable.demolitionist;
                        trait.add(tmp);
                        break;
                    case "Infiltrator":
                        tmp = R.drawable.infiltrator;
                        trait.add(tmp);
                        break;
                    case "Manareaver":
                        tmp = R.drawable.manareaver;
                        trait.add(tmp);
                        break;
                    case "Mechpilot":
                        tmp = R.drawable.mechpilot;
                        trait.add(tmp);
                        break;
                    case "Mercenary":
                        tmp = R.drawable.mercenary;
                        trait.add(tmp);
                        break;
                    case "Mystic":
                        tmp = R.drawable.mystic;
                        trait.add(tmp);
                        break;
                    case "Paragon":
                        tmp = R.drawable.paragon;
                        trait.add(tmp);
                        break;
                    case "Protector":
                        tmp = R.drawable.protector;
                        trait.add(tmp);
                        break;
                    case "Rebel":
                        tmp = R.drawable.rebel;
                        trait.add(tmp);
                        break;
                    case "Void":
                        tmp = R.drawable.set3_void;
                        trait.add(tmp);
                        break;
                    case "Sniper":
                        tmp = R.drawable.sniper;
                        trait.add(tmp);
                        break;
                    case "Sorcerer":
                        tmp = R.drawable.sorcerer;
                        trait.add(tmp);
                        break;
                    case "Spacepirate":
                        tmp = R.drawable.spacepirate;
                        trait.add(tmp);
                        break;
                    case "Starguardian":
                        tmp = R.drawable.starguardian;
                        trait.add(tmp);
                        break;
                    case "Starship":
                        tmp = R.drawable.starship;
                        trait.add(tmp);
                        break;
                    case "Valkyrie":
                        tmp = R.drawable.valkyrie;
                        trait.add(tmp);
                        break;
                    case "Vanguard":
                        tmp = R.drawable.vanguard;
                        trait.add(tmp);
                        break;
                }
            }

            for(int i = 0 ; i < data.getUnit().size() ; i++) {
                int tmp = 0;
                switch (data.getUnit().get(i)) {
                    case "TFT3_Ahri":
                        tmp = R.drawable.ahri;
                        unit.add(tmp);
                        break;
                    case "TFT3_Annie":
                        tmp = R.drawable.annie;
                        unit.add(tmp);
                        break;
                    case "TFT3_Ashe":
                        tmp = R.drawable.ashe;
                        unit.add(tmp);
                        break;
                    case "TFT3_AureLionSol":
                        tmp = R.drawable.aurelionsol;
                        unit.add(tmp);
                        break;
                    case "TFT3_Bard":
                        tmp = R.drawable.bard;
                        unit.add(tmp);
                        break;
                    case "TFT3_Blitzcrank":
                        tmp = R.drawable.blitzcrank;
                        unit.add(tmp);
                        break;
                    case "TFT3_Caitlyn":
                        tmp = R.drawable.caitlyn;
                        unit.add(tmp);
                        break;
                    case "TFT3_Cassiopeia":
                        tmp = R.drawable.cassiopeia;
                        unit.add(tmp);
                        break;
                    case "TFT3_Chogath":
                        tmp = R.drawable.chogath;
                        unit.add(tmp);
                        break;
                    case "TFT3_Darius":
                        tmp = R.drawable.darius;
                        unit.add(tmp);
                        break;
                    case "TFT3_Ekko":
                        tmp = R.drawable.ekko;
                        unit.add(tmp);
                        break;
                    case "TFT3_Ezreal":
                        tmp = R.drawable.ezreal;
                        unit.add(tmp);
                        break;
                    case "TFT3_Fiora":
                        tmp = R.drawable.fiora;
                        unit.add(tmp);
                        break;
                    case "TFT3_Fizz":
                        tmp = R.drawable.fizz;
                        unit.add(tmp);
                        break;
                    case "TFT3_Gangplank":
                        tmp = R.drawable.gangplank;
                        unit.add(tmp);
                        break;
                    case "TFT3_Garen":
                        tmp = R.drawable.garen;
                        unit.add(tmp);
                        break;
                    case "TFT3_Gnar":
                        tmp = R.drawable.gnar;
                        unit.add(tmp);
                        break;
                    case "TFT3_Graves":
                        tmp = R.drawable.graves;
                        unit.add(tmp);
                        break;
                    case "TFT3_Illoai":
                        tmp = R.drawable.illaoi;
                        unit.add(tmp);
                        break;
                    case "TFT3_Irelia":
                        tmp = R.drawable.irelia;
                        unit.add(tmp);
                        break;
                    case "TFT3_Janna":
                        tmp = R.drawable.janna;
                        unit.add(tmp);
                        break;
                    case "TFT3_JarvanIV":
                        tmp = R.drawable.jarvaniv;
                        unit.add(tmp);
                        break;
                    case "TFT3_Jayce":
                        tmp = R.drawable.jayce;
                        unit.add(tmp);
                        break;
                    case "TFT3_Jhin":
                        tmp = R.drawable.jhin;
                        unit.add(tmp);
                        break;
                    case "TFT3_Jinx":
                        tmp = R.drawable.jinx;
                        unit.add(tmp);
                        break;
                    case "TFT3_Kaisa":
                        tmp = R.drawable.kaisa;
                        unit.add(tmp);
                        break;
                    case "TFT3_Karma":
                        tmp = R.drawable.karma;
                        unit.add(tmp);
                        break;
                    case "TFT3_Kassadin":
                        tmp = R.drawable.kassadin;
                        unit.add(tmp);
                        break;
                    case "TFT3_Kayle":
                        tmp = R.drawable.kayle;
                        unit.add(tmp);
                        break;
                    case "TFT3_Khazix":
                        tmp = R.drawable.khazix;
                        unit.add(tmp);
                        break;
                    case "TFT3_Kogmaw":
                        tmp = R.drawable.kogmaw;
                        unit.add(tmp);
                        break;
                    case "TFT3_Leona":
                        tmp = R.drawable.leona;
                        unit.add(tmp);
                        break;
                    case "TFT3_Lucian":
                        tmp = R.drawable.lucian;
                        unit.add(tmp);
                        break;
                    case "TFT3_Lulu":
                        tmp = R.drawable.lulu;
                        unit.add(tmp);
                        break;
                    case "TFT3_Lux":
                        tmp = R.drawable.lux;
                        unit.add(tmp);
                        break;
                    case "TFT3_Malphite":
                        tmp = R.drawable.malphite;
                        unit.add(tmp);
                        break;
                    case "TFT3_MasterYi":
                        tmp = R.drawable.masteryi;
                        unit.add(tmp);
                        break;
                    case "TFT3_MissFortune":
                        tmp = R.drawable.missfortune;
                        unit.add(tmp);
                        break;
                    case "TFT3_Modekaiser":
                        tmp = R.drawable.mordekaiser;
                        unit.add(tmp);
                        break;
                    case "TFT3_Nautilus":
                        tmp = R.drawable.nautilus;
                        unit.add(tmp);
                        break;
                    case "TFT3_Neeko":
                        tmp = R.drawable.neeko;
                        unit.add(tmp);
                        break;
                    case "TFT3_Nocturne":
                        tmp = R.drawable.nocturne;
                        unit.add(tmp);
                        break;
                    case "TFT3_Poppy":
                        tmp = R.drawable.poppy;
                        unit.add(tmp);
                        break;
                    case "TFT3_Rakan":
                        tmp = R.drawable.rakan;
                        unit.add(tmp);
                        break;
                    case "TFT3_Riven":
                        tmp = R.drawable.riven;
                        unit.add(tmp);
                        break;
                    case "TFT3_Rumble":
                        tmp = R.drawable.rumble;
                        unit.add(tmp);
                        break;
                    case "TFT3_Shaco":
                        tmp = R.drawable.shaco;
                        unit.add(tmp);
                        break;
                    case "TFT3_Shen":
                        tmp = R.drawable.shen;
                        unit.add(tmp);
                        break;
                    case "TFT3_Sona":
                        tmp = R.drawable.sona;
                        unit.add(tmp);
                        break;
                    case "TFT3_Soraka":
                        tmp = R.drawable.soraka;
                        unit.add(tmp);
                        break;
                    case "TFT3_Syndra":
                        tmp = R.drawable.syndra;
                        unit.add(tmp);
                        break;
                    case "TFT3_Teemo":
                        tmp = R.drawable.teemo;
                        unit.add(tmp);
                        break;
                    case "TFT3_Thresh":
                        tmp = R.drawable.thresh;
                        unit.add(tmp);
                        break;
                    case "TFT3_TwistedFate":
                        tmp = R.drawable.twistedfate;
                        unit.add(tmp);
                        break;
                    case "TFT3_Urgot":
                        tmp = R.drawable.urgot;
                        unit.add(tmp);
                        break;
                    case "TFT3_Vayne":
                        tmp = R.drawable.vayne;
                        unit.add(tmp);
                        break;
                    case "TFT3_VelKoz":
                        tmp = R.drawable.velkoz;
                        unit.add(tmp);
                        break;
                    case "TFT3_Vi":
                        tmp = R.drawable.vi;
                        unit.add(tmp);
                        break;
                    case "TFT3_Viktor":
                        tmp = R.drawable.viktor;
                        unit.add(tmp);
                        break;
                    case "TFT3_WuKong":
                        tmp = R.drawable.wukong;
                        unit.add(tmp);
                        break;
                    case "TFT3_Xayah":
                        tmp = R.drawable.xayah;
                        unit.add(tmp);
                        break;
                    case "TFT3_Xerath":
                        tmp = R.drawable.xerath;
                        unit.add(tmp);
                        break;
                    case "TFT3_XinZhao":
                        tmp = R.drawable.xinzhao;
                        unit.add(tmp);
                        break;
                    case "TFT3_Yasuo":
                        tmp = R.drawable.yasuo;
                        unit.add(tmp);
                        break;
                    case "TFT3_Zed":
                        tmp = R.drawable.zed;
                        unit.add(tmp);
                        break;
                    case "TFT3_Ziggs":
                        tmp = R.drawable.ziggs;
                        unit.add(tmp);
                        break;
                    case "TFT3_Zoe":
                        tmp = R.drawable.zoe;
                        unit.add(tmp);
                        break;
                }
            }
            // 시너지 유닛 리스트에 저장 완료
            System.out.println("시너지 개수 : " + trait.size());
            System.out.println("유닛 개수 : " + unit.size());

            switch (trait.size()) {
                case 0:
                    imageView1.setImageResource(R.drawable.void_trait);
                    imageView2.setImageResource(R.drawable.void_trait);
                    imageView3.setImageResource(R.drawable.void_trait);
                    imageView4.setImageResource(R.drawable.void_trait);
                    imageView5.setImageResource(R.drawable.void_trait);
                    imageView6.setImageResource(R.drawable.void_trait);
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 1:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(R.drawable.void_trait);
                    imageView3.setImageResource(R.drawable.void_trait);
                    imageView4.setImageResource(R.drawable.void_trait);
                    imageView5.setImageResource(R.drawable.void_trait);
                    imageView6.setImageResource(R.drawable.void_trait);
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 2:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(R.drawable.void_trait);
                    imageView4.setImageResource(R.drawable.void_trait);
                    imageView5.setImageResource(R.drawable.void_trait);
                    imageView6.setImageResource(R.drawable.void_trait);
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 3:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(R.drawable.void_trait);
                    imageView5.setImageResource(R.drawable.void_trait);
                    imageView6.setImageResource(R.drawable.void_trait);
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 4:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(R.drawable.void_trait);
                    imageView6.setImageResource(R.drawable.void_trait);
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 5:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(R.drawable.void_trait);
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 6:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(R.drawable.void_trait);
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 7:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(trait.get(6));
                    imageView8.setImageResource(R.drawable.void_trait);
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;
                case 8:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(trait.get(6));
                    imageView8.setImageResource(trait.get(7));
                    imageView9.setImageResource(R.drawable.void_trait);
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;

                case 9:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(trait.get(6));
                    imageView8.setImageResource(trait.get(7));
                    imageView9.setImageResource(trait.get(8));
                    imageView10.setImageResource(R.drawable.void_trait);
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;

                case 10:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(trait.get(6));
                    imageView8.setImageResource(trait.get(7));
                    imageView9.setImageResource(trait.get(8));
                    imageView10.setImageResource(trait.get(9));
                    imageView11.setImageResource(R.drawable.void_trait);
                    imageView12.setImageResource(R.drawable.void_trait);
                    break;

                case 11 :
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(trait.get(6));
                    imageView8.setImageResource(trait.get(7));
                    imageView9.setImageResource(trait.get(8));
                    imageView10.setImageResource(trait.get(9));
                    imageView11.setImageResource(trait.get(10));
                    imageView12.setImageResource(R.drawable.void_trait);

                default:
                    imageView1.setImageResource(trait.get(0));
                    imageView2.setImageResource(trait.get(1));
                    imageView3.setImageResource(trait.get(2));
                    imageView4.setImageResource(trait.get(3));
                    imageView5.setImageResource(trait.get(4));
                    imageView6.setImageResource(trait.get(5));
                    imageView7.setImageResource(trait.get(6));
                    imageView8.setImageResource(trait.get(7));
                    imageView9.setImageResource(trait.get(8));
                    imageView10.setImageResource(trait.get(9));
                    imageView11.setImageResource(trait.get(10));
                    imageView12.setImageResource(trait.get(11));
                    break;
            }

            switch (unit.size()) {
                case 0:
                    imageView13.setImageResource(R.drawable.void_unit);
                    imageView14.setImageResource(R.drawable.void_unit);
                    imageView15.setImageResource(R.drawable.void_unit);
                    imageView16.setImageResource(R.drawable.void_unit);
                    imageView17.setImageResource(R.drawable.void_unit);
                    imageView18.setImageResource(R.drawable.void_unit);
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 1:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(R.drawable.void_unit);
                    imageView15.setImageResource(R.drawable.void_unit);
                    imageView16.setImageResource(R.drawable.void_unit);
                    imageView17.setImageResource(R.drawable.void_unit);
                    imageView18.setImageResource(R.drawable.void_unit);
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 2:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(R.drawable.void_unit);
                    imageView16.setImageResource(R.drawable.void_unit);
                    imageView17.setImageResource(R.drawable.void_unit);
                    imageView18.setImageResource(R.drawable.void_unit);
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 3:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(R.drawable.void_unit);
                    imageView17.setImageResource(R.drawable.void_unit);
                    imageView18.setImageResource(R.drawable.void_unit);
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 4:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(R.drawable.void_unit);
                    imageView18.setImageResource(R.drawable.void_unit);
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 5:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(R.drawable.void_unit);
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 6:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(R.drawable.void_unit);
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 7:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(unit.get(6));
                    imageView20.setImageResource(R.drawable.void_unit);
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 8:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(unit.get(6));
                    imageView20.setImageResource(unit.get(7));
                    imageView21.setImageResource(R.drawable.void_unit);
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 9:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(unit.get(6));
                    imageView20.setImageResource(unit.get(7));
                    imageView21.setImageResource(unit.get(8));
                    imageView22.setImageResource(R.drawable.void_unit);
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 10:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(unit.get(6));
                    imageView20.setImageResource(unit.get(7));
                    imageView21.setImageResource(unit.get(8));
                    imageView22.setImageResource(unit.get(9));
                    imageView23.setImageResource(R.drawable.void_unit);
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                case 11:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(unit.get(6));
                    imageView20.setImageResource(unit.get(7));
                    imageView21.setImageResource(unit.get(8));
                    imageView22.setImageResource(unit.get(9));
                    imageView23.setImageResource(unit.get(10));
                    imageView24.setImageResource(R.drawable.void_unit);
                    break;
                default:
                    imageView13.setImageResource(unit.get(0));
                    imageView14.setImageResource(unit.get(1));
                    imageView15.setImageResource(unit.get(2));
                    imageView16.setImageResource(unit.get(3));
                    imageView17.setImageResource(unit.get(4));
                    imageView18.setImageResource(unit.get(5));
                    imageView19.setImageResource(unit.get(6));
                    imageView20.setImageResource(unit.get(7));
                    imageView21.setImageResource(unit.get(8));
                    imageView22.setImageResource(unit.get(9));
                    imageView23.setImageResource(unit.get(10));
                    imageView24.setImageResource(unit.get(11));
                    break;
            }
        }
    }
}
