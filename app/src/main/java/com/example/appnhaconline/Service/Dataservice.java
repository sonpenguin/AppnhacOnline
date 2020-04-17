package com.example.appnhaconline.Service;

import com.example.appnhaconline.Model.Album;
import com.example.appnhaconline.Model.Baihat;
import com.example.appnhaconline.Model.ChudeTheloai;
import com.example.appnhaconline.Model.Playlist;
import com.example.appnhaconline.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChudeTheloai> GetDataChudeTheloai();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumhot();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaihathot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihatquangcao(@Field("idquangcao") String idquangcao);


}
