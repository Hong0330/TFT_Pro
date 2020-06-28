package com.example.tftstats2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class trait_fragment extends Fragment {

    private View view;

    int[] trait = new int[26];

    RecyclerView recyclerView;
    TraitAdapter trait_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        if(getArguments() != null) {
            trait = getArguments().getIntArray("trait");
        }

        System.out.println("시너지 트렌드 선택");
        view = inflater.inflate(R.layout.trait_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.trait_list);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager1);
        trait_adapter = new TraitAdapter();
        recyclerView.setAdapter(trait_adapter);

        for(int i = 0 ; i < trait.length ; i++) {
            Data data = new Data();
            if(trait[i] > 0) {
                System.out.println("시너지 어댑터 추가");
                data.setNumber(i);
                data.setCount(trait[i]);
                trait_adapter.addItem(data);
            }
        }
        trait_adapter.notifyDataSetChanged();

        return view;
    }

}