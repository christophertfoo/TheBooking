import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.status;
import static play.test.Helpers.stop;
import java.util.HashMap;
import java.util.Map;
import models.Student;
import models.Book;
import models.Condition;
import models.Offer;
import models.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;





public class ControlTest {
  private FakeApplication application;
  
  @Before
  public void startApp() {
    application = fakeApplication(inMemoryDatabase());
    start(application);
  }
  
  @After
  public void stopApp(){
    stop(application);
  }
  
  @Test
  public void testBookController(){
    //test GET /books on an empty database
    Result result = callAction(controllers.routes.ref.ControlBook.index());
    assertTrue("Empty books", contentAsString(result).contains("No books"));
    
    //test GET /books on a database containing a single product.
    String bookId = "Book-01";
    Book book = new Book(bookId, "2k leagues under", "1st", "1234567891", "10.01");
    book.save();
    result = callAction(controllers.routes.ref.ControlBook.index());
    assertTrue("One book", contentAsString(result).contains(bookId));
    
    //test GET /books/Book-01
    result = callAction(controllers.routes.ref.ControlBook.details(bookId));
    assertTrue("Book detail", contentAsString(result).contains(bookId));
    
    //test GET /books/BadBookId and make sure get a 404
    result = callAction(controllers.routes.ref.ControlBook.details("BadBookId"));
    assertEquals("Book detail (bad)", NOT_FOUND, status(result));
    
    //Test POST /products (with simulated, valid form data)
    Map<String, String> bookData = new HashMap<String, String>();
    bookData.put("bookId", "Book-02");
    bookData.put("name", "1001 nights");
    bookData.put("edition", "Revised");
    bookData.put("isbn", "1987654321");
    bookData.put("priceOfNew", "100.01");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(bookData);
    result = callAction(controllers.routes.ref.ControlBook.newBook(), request);
    assertEquals("Create new book", OK, status(result));
    
    //Test POST /products (with simulated invalid data)
    request = fakeRequest();
    result = callAction(controllers.routes.ref.ControlBook.newBook(), request);
    assertEquals("Create bad book fails", BAD_REQUEST, status(result));
    
    //test DELETE /products/Product-01 (a valid productId)
    result = callAction(controllers.routes.ref.ControlBook.delete(bookId));
    assertEquals("Delete current book OK", OK, status(result));
    result = callAction(controllers.routes.ref.ControlBook.details(bookId));
    assertEquals("Deleted book gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.ControlBook.delete(bookId));
    assertEquals("Delete missing book also OK", OK, status(result));
    
  } //end test book controller
  
  @Test
  public void testStudentController() {
    //test GET /students on an empty database
    Result result = callAction(controllers.routes.ref.ControlStudent.index());
    assertTrue("Empty students", contentAsString(result).contains("No students"));
    
    //test GET /students on a database containing a single student.
    String studentId = "Student-01";
    Student student = new Student(studentId, "Fname", "Lname", "somewhere@someplace.country");
    student.save();
    result = callAction(controllers.routes.ref.ControlStudent.index());
    assertTrue("One Student", contentAsString(result).contains(studentId));
    
    //test GET /students/Student-01
    result = callAction(controllers.routes.ref.ControlStudent.details(studentId));
    assertTrue("Student detail", contentAsString(result).contains(studentId));
    
    //test GET /students/BadStudentId and make sure get a 404
    result = callAction(controllers.routes.ref.ControlStudent.details("BadStudentId"));
    assertEquals("Student detail (bad)", NOT_FOUND, status(result));
    
    //Test POST /students (with simulated, valid form data)
    Map<String, String> studentData = new HashMap<String, String>();
    studentData.put("studentId", "Student-02");
    studentData.put("firstName", "beginning");
    studentData.put("lastName", "end");
    studentData.put("emailAddress", "a@b.com");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(studentData);
    result = callAction(controllers.routes.ref.ControlStudent.newStudent(), request);
    assertEquals("Create new Student", OK, status(result));
    
    //Test POST /students (with simulated invalid data)
    request = fakeRequest();
    result = callAction(controllers.routes.ref.ControlStudent.newStudent(), request);
    assertEquals("Create bad Student fails", BAD_REQUEST, status(result));
    
    //test DELETE /students/Student-01 (a valid studentId)
    result = callAction(controllers.routes.ref.ControlStudent.delete(studentId));
    assertEquals("Delete current student OK", OK, status(result));
    result = callAction(controllers.routes.ref.ControlStudent.details(studentId));
    assertEquals("Deleted student gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.ControlStudent.delete(studentId));
    assertEquals("Delete missing student also OK", OK, status(result));
    
  } //end student

  @Test
  public void testConditionController() {
    //test GET /conditions on an empty database
    Result result = callAction(controllers.routes.ref.ControlCondition.index());
    assertTrue("Empty Condition", contentAsString(result).contains("No conditions"));
    
    //test GET /conditions on a database containing a single Condition.
    String conditionId = "Condition-01";
    Condition condition = new Condition(conditionId, "Near mint");
    condition.save();
    result = callAction(controllers.routes.ref.ControlCondition.index());
    assertTrue("One Condition", contentAsString(result).contains(conditionId));
    
    //test GET /conditions/Condition-01
    result = callAction(controllers.routes.ref.ControlCondition.details(conditionId));
    assertTrue("Condition detail", contentAsString(result).contains(conditionId));
    
    //test GET /conditions/BadConditionId and make sure get a 404
    result = callAction(controllers.routes.ref.ControlCondition.details("BadConditionId"));
    assertEquals("Condition detail (bad)", NOT_FOUND, status(result));
    
    //Test POST /conditions (with simulated, valid form data)
    Map<String, String> conditionData = new HashMap<String, String>();
    conditionData.put("conditionId", "Condition-02");
    conditionData.put("description", "Used");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(conditionData);
    result = callAction(controllers.routes.ref.ControlCondition.newCondition(), request);
    assertEquals("Create new Condition", OK, status(result));
    
    //Test POST /students (with simulated invalid data)
    request = fakeRequest();
    result = callAction(controllers.routes.ref.ControlCondition.newCondition(), request);
    assertEquals("Create bad Condition fails", BAD_REQUEST, status(result));
    
    //test DELETE /students/Student-01 (a valid studentId)
    result = callAction(controllers.routes.ref.ControlCondition.delete(conditionId));
    assertEquals("Delete current condition OK", OK, status(result));
    result = callAction(controllers.routes.ref.ControlCondition.details(conditionId));
    assertEquals("Deleted condition gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.ControlCondition.delete(conditionId));
    assertEquals("Delete missing condition also OK", OK, status(result));
    
  } //end condition
  

  @Test
  public void testOfferController() {
    //test GET /offers on an empty database
    Result result = callAction(controllers.routes.ref.ControlOffer.index());
    assertTrue("Empty Offer", contentAsString(result).contains("No offers"));
    
    //test GET /offers on a database containing a single offer.
    //Offers are dependent upon existing student, book, condition
    Student stu = new Student("Student-01", "Fname", "Lname", "stu@school.room");
    stu.save();
    Book bk = new Book("Book-01", "B and B", "An edition", "123456789", "10.50");
    bk.save();
    Condition cnd= new Condition("Condition-01", "Worn");
    cnd.save();
    String offerId = "Offer-01";
    Offer offer = new Offer(offerId, bk, cnd, "5.00", stu);
    offer.save();
    result = callAction(controllers.routes.ref.ControlOffer.index());
    assertTrue("One Offer: " + contentAsString(result), contentAsString(result).contains(offerId));
    
    
    
    //test GET /offers/Offer-01
    result = callAction(controllers.routes.ref.ControlOffer.details(offerId));
    assertTrue("Offer detail", contentAsString(result).contains(offerId));
    
    //test GET /offers/BadOfferId and make sure get a 404
    result = callAction(controllers.routes.ref.ControlOffer.details("BadOfferId"));
    assertEquals("Offer detail (bad)", NOT_FOUND, status(result));
    
    //Test POST /offers (with simulated, valid form data)
    //first test with non-existing student, book, condition
    Map<String, String> offerData = new HashMap<String, String>();
    offerData.put("offerId", "Offer-02");
    offerData.put("book.bookId", "Book-02");
    offerData.put("book.name", "Book of bass");
    offerData.put("book.edition", "Big edition");
    offerData.put("book.isbn", "12481632");
    offerData.put("book.priceOfNew", "100.00");
    offerData.put("condition.conditionId", "Condition-02");
    offerData.put("condition.description", "Extra large");
    offerData.put("price", "1.00");
    offerData.put("student.studentId", "Student-02");
    offerData.put("student.firstName", "AFirst");
    offerData.put("student.lastName", "ZLast");
    offerData.put("student.emailAddress", "notStu@location.country");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(offerData);
    result = callAction(controllers.routes.ref.ControlOffer.newOffer(), request);
    assertEquals("Create new Offer" + contentAsString(result), BAD_REQUEST, status(result));
    
    Book b3 = new Book("Book-03", "Book trilogy", "3rd edition", "675438921", "100");
    b3.save();
    Condition c3 = new Condition("Condition-03", "New");
    c3.save();
    Student s3 = new Student("Student-03", "Sandaime", "Hoka", "3rd@gen.jeepahn");
    s3.save();
    
    //Creation by transient value associations.
    offerData = new HashMap<String, String>();
    offerData.put("offerId", "Offer-03");
    offerData.put("bookId", "Book-03");
    offerData.put("conditionId", "Condition-03");
    offerData.put("studentId", "Student-03");
    offerData.put("studentId", "Student-03");
    offerData.put("price", "50");
    request = fakeRequest();
    request.withFormUrlEncodedBody(offerData);
    result = callAction(controllers.routes.ref.ControlOffer.newOffer(), request);
    assertEquals("Create a new offer by transient associations", OK, status(result));
    
    //creation with existing id's
    offerData = new HashMap<String, String>();
    offerData.put("offerId", "Offer-04");
    offerData.put("bookId", "Book-03");
    offerData.put("conditionId", "Condition-03");
    offerData.put("studentId", "Student-03");
    offerData.put("price", "10");
    request = fakeRequest();
    request.withFormUrlEncodedBody(offerData);
    result = callAction(controllers.routes.ref.ControlOffer.newOffer(), request);
    assertEquals("Create a new offer by transient associations. Existing book,cond,stu", OK, status(result));
    

    //creation with non-existing id's. Should fail
    offerData = new HashMap<String, String>();
    offerData.put("offerId", "Offer-05");
    offerData.put("bookId", "NotReadableID");
    offerData.put("conditionId", "SoBadAConditionItIsntUsed");
    offerData.put("studentId", "BadStudentNoAForYou");
    offerData.put("price", "10");
    request = fakeRequest();
    request.withFormUrlEncodedBody(offerData);
    result = callAction(controllers.routes.ref.ControlOffer.newOffer(), request);
    assertEquals("Create a new offer with bad Id. Should return bad.", BAD_REQUEST, status(result));
    
    
    
    //Test POST /offers (with simulated invalid data)
    request = fakeRequest();
    result = callAction(controllers.routes.ref.ControlOffer.newOffer(), request);
    assertEquals("Create bad Offer fails", BAD_REQUEST, status(result));
    
    //Used for debugging
    //assertEquals("This is the offers in database:\n " + Offer.find().findList() , 0 , 1);
    //assertEquals("This is the books in database:\n " + Book.find().findList() , 0 , 1);
    //assertEquals("This is the students in database:\n " + Student.find().findList() , 0 , 1);
    
    //test DELETE /offers/Offer-01 (a valid studentId)
    result = callAction(controllers.routes.ref.ControlOffer.delete(offerId));
    assertEquals("Delete current offer OK", OK, status(result));
    result = callAction(controllers.routes.ref.ControlOffer.details(offerId));
    assertEquals("Deleted offer gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.ControlOffer.delete(offerId));
    assertEquals("Delete missing offer also OK", OK, status(result));

    //for debugging
    //assertEquals("This is the students in database:\n " + Student.find().findList() , 0 , 1);
  } //end condition
  

  @Test
  public void testRequestController() {
    //test GET /requests on an empty database
    Result result = callAction(controllers.routes.ref.ControlRequest.index());
    assertTrue("Empty Request", contentAsString(result).contains("No requests"));
    
    //test GET /requests on a database containing a single offer.
    //Requests are dependent upon existing student, book, condition
    Student stu = new Student("Student-01", "Fname", "Lname", "stu@school.room");
    stu.save();
    Book bk = new Book("Book-01", "B and B", "An edition", "123456789", "10.50");
    bk.save();
    Condition cnd= new Condition("Condition-01", "Worn");
    cnd.save();
    String reqId = "Request-01";
    Request req = new Request(reqId, bk, cnd, "5.00", stu);
    req.save();
    result = callAction(controllers.routes.ref.ControlRequest.index());
    assertTrue("One Request: " + contentAsString(result), contentAsString(result).contains(reqId));
    
    
    
    //test GET /requets/Request-01
    result = callAction(controllers.routes.ref.ControlRequest.details(reqId));
    assertTrue("Request detail", contentAsString(result).contains(reqId));
    
    //test GET /requets/BadRequestId and make sure get a 404
    result = callAction(controllers.routes.ref.ControlRequest.details("BadRequestId"));
    assertEquals("Request detail (bad)", NOT_FOUND, status(result));
    
    //Test POST /requests (with simulated, valid form data)
    //first test with non-existing student, book, condition
    Map<String, String> reqData = new HashMap<String, String>();
    reqData.put("requestId", "Offer-02");
    reqData.put("book.bookId", "Book-02");
    reqData.put("book.name", "Book of bass");
    reqData.put("book.edition", "Big edition");
    reqData.put("book.isbn", "12481632");
    reqData.put("book.priceOfNew", "100.00");
    reqData.put("condition.conditionId", "Condition-02");
    reqData.put("condition.description", "Extra large");
    reqData.put("price", "1.00");
    reqData.put("student.studentId", "Student-02");
    reqData.put("student.firstName", "AFirst");
    reqData.put("student.lastName", "ZLast");
    reqData.put("student.emailAddress", "notStu@location.country");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(reqData);
    result = callAction(controllers.routes.ref.ControlRequest.newRequest(), request);
    assertEquals("Create new Request" + contentAsString(result), BAD_REQUEST, status(result));
    
    Book b3 = new Book("Book-03", "Book trilogy", "3rd edition", "675438921", "100");
    b3.save();
    Condition c3 = new Condition("Condition-03", "New");
    c3.save();
    Student s3 = new Student("Student-03", "Sandaime", "Hoka", "3rd@gen.jeepahn");
    s3.save();
    
    //Creation by transient value associations.
    reqData = new HashMap<String, String>();
    reqData.put("requestId", "Offer-03");
    reqData.put("bookId", "Book-03");
    reqData.put("conditionId", "Condition-03");
    reqData.put("studentId", "Student-03");
    reqData.put("price", "50");
    request = fakeRequest();
    request.withFormUrlEncodedBody(reqData);
    result = callAction(controllers.routes.ref.ControlRequest.newRequest(), request);
    assertEquals("Create a new request by transient associations", OK, status(result));
    
    //creation with existing id's
    reqData = new HashMap<String, String>();
    reqData.put("requestId", "Request-04");
    reqData.put("bookId", "Book-03");
    reqData.put("conditionId", "Condition-03");
    reqData.put("studentId", "Student-03");
    reqData.put("price", "10");
    request = fakeRequest();
    request.withFormUrlEncodedBody(reqData);
    result = callAction(controllers.routes.ref.ControlRequest.newRequest(), request);
    assertEquals("Create a new request by transient associations. Existing book,cond,stu", OK, status(result));
    

    //creation with non-existing id's. Should fail
    reqData = new HashMap<String, String>();
    reqData.put("requestId", "Request-05");
    reqData.put("bookId", "NotReadableID");
    reqData.put("conditionId", "SoBadAConditionItIsntUsed");
    reqData.put("studentId", "BadStudentNoAForYou");
    reqData.put("price", "10");
    request = fakeRequest();
    request.withFormUrlEncodedBody(reqData);
    result = callAction(controllers.routes.ref.ControlRequest.newRequest(), request);
    assertEquals("Create a new request with bad Id. Should return bad.", BAD_REQUEST, status(result));
    
    
    
    //Test POST /requests (with simulated invalid data)
    request = fakeRequest();
    result = callAction(controllers.routes.ref.ControlRequest.newRequest(), request);
    assertEquals("Create bad Request fails", BAD_REQUEST, status(result));
    
    //Used for debugging
    //assertEquals("This is the offers in database:\n " + Offer.find().findList() , 0 , 1);
    //assertEquals("This is the books in database:\n " + Book.find().findList() , 0 , 1);
    //assertEquals("This is the students in database:\n " + Student.find().findList() , 0 , 1);
    
    //test DELETE /requests/Request-01 (a valid studentId)
    result = callAction(controllers.routes.ref.ControlRequest.delete(reqId));
    assertEquals("Delete current request OK", OK, status(result));
    result = callAction(controllers.routes.ref.ControlRequest.details(reqId));
    assertEquals("Deleted request gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.ControlRequest.delete(reqId));
    assertEquals("Delete missing request also OK", OK, status(result));
    
  } //end condition

} //end class
