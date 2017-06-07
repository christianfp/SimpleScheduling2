/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucbcba.simplescheduling.resource;

import bo.edu.ucbcba.simplescheduling.model.MyClass;
import bo.edu.ucbcba.simplescheduling.model.Student;
import bo.edu.ucbcba.simplescheduling.response.ErrorResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Internet
 */
@Path("v1/classes")
public class ClassesResource {

    @Context
    private UriInfo context;
    private final Gson gson = new Gson();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCLass(String jsonString){
        MyClass classe = gson.fromJson(jsonString, MyClass.class);
         String classeCode = classe.getCode();
        if (GenericResource.getClass(classeCode) != null) { // ya existe
            ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), 
                    Response.Status.BAD_REQUEST, "ERR_001", "Creation failed.", 
                    "Class was not created", 
                    Arrays.asList("la clase ya existe"));
            return Response.ok(gson.toJson(errorResponse), MediaType.APPLICATION_JSON)
                    .status(Response.Status.BAD_REQUEST).build();
        }
        String title = classe.getTitle();
        String descp = classe.getDescription();
        List<Integer> studentIds = classe.getStudentIds();
        GenericResource.putClass(classe);
        return Response.ok(gson.toJson(classe), 
                MediaType.APPLICATION_JSON)
                .status(Response.Status.CREATED)
                .build();
        
       
    }
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClasses() {
        List<MyClass> classes = 
                new ArrayList<>(GenericResource.getClassMap().values());
        return Response.ok(gson.toJson(classes), 
                MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCode(@PathParam("code") String code) {
        // search student
        MyClass classe = GenericResource.getClass(code);
        if (classe != null) {
            return Response.ok(gson.toJson(classe), 
                    MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("{code}")
    public Response deleteClass(@PathParam("code") String code) {
        // search student
        MyClass classe = GenericResource.getClass(code);
        if (classe != null) {
            GenericResource.removeClass(code);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("{code}")
    public Response editClass(String jsonString) {
        MyClass classe = gson.fromJson(jsonString, MyClass.class);
        
        String classCode = classe.getCode();
        String title = classe.getTitle();
        String descp = classe.getDescription();
        List<Integer> studentsIds = classe.getStudentIds();
        
        MyClass currentClass = GenericResource.getClass(classCode);
        if (currentClass != null) {
            if (!title.isEmpty()) {
                currentClass.setTitle(title);
            }
            if (!descp.isEmpty()) {
                currentClass.setDescription(descp);
            }
            currentClass.setStudentIds(studentsIds);
            return Response.ok(gson.toJson(currentClass), 
                    MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();
        }   
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }        
    }
}
