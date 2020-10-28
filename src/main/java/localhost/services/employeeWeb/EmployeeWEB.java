package localhost.services.employeeWeb;

import localhost.models.Department;
import localhost.models.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class EmployeeWEB {
  @PersistenceContext(unitName = "EmployeeServiceEE")
  EntityManager em;

  public Employee findOne(long id) {
    return em.find(Employee.class, id);
  }

  public Employee createOne(String name, int salary, int depId) {
    Employee newEmp = null;
    Department department = em.find(Department.class, depId);
    if (department != null) {
      newEmp = new Employee();
      newEmp.setName(name);
      newEmp.setSalary(salary);
      newEmp.setDepId(department.getId()); // newEmp.setDepId(depId);
      em.persist(newEmp);
    }
    return newEmp;
  }

  public List<Employee> findAll() {
    TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
    return query.getResultList();
  }
}
