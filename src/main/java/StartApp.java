import localhost.models.Employee;
import localhost.services.EntityServices;
import localhost.services.managementService.ManagementService;

public class StartApp {
  public static void main(String[] args) {
    ManagementService managementService = new ManagementService();
    EntityServices<Employee> employee = managementService.getEmployeeService();

    managementService.beginTransaction();
    employee.createOne("Ivan", 1200);
    managementService.commitTransaction();

    managementService.finishAll();
  }
}
