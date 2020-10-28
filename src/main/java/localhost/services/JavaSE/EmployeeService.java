package localhost.services.JavaSE;

import localhost.models.Employee;
import localhost.services.EntityServices;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeService implements EntityServices<Employee> {
  protected EntityManager em;

  public EmployeeService(EntityManager em) {
    this.em = em;
  }

  public Employee createOne(String name, int salary) {
    Employee emp = new Employee();
    emp.setName(name);
    emp.setSalary(salary);
    em.persist(emp);
    return emp;
  }

  public void removeOne(long id) {
    Employee employee = this.findOne(id);
    if (employee != null) em.remove(employee);
  }

  public Employee raiseSalary(long id, int raise) {
    Employee employee = this.findOne(id);
    if (employee == null) return null;
    employee.setSalary(employee.getSalary() + raise);
    return employee;
  }

  public Employee findOne(long id) {
    Employee employee = em.find(Employee.class, id);
    return employee;
  }

  public List<Employee> findAll() {
    TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
    return query.getResultList();
  }
}
