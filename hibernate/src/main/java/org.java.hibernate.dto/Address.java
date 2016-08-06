package org.java.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/* makes address table to be embeddable in UserDetails table so in userdetails table will have 4 new columns instead of
single Address column; 4 new columns are 4 private variables defined in this class; Address field in UserDetails must be marked
as @Embedded
*/
@Embeddable
public class Address {

    /*column names defined here will be used as default names of in userDetails table but if userDetails have 2 address variables
    one for homeAddress and other for Office Address then we can use @AttributeOverride on OfficeAddress to give new column
    names to to office address columns in userDetails table*/
    @Column(name = "STREET_NAME")
    private String street;
    @Column(name = "CITY_NAME")
    private String city;
    @Column(name = "STATE_NAME")
    private String state;
    @Column(name = "PINCODE_NUMBER")
    private String pincode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


}
