package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

/**
 * This is intended to represent an offer to sell a book.
 * 
 * @author Dylan Kobayashi
 * 
 */
@Entity
public class Offer extends Model {

  /** Serial Version UID. */
  private static final long serialVersionUID = -759560009407130916L;
  /** Primary key. */
  @Id
  public long id;
  /** Price the book is being offered for. */
  public double price;
  /** Which book is being offered. */
  @ManyToOne(cascade = CascadeType.ALL)
  public Book book;
  /** Condition being offered at. */
  @ManyToOne(cascade = CascadeType.ALL)
  public Condition condition;
  /** Who is making the offer. */
  @ManyToOne(cascade = CascadeType.ALL)
  public Student student;

  /**
   * Returns the student who made this offer.
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
   * Constructor for an offer.
   * 
   * @param book being offered.
   * @param condition of the book.
   * @param price being asked.
   * @param student who made the offer.
   */
  public Offer(Book book, Condition condition, double price, Student student) {
    this.book = book;
    this.condition = condition;
    this.price = price;
    this.student = student;
  }

  /**
   * Should find all instances of offer in the database.
   * 
   * @return a Finder holding each offer.
   */
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
}
