package com.shubh.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastname;

    @Column(name = "street")
    private String street;

    @Column(name = "landmark")
    private String landmark;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String State;
    @Column(name = "zipcode")
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore

    private User user;
    private String mobile;

  public Address(){

  }

    public Address(long id, String firstName, String lastname, String street, String landmark, String city, String state, String zipcode, User user, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.street = street;
        this.landmark = landmark;
        this.city = city;
        this.State = state;
        this.zipcode = zipcode;
        this.user = user;
        this.mobile = mobile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
