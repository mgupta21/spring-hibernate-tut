package org.java.hibernate.dto;
// 2nd bean file to read

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;
    private String vehicleName;

    // Suppose one user has many vehicles but one vehile belongs to single user; Instead of @ManytoOne we can also add @OneToMany in USER_DETAILS table; Do one of these method but not both
// In main do vehicle1.getUser().add(user) instead of user.getVehicle().add(vehicle1);
    @ManyToOne
    private UserDetails user2;

    // adding new column in vehicle table instead of separate table to map vehicle and user table; must have @OneToMany(mappedBy="userV") in UserDetails class
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDetails userV;

    // Suppose there one user can have many vehicles but a vehicle is not assigned to any user yet and on accessing user of this vehicle hibernate will through an exception to avoid use NotFound
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private UserDetails user;

    // Create Collection of Vehicles in UserDetails and collection of Users in Vehicle; Add @ManyToMany annotation in both classes; During ManyToMany (mappedBy="[Object]") should be used because by default hibernate create 4 tables USER_DETAILS, VEHICLE, USER_DETAILS_VEHICLE and VEHICLE_USER_DETAILS; mappedBy="vehicle" removes redundant mapping table VEHICLE_USER_DETAILS
    @ManyToMany(mappedBy = "vehicle")
    private Collection<UserDetails> userList = new ArrayList<UserDetails>();

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public Collection<UserDetails> getUserList() {
        return userList;
    }

    public void setUserList(Collection<UserDetails> userList) {
        this.userList = userList;
    }

    public UserDetails getUserV() {
        return userV;
    }

    public void setUserV(UserDetails userV) {
        this.userV = userV;
    }
}
