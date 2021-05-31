package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class OrderModel extends BaseModel implements Serializable {
    private String _id,collection_center,order_no,payment_type,delivery_type,order_status,shipping_fees,sub_total,main_total,promocode_discount,extra_location,wetmarket_id,delivery_date,user_name,user_pic,user_phone_number,created_date,created_time,employee_order_status,client_order_status,accepted_user_type,accepted_by,purchaser_flag,payment_status,purchaser_type,deliver_type,deliver_flag;
    private TimeSlotModel timeslot;
    private List<ProductModel> orderproduct;
    private List<OrderModel> docs;
    private WetMarketModel wetmarket;
    private OrderModel result;
    private UserModel purchased_by,deliver_by;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCollection_center() {
        return collection_center;
    }

    public void setCollection_center(String collection_center) {
        this.collection_center = collection_center;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getShipping_fees() {
        return shipping_fees;
    }

    public void setShipping_fees(String shipping_fees) {
        this.shipping_fees = shipping_fees;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getMain_total() {
        return main_total;
    }

    public void setMain_total(String main_total) {
        this.main_total = main_total;
    }

    public String getPromocode_discount() {
        return promocode_discount;
    }

    public void setPromocode_discount(String promocode_discount) {
        this.promocode_discount = promocode_discount;
    }

    public String getExtra_location() {
        return extra_location;
    }

    public void setExtra_location(String extra_location) {
        this.extra_location = extra_location;
    }

    public String getWetmarket_id() {
        return wetmarket_id;
    }

    public void setWetmarket_id(String wetmarket_id) {
        this.wetmarket_id = wetmarket_id;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public TimeSlotModel getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlotModel timeslot) {
        this.timeslot = timeslot;
    }

    public List<ProductModel> getOrderproduct() {
        return orderproduct;
    }

    public void setOrderproduct(List<ProductModel> orderproduct) {
        this.orderproduct = orderproduct;
    }

    public List<OrderModel> getDocs() {
        return docs;
    }

    public void setDocs(List<OrderModel> docs) {
        this.docs = docs;
    }

    public WetMarketModel getWetmarket() {
        return wetmarket;
    }

    public void setWetmarket(WetMarketModel wetmarket) {
        this.wetmarket = wetmarket;
    }

    public OrderModel getResult() {
        return result;
    }

    public void setResult(OrderModel result) {
        this.result = result;
    }

    public String getEmployee_order_status() {
        return employee_order_status;
    }

    public void setEmployee_order_status(String employee_order_status) {
        this.employee_order_status = employee_order_status;
    }

    public String getClient_order_status() {
        return client_order_status;
    }

    public void setClient_order_status(String client_order_status) {
        this.client_order_status = client_order_status;
    }

    public String getAccepted_user_type() {
        return accepted_user_type;
    }

    public void setAccepted_user_type(String accepted_user_type) {
        this.accepted_user_type = accepted_user_type;
    }

    public String getAccepted_by() {
        return accepted_by;
    }

    public void setAccepted_by(String accepted_by) {
        this.accepted_by = accepted_by;
    }

    public String getPurchaser_flag() {
        return purchaser_flag;
    }

    public void setPurchaser_flag(String purchaser_flag) {
        this.purchaser_flag = purchaser_flag;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPurchaser_type() {
        return purchaser_type;
    }

    public void setPurchaser_type(String purchaser_type) {
        this.purchaser_type = purchaser_type;
    }

    public String getDeliver_type() {
        return deliver_type;
    }

    public void setDeliver_type(String deliver_type) {
        this.deliver_type = deliver_type;
    }

    public String getDeliver_flag() {
        return deliver_flag;
    }

    public void setDeliver_flag(String deliver_flag) {
        this.deliver_flag = deliver_flag;
    }

    public UserModel getPurchased_by() {
        return purchased_by;
    }

    public void setPurchased_by(UserModel purchased_by) {
        this.purchased_by = purchased_by;
    }

    public UserModel getDeliver_by() {
        return deliver_by;
    }

    public void setDeliver_by(UserModel deliver_by) {
        this.deliver_by = deliver_by;
    }
}
