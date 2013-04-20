package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
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
  private Long primaryKey;
  @Required
  private String conditionId;
  /** A short description of the condition. */
  @Required
  private String description;

  /** All requests with this condition. */
  @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL)
  private List<Request> requests = new ArrayList<Request>();
  /** All offers with this condition. */
  @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL)
  private List<Offer> offers = new ArrayList<Offer>();

  /**
   * Constructor for condition.
   * 
   * @param description of the condition.
   */
  public Condition(String conditionId, String description) {
    this.conditionId = conditionId;
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
  
  public String toString(){
    return String.format("[Condition %s %s]", conditionId, description);
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
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
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
