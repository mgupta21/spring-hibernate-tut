package org.java.hibernate.dto;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

//3rd way of extracting data from database
public class HibernateNamedQuery {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // 2nd (b) extraction way; eg 1,2

        Query query = session.createQuery("CrudUser.byId"); // use the named query (HQL)
        query.setInteger(0, 2); // set 1st (index 0) parameter of named query as 2/ query: select * from CrudUser where UserId = 2

        Query query2 = session.createQuery("CrudUser.byName"); // use native query (SQL)
        query2.setString(0, "Mayank Gupta"); // set parameter of native query

        // The 3rd Way; as constraints grow queries become bigger and hard to maintain; use criteria to resolve this issue
        // eg: 3
        Criteria criteria = session.createCriteria(CrudUser.class); // defining class on which the criteria should apply
        criteria.add(Restrictions.eq("UserName", "Mayank"));
        criteria.add(Restrictions.or(Restrictions.between("UserId", 0, 3), Restrictions.between("UserId", 7, 9))); // can add as many restrictions as required

        // eg; 4 ; projections are equivalent to getting Top 5 Ids, MaxID etc. queries in HQL
        // fetch all rows of userID column
        Criteria criteriaP = session.createCriteria(CrudUser.class).setProjection(Projections.property("UserId"));
        // fetch only max userId; i.e. aggregate function
        Criteria criteriaP1 = session.createCriteria(CrudUser.class).setProjection(Projections.max("UserId"));
// fetch no. of userIds
        Criteria criteriaP2 = session.createCriteria(CrudUser.class).setProjection(Projections.count("UserId"));
        Criteria criteriaP3 = session.createCriteria(CrudUser.class).addOrder(Order.desc("UserId"));

        // eg. 5: examples are useful when we have too many properties and criteria to satisfy so creating .add(Restrictions could be painful
        // so to resolve the issue create an example and ask hibernate to return all users which look like example
        CrudUser exampleUser = new CrudUser();
        exampleUser.setUserId(3); // this restriction is ignored as hibernate ignores null and primary keys in Examples
        exampleUser.setUserName("user%");

        Example exampleX = Example.create(exampleUser).excludeProperty("email"); // exclude unwanted columns in final result
        //Example example = Example.create(exampleUser); // used with exampleUser.setUserName("user 10");
        Example example = Example.create(exampleUser).enableLike();

        Criteria criteriaE = session.createCriteria(CrudUser.class).add(example);


        // Common to above examples
        List<CrudUser> users = (List<CrudUser>) criteriaE.list(); //pass criteria set instead of query

        session.getTransaction().commit();
        session.close();

        System.out.println("Number of users stored in the Database are: " + users.size());
        for (CrudUser x : users) {
            System.out.println(x.getUserName());
        }
    }
}
