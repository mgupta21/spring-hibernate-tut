package org.java.hibernate.dto;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateCache {
/*Using Hibernate Api (store transactions in a session and then save)
- Create a Session Factory
- Create a Session from session Factory
- Use the session to save model objects; commit; close*/

    public static void main(String[] args) {
        // configuration-gets/opens hibernate.Cfg file; configure the database as per file; buildSeesionFactory returns a session
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from CrudUser where userId = 1");
        query.setCacheable(true);
        List user = query.list();

        //CrudUser user = (CrudUser)session.get(CrudUser.class, 1);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        Query query2 = session2.createQuery("from CrudUser where userId = 1");
        query2.setCacheable(true);
        user = query2.list();

        //CrudUser user2 = (CrudUser)session2.get(CrudUser.class, 1);
        session2.getTransaction().commit();
        session2.close();
    }
}

