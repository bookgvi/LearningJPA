package localhost.services.WEB;

import localhost.models.Department;
import localhost.models.Employee;
import org.hibernate.loader.custom.CustomQuery;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DepartmentWEB {
  @PersistenceContext
  EntityManager em;

  @EJB
  EmployeeWEB employeeWEB;

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
//      List<Employee> employees = employeeWEB.findByDepartment(department);
//      try {
//        for (Employee employee : employees) {
//          employeeWEB.deleteOne(employee.getId());
//        }
      em.remove(department);
//      } catch (Exception ignored) {
//      }
      return department;
    }
    return null;
  }
}
