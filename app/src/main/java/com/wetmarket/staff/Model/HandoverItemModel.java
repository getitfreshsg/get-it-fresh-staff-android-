package com.wetmarket.staff.Model;

import com.wetmarket.staff.retrofit.model.OrderModel;
import com.wetmarket.staff.retrofit.model.ProductModel;

import java.util.List;

public class HandoverItemModel {

    private String incomplete_status, order_id;

    private List<ProductModel> orderproduct;

    public String getIncomplete_status() {
        return incomplete_status;
    }

    public void setIncomplete_status(String incomplete_status) {
        this.incomplete_status = incomplete_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public List<ProductModel> getOrderproduct() {
        return orderproduct;
    }

    public void setOrderproduct(List<ProductModel> orderproduct) {
        this.orderproduct = orderproduct;
    }
}
