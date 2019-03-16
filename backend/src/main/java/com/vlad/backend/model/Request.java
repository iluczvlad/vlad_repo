package com.vlad.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;
    private Date placementDate;
    private Date confirmationDate;
    @ManyToOne
    private User basic;
    @ManyToOne
    private User clerk;
    private String phoneNr;
    private String address;
    @ManyToMany
    private List<ShoppingItem> orderItems;

    public User getBasic() {
        return basic;
    }

    public void setBasic(User basic) {
        this.basic = basic;
    }

    public User getClerk() {
        return clerk;
    }

    public void setClerk(User clerk) {
        this.clerk = clerk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ShoppingItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ShoppingItem> orderItems) {
        this.orderItems = orderItems;
    }
}
