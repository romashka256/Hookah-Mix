package com.hookah.roma.hookahmix;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mix {

    private String ingred1;
    private String ingred2;
    private String ingred3;
    private String ingred4;
    private String proc1;
    private String proc2;
    private String proc3;
    private String proc4;
    private String description;
    private String rating;
    private String favourite;
    private String family;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

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

    public String getIngred4() {
        return ingred4;
    }

    public void setIngred4(String ingred4) {
        this.ingred4 = ingred4;
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

    public String getProc4() {
        return proc4;
    }

    public void setProc4(String proc4) {
        this.proc4 = proc4;
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

    @SerializedName("mixesArrayList")
    @Expose
    private List<MixesArrayList> mixesArrayList = null;

    public List<MixesArrayList> getMixesArrayList() {
        return mixesArrayList;
    }

    public void setMixesArrayList(List<MixesArrayList> mixesArrayList) {
        this.mixesArrayList = mixesArrayList;
    }

}
