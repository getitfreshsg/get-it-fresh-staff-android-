package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;

public class TimeSlotModel implements Serializable {
    private String _id,name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
