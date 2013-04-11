package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * This is intended to represent a required book for a class.
 * 
 * @author Dylan Kobayashi
 * 
 */
@Entity
public class Book extends Model {

  /** Serial version UID. */
  private static final long serialVersionUID = 8422100578548802939L;
  /** Primary key. */
  @Id
  public long id;
  /** Name of book. */
  public String name;
  /** Edition of book. */
  public String edition;
  /** ISBN of book. */
  public long isbn;
  /** The msrp(I think) of book. */
  public double priceOfNew;

  /** Requests that are associated to this book. */
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  public List<Request> requests = new ArrayList<Request>();

  /** Offers that are associated to this book. */
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  public List<Offer> offers = new ArrayList<Offer>();

  /**
   * Constructor makes a book with given parameters.
   * 
   * @param name of the book.
   * @param edition of the book.
   * @param isbn of the book.
   * @param price of the book.
   */
  public Book(String name, String edition, long isbn, double price) {
    this.name = name;
    this.edition = edition;
    this.isbn = isbn;
    this.priceOfNew = price;
  }

  /**
   * Should find all instances of Book in the database.
   * 
   * @return a Finder holding each book.
   */
  public static Finder<Long, Book> find() {
    return new Finder<Long, Book>(Long.class, Book.class);
  }
}
