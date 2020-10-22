package localhost.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.models.Employee;
import localhost.services.employeeWeb.EmployeeWEB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "employee servlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
  @EJB
  EmployeeWEB empService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Writer w = resp.getWriter();
    Gson gson = new Gson();
    String payload = gson.toJson(empService.findAll());
    w.write(payload);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BufferedReader buffer = req.getReader();
    JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
    String name = jsonObject.get("name").toString();
    int salary = jsonObject.get("salary").getAsInt();
    Employee newEmployee = empService.createOne(name, salary);
    resp.getWriter().printf("New Employee: %s%n", newEmployee);
  }
}
