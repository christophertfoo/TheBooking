package controllers;

import java.util.List;
import models.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;

public class ControlStudent extends Controller{
  
  public static Result index(){
    List<Student> students = Student.find().findList();
    return ok(students.isEmpty() ? "No students" : students.toString());
  }
  
  public static Result details(String studentId){
    Student student = Student.find().where().eq("studentId", studentId).findUnique();
    return (student == null) ? notFound("No student found") : ok(student.toString());
  }
  
  public static Result newStudent(){
    //Create a student form and bind the requiest variables to it.
    Form<Student> studentForm = form(Student.class).bindFromRequest();
    //validate the form values
    if (studentForm.hasErrors()) {
      return badRequest("Student needs: id, first name, last name, and email address");
    }
    //form is ok, so make a student and save it.
    Student student = studentForm.get();
    student.save();
    return ok(student.toString());
  }
  
  public static Result delete(String studentId){
    Student student = Student.find().where().eq("studentId", studentId).findUnique();
    if (student != null) {
      student.delete();
    }
    return ok();
  }

}
