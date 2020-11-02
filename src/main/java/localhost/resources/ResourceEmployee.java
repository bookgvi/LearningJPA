package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.DAO.DAODepartment;
import localhost.models.Department;
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
  @EJB
  DAODepartment dep;

  @Inject
  ValidatorsBean validatorsBean;

  @GET
  @Consumes("application/json")
  public Response getAll(@QueryParam("by_dep") Integer by_dep) {
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
    validatorsBean.requireNonNul(id, "id не может быть пустым или равным 0");
    Employee employee = emp.findOne(id);
    validatorsBean.requireNonNul(employee, "Employee с таким id не существует");
    return Response.ok().entity(employee.getMapForJson()).build();
  }

  @POST
  @Consumes("application/json")
  public Response createOne(InputStream payload) {
    String name = null;
    int salary = 0;
    int depId = 0;
    BufferedReader buffer = new BufferedReader(new InputStreamReader(payload));
    JsonObject jsonPayload = JsonParser.parseReader(buffer).getAsJsonObject();
    name = jsonPayload.get("name").getAsString();
    salary = jsonPayload.get("salary").getAsInt();
    depId = jsonPayload.get("department").getAsInt();

    validatorsBean.requireNonNul(name, "name не может быть пустым");
    Department department = dep.findOne(depId);
    validatorsBean.requireNonNul(department, "Department с таким id несуществует");
    localhost.models.Employee employee = emp.createOne(name, salary, depId, department);

    return Response.status(201).entity(employee.getMapForJson()).build();
  }
}
