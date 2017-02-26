package com.hookah.roma.hookahmix;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

public class Tabak  {
    private String name;
        private String family;
    private String rating;
    private String description;
    private String isfavourite;
    private UUID id;

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
    public void setIsfavourite(String isfavourite) {this.isfavourite = isfavourite;}
    public String getName() {
        return name;
    }
    public String getRating() {return rating;}
    public String isfavourite() {
        return isfavourite;
    }
    public String getFamily() {
        return family;
    }
    public String getDescription() {
        return description;
    }
    public UUID getId() {
        return id;
    }


    @SerializedName("tabaksArrayList")
    @Expose
    private List<TabaksArrayList> tabaksArrayList = null;

    public List<TabaksArrayList> getTabaksArrayList() {
        return tabaksArrayList;
    }

    public void setTabaksArrayList(List<TabaksArrayList> tabaksArrayList) {
        this.tabaksArrayList = tabaksArrayList;
    }


}
