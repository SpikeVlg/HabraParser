package com.github.spikevlg.habraparser;

public class HabraItem {
    private int id;
    private String name;
    private int favorites;
    private int visibles;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public void setVisibles(int visibles) {
        this.visibles = visibles;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getVisibles() {
        return visibles;
    }

}
