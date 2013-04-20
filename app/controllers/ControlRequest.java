package controllers;

import java.util.List;
import models.Request;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;

public class ControlRequest extends Controller{
  
  public static Result index(){
    List<Request> requests = Request.find().findList();
    return ok(requests.isEmpty() ? "No requests" : requests.toString());
  }
  
  public static Result details(String requestId){
    Request request = Request.find().where().eq("requestId", requestId).findUnique();
    return (request == null) ? notFound("No request found") : ok(request.toString());
  }
  
  public static Result newRequest(){
    //Create a request form and bind the request variables to it.
    Form<Request> requestForm = form(Request.class).bindFromRequest();
    //validate the form values
    if (requestForm.hasErrors()) {
      return badRequest("Request needs: id, student, book, condition and desired price");
    }
    //form is ok, so make a request and save it.
    Request request = requestForm.get();
    request.save();
    return ok(request.toString());
  }
  
  public static Result delete(String requestId){
    Request request = Request.find().where().eq("requestId", requestId).findUnique();
    if (request != null) {
      request.delete();
    }
    return ok();
  }

}
