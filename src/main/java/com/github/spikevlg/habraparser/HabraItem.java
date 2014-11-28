package com.github.spikevlg.habraparser;

import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.Objects;

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
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("title", title)
                .add("author", author)
                .add("isTranslate", isTranslate)
                .add("pageViews", pageViews)
                .add("countStars", countStars)
                .add("score", score)
                .add("\nlistHubs", listHubs)
                .add("\nlistTags", listTags)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HabraItem){
            HabraItem other = (HabraItem)obj;
            return Objects.equals(id, other.id)
                    && Objects.equals(title, other.title)
                    && Objects.equals(author, other.author);
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