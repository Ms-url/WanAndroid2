package com.example.wanandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
  用于文章
 */
public class UsefulData {
    private String top;
    private String title;
    private String niceDate;
    private String link;
    private String shareUser;
    private String desc;
    private String author;
    private String chapterName;
    private String superChapterName;
    private String projectLink;
    private int id;

    public UsefulData(String title, String niceDate, String link, String shareUser, String desc, String author, String chapterName, String superChapterName, String projectLink, int id, String top) {
        this.title = title;
        this.niceDate = niceDate;
        this.link = link;
        this.shareUser = shareUser;
        this.desc = desc;
        this.author = author;
        this.chapterName = chapterName;
        this.superChapterName = superChapterName;
        this.projectLink = projectLink;
        this.id = id;
        this.top = top;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
