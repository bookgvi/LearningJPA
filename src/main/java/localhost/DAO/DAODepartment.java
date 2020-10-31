package localhost.DAO;

import localhost.models.Department;
import localhost.models.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import java.util.List;

@Stateless
public class DAODepartment {
  @PersistenceContext
  EntityManager em;

  @EJB
  DAOEmployee DAOEmployee;

  public Department findOne(int id) {
    return em.find(Department.class, id);
  }

  public List<Department> findAll() {
    TypedQuery<Department> query = em.createQuery("SELECT d from Department d", Department.class);
    return query.getResultList();
  }

  public Department createOne(String name, String description) {
    Department department = new Department();
    department.setDescription(description);
    department.setName(name);
    em.persist(department);
    return department;
  }

  public Department changeOne(int id, String name, String description) {
    Department department = this.findOne(id);
    if (department != null) {
      department.setName(name);
      department.setDescription(description);
    }
    return department;
  }
  public Department addEmployees(int id, List<Employee> employees) {
    Department department = this.findOne(id);
    if (department != null) {
      department.setEmployees(employees);
    }
    return department;
  }
  public Department addEmployee(int id, Employee employee) {
    Department department = this.findOne(id);
    if (department != null) {
      department.addEmployee(employee);
    }
    return department;
  }

  public Department deleteOne(int id) {
    Department department = this.findOne(id);
    if (department != null) {
      // если не использовать orphanRemoval = true в анотации @OneToMany - нужно раскоментить код для "ручного кскадного удаления"
      TypedQuery query = em.createQuery("DELETE FROM Employee e WHERE e.department = :department", Employee.class);
      query.setParameter("department", department);
      query.executeUpdate();
      em.remove(department);
      return department;
    }
    return null;
  }
}
