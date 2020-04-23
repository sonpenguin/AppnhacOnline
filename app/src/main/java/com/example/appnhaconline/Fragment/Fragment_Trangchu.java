package com.example.appnhaconline.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appnhaconline.R;

public class Fragment_Trangchu extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //view có chức năng để gán layout cho fragment và tương tác với layout của fragment
        view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        return view;
    }
}
