package com.example.appnhaconline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhaconline.Model.Baihat;
import com.example.appnhaconline.R;

import java.util.ArrayList;

public class PlaynhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext,imgpre,imgrandom;
    ViewPager viewPagerplaynhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        init();
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

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.textviewtimesong);
        txttotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgpre = findViewById(R.id.imagebuttonpre);
        imgrandom = findViewById(R.id.imagebuttonshuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
    }
}
