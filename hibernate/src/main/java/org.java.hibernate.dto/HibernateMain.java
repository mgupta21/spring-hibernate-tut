package org.java.hibernate.dto;

// 2nd among Hibernate*.java

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// inheritance concept in hibernate
public class HibernateMain {

    public static void main(String[] args) {

        Vehicles vehicle = new Vehicles();
        TwoWheeler bike = new TwoWheeler();
        FourWheeler car = new FourWheeler();

        vehicle.setVehicleName("car");
        vehicle.setVehicleId(01);

        bike.setVehicleId(21);
        bike.setVehicleName("Pulsar");
        bike.setSteeringHandle("bajaj Handle");

        car.setVehicleId(31);
        car.setVehicleName("CR-V");
        car.setStreeingWheel("Honda Handle");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(bike);
        session.save(car);
        session.save(vehicle);

        session.getTransaction().commit();
        session.close();

    }

}
