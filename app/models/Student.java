package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.Email;
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
  private Long primaryKey;
  @Required
  private String studentId;
  /** First name. */
  @Required
  private String firstName;
  /** Last name. */
  @Required
  private String lastName;
  /** Email address. */
  @Required
  @Email
  private String emailAddress;

  /** All requests made by student. */
  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  private List<Request> requests = new ArrayList<Request>();

  /** All offers made by student. */
  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  private List<Offer> offers = new ArrayList<Offer>();
  
  /**
   * Constructor for student.
   * 
   * @param fname first name.
   * @param lname last name.
   * @param email email address.
   */
  public Student(String studentId, String fname, String lname, String email) {
    this.studentId = studentId;
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
  
  public String toString(){
    return String.format("[Student %s %s %s %s]", studentId, firstName, lastName, emailAddress);
  }

  /**
   * @return the primaryKey
   */
  public Long getPrimaryKey() {
    return primaryKey;
  }

  /**
   * @param primaryKey the primaryKey to set
   */
  public void setPrimaryKey(Long primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * @return the studentId
   */
  public String getStudentId() {
    return studentId;
  }

  /**
   * @param studentId the studentId to set
   */
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the emailAddress
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * @param emailAddress the emailAddress to set
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * @return the requests
   */
  public List<Request> getRequests() {
    return requests;
  }

  /**
   * @param requests the requests to set
   */
  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  /**
   * @return the offers
   */
  public List<Offer> getOffers() {
    return offers;
  }

  /**
   * @param offers the offers to set
   */
  public void setOffers(List<Offer> offers) {
    this.offers = offers;
  }
  
  
}
