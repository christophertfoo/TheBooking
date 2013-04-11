

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import models.Book;
import models.Condition;
import models.Offer;
import models.Request;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.FakeApplication;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;

/**
 * Testing models and their structure.
 * 
 * @author Dylan Kobayashi
 *
 */
public class ModelTest {
  /** Used to hold temporary information*/
  private FakeApplication application;
  /** Application should only write to in memory database.*/
  @Before
  public void startApp(){
    application = fakeApplication(inMemoryDatabase());
    start(application);
  }
  /** At end of test, stop the application.*/
  @After
  public void stopApp(){
    stop(application);
  }
  /** Test each of the models and their structure.*/
  @Test
  public void testModel(){
    //create 2 students
    Student buyer = new Student("New", "Student", "WantBook@icsdept.hawaii");
    Student seller = new Student("My", "Self", "SellBook@icsdept.hawaii");
    //create 1 book
    Book icsBook = new Book("Java Style Elements", "Edition 2", 00000000001, 30.0);
    //create 1 condition
    Condition brandNew = new Condition("Brand New");
    //create 1 request
    Request request = new Request(icsBook, brandNew, 10.0, buyer);
    //create 1 offer
    Offer offer = new Offer(icsBook, brandNew, 10.0, seller);
    //now associate correctly for request.
    buyer.requests.add(request);
    icsBook.requests.add(request);
    brandNew.requests.add(request);
    //associate for offer
    seller.offers.add(offer);
    icsBook.offers.add(offer);
    brandNew.offers.add(offer);
    
    //persist the sample model by saving all entities and relationships
    buyer.save();
    seller.save();
    icsBook.save();
    brandNew.save();
    request.save();
    offer.save();
    
    //retrieve the entire model from the database
    List<Student> students = Student.find().findList();
    List<Student> buyers = Student.find().where().eq("firstName", "New").findList();
    List<Student> sellers = Student.find().where().eq("lastName", "Self").findList();
    
    List<Book> books = Book.find().findList();
    List<Condition> conditions = Condition.find().findList();
    List<Offer> offers = Offer.find().findList();
    List<Request> requests = Request.find().findList();
    
    //check that we've recovered all entities
    assertEquals("Checking Students", students.size(), 2);
    assertEquals("Checking Buyers", buyers.size(), 1);
    assertEquals("Checking Request List", requests.size(), 1);
    assertEquals("Checking Offer List", offers.size(), 1);
    assertEquals("Checking Sellers", sellers.size(), 1);
    
    assertEquals("Offers to sellers", sellers.get(0).offers.get(0), offers.get(0));
    assertEquals("Offers to books", offers.get(0).book, books.get(0));
    assertEquals("Offers to conditions", offers.get(0).condition, conditions.get(0));
    assertEquals("Requests to buyers", buyers.get(0).requests.get(0), requests.get(0));
    assertEquals("Requests to books", requests.get(0).book, books.get(0));
    assertEquals("Requests to condition", requests.get(0).condition, conditions.get(0));
    
    
    //First remove the offer from seller.
    //seller.offers.clear();
    seller.clearOffers();
    offer.removeStudent();
    //Save to db.
    seller.update();
    offer.update();
    //The previously retrieved seller should still have the offer.
    assertTrue("Previously retrieved seller still has offer", !sellers.get(0).offers.isEmpty());
    //Sanity check the seller that was modified
    assertTrue("Modified seller has no offer", seller.offers.isEmpty());
    
    //assertTrue("Offer has no student?", offer.student == null);
    //assertTrue("New offer recall has no student?" + Offer.find().findList().get(0).student, Offer.find().findList().get(0).student == null);
    
    //If retrieved, it shouldn't have an offer.
    //assertTrue("redo-0Fresh seller has no offer", Student.find().findList().get(0).offers.isEmpty());
    //assertTrue("redo-1Fresh seller has no offer" + Student.find().findList().get(1).lastName, Student.find().findList().get(1).offers.isEmpty());
    
    
    assertTrue("Fresh seller has no offer. Original seller name:" + seller.firstName + "Found seller's name: " + 
        Student.find().where().eq("lastName", "Self").findList().get(0).firstName,
        
        
        Student.find().where().eq("lastName", "Self").findList().get(0).offers.isEmpty());
    //Just as the offer should have been updated to remove the student.
    assertTrue("Fresh offer has no seller", Offer.find().findList().get(0).student==null);
    //If the offer is deleted, then db should not find it.
    //Offer.find().findList().get(0).delete();
    //assertTrue("No more offers in database", Offer.find().findList().isEmpty());
  }
  
  
} //end test
