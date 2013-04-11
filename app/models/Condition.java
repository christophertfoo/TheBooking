package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * This is intended to represent the conditions of a book.
 * 
 * @author Dylan Kobayashi
 * 
 */
@Entity
public class Condition extends Model {

  /** Serial Version UID. */
  private static final long serialVersionUID = 3265159995594874517L;
  /** Primary key. */
  @Id
  public long id;
  /** A short description of the condition. */
  public String description;

  /** All requests with this condition. */
  @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL)
  public List<Request> requests = new ArrayList<Request>();
  /** All offers with this condition. */
  @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL)
  public List<Offer> offers = new ArrayList<Offer>();

  /**
   * Constructor for condition.
   * 
   * @param description of the condition.
   */
  public Condition(String description) {
    this.description = description;
  }

  /**
   * Should find all instances of condition in the database.
   * 
   * @return a Finder holding each condition.
   */
  public static Finder<Long, Condition> find() {
    return new Finder<Long, Condition>(Long.class, Condition.class);
  }

}
