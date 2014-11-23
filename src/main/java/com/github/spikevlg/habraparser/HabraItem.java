package com.github.spikevlg.habraparser;

import java.util.List;

public class HabraItem {
    private int id;
    private String title;
    private String author;
    private int countStars;
    private int pageViews;
    private boolean isTranslate;
    private double score;
    private List<String> listHubs;
    private List<String> listTags;

    @Override
    public String toString() {
        return String.format("id=%d title='%s' author='%s' isTranslate=%b pageViews=%d countStars=%d score=%.2f%n"
                + "listHubs=%s%nlistTags=%s", id, title, author, isTranslate, pageViews, countStars, score, listHubs
                , listTags);
    }

    @Override
    public int hashCode() {
        return id + title.hashCode() + author.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HabraItem){
            HabraItem other = (HabraItem)obj;
            return id == other.id
                    && title == other.title
                    && author == other.author;
        } else return false;
    }

    public List<String> getListTags() {
        return listTags;
    }

    public void setListTags(List<String> listTags) {
        this.listTags = listTags;
    }


    public List<String> getListHubs() {
        return listHubs;
    }

    public void setListHubs(List<String> listHubs) {
        this.listHubs = listHubs;
    }
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCountComments() {
        return countComments;
    }

    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }

    private int countComments;

    public boolean isTranslate() {
        return isTranslate;
    }

    public void setTranslate(boolean isTranslate) {
        this.isTranslate = isTranslate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCountStars(int countStars) {
        this.countStars = countStars;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public int getCountStars() {
        return countStars;
    }

    public int getPageViews() {
        return pageViews;
    }

}
