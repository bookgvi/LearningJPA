package localhost.DAO;

import localhost.models.Department;
import localhost.models.Employee;
import localhost.services.ProvidersExceptions.NullPointer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DAODepartment {
  @PersistenceContext
  EntityManager em;

  @EJB
  DAOEmployee DAOEmployee;

  public Department findOne(int id) {
    Department department = em.find(Department.class, id);
    if (department == null)
      throw new NullPointerException();
    return department;
  }

  public List<Department> findAll() {
    TypedQuery<Department> query = em.createNamedQuery(Department.FIND_ALL, Department.class);
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
    department.setName(name);
    department.setDescription(description);
    return department;
  }

  public Department addEmployees(int id, List<Employee> employees) {
    Department department = this.findOne(id);
    department.setEmployees(employees);
    return department;
  }

  public Department addEmployee(int id, Employee employee) {
    Department department = this.findOne(id);
    department.addEmployee(employee);
    return department;
  }

  public Department deleteOne(int id) {
    Department department = this.findOne(id);
    // если не использовать orphanRemoval = true в анотации @OneToMany - нужно раскоментить код для "ручного кскадного удаления"
    Query query = em.createNamedQuery(Employee.GET_BY_DEPARTMENT);
    query.setParameter("department", department);
    query.executeUpdate();
    em.remove(department);
    return department;
  }
}
