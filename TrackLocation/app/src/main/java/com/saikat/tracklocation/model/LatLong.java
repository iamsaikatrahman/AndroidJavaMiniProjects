package com.saikat.tracklocation.model;

public class LatLong {
    private String userLagtude;
    private String userLongittude;

    public LatLong(String userLagtude, String userLongittude) {
        this.userLagtude = userLagtude;
        this.userLongittude = userLongittude;
    }

    public String getUserLagtude() {
        return userLagtude;
    }

    public void setUserLagtude(String userLagtude) {
        this.userLagtude = userLagtude;
    }

    public String getUserLongittude() {
        return userLongittude;
    }

    public void setUserLongittude(String userLongittude) {
        this.userLongittude = userLongittude;
    }
}
