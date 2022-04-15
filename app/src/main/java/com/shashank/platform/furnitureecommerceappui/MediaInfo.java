package com.shashank.platform.furnitureecommerceappui;

public class MediaInfo {

    private String playlist_id;
    private String title;
    private String uploadtime;
    private String vediotime;
    private String hit;
    private String likenum;
    private  String profit;
    private String ad_brand;
    private  String ad_name;
    private  String ad_second;

    public MediaInfo (){}
    public MediaInfo(String title, String uploadtime, String vediotime, String hit, String likenum, String profit,String playlist_id,String ad_brand,String ad_name,String ad_second){

        this.profit = profit;
        this.title =title;
        this.uploadtime = uploadtime;
        this.vediotime = vediotime;
        this. hit =hit;
        this.likenum = likenum;
        this.playlist_id = playlist_id;
        this.ad_brand = ad_brand;
        this.ad_name = ad_name;
        this.ad_second = ad_second;

    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
    public String getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(String playlist_id) {
        this.playlist_id = playlist_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getVediotime() {
        return vediotime;
    }

    public void setVediotime(String vediotime) {
        this.vediotime = vediotime;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getLikenum() {
        return likenum;
    }

    public void setLikenum(String likenum) {
        this.likenum = likenum;
    }

    public String getAd_brand() {
        return ad_brand;
    }
    public void setAd_brand(String ad_brand) {
        this.ad_brand = ad_brand;
    }

    public String getAd_name() {
        return ad_name;
    }
    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_second() {
        return ad_second;
    }
    public void setAd_second(String ad_second) {
        this.ad_second = ad_second;
    }
}
