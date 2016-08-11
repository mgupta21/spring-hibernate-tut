package org.java.spring.dao;

//import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/* in jdbcDaoImpl we defined the templates instances, getters, setters and assigned the datasource; but in enterprise application
 where there is large dao classes defining these instances in every class would be repeating the code; so its better to put
instances in one class and let other class extend it; spring has already created superClasses as SimpleJdbcDaoSupport, JdbcDaoSupport
and namedParameterJdbcTemplateSupport*/

/* to get instance of template from support use this.getJdbcTemplate() and set dataSource in template through spring.XML */

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {

    public int getCircleCount() {
        String sql = "Select count(*) from Circle";
        return this.getJdbcTemplate().queryForInt(sql); // used getJdbcTemplate and not new jdbcTemplate() as in jdbcDaoImpl
    }
}

