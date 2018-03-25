package com.example.hp.booleans.FetchData;

/**
 * Created by HP on 24-03-2018.
 */

public class Data {
    private String title ;
    private String fake_news;
    private String real_news;
    private String image_url;
    private String dop;
    private String person_name;
    private String news_action;
    private String news_place;
    private String others;

    public Data(){

    }

    public Data(String title, String fake_news, String real_news, String image_url, String dop, String person_name, String news_action, String news_place, String others) {
        this.title = title;
        this.fake_news = fake_news;
        this.real_news = real_news;
        this.image_url = image_url;
        this.dop = dop;
        this.person_name = person_name;
        this.news_action = news_action;
        this.news_place = news_place;
        this.others = others;
    }

    public String getTitle() {
        return title;
    }

    public String getFake_news() {
        return fake_news;
    }

    public String getReal_news() {
        return real_news;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDop() {
        return dop;
    }

    public String getPerson_name() {
        return person_name;
    }

    public String getNews_action() {
        return news_action;
    }

    public String getNews_place() {
        return news_place;
    }

    public String getOthers() {
        return others;
    }
}
