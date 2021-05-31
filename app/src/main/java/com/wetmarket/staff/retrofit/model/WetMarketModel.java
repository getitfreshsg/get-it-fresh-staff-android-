package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class WetMarketModel extends BaseModel implements Serializable {

    private String _id, type, name, address;
    private List<String> coordinates;
    private List<WetMarketModel> result;
    private WetMarketModel location;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public List<WetMarketModel> getResult() {
        return result;
    }

    public void setResult(List<WetMarketModel> result) {
        this.result = result;
    }

    public WetMarketModel getLocation() {
        return location;
    }

    public void setLocation(WetMarketModel location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return name;
    }
}
