package com.example.appnhaconline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat {

@SerializedName("Idbaihat")
@Expose
private String idbaihat;
@SerializedName("TenBaihat")
@Expose
private String tenBaihat;
@SerializedName("HinhBaihat")
@Expose
private String hinhBaihat;
@SerializedName("CaSi")
@Expose
private String caSi;
@SerializedName("LinkBaihat")
@Expose
private String linkBaihat;
@SerializedName("Luotthich")
@Expose
private String luotthich;

public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenBaihat() {
return tenBaihat;
}

public void setTenBaihat(String tenBaihat) {
this.tenBaihat = tenBaihat;
}

public String getHinhBaihat() {
return hinhBaihat;
}

public void setHinhBaihat(String hinhBaihat) {
this.hinhBaihat = hinhBaihat;
}

public String getCaSi() {
return caSi;
}

public void setCaSi(String caSi) {
this.caSi = caSi;
}

public String getLinkBaihat() {
return linkBaihat;
}

public void setLinkBaihat(String linkBaihat) {
this.linkBaihat = linkBaihat;
}

public String getLuotthich() {
return luotthich;
}

public void setLuotthich(String luotthich) {
this.luotthich = luotthich;
}

}