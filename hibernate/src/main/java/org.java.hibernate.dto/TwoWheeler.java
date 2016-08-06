package org.java.hibernate.dto;


import javax.persistence.Entity;


@Entity
/*@DiscriminatorValue(value = "bike")*/
public class TwoWheeler extends Vehicles {

    private String steeringHandle;

    public String getSteeringHandle() {
        return steeringHandle;
    }

    public void setSteeringHandle(String steeringHandle) {
        this.steeringHandle = steeringHandle;
    }

}
