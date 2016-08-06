package org.java.hibernate.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/*@Inheritance(strategy=InheritanceType.SINGLE_TABLE) - default, single table
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // creates separate table for parent and child classes with inherited columns; normalized; no need for discriminator column
@Inheritance(strategy=InheritanceType.JOINED) // 3rd strategy; vehicle has name ids of twowheeler and fourwheeler as well and twowheeler and fourwheeler have only vechileId and SteeringType column and not the inherited columns; best normalized form
/*@DiscriminatorColumn(name="VEHICLE_TYPE",discriminatorType=DiscriminatorType.STRING)*/
public class Vehicles {

    @Id
    @GeneratedValue // @GeneratedValue property is also inherited in child classes
    private int vehicleId;
    private String vehicleName;

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
}

