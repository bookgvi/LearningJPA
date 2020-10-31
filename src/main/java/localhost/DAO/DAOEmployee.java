package localhost.DAO;

import localhost.models.Department;
import localhost.models.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DAOEmployee {
  @PersistenceContext(unitName = "EmployeeServiceEE")
  EntityManager em;

  @EJB
  DAODepartment DAODepartment;

  public Employee findOne(long id) {
    return em.find(Employee.class, id);
  }

  public Employee createOne(String name, int salary, int depId) {
    Employee newEmp = null;
    Department department = this.DAODepartment.findOne(depId); // em.find(Department.class, depId);
    if (department != null) {
      newEmp = new Employee();
      newEmp.setName(name);
      newEmp.setSalary(salary);
//      newEmp.setDepId(department.getId()); // newEmp.setDepId(depId);
      newEmp.setDepartment(department);
      DAODepartment.addEmployee(depId, newEmp);
      em.persist(newEmp);
    }
    return newEmp;
  }

  public List findAll() {
    Query query = em.createNamedQuery(Employee.FIND_ALL);
    return query.getResultList();
  }

  public List findByDepartment(Department dep) {
    Query query = em.createNamedQuery(Employee.GET_BY_DEPARTMENT);
    query.setParameter("dep", dep);
    return query.getResultList();
  }

  public Employee deleteOne(long id) {
    Employee employee = this.findOne(id);
    if (employee == null) return null;
    em.remove(employee);
    return employee;
  }
}
