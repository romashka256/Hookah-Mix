package com.hookah.roma.hookahmix;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tabak  {
    private String name;
        private String family;
    private String rating;
    private String description;
    private String favourite;
    private String id;
    private String second_name;

    public Tabak() {}
    public void setName(String name) {
        this.name = name;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIsfavourite(String isfavourite) {this.favourite = isfavourite;}
    public void setSecond_name(String second_name){this.second_name = second_name;}
    public String getName() {
        return name;
    }
    public String getRating() {return rating;}
    public String isfavourite() {return favourite;}
    public String getFamily() {
        return family;
    }
    public String getDescription() {
        return description;
    }
    public String getId() { return id; }
    public String getPhotoFilename() {return getId();}
    public String getSecond_name(){return second_name; }



    @SerializedName("tabaksArrayList")
    @Expose
    private List<Tabak> tabaksArrayList = null;

    public List<Tabak> getTabaksArrayList() {
        return tabaksArrayList;
    }

    public void setTabaksArrayList(List<Tabak> tabaksArrayList) {
        this.tabaksArrayList = tabaksArrayList;
    }


}
