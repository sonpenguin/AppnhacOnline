package com.example.appnhaconline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhaconline.Adapter.ViewPagerPlaylistnhacAdapter;
import com.example.appnhaconline.Fragment.Fragment_Dianhac;
import com.example.appnhaconline.Fragment.Fragment_Play_Danhsachbaihat;
import com.example.appnhaconline.Model.Baihat;
import com.example.appnhaconline.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlaynhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext,imgpre,imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhacAdapter adapternhac;
    Fragment_Dianhac fragment_dianhac;
    Fragment_Play_Danhsachbaihat fragment_play_danhsachbaihat;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               if(adapternhac.getItem(1) != null){
                   if(mangbaihat.size() > 0){
                       fragment_dianhac.Playnhac(mangbaihat.get(0).getHinhBaihat());
                       handler.removeCallbacks(this);
                   }else{
                       handler.postDelayed(this, 300);
                   }
               }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    if (fragment_dianhac.objectAnimator!=null){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            fragment_dianhac.objectAnimator.pause();
                        }
                    }
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if (fragment_dianhac.objectAnimator!=null){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            fragment_dianhac.objectAnimator.resume();
                        }
                    }
                }
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        //Xóa dữ liệu cũ trước khi intent tránh đè dữ liệu cũ
        mangbaihat.clear();
        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);

            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
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
        fragment_dianhac = new Fragment_Dianhac();
        fragment_play_danhsachbaihat = new Fragment_Play_Danhsachbaihat();
        adapternhac = new ViewPagerPlaylistnhacAdapter(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danhsachbaihat);
        adapternhac.AddFragment(fragment_dianhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dianhac = (Fragment_Dianhac) adapternhac.getItem(1);
        if(mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    // class de thuc hien thu tu hat 1 ca khuc
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {

            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            // Stream_music: Lay ca khuc online
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            Timesong();
        }
    }

    private void Timesong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        // Lay du lieu tong thoi gian cua ca khuc
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        //khi keo thoi gian no se cap nhat lai
        sktime.setMax(mediaPlayer.getDuration());
    }
}
