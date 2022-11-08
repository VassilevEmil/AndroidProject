package com.example.androidproject.Entities;


    // Model class for the news


public class NewsModel {

    private String title, creator, description, content, pubDate, image_url, category, url;

    public NewsModel(String title, String creator, String description, String content, String pubDate, String image_url, String category, String url) {
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.content = content;
        this.pubDate = pubDate;
        this.image_url = image_url;
        this.category = category;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
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

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
