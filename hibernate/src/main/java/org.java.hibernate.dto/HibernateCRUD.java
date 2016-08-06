package org.java.hibernate.dto;

// [(UserDetails)session.get(UserDetails.class, 1)]
// 1st way to extract data from DB

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateCRUD {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); //read configuration <session-factory> in hibernate.cfg.xml and get session factory
        Session session = sessionFactory.openSession(); // open a session
        session.beginTransaction(); // begin the transaction

        for (int i = 0; i < 10; i++) {
            CrudUser crudUser = new CrudUser();
            crudUser.setUserId(i);
            crudUser.setUserName("user" + i);  // transient object
            session.save(crudUser); // save data in database; persistent object; once the object is persisted any new updates called after
            //save statement will be automatically saved in database
        }

        CrudUser user = (CrudUser) session.get(CrudUser.class, 3);
        user.setUserName("Mayank Gupta");
        session.update(user);
        //session.delete(user);

        System.out.println("The user Retrieved is: " + user.getUserName());
        session.getTransaction().commit(); // persist the transaction
        session.close(); // close session; object is detached

        // situation when we fetch the data from DB and close the session and once an update is made by user
        // then again open the session and save the records
        session = sessionFactory.openSession();
        session.beginTransaction();
        user.setUserName("New User");
        session.update(user); // find the detached user make it persistant and update the record// used .update instead of .save

        session.getTransaction().commit();
        session.close();


    }
}

