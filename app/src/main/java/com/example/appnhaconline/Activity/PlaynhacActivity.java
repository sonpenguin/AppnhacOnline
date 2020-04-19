package com.example.appnhaconline.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appnhaconline.Model.Baihat;
import com.example.appnhaconline.R;

import java.util.ArrayList;

public class PlaynhacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        Intent intent = getIntent();
        if(intent.hasExtra("cakhuc")){
            Baihat baihat = intent.getParcelableExtra("cakhuc");
            Toast.makeText(this, baihat.getTenBaihat(), Toast.LENGTH_SHORT).show();
        }
        if (intent.hasExtra("cacbaihat")){
            ArrayList<Baihat> mangbaihat = intent.getParcelableArrayListExtra("cacbaihat");
            for(int i =0; i<mangbaihat.size();i++){
                Log.d("Test",mangbaihat.get(i).getTenBaihat());
            }
        }
    }
}
