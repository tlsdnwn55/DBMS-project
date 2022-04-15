package com.shashank.platform.furnitureecommerceappui;

import android.widget.ImageView;

public class PlaylistInfo {

    private String playlist_title;
    private String playlist_id;
    private String total_profit;



    public PlaylistInfo(){};
    public  PlaylistInfo(String playlist_id, String playlist_title, String total_profit){
        this.playlist_id = playlist_id;
        this.playlist_title =playlist_title;
        this.total_profit =  total_profit ;

    }


    public String getPlaylist_title() {
        return playlist_title;
    }

    public void setPlaylist_title(String playlist_title) {
        this.playlist_title = playlist_title;
    }

    public String getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(String playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getTotal_profit() {
        return total_profit;
    }

    public void setTotal_profit(String total_profit) {
        this.total_profit = total_profit;
    }
}
