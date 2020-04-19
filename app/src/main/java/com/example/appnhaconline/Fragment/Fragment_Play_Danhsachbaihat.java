package com.example.appnhaconline.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhaconline.Activity.PlaynhacActivity;
import com.example.appnhaconline.Adapter.PlaynhacAdapter;
import com.example.appnhaconline.R;

public class Fragment_Play_Danhsachbaihat extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlaynhacAdapter playnhacAdapter;
    @Nullable
    @Override
    //Tao ham oncreateview de gan layout vao
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsachbaihat,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recyclerviewplaybaihat);
        if(PlaynhacActivity.mangbaihat.size() > 0){
            playnhacAdapter = new PlaynhacAdapter(getActivity(), PlaynhacActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }


        return view;
    }
}
