package com.example.appnhaconline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist  implements Serializable {

@SerializedName("IdPlaylist")
@Expose
private String idPlaylist;
@SerializedName("TenPlaylist")
@Expose
private String tenPlaylist;
@SerializedName("Hinhnen")
@Expose
private String hinhnen;
@SerializedName("Hinhicon")
@Expose
private String hinhicon;

public String getIdPlaylist() {
return idPlaylist;
}

public void setIdPlaylist(String idPlaylist) {
this.idPlaylist = idPlaylist;
}

public String getTenPlaylist() {
return tenPlaylist;
}

public void setTenPlaylist(String tenPlaylist) {
this.tenPlaylist = tenPlaylist;
}

public String getHinhnen() {
return hinhnen;
}

public void setHinhnen(String hinhnen) {
this.hinhnen = hinhnen;
}

public String getHinhicon() {
return hinhicon;
}

public void setHinhicon(String hinhicon) {
this.hinhicon = hinhicon;
}

}