package org.java.hibernate.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

    //	In general if create a object like User object and want to persist or save it to DB we use data layer object DAO but in this example we have used hibernate API's
    public static void main(String[] args) {

        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setUserName("Mayank");

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleId(1);
        vehicle1.setVehicleName("Ferrari");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleId(2);
        vehicle2.setVehicleName("BMW");

        // OneToMany
        /*user.getVehicle().add(vehicle1);
        user.getVehicle().add(vehicle2);*/

        // ManyToMany
/*vehicle1.getUserList().add(user);
        vehicle2.getUserList().add(user);*/
        /*

Address homeAdr = new Address();
		homeAdr.setCity("Chicago");
		homeAdr.setPincode("60607");
		homeAdr.setState("Illinois");
		homeAdr.setStreet("1065 West Polk");

		Address ofcAdr = new Address();
		ofcAdr.setCity("Chicago");
		ofcAdr.setPincode("60606");
		ofcAdr.setState("Illinois");
		ofcAdr.setStreet("550 West Washington");

		// ElementCollection
		user.getListOfAddresses().add(homeAdr);
		user.getListOfAddresses().add(ofcAdr);
		*/

//Embedded
        /*user.setHomeAdr(homeAdr);
		user.setOfficeAdr(ofcAdr);
		user.setJoinDate(new Date());
		user.setDescription("Registered Student");*/

        // created once in an application
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();// where the actual transaction to database occurs

        // @OneToMany(cascade=CascadeType.PERSIST)
        session.persist(user);

        //session.save(user);
		/*session.save(vehicle1);`
		session.save(vehicle2);*/

        session.getTransaction().commit();
        session.close();

        // reading/fetching the data from the dataBase
        user = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        user = (UserDetails) session.get(UserDetails.class, 1); /*lazy initialization; we do not initialize entire set of objects but only the first layer; so listOdAddress are not pulled here unless fetch is defined as eager; */

        //	int count = user.getListOfAddresses().size();
        //	System.out.printf("The user %s has %d addresses stored in database", user.getUserName(), count);
        System.out.println("User Name recieved is: " + user.getUserName());

        session.close();
    }

/*Hibernate Proxy (concept of lazy initialization)
- Proxy calss is a sub class of parent USER_DETAILS class created internally by hibernatehas having all getters of userDetails; if we do userDetails.get it internally fetches getters of proxy
- so when we fetch say first record of USER_DETAILS table [(UserDetails)session.get(UserDetails.class, 1)] proxy object passes getName or getId to userDetails object but not collection of addresses; but if we do user.getListOfAddresses() then proxy object’s user.getListOfAddresses() method first fetch values from database and then pass values to parent class’s user.getListOfAddresses() method */

}
