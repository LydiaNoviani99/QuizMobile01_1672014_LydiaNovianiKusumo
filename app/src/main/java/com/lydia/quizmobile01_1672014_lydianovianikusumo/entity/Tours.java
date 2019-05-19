package com.lydia.quizmobile01_1672014_lydianovianikusumo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author 1672014 - Lydia Noviani Kusumo
 */

public class Tours {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("category")
    private String category;
    @SerializedName("location")
    private String location;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "Tours{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
