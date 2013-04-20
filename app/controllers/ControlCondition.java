package controllers;

import java.util.List;
import models.Condition;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;

public class ControlCondition extends Controller{
  
  public static Result index(){
    List<Condition> conditions = Condition.find().findList();
    return ok(conditions.isEmpty() ? "No conditions" : conditions.toString());
  }
  
  public static Result details(String conditionId){
    Condition condition = Condition.find().where().eq("conditionId", conditionId).findUnique();
    return (condition == null) ? notFound("No condition found") : ok(condition.toString());
  }
  
  public static Result newCondition(){
    //Create a condition form and bind the request variables to it.
    Form<Condition> conditionForm = form(Condition.class).bindFromRequest();
    //validate the form values
    if (conditionForm.hasErrors()) {
      return badRequest("Condition needs: id and description");
    }
    //form is ok, so make a condition and save it.
    Condition condition = conditionForm.get();
    condition.save();
    return ok(condition.toString());
  }
  
  public static Result delete(String conditionId){
    Condition condition = Condition.find().where().eq("conditionId", conditionId).findUnique();
    if (condition != null) {
      condition.delete();
    }
    return ok();
  }

}
