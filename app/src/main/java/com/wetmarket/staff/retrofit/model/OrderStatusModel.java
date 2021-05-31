package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusModel extends BaseModel implements Serializable {
    private String _id, status_code, status_name;
    private List<OrderStatusModel> result=new ArrayList<>();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public List<OrderStatusModel> getResult() {
        return result;
    }

    public void setResult(List<OrderStatusModel> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return status_name;
    }
}
