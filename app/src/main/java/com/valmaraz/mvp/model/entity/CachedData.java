package com.valmaraz.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vAlmaraz on 27/07/2016.
 * https://www.valmaraz.com
 */
public class CachedData {

    @SerializedName("id")
    public String id;

    @SerializedName("saved_timestamp")
    public long savedTimestamp;

    @SerializedName("response_json")
    public String responseJson;

    public CachedData(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof CachedData)) {
            return false;
        }
        CachedData other = (CachedData) o;
        if (other.id != null && id == null || other.id == null && id != null || other.id != null && id != null && !other.id.equals(id)) {
            return false;
        }
        return other.responseJson.equals(responseJson);
    }

    public void saveResponse(String responseJson) {
        this.responseJson = responseJson;
        savedTimestamp = System.currentTimeMillis();
    }
}
