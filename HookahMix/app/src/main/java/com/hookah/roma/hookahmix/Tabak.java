package com.hookah.roma.hookahmix;


import java.util.UUID;

public class Tabak {
    private String name;
    private String family;
    private String rating;
    private String description;
    private boolean isfavourite;
    private UUID id;

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public boolean isfavourite() {
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
}
