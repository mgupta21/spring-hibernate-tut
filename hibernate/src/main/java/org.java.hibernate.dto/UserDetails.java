package org.java.hibernate.dto;

import javax.persistence.*;
import java.util.*;

// Not in persistent API and are specific only to hibernate

//@Entity(name="USER_DETAILS")  // entity persist class into db table; table name is user defined
@Table(name = "USER_DETAILS") //set name of database table as USER_DETAILS without changing entity's name
public class UserDetails {

    @Id //@GeneratedValue(strategy=GenerationType.AUTO)  // auto generate primary key of the table
    @Column(name = "USER_ID") // use USER_ID as name of DB table column
    private int userId;

    @Column(name = "USER_NAME")
    @Transient //restrict the hibernate from creating a USER_NAME column in dataBase Table
    private String userName;

    @Temporal(TemporalType.DATE) // saves only the date and not the time
    private Date joinDate;
    @Lob // large objects
    private String description;

    @Embedded // embed variables of Address class as columns in UserDetails table
    private Address homeAdr;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "OFFICE_STREET_NAME")),
            @AttributeOverride(name = "city", column = @Column(name = "OFFICE_CITY_NAME")),
            @AttributeOverride(name = "state", column = @Column(name = "OFFICE_STATE_NAME")),
            @AttributeOverride(name = "pincode", column = @Column(name = "OFFICE_PINCODE_NUMBER")),
    })
    private Address officeAdr;
//overrides the default column names for officeAddress columns in USER_DETAILS Table in DB
// Use EmbeddedID to combine id columns of table and embedded table as the primary key

    //@ElementCollection used when we have list of objects instead of fixed no. of address (2 - office/home); We cannot embed the table in user table but create separate Address table with UserId as foreign key (a.k.a joinTable )
// ElementCollection tells java that Addresses Object should be persisted separatly in DB and is not embedded in user details table; so 2 tables are create USER_DETAILS and UserDetails_listofaddresses having Foreign key UserDetails_UserId; @ElementCollection doesn’t create primary key and UserDetails_UserId will have duplicate UserDetails_UserId
    @ElementCollection
    private Set<Address> ListOfAddresses2 = new HashSet();

    //@JoinTable used with List of Objects or ElementCollection; user defined joined table name and column name otherwise UserDetails_listofaddresses; total 2 tables in DB USER_DETAILS and USER_ADDRESS
// FetchType.EAGER tells hibernate to fetch JoinTable USER_ADDRESS data when a USER_DETAILS record is fetched; Lazy, fetch only id and name and not the joinTable.
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    private Set<Address> ListOfAddresses3 = new HashSet();


    // CollectionId (used in Collection<>) add additional primary key(optional) column (ADDRESS_ID) in USER_ADDRESS table in addition to USER_ID (foreign key); since Address class has no Id column use GenericGenerator to Auto generate primary key;
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    //@GenericGenerator(name = "hello-gen", strategy = "hilo”) , @CollectionId(columns = {@Column(name = "ADDRESS_ID")}, generator = "hello-gen", type = @Type(type = "long"))")
    private Collection<Address> ListOfAddresses = new ArrayList<Address>();

    // Mapping is used when there is an entity inside entity, Address was not an entity but embeddable object (not defined in hibernate.cfg.xml) but Vehicle is an entity and has separate table;
// JoinTable give new name Vehicle_ID to foreign key of USER_DETAILS table which by default was vehicle_vehicleid
    @OneToOne
    @JoinColumn(name = "Vehicle_ID")
    private Vehicles vehicle;

    // Suppose one user has many vehicles but one vehile belongs to single user
// OneToOne added foreign key column to USER_DETAILS table but OneToMany doesn’t add any foreign key in either of tables instead create 3rd Table USER_DETAILS_VEHICLE with USER_DETAILS_userId and VEHICLE_vehicleId columns; JoinTable change default names of 3rd table and its columns
// Instead of doing OneToMany in USER_DETAILS we can create User object in Vehicle entity and do @ManyToOne
    @OneToMany
    @JoinTable(name = "USER_VEHICLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID"))
    private Collection<Vehicle> vehicles2 = new ArrayList<Vehicle>();

    //@OneToMany generated a 3rd table USER_VEHICLE but we can still use only 2 tables by adding UderID as foreign key to Vehicle table (USER_DETAILS table cannot have vehicle_ID column becoz one user can have multiple vehicles but we can have vehicle id have USER_ID column by mappedBy="USER_DETAILS Object in Vehicle Entity". For this approach declare both @OneToMany(mappedBy="userV") in USER_DETAILS and @ManyToOne in Vehicles table */
    @OneToMany(mappedBy = "userV")
    private Collection<Vehicle> vehicles3 = new ArrayList<Vehicle>();

    // Suppose one User can hire many vehicles and one vehicle can be rented to many users
// In OneToMany we can choose to add 3 or 2 tables but in ManyToMany there must be 3rd table to map UserID to VehicleID; Create Collection of Vehicles in UserDetails and collection of Users in Vehicle; Add @ManyToMany annotation in both classes
    @ManyToMany
    private Collection<Vehicle> vehicles4 = new ArrayList<Vehicle>();

    // if a user has 2 vehicles then in Main we do session.save(user) and session.save(vehicle1), session.save(vehicle2) but instead of these three lines we have declare cascade here and do session.persist(user) so dependent entities are automatically persisted
    @OneToMany(cascade = CascadeType.PERSIST)
    //@OneToMany
    private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getHomeAdr() {
        return homeAdr;
    }

    public void setHomeAdr(Address homeAdr) {
        this.homeAdr = homeAdr;
    }

    public Address getOfficeAdr() {
        return officeAdr;
    }

    public void setOfficeAdr(Address officeAdr) {
        this.officeAdr = officeAdr;
    }

    public Set<Address> getListOfAddresses() {
        return ListOfAddresses2;
    }

    public void setListOfAddresses(Collection<Address> listOfAddresses) {
        ListOfAddresses = listOfAddresses;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
    }

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Collection<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setListOfAddresses(Set<Address> listOfAddresses) {
        ListOfAddresses = listOfAddresses;
    }
}
