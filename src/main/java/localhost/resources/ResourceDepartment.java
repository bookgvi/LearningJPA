package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.models.Department;
import localhost.DAO.DAODepartment;
import localhost.services.Validators.ValidatorsBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/department")
@RequestScoped
public class ResourceDepartment {
  @EJB
  DAODepartment DAODepartment;

  @Inject
  ValidatorsBean validatorsBean;

  @GET
  @Consumes("application/json")
  public Response getAll() {
    List<Department> departments = DAODepartment.findAll();
    ArrayList<HashMap<String, Object>> departmentsArr = new ArrayList<>();
    for (Department department : departments) {
      departmentsArr.add(department.getMapForJson());
    }
    return Response.ok().entity(departmentsArr).build();
  }

  @GET
  @Path("{id}")
  @Consumes("application/json")
  public Response getOne(@PathParam("id") int id) {
    validatorsBean.requireNonNul(id, "id не может быть пустым или равным 0");
    Department department = DAODepartment.findOne(id);
    validatorsBean.requireNonNul(department, "Department с таким id несуществует");
    return Response.status(200).entity(department.getMapForJson()).build();
  }

  @DELETE
  @Path("{id}")
  public Response deleteOne(@PathParam("id") int id) {
    validatorsBean.requireNonNul(id, "id не может быть пустым или равным 0");
    Department department = DAODepartment.deleteOne(id);
    return Response.ok().build();
  }

  @POST
  @Consumes("application/json")
  public Response createOne(InputStream inputStream) {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
    JsonObject payloadJson = JsonParser.parseReader(buffer).getAsJsonObject();
    String name = payloadJson.get("name").getAsString();
    validatorsBean.requireNonNul(name, "name не может быть пустым");
    String description = payloadJson.get("description").getAsString();

    Department newDepartment = DAODepartment.createOne(name, description);

    return Response.status(Response.Status.CREATED).entity(newDepartment.getMapForJson()).build();

  }

  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response updateOne(@PathParam("id") int id, InputStream inputStream) {
    validatorsBean.requireNonNul(id, "id не может быть пустым или равным 0");

    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
    JsonObject payloadJson = JsonParser.parseReader(buffer).getAsJsonObject();
    String description = null;
    String name = payloadJson.get("name").getAsString();
    validatorsBean.requireNonNul(name, "name не может быть пустым");
    if (!payloadJson.get("description").isJsonNull()) {
      description = payloadJson.get("description").getAsString();
    }
    Department department = DAODepartment.findOne(id);
    validatorsBean.requireNonNul(department, "Department с таким id несуществует");
    Department updatedDepartment = DAODepartment.changeOne(department, name, description);
    return Response.ok().entity(updatedDepartment.getMapForJson()).build();
  }
}
