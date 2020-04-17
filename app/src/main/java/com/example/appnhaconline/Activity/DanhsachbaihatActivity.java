package com.example.appnhaconline.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appnhaconline.Model.Quangcao;
import com.example.appnhaconline.R;

public class DanhsachbaihatActivity extends AppCompatActivity {

    Quangcao quangcao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        DataIntent();
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent !=null){
            if(intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this,quangcao.getTenBaiHat(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
