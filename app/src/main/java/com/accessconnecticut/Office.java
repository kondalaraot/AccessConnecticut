package com.accessconnecticut;

import java.io.Serializable;

/**
 * Created by KTirumalsetty on 12/22/2016.
 */

public class Office implements Serializable {

    /*"fax": null,
            "name": "Capitol Office",
            "phone": "860-240-8585",
            "address": "Legislative Office Building\nRoom 4025\nHartford, CT 06106",
            "type": "capitol",
            "email": "Douglas.McCrory@cga.ct.gov" */

    private String fax;
    private String name;
    private String phone;
    private String address;
    private String type;
    private String email;

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
