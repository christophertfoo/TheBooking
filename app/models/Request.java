package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

/**
 * This is intended to represent a request to buy a book.
 * 
 * @author Dylan Kobayashi
 * 
 */
@Entity
public class Request extends Model {

  /** Serial Version UID. */
  private static final long serialVersionUID = -125349722391271082L;
  /** Primary Key. */
  @Id
  public long id;
  /** Price willing to buy at. */
  public double price;
  /** Desired condition. */
  @ManyToOne(cascade = CascadeType.ALL)
  public Condition condition;
  /** Which book. */
  @ManyToOne(cascade = CascadeType.ALL)
  public Book book;
  /** Who made the request. */
  @ManyToOne(cascade = CascadeType.ALL)
  public Student student;

  /**
   * Returns the student who made this request.
   * @return the student.
   */
  public Student getStudent(){
    return student;
  }
  
  /**
   * Nullifies the student.
   */
  public void removeStudent(){
    student = null;
  }
  
  
  /**
   * Constructor for a request.
   * 
   * @param book being sought.
   * @param condition desired.
   * @param price willing to pay.
   * @param student who made the request.
   */
  public Request(Book book, Condition condition, double price, Student student) {
    this.book = book;
    this.condition = condition;
    this.price = price;
    this.student = student;
  }

  /**
   * Should find all instances of request in the database.
   * 
   * @return a Finder holding each request.
   */
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
}
