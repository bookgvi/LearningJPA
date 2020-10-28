package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.models.Department;
import localhost.services.WEB.DepartmentWEB;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Path("/department")
public class ResourceDepartment {
  @EJB
  DepartmentWEB departmentWEB;

  @GET
  public Response getAll() {
    List<Department> departments = departmentWEB.findAll();
    if (departments.size() > 0) return Response.ok().entity(departments).build();
    return Response.noContent().build();
  }

  @GET
  @Path("{id}")
  public Response getOne(@PathParam("id") int id) {
    Department department = departmentWEB.findOne(id);
    if (department != null) return Response.status(200).entity(department).build();
    return Response.noContent().build();
  }

  @DELETE
  @Path("{id}")
  public Response deleteOne(@PathParam("id") int id) {
    Department department = departmentWEB.deleteOne(id);
    if (department != null) Response.ok().build();
    return Response.noContent().build();
  }

  @POST
  public Response createOne(InputStream inputStream) {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
    JsonObject payloadJson = JsonParser.parseReader(buffer).getAsJsonObject();
    try {
      String name = payloadJson.get("name").getAsString();
      String description = payloadJson.get("description").getAsString();
      Department newDepartment = departmentWEB.createOne(name, description);
      if (newDepartment != null) return Response.status(201).entity(newDepartment).build();
    } catch (Exception ignored) {
    }
    return Response.status(400).build();
  }

  @PUT
  @Path("{id}")
  public Response updateOne(@PathParam("id") int id, InputStream inputStream) {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
    JsonObject payloadJson = JsonParser.parseReader(buffer).getAsJsonObject();
    try {
      String description = null;
      String name = payloadJson.get("name").getAsString();
      if (!payloadJson.get("description").isJsonNull()) {
        description = payloadJson.get("description").getAsString();
      }
      System.out.printf("%s, %s%n", name, description);
      Department updatedDepartment = departmentWEB.changeOne(id, name, description);
      if (updatedDepartment != null) return Response.ok().entity(updatedDepartment).build();
    } catch (Exception ignored) {
    }
    return Response.noContent().build();
  }
}
