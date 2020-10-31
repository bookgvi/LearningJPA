package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.models.Employee;
import localhost.DAO.DAOEmployee;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/employee")
public class ResourceEmployee {

  @EJB
  DAOEmployee emp;

  @GET
  @Consumes("application/json")
  public Response getAll() {
    List<Employee> employeeList = emp.findAll();
    ArrayList<HashMap<String, Object>> enployeeArr = new ArrayList<>();
    for(Employee employee: employeeList) {
      enployeeArr.add(employee.getMapForJson());
    }
    return Response.ok().entity(enployeeArr).build();
  }

  @GET
  @Path("{id}")
  @Consumes("application/json")
  public Response getOne(@PathParam("id") int id) {
    Employee employee = emp.findOne(id);
    if (employee != null) {
      return Response.ok().entity(employee.getMapForJson()).build();
    }
    return Response.status(400).build();
  }

  @POST
  @Consumes("application/json")
  public Response createOne(
    InputStream payload
  ) {
    String name = null;
    int salary = 0;
    int department = 0;
    try {
      BufferedReader buffer = new BufferedReader(new InputStreamReader(payload));
      JsonObject jsonPayload = JsonParser.parseReader(buffer).getAsJsonObject();
      name = jsonPayload.get("name").getAsString();
      salary = jsonPayload.get("salary").getAsInt();
      department = jsonPayload.get("department").getAsInt();
    } catch (Exception ignored) {
    }
    localhost.models.Employee employee = emp.createOne(name, salary, department);

    if (employee != null) {
      return Response.status(201).entity(employee.getMapForJson()).build();
    }
    return Response.status(400).build(); // Bad request
  }
}
