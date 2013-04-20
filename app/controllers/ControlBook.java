package controllers;

import java.util.List;
import models.Book;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;

public class ControlBook extends Controller{
  
  public static Result index(){
    List<Book> books = Book.find().findList();
    return ok(books.isEmpty() ? "No books" : books.toString());
  }
  
  public static Result details(String bookId){
    Book book = Book.find().where().eq("bookId", bookId).findUnique();
    return (book == null) ? notFound("No book found") : ok(book.toString());
  }
  
  public static Result newBook(){
    //Create a book form and bind the request variables to it.
    Form<Book> bookForm = form(Book.class).bindFromRequest();
    //validate the form values
    if (bookForm.hasErrors()) {
      return badRequest("Book needs: id, name, edition, isbn and price of new");
    }
    //form is ok, so make a book and save it.
    Book book = bookForm.get();
    book.save();
    return ok(book.toString());
  }
  
  public static Result delete(String bookId){
    Book book = Book.find().where().eq("bookId", bookId).findUnique();
    if (book != null) {
      book.delete();
    }
    return ok();
  }

}
