package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
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
  private Long primaryKey;
  @Required
  private String bookId;
  @Required
  private String name;
  /** Edition of book. */
  @Required
  private String edition;
  /** ISBN of book. */
  @Required
  private String isbn;
  /** The msrp(I think) of book. */
  @Required
  private String priceOfNew;

  /** Requests that are associated to this book. */
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private List<Request> requests = new ArrayList<Request>();

  /** Offers that are associated to this book. */
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private List<Offer> offers = new ArrayList<Offer>();

  /**
   * Constructor makes a book with given parameters.
   * 
   * @param name of the book.
   * @param edition of the book.
   * @param isbn of the book.
   * @param price of the book.
   */
  public Book(String bookId, String name, String edition, String isbn, String price) {
    this.bookId = bookId;
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
  
  public String toString(){
    return String.format("[Book %s %s %s %s %s]", bookId, name, edition, isbn, priceOfNew);
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
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the edition
   */
  public String getEdition() {
    return edition;
  }

  /**
   * @param edition the edition to set
   */
  public void setEdition(String edition) {
    this.edition = edition;
  }

  /**
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * @param isbn the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * @return the priceOfNew
   */
  public String getPriceOfNew() {
    return priceOfNew;
  }

  /**
   * @param priceOfNew the priceOfNew to set
   */
  public void setPriceOfNew(String priceOfNew) {
    this.priceOfNew = priceOfNew;
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
