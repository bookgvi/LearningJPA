package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.models.Employee;
import localhost.DAO.DAOEmployee;
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

@Path("/employee")
@RequestScoped
public class ResourceEmployee {

  @EJB
  DAOEmployee emp;
  @Inject
  ValidatorsBean validatorsBean;

  @GET
  @Consumes("application/json")
  public Response getAll(@QueryParam("by_dep") Integer by_dep) {
    System.out.printf("by_dep: %s%n", by_dep == null);
    List<Employee> employeeList = emp.findAll();

    // Есил переда фильтрующий параметр, то получим данные с его учетом
    if (by_dep != null) employeeList = emp.findAll(by_dep);

    ArrayList<HashMap<String, Object>> enployeeArr = new ArrayList<>();
    for (Employee employee : employeeList) {
      enployeeArr.add(employee.getMapForJson());
    }
    return Response.ok().entity(enployeeArr).build();
  }

  @GET
  @Path("{id}")
  @Consumes("application/json")
  public Response getOne(@PathParam("id") int id) {
    Employee employee = emp.findOne(id);
    if (employee == null)
      throw new WebApplicationException(Response.Status.NOT_FOUND);

    return Response.ok().entity(employee.getMapForJson()).build();
  }

  @POST
  @Consumes("application/json")
  public Response createOne(InputStream payload) {
    String name = null;
    int salary = 0;
    int department = 0;
    BufferedReader buffer = new BufferedReader(new InputStreamReader(payload));
    JsonObject jsonPayload = JsonParser.parseReader(buffer).getAsJsonObject();
    name = jsonPayload.get("name").getAsString();
    salary = jsonPayload.get("salary").getAsInt();
    department = jsonPayload.get("department").getAsInt();

    validatorsBean.requireNonNul(name, "name");
    localhost.models.Employee employee = emp.createOne(name, salary, department);

    return Response.status(201).entity(employee.getMapForJson()).build();
  }
}
