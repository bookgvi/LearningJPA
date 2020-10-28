package localhost.services.WEB;

import localhost.models.Department;
import localhost.models.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class EmployeeWEB {
  @PersistenceContext(unitName = "EmployeeServiceEE")
  EntityManager em;

  @EJB
  DepartmentWEB departmentWEB;

  public Employee findOne(long id) {
    return em.find(Employee.class, id);
  }

  public Employee createOne(String name, int salary, int depId) {
    Employee newEmp = null;
    Department department = this.departmentWEB.findOne(depId); // em.find(Department.class, depId);
    if (department != null) {
      newEmp = new Employee();
      newEmp.setName(name);
      newEmp.setSalary(salary);
      newEmp.setDepId(department.getId()); // newEmp.setDepId(depId);
      departmentWEB.addEmployee(depId, newEmp);
      em.persist(newEmp);
    }
    return newEmp;
  }

  public List<Employee> findAll() {
    TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
    return query.getResultList();
  }

  public List<Employee> findByDepId(int depId) {
    TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.depId = :depId", Employee.class);
    query.setParameter("depId", depId);
    return query.getResultList();
  }

  public Employee deleteOne(long id) {
    Employee employee = this.findOne(id);
    if (employee == null) return null;
    em.remove(employee);
    return employee;
  }
}
