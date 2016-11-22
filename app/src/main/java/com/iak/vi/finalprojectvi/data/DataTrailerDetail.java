package com.iak.vi.finalprojectvi.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taufiqotulfaidah on 11/22/16.
 */

public class DataTrailerDetail {

    @SerializedName("id")
    String idTrailer;

    @SerializedName("iso_639_1")
    String iso639;

    @SerializedName("iso_3166_1")
    String iso3166;

    @SerializedName("key")
    String keyTrailer;

    @SerializedName("name")
    String nameTrailer;

    @SerializedName("site")
    String siteTrailer;

    @SerializedName("size")
    String sizeTrailer;

    @SerializedName("type")
    String typeTrailer;

    public String getIdTrailer() {
        return idTrailer;
    }

    public void setIdTrailer(String idTrailer) {
        this.idTrailer = idTrailer;
    }

    public String getIso639() {
        return iso639;
    }

    public void setIso639(String iso639) {
        this.iso639 = iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public String getKeyTrailer() {
        return keyTrailer;
    }

    public void setKeyTrailer(String keyTrailer) {
        this.keyTrailer = keyTrailer;
    }

    public String getNameTrailer() {
        return nameTrailer;
    }

    public void setNameTrailer(String nameTrailer) {
        this.nameTrailer = nameTrailer;
    }

    public String getSiteTrailer() {
        return siteTrailer;
    }

    public void setSiteTrailer(String siteTrailer) {
        this.siteTrailer = siteTrailer;
    }

    public String getSizeTrailer() {
        return sizeTrailer;
    }

    public void setSizeTrailer(String sizeTrailer) {
        this.sizeTrailer = sizeTrailer;
    }

    public String getTypeTrailer() {
        return typeTrailer;
    }

    public void setTypeTrailer(String typeTrailer) {
        this.typeTrailer = typeTrailer;
    }
}
