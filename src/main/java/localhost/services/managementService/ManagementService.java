package localhost.services.managementService;

import localhost.models.Employee;
import localhost.services.EntityServices;
import localhost.services.employeeService.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagementService {
  private EntityManagerFactory emf;
  private EntityManager em;

  public ManagementService() {
    this.emf = Persistence.createEntityManagerFactory("EmployeeService");
    this.em = emf.createEntityManager();
  }

  public EntityServices<Employee> getEmployeeService() {
    return new EmployeeService(em);
  }

  public void beginTransaction() {
    this.em.getTransaction().begin();
  }

  public void commitTransaction() {
    this.em.getTransaction().commit();
  }

  public void finishAll() {
    this.em.close();
    this.emf.close();
  }
}
