/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucbcba.simplescheduling.resource;

import bo.edu.ucbcba.simplescheduling.model.Student;
import bo.edu.ucbcba.simplescheduling.response.ErrorResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Diego
 */
@Path("v1/students")
public class StudentsResource {

    @Context
    private UriInfo context;
    private final Gson gson = new Gson();

    /**
     * Creates a new instance of StudentsResource
     */
    public StudentsResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        List<Student> students = 
                new ArrayList<>(GenericResource.getStudentMap().values());
        return Response.ok(gson.toJson(students), 
                MediaType.APPLICATION_JSON).build();
    }
    
    /**
     * Returns a student based on it´s studentId.
     * @param studentId
     * @return 
     */
    @GET
    @Path("{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("studentId") Integer studentId) {
        // search student
        Student student = GenericResource.getStudent(studentId);
        if (student != null) {
            return Response.ok(gson.toJson(student), 
                    MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Creates a new student.
     * @param jsonString String
     * @return String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response students(String jsonString) {
        Student student = gson.fromJson(jsonString, Student.class);
        // TODO validate params
        int studentId = student.getStudentId();
        if (GenericResource.getStudent(studentId) != null) { // ya existe
            ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), 
                    Response.Status.BAD_REQUEST, "ERR_001", "Creation failed.", 
                    "Student was not created", 
                    Arrays.asList("El usuario ya existe"));
            return Response.ok(gson.toJson(errorResponse), MediaType.APPLICATION_JSON)
                    .status(Response.Status.BAD_REQUEST).build();
        }
        String lastName = student.getLastName();
        String firstName = student.getFirstName();
        List<String> classCodes = student.getClassCodes();
        GenericResource.putStudent(student);
        return Response.ok(gson.toJson(student), 
                MediaType.APPLICATION_JSON)
                .status(Response.Status.CREATED)
                .build();
    }
    
    @DELETE
    @Path("{studentId}")
    public Response deleteStudent(@PathParam("studentId") Integer studentId) {
        // search student
        Student student = GenericResource.getStudent(studentId);
        if (student != null) {
            GenericResource.removeStudent(studentId);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("{studentId}")
    public Response editStudent(String jsonString) {
        Student student = gson.fromJson(jsonString, Student.class);
        
        int studentId = student.getStudentId();
        String lastName = student.getLastName();
        String firstName = student.getFirstName();
        List<String> classCodes = student.getClassCodes();
        
        Student currentStudent = GenericResource.getStudent(studentId);
        if (currentStudent != null) {
            if (!lastName.isEmpty()) {
                currentStudent.setLastName(lastName);
            }
            if (!firstName.isEmpty()) {
                currentStudent.setFirstName(firstName);
            }
            currentStudent.setClassCodes(classCodes);
            return Response.ok(gson.toJson(currentStudent), 
                    MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();
        }   
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }        
    }
}
