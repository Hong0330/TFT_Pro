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

public class cham_fragment extends Fragment {

    private View view;

    RecyclerView recyclerView;
    UnitAdapter unit_adapter;

    int[] unit = new int[67];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if(getArguments() != null) {
            unit = getArguments().getIntArray("unit");
        }
        System.out.println("챔피언 트렌드 선택");
        view = inflater.inflate(R.layout.cham_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.cham_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        unit_adapter = new UnitAdapter();
        recyclerView.setAdapter(unit_adapter);

        System.out.println("챔피언 갯수 " + unit.length);
        for(int i = 0 ; i < unit.length ; i++) {
            Data data = new Data();
            if(unit[i] > 0) {
                System.out.println("챔피언 어댑터 추가");
                data.setNumber(i);
                data.setCount(unit[i]);
                unit_adapter.addItem(data);
            }
        }
        unit_adapter.notifyDataSetChanged();

        return view;
    }

}