package localhost.servlet;

import localhost.services.employeeWeb.EmployeeWEB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "employee servlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
  @EJB
  EmployeeWEB empService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Writer w = resp.getWriter();
    w.write(empService.find(0).toString());
  }
}
