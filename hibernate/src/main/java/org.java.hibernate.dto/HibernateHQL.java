package org.java.hibernate.dto;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

// 2nd way of fetching data
public class HibernateHQL {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // eg: 1
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query2 = session.createQuery("select UserName from CrudUser");
        List<String> userNames = (List<String>) query2.list(); // queryObject.List() retrieves list of objects

        session.getTransaction().commit();
        session.close();

        // since users are retrieved and saved in a object we can iterate through the results after closing the session
        for (String y : userNames) {
            System.out.println(y);
        }

        // eg: 2
        String minId = "2";
        String uName = "Mayank Gupta";

        // to get more control on database updates we can use query instead of user.Set, get, session.delete, update commands
        // to do so just create a query and provide to query object
        Query query = session.createQuery(" from CrudUser where UserId > :minId and userName = :uName");
        query.setInteger("minId", Integer.parseInt(minId));
        query.setString("uName", uName);

        // eg: 3 ; pagination logic

        // fetch result no. 3 to 13
        query.setFirstResult(3);
        query.setMaxResults(10);

        // common to eg 2 and 3
        List<CrudUser> users = (List<CrudUser>) query.list();

        session.getTransaction().commit();
        session.close();

        System.out.println("Number of users stored in the Database are: " + users.size());

        for (CrudUser x : users) {
            System.out.println(x.getUserName());
        }
    }
}
