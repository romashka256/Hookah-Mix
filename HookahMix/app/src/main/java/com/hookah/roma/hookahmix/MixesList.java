
package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MixesList {

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
