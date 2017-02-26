
package com.hookah.roma.hookahmix;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TabaksArrayList {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("favourite")
    @Expose
    private String favourite;
    @SerializedName("family")
    @Expose
    private String family;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

}
