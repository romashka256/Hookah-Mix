
package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MixesArrayList {

    @SerializedName("ingred1")
    @Expose
    private String ingred1;
    @SerializedName("ingred2")
    @Expose
    private String ingred2;
    @SerializedName("ingred3")
    @Expose
    private String ingred3;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("proc1")
    @Expose
    private String proc1;
    @SerializedName("proc2")
    @Expose
    private String proc2;
    @SerializedName("proc3")
    @Expose
    private String proc3;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("family")
    @Expose
    private String family;
    @SerializedName("favourite")
    @Expose
    private String favourite;

    public String getIngred1() {
        return ingred1;
    }

    public void setIngred1(String ingred1) {
        this.ingred1 = ingred1;
    }

    public String getIngred2() {
        return ingred2;
    }

    public void setIngred2(String ingred2) {
        this.ingred2 = ingred2;
    }

    public String getIngred3() {
        return ingred3;
    }

    public void setIngred3(String ingred3) {
        this.ingred3 = ingred3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProc1() {
        return proc1;
    }

    public void setProc1(String proc1) {
        this.proc1 = proc1;
    }

    public String getProc2() {
        return proc2;
    }

    public void setProc2(String proc2) {
        this.proc2 = proc2;
    }

    public String getProc3() {
        return proc3;
    }

    public void setProc3(String proc3) {
        this.proc3 = proc3;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

}
