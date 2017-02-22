package com.hookah.roma.hookahmix.models.objects;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Tabak implements Parcelable {
    private String name;
    private String family;
    private String rating;
    private String description;
    private String isfavourite;
    private UUID id;

    public Tabak(Parcel in) {
        name = in.readString();
        family = in.readString();
        rating = in.readString();
        description = in.readString();
        isfavourite = in.readString();
    }

    public static final Creator<Tabak> CREATOR = new Creator<Tabak>() {
        @Override
        public Tabak createFromParcel(Parcel in) {
            return new Tabak(in);
        }

        @Override
        public Tabak[] newArray(int size) {
            return new Tabak[size];
        }
    };

    public Tabak() {

    }

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

    public void setIsfavourite(String isfavourite) {
        this.isfavourite = isfavourite;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(family);
        dest.writeString(description);
        dest.writeString(rating);
        dest.writeString(isfavourite);
    }
}
