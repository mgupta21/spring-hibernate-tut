package org.java.hibernate.dto;

//4th bean file to read

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity // declare the class as DB table
@Cacheable // see online tutorials for cache
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
// inform hibernate to update database only if value of object is
//changed, if not them even if user has asked to update the object hibernate will run only select to check db values and not update the DB

@NamedQuery(name = "CrudeUser.byId", query = "from CrudUser where userId= ?")
// Instead of using HQL query in calling file we can define the query in entity class itself
@NamedNativeQuery(name = "CrudeUser.byName", query = "select * from CrudUser where userName=?", resultClass = CrudUser.class)
// instead of using HQL query we can simply use
//SQL query; requires class name to be mentioned since hibernate doesn't map SQL queries to entity but HQL does
public class CrudUser {
    @Id //@GeneratedValue //Auto generate the primary key for each new row entry
    private int UserId;
    private String UserName;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}

