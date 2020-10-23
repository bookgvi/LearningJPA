package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.services.employeeWeb.EmployeeWEB;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Path("/employee")
public class Employee {

  @EJB
  EmployeeWEB emp;

  @GET
  public Response getAll() {
    return Response.ok(emp.findAll()).build();
  }

  @GET
  @Path("{id}")
  public Response getOne(@PathParam("id") int id) {
    return Response.ok(emp.findOne(id)).build();
  }

  @POST
  @Consumes("application/json")
  public Response createOne(
    InputStream payload
  ) {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(payload));
    JsonObject jsonPayload = JsonParser.parseReader(buffer).getAsJsonObject();
    String name = jsonPayload.get("name").getAsString();
    int salary = jsonPayload.get("salary").getAsInt();
    localhost.models.Employee employee = emp.createOne(name, salary);
    if (employee != null) return Response.status(201).entity(employee).build();
    return Response.notModified().build();
  }
}
