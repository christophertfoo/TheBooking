package controllers;

import java.util.List;
import models.Offer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;

public class ControlOffer extends Controller{
  
  public static Result index(){
    List<Offer> offers = Offer.find().findList();
    return ok(offers.isEmpty() ? "No offers" : offers.toString());
  }
  
  public static Result details(String offerId){
    Offer offer = Offer.find().where().eq("offerId", offerId).findUnique();
    return (offer == null) ? notFound("No offer found") : ok(offer.toString());
  }
  
  public static Result newOffer(){
    //Create a offer form and bind the request variables to it.
    Form<Offer> offerForm = form(Offer.class).bindFromRequest();
    //validate the form values
    if (offerForm.hasErrors()) {
      return badRequest("Offer needs: id, student, book, condition and desired price");
    }
    //form is ok, so make a offer and save it.
    Offer offer = offerForm.get();
    offer.save();
    return ok(offer.toString());
  }
  
  public static Result delete(String offerId){
    Offer offer = Offer.find().where().eq("offerId", offerId).findUnique();
    if (offer != null) {
      offer.delete();
    }
    return ok();
  }

}
