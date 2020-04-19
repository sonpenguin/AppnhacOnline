package com.example.appnhaconline.Service;

import com.example.appnhaconline.Model.Album;
import com.example.appnhaconline.Model.Baihat;
import com.example.appnhaconline.Model.ChuDe;
import com.example.appnhaconline.Model.ChudeTheloai;
import com.example.appnhaconline.Model.Playlist;
import com.example.appnhaconline.Model.Quangcao;
import com.example.appnhaconline.Model.TheLoai;

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

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihatplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>>  GetDanhsachcacplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllchude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> Getdanhsachtatcaalbum();

}
