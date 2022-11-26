package com.example.androidproject.Entities.News;


    // Model class for the news


import java.util.ArrayList;

public class NewsModel {

    private String title, description, content, pubDate, image_url, link;
    private ArrayList<String> creator, category;
    private String author;

    public NewsModel()
    {
        this.title = "";
        this.creator = new ArrayList<>();
        this.description = "";
        this.content = "";
        this.pubDate = "";
        this.image_url = "";
        this.category = new ArrayList<>();
        this.link = "";
        this.author = "";
    }
//
//    public NewsModel(){
//
//    }

    public NewsModel(String title,ArrayList<String> creator, String description, String content, String pubDate, String image_url, ArrayList<String> category, String link) {
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.content = content;
        this.pubDate = pubDate;
        this.image_url = image_url;
        this.category = category;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getImage_url() {
        return image_url;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public String getLink() {
        return link;
    }
    public String getAuthor(){
        return author;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setAuthor(String author){
        this.author = author;
    }



    public void setCreator(java.util.ArrayList<String> creator) {
        this.creator = creator;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "NewsModel{" +

                ", image_url='" + image_url + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
