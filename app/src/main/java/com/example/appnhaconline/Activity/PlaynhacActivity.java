package com.example.appnhaconline.Activity;

import androidx.annotation.RequiresApi;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
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
import java.util.List;
import java.util.Random;

public class PlaynhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerplaynhac;
    Spinner spinnerSpeed;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhacAdapter adapternhac;
    Fragment_Dianhac fragment_dianhac;
    Fragment_Play_Danhsachbaihat fragment_play_danhsachbaihat;
    MediaPlayer mediaPlayer;
    int positon = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

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

    private void Speed() {

        spinnerSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1.0f));
                }
                if (i == 1) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(0.5f));
                }
                if (i == 2) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(0.8f));
                }
                if (i == 3) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1.1f));
                }
                if (i == 4) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1.3f));
                }
                if (i == 5) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1.5f));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void eventClick() {
        final List<String> list = new ArrayList<>();
        list.add("1.0");
        list.add("0.5");
        list.add("0.8");
        list.add("1.1");
        list.add("1.3");
        list.add("1.5");

        final ArrayAdapter<String> adapterspeed = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        // Dinh nghia cho spinner kieu single choice
        // set Adapter vao thang spinner
        adapterspeed.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerSpeed.setAdapter(adapterspeed);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (mangbaihat.size() > 0) {
                        fragment_dianhac.Playnhac(mangbaihat.get(0).getHinhBaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    if (fragment_dianhac.objectAnimator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            fragment_dianhac.objectAnimator.pause();
                        }
                    }
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if (fragment_dianhac.objectAnimator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            fragment_dianhac.objectAnimator.resume();
                        }
                    }
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.iconshuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //Khi nguoi dung keo toi vi tri nào đó và ngừng kéo thì phát tại vị trí đó
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (positon < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        positon++;
                        if (repeat == true) {
                            if (positon == 0) {
                                positon = mangbaihat.size();
                            }
                            positon -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == positon) {
                                positon = index - 1;
                            }
                            positon = index;
                        }
                        if (positon > (mangbaihat.size()) - 1) {
                            positon = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(positon).getLinkBaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(positon).getHinhBaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(positon).getTenBaihat());
                        spinnerSpeed.setAdapter(adapterspeed);
                        UpdateTime();
                    }
                }
                //Bắt sự kiện sau khi người dùng click nút next hoặc pre thì khóa nút đó 5s rồi mới cho click lại
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (positon < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        positon--;
                        if (positon < 0) {
                            positon = mangbaihat.size() - 1;
                        }
                        if (repeat == true) {
                            positon += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == positon) {
                                positon = index - 1;
                            }
                            positon = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(positon).getLinkBaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(positon).getHinhBaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(positon).getTenBaihat());
                        spinnerSpeed.setAdapter(adapterspeed);
                        UpdateTime();
                    }
                }
                //Bắt sự kiện sau khi người dùng click nút next hoặc pre thì khóa nút đó 5s rồi mới cho click lại
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        //Xóa dữ liệu cũ trước khi intent tránh đè dữ liệu cũ
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);

            }
            if (intent.hasExtra("cacbaihat")) {
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
        spinnerSpeed = findViewById(R.id.spinnerspeed);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Khi mở danh sách ca khúc mới sẽ ngừng phát và xóa danh sách cũ
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.RED);
        fragment_dianhac = new Fragment_Dianhac();
        fragment_play_danhsachbaihat = new Fragment_Play_Danhsachbaihat();
        adapternhac = new ViewPagerPlaylistnhacAdapter(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danhsachbaihat);
        adapternhac.AddFragment(fragment_dianhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dianhac = (Fragment_Dianhac) adapternhac.getItem(1);
        if (mangbaihat.size() > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaihat());
            imgplay.setImageResource(R.drawable.iconpause);
            Speed();
        }
    }

    // class de thuc hien thu tu hat 1 ca khuc
    class PlayMp3 extends AsyncTask<String, Void, String> {

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
            UpdateTime();
        }
    }

    private void Timesong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        // Lay du lieu tong thoi gian cua ca khuc
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        //khi keo thoi gian no se cap nhat lai
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        final List<String> list = new ArrayList<>();
        list.add("x1.0");
        list.add("x0.5");
        list.add("x0.8");
        list.add("x1.1");
        list.add("x1.3");
        list.add("x1.5");

        final ArrayAdapter<String> adapterspeed1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        // Dinh nghia cho spinner kieu single choice
        // set Adapter vao thang spinner
        adapterspeed1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerSpeed.setAdapter(adapterspeed1);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    //Khi phat toi bai cuoi cung
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        //Tao handler lang nghe khi chuyen bai hat lam gi
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (positon < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        positon++;
                        if (repeat == true) {
                            if (positon == 0) {
                                positon = mangbaihat.size();
                            }
                            positon -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == positon) {
                                positon = index - 1;
                            }
                            positon = index;
                        }
                        if (positon > (mangbaihat.size()) - 1) {
                            positon = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(positon).getLinkBaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(positon).getHinhBaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(positon).getTenBaihat());
                        spinnerSpeed.setAdapter(adapterspeed1);

                    }
                    //Bắt sự kiện sau khi người dùng click nút next hoặc pre thì khóa nút đó 5s rồi mới cho click lại
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    //Xóa đi lắng nghe để tạo ra thread mới
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
