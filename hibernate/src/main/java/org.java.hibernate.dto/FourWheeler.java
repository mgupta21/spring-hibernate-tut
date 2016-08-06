package org.java.hibernate.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*Hibernate inheritance concept
- Assume vehicles has two fields name and ID
- FourWheeler and TwoWheeler extends Vehicles
- TwoWheeler has steeringHandle and FourWheeler has steeringWheel
- Hibernate in DB will create only one single table Vehicle and it will have 5 columns ID, Name , streeingHandle and FourWheeler
- 5th column name is dType which could be Vehicle, TwoWheeler or FourWheeler; in order to use different name of 5th column
use @DiscriminatorColumn(name="VEHICLE_TYPE" in Vehicles and to choose values different than subclass name (eg: car instead of FourWheeler) use @DiscriminatorValue(value = "car") in FourWheeler and TwoWheeler to avoid default name;
- In order to use separate 3 tables for parent and child instead of single put inheritance strategy as TABLE_PER_CLASS in vehicles entity*/

@Entity
@DiscriminatorValue(value = "car") // default name is class name changed to car
public class FourWheeler extends Vehicles {

    private String streeingWheel;

    public String getStreeingWheel() {
        return streeingWheel;
    }

    public void setStreeingWheel(String streeingWheel) {
        this.streeingWheel = streeingWheel;
    }


}
