package com.example.appnhaconline.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Baihat implements Parcelable {

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

    protected Baihat(Parcel in) {
        idbaihat = in.readString();
        tenBaihat = in.readString();
        hinhBaihat = in.readString();
        caSi = in.readString();
        linkBaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idbaihat);
        dest.writeString(tenBaihat);
        dest.writeString(hinhBaihat);
        dest.writeString(caSi);
        dest.writeString(linkBaihat);
        dest.writeString(luotthich);
    }
}