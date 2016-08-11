package org.java.spring.dao;

import org.apache.derby.client.am.PreparedStatement;
import org.apache.derby.client.am.ResultSet;
import org.java.spring.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Component // marking the bean as spring bean
public class JdbcDaoImpl {
    //@Autowired // put @Autowired at setDataSource() to make spring initialize dataSource at load
    private DataSource dataSource; // don't forget to create getters and setters
    //	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // alternative Template, supports named parameters :id, :name
    private SimpleJdbcTemplate simpleJdbcTemplate; // another alternative parameter supports both ? and named parameter placeholder in sql

    // using JDBC
    public Circle getCircle(int id) {

        Connection conn = null;
        try {

            // connecting without spring using simple JDBC
            String driver = "org.apache.derby.jdbc.ClientDriver";  // define the database client
            Class.forName(driver).newInstance(); // create an object of the client
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db"); // connect to database client

            PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM Circle where id = ?");
            ps.setInt(1, id);

            Circle circle = null;
            ResultSet rs = (ResultSet) ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(id, rs.getString("name"));
            }
            rs.close();
            ps.close();
            return circle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // using Spring Datasource Altnative 1
    public Circle getCircle1(int id) {

        Connection conn = null;
        try {

            // defined dataSource in Spring.XML and can be used by different methods rather than redefining
            conn = dataSource.getConnection(); // not used in simple JDBC but in spring; dataSource defined in spring.XML calling getConnection() of spring DriverManagerDataSource class

            PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM Circle where id = ?");
            ps.setInt(1, id);

            Circle circle = null;
            ResultSet rs = (ResultSet) ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(id, rs.getString("name"));
            }
            rs.close();
            ps.close();
            return circle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

	/* getCircle1 and getCircle methods get a single row based on ID; if we want to get CircleCount we will have to write another method
     *  with new query along with unnecessary and repetitive code such as prepare, execute, close and exception handling
	 *  to make it simple spring provides jdbcTemplate that takes dataSource and query only and takes care of all unnecessary code automatically   */

    // using alternative 3
    public int getCircleCount1() {
        String sql = "Select count(*) from Circle";
        jdbcTemplate.setDataSource(getDataSource()); // getDataSource() defined in spring.XML and set in jdbcTemplate
        return jdbcTemplate.queryForInt(sql); // returns Integer so use QueryForInt
    }

    // using alternative 4;
    /*we would still be repeating the code if we set datasouce everytime we use jdbcTemplate for different methods;
    an alternative is to define jdbcTemplate bean in spring.XML and set its dataSource property their or initialize the
	jdbcTemplate in setDataSource()	itself in this class*/
    public int getCircleCount() {
        String sql = "Select count(*) from Circle";
        // dataSource is set in setDataSource()
        return jdbcTemplate.queryForInt(sql); // returns Integer so use QueryForInt
    }

    /* In getCircleCount we didn't pass any parameter in query and we retrieved int value; suppose we need to fetch string object for particular ID */
    public String getCircleName(int circleId) {
        String sql = "Select name from Circle where id = ?"; //? is replaced by circleId parameter passed to this method
        return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, String.class); // pass SQL query, parametrs of query and type of object returned (String in this case)
    }

    /* Retrieving Circle object; spring doesn't know about custom circle model and which column to map to which variable */
    public Circle getCircleForId(int circleId) {
        String sql = "Select * from Circle where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, new CircleMapper()); // circle mapper class is defined below
    }//jdbc doesn't now how to map Objects to respective fields so use RowMapper as below

    private static final class CircleMapper implements RowMapper<Circle> { // class implement RowMapper Interface having mapRow() method

        @Override
        public Circle mapRow(java.sql.ResultSet resultSet, int rowNum) //mapRow takes 2 parameters resultSet(array of rows) and present position in result set via rowNum
                throws SQLException {
            // below is user defined code (rest was from interface)
            Circle circle = new Circle();
            circle.setId(resultSet.getInt("ID"));
            circle.setName(resultSet.getString("NAME"));
            return circle;
        }// passing the logic for mapping the record of result set to an object and returning the object	}

        /*Retrieving all rows and not based on ID*/
        public List<Circle> getAllCircles() {
            String sql = "Select * from Circle";
            return jdbcTemplate.query(sql, new CircleMapper()); // no parameter passed as we want all the results
        }

        // Inserting Data in DataBase (Uses void methods as nothing is returned)
        public void insertCircle(Circle circle) {
            String sql = "Insert into Circle (ID, Name) values (?,?)";
            jdbcTemplate.update(sql, new Object[]{circle.getId(), circle.getName()}); // passing sql and circle objects with parameters
        }

        // creating new table in DB through code; earlier we created circle manually in DB
        public void createTriangleTable() {
            String sql = "Create Table Triangle (ID Integer, Name Varchar(50))";
            jdbcTemplate.execute(sql);
        }

        // named parameters instead of ?; use namedParameterJdbcTemplate and not JdbcTemplate; shortcoming of JdbcTemplate cannot take query with namedParameters
        public void insertCircleNM(Circle circle) {
            String sql = "Insert into Circle (ID, Name) values (:id,:name)";
            //SqlParameterSource is source of named parameters in SQl
            SqlParameterSource namedParameter = new MapSqlParameterSource("id", circle.getId()).addValue("name", circle.getName());
            // cannot create new SqlParameterSource as it is an interface but MapSqlParameterSource which is implementation of this interface
            namedParameterJdbcTemplate.update(sql, namedParameter); // takes sql and namedParameter as parameters
        }

        public JdbcTemplate getJdbcTemplate() {
            return jdbcTemplate;
        }

        public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public DataSource getDataSource() {
            return dataSource;
        }

        @Autowired
        public void setDataSource(DataSource dataSource) {
            //this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
            this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        }
    }

