package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import play.data.validation.Constraints.Required;
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
  private Long primaryKey;
  @Required
  private String offerId;
  /** Price the book is being offered for. */
  @Required
  private String price;
  /** Which book is being offered. */
  @Required
  @ManyToOne(cascade = CascadeType.ALL)
  private Book book;
  /** Condition being offered at. */
  @Required
  @ManyToOne(cascade = CascadeType.ALL)
  private Condition condition;
  /** Who is making the offer. */
  @Required
  @ManyToOne(cascade = CascadeType.ALL)
  private Student student;
  @Transient
  private String bookId;
  @Transient
  private String conditionId;
  @Transient
  private String studentId;
  
  
  
  /**
   * Constructor for an offer.
   * 
   * @param book being offered.
   * @param condition of the book.
   * @param price being asked.
   * @param student who made the offer.
   */
  public Offer(String offerId, Book book, Condition condition, String price, Student student) {
    this.offerId = offerId;
    this.book = book;
    this.condition = condition;
    this.price = price;
    this.student = student;
  }
  
  public String validate() {
    String msg = null;
    
    if( book != null){
      if(!Book.find().findList().contains(this.book)) {
        msg = "Book with id: " + book.getBookId() + " doesn't exist.";
      }
    }
    else {
      msg = "Book with id: " + book.getBookId() + " doesn't exist.";
    }
    if( condition != null){
      if(!Condition.find().findList().contains(this.condition)) {
        if(msg == null){
          msg = "Condition with id: " + condition.getConditionId() + " doesn't exist.";
        }
        else {
          msg = msg.concat(" Condition with id: " + condition.getConditionId() + " doesn't exist.");
        }
      }
    }
    else if(condition ==null && msg == null){
      msg = "Condition with id: " + condition.getConditionId() + " doesn't exist.";
    }
    else {
      msg = msg.concat(" Condition with id: " + condition.getConditionId() + " doesn't exist.");
    }
    if( student != null){
      if(!Student.find().findList().contains(this.student)) {
        if(msg == null){
          msg = "Student with id: " + student.getStudentId() + " doesn't exist.";
        }
        else {
          msg = msg.concat(" Student with id: " + student.getStudentId() + " doesn't exist.");
        }
      }
    }
    else if(student ==null && msg == null){
      msg = "Student with id: " + student.getStudentId() + " doesn't exist.";
    }
    else {
      msg = msg.concat(" Student with id: " + student.getStudentId() + " doesn't exist.");
    }
    
    return msg;
  }

  /**
   * Should find all instances of offer in the database.
   * 
   * @return a Finder holding each offer.
   */
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
  
  public String toString(){
    return String.format("[Offer %s %s %s %s %s]", offerId, book, condition, price, student);
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
   * @return the offerId
   */
  public String getOfferId() {
    return offerId;
  }

  /**
   * @param offerId the offerId to set
   */
  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }

  /**
   * @return the price
   */
  public String getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * @return the book
   */
  public Book getBook() {
    return book;
  }

  /**
   * @param book the book to set
   */
  public void setBook(Book book) {
    this.book = book;
  }

  /**
   * @return the condition
   */
  public Condition getCondition() {
    return condition;
  }

  /**
   * @param condition the condition to set
   */
  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  /**
   * @return the student
   */
  public Student getStudent() {
    return student;
  }

  /**
   * @param student the student to set
   */
  public void setStudent(Student student) {
    this.student = student;
  }

  /**
   * @return the bookId
   */
  public String getBookId() {
    return bookId;
  }

  /**
   * @param bookId the bookId to set
   */
  public void setBookId(String bookId) {
    this.bookId = bookId;
    this.book = Book.find().where().eq("bookId", bookId).findUnique();
  }

  /**
   * @return the conditionId
   */
  public String getConditionId() {
    return conditionId;
  }

  /**
   * @param conditionId the conditionId to set
   */
  public void setConditionId(String conditionId) {
    this.conditionId = conditionId;
    this.condition = Condition.find().where().eq("conditionId", conditionId).findUnique();
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
    this.student = Student.find().where().eq("studentId", studentId).findUnique();
  }
  
  
  
}
