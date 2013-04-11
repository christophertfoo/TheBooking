package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * This is intended to represent a student.
 * 
 * @author Dylan Kobayashi
 * 
 */
@Entity
public class Student extends Model {

  /** Serial Version UID. */
  private static final long serialVersionUID = -971170679958998217L;
  /** Primary key. */
  @Id
  public long id;
  /** First name. */
  public String firstName;
  /** Last name. */
  public String lastName;
  /** Email address. */
  public String emailAddress;

  /** All requests made by student. */
  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  public List<Request> requests = new ArrayList<Request>();

  /** All offers made by student. */
  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  public List<Offer> offers = new ArrayList<Offer>();
  
  /**
   * Returns the list of offers.
   * @return the list.
   */
  public List<Offer> getOffers(){
    return offers;
  }
  
  /**
   * Removes all offers associated.
   */
  public void clearOffers(){
    offers.clear();
  }

  /**
   * Constructor for student.
   * 
   * @param fname first name.
   * @param lname last name.
   * @param email email address.
   */
  public Student(String fname, String lname, String email) {
    this.firstName = fname;
    this.lastName = lname;
    this.emailAddress = email;
  }

  /**
   * Should find all instances of condition in the database.
   * 
   * @return a Finder holding each condition.
   */
  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
}
