package localhost.services.employeeWeb;

import localhost.models.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmployeeWEB {
  @PersistenceContext(unitName = "EmployeeServiceEE")
  EntityManager em;

  public Employee find(long id) {
    return em.find(Employee.class, id);
  }
}
