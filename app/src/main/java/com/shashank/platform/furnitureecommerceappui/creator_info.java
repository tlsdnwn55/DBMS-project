package com.shashank.platform.furnitureecommerceappui;

public class creator_info {

    private String nickname;
    private String country;
    private String main_genre;
    private String subscribers;
    private String join_date;

   public creator_info(){};
   public creator_info(String nickname,String country,String main_genre,String subscribers,String join_date){
       this.nickname = nickname;
       this.country = country;
       this.main_genre = main_genre;
       this.subscribers = subscribers;
       this.join_date = join_date;
   }


    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getMain_genre() {
        return main_genre;
    }
    public void setMain_genre(String main_genre) {
        this.main_genre = main_genre;
    }

    public String getSubscribers() {
        return subscribers;
    }
    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }

    public String getJoin_date() {
        return join_date;
    }
    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }
}
