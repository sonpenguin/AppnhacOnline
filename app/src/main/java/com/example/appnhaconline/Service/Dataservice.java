package com.example.appnhaconline.Service;

import com.example.appnhaconline.Model.ChudeTheloai;
import com.example.appnhaconline.Model.Playlist;
import com.example.appnhaconline.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChudeTheloai> GetDataChudeTheloai();


}
