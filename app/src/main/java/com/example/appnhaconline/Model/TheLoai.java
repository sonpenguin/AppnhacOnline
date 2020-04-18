package com.example.appnhaconline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TheLoai implements Serializable {

@SerializedName("IdTheloai")
@Expose
private String idTheloai;
@SerializedName("Idkeychude")
@Expose
private String idkeychude;
@SerializedName("TenTheloai")
@Expose
private String tenTheloai;
@SerializedName("HinhTheloai")
@Expose
private String hinhTheloai;

public String getIdTheloai() {
return idTheloai;
}

public void setIdTheloai(String idTheloai) {
this.idTheloai = idTheloai;
}

public String getIdkeychude() {
return idkeychude;
}

public void setIdkeychude(String idkeychude) {
this.idkeychude = idkeychude;
}

public String getTenTheloai() {
return tenTheloai;
}

public void setTenTheloai(String tenTheloai) {
this.tenTheloai = tenTheloai;
}

public String getHinhTheloai() {
return hinhTheloai;
}

public void setHinhTheloai(String hinhTheloai) {
this.hinhTheloai = hinhTheloai;
}

}