package com.example.a5g;

public class News {
    private String newsTitle;   //新聞標題
    private String newsUrl;     //新聞鏈接地址
    private String desc;        //新聞概要
    private String newsTime;    //新聞時間與來源

    public News(String newsTitle, String newsUrl, String desc, String newsTime) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.desc = desc;
        this.newsTime = newsTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}